package com.happyJ.realestate.common.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

@SuppressWarnings("rawtypes")
public class XmlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    // Content-Length Header
    private static final boolean    USE_CONTENT_LENGTH = true;
    private static final int        INIT_BUFFER_SIZE = 10240;

    private final TransformerFactory transformerFactory = TransformerFactory.newInstance();
    private final ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class, JAXBContext>();


    /**
     * Protected constructor that sets the {@link #setSupportedMediaTypes(java.util.List) supportedMediaTypes}
     * to {@code text/xml} and {@code application/xml}, and {@code application/*-xml}.
     */
    public XmlHttpMessageConverter() {
        super(MediaType.APPLICATION_XML, MediaType.TEXT_XML, new MediaType("application", "*+xml"));
    }

    /**
     * Converter가 정상적으로 처리되었을 때  변환된 객체를 외부에서 참조할 수 있도록 해주기 위해 호출해 준다.
     * 외부에서는 클래스와 함수를 재정의 해서 사용하면 Conveter가 끝난 후 값을 참조할 수 있음.
     *
     * @param obj 변환된 객체
     */
    public void readInternalRefer(Object obj) {
        // nothing to do...
    }

    @Override
    public final Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {

        Object result = null;

        try {
            Source source = new StreamSource(inputMessage.getBody());
            Unmarshaller unmarshaller = createUnmarshaller(clazz);

            if (clazz.isAnnotationPresent(XmlRootElement.class)) {
                result = unmarshaller.unmarshal(source);
            }
            else {
                JAXBElement jaxbElement = unmarshaller.unmarshal(source, clazz);
                result = jaxbElement.getValue();
            }

        } catch (UnmarshalException ex) {
            Exception originE = new HttpMessageNotReadableException("Could not unmarshal to [" + clazz + "]: " + ex.getMessage(), ex);
            throwChannelException("NCH01006R", originE);

        } catch (JAXBException ex) {
            Exception originE = new HttpMessageConversionException("Could not instantiate JAXBContext: " + ex.getMessage(), ex);
            throwChannelException("NCH01006R", originE);
        }

        // 외부에서 참조할 수 있도록 해주기 위해 호출
        readInternalRefer(result);

        return result;
    }

    @Override
    protected final void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException {

        ByteArrayOutputStream baos = null;

        try {
            Class clazz = ClassUtils.getUserClass(o);
            Marshaller marshaller = createMarshaller(clazz);
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            setCharset(outputMessage.getHeaders().getContentType(), marshaller);

            // Set content-length
            if(USE_CONTENT_LENGTH){
                baos = new ByteArrayOutputStream(INIT_BUFFER_SIZE);
                StreamResult sr = new StreamResult(baos);

                marshaller.marshal(o, sr);

                int responseLength = baos.size();

                // 쓰레드 컨텍스트에 응답 사이즈 설정 20130118 허기철...
//                AbstractThreadContextData abstractThreadContextData = ThreadContext.get();
//                if (abstractThreadContextData != null) {
//                    ChannelThreadContextData threadContextData = (ChannelThreadContextData) abstractThreadContextData;
//                    threadContextData.setOutputSize(responseLength);
//                }
                
                // Set Content-Length
                outputMessage.getHeaders().setContentLength(responseLength);

                // Buffer Write
                baos.writeTo(outputMessage.getBody());

            }else{
                marshaller.marshal(o, new StreamResult(outputMessage.getBody()));
            }
        } catch (MarshalException ex) {
            Exception originE = new HttpMessageNotWritableException("Could not marshal [" + o + "]: " + ex.getMessage(), ex);
            throwChannelException("NCH01008R", originE);

        } catch (JAXBException ex) {
            Exception originE = new HttpMessageConversionException("Could not instantiate JAXBContext: " + ex.getMessage(), ex);
            throwChannelException("NCH01008R", originE);

        } finally {
            if(baos != null) {
                try {
                    baos.close();
                } catch (Throwable t) {
                    baos = null;
                }
            }
        }
    }
    private void setCharset(MediaType contentType, Marshaller marshaller) throws PropertyException {
        if (contentType != null && contentType.getCharSet() != null) {
            marshaller.setProperty(Marshaller.JAXB_ENCODING, contentType.getCharSet().name());
        }
    }

    /**
     * Transforms the given {@code Source} to the {@code Result}.
     * @param source the source to transform from
     * @param result the result to transform to
     * @throws TransformerException in case of transformation errors
     */
    protected void transform(Source source, Result result) throws TransformerException {
        this.transformerFactory.newTransformer().transform(source, result);
    }

    /**
     * Creates a new {@link Marshaller} for the given class.
     *
     * @param clazz the class to create the marshaller for
     * @return the {@code Marshaller}
     * @throws HttpMessageConversionException in case of JAXB errors
     */
    protected final Marshaller createMarshaller(Class clazz) {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createMarshaller();
        }
        catch (JAXBException ex) {
            throw new HttpMessageConversionException(
                    "Could not create Marshaller for class [" + clazz + "]: " + ex.getMessage(), ex);
        }
    }

    /**
     * Creates a new {@link Unmarshaller} for the given class.
     *
     * @param clazz the class to create the unmarshaller for
     * @return the {@code Unmarshaller}
     * @throws HttpMessageConversionException in case of JAXB errors
     */
    protected final Unmarshaller createUnmarshaller(Class clazz) throws JAXBException {
        try {
            JAXBContext jaxbContext = getJaxbContext(clazz);
            return jaxbContext.createUnmarshaller();
        }
        catch (JAXBException ex) {
            throw new HttpMessageConversionException(
                    "Could not create Unmarshaller for class [" + clazz + "]: " + ex.getMessage(), ex);
        }
    }

    /**
     * Returns a {@link JAXBContext} for the given class.
     *
     * @param clazz the class to return the context for
     * @return the {@code JAXBContext}
     * @throws HttpMessageConversionException in case of JAXB errors
     */
    protected final JAXBContext getJaxbContext(Class clazz) {
        Assert.notNull(clazz, "'clazz' must not be null");
        JAXBContext jaxbContext = jaxbContexts.get(clazz);
        if (jaxbContext == null) {
            try {
                jaxbContext = JAXBContext.newInstance(clazz);
                jaxbContexts.putIfAbsent(clazz, jaxbContext);
            }
            catch (JAXBException ex) {
                throw new HttpMessageConversionException(
                        "Could not instantiate JAXBContext for class [" + clazz + "]: " + ex.getMessage(), ex);
            }
        }
        return jaxbContext;
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return (clazz.isAnnotationPresent(XmlRootElement.class) || clazz.isAnnotationPresent(XmlType.class)) &&
                canRead(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return AnnotationUtils.findAnnotation(clazz, XmlRootElement.class) != null && canWrite(mediaType);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // should not be called, since we override canRead/Write
        throw new UnsupportedOperationException();
    }

    /**
     * NddsChannelException을 throw 한다.
     *
     * @param errorCode 오류코드
     * @param e Exception
     */
    private void throwChannelException(String errorCode, Exception e) {

      String message = e.getMessage();
      if(message == null || message.isEmpty())
          message = "unknown error";

      logger.debug(message);

      int index = message.indexOf("\n", 1);
      if(index > 0)
          message = message.substring(0, index);

//    throw new NddsChannelException(errorCode, message, e);
    throw new RuntimeException();
    }
}
