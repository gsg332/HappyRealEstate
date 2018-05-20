package com.happyJ.realestate.common.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;


public class JsonHttpMessageConverter extends MappingJacksonHttpMessageConverter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // Content-Length Header
    private static final boolean USE_CONTENT_LENGTH = true;
    private static final int INIT_BUFFER_SIZE = 10240;

    public JsonHttpMessageConverter() {
        super();

        // ObjectMapper 설정변경을 위해 생성
        ObjectMapper objectMapper = new ObjectMapper();
        // json 문자열에 새로 추가된 프로퍼티는 객체 매핑시 무시하도록 설정함
        objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // ObjectMapper를 주입
        setObjectMapper(objectMapper);
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
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        Object result = null;

        try {
            JavaType javaType = getJavaType(clazz.getGenericSuperclass(), clazz);
            result = getObjectMapper().readValue(inputMessage.getBody(), javaType);

        } catch (JsonProcessingException ex) {
            Exception originE = new HttpMessageNotReadableException("Could not read JSON: " + ex.getMessage(), ex);
            throwChannelException("NCH01006R", originE);

        } catch (Exception e) {
            throwChannelException("NCH01006R", e);
        }

        // 외부에서 참조할 수 있도록 해주기 위해 호출
        readInternalRefer(result);

        return result;
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        ByteArrayOutputStream baos = null;
        JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());

        try {
            if (USE_CONTENT_LENGTH) {
                baos = new ByteArrayOutputStream(INIT_BUFFER_SIZE);
                JsonGenerator jsonGenerator = getObjectMapper().getJsonFactory().createJsonGenerator(baos, encoding);
                getObjectMapper().writeValue(jsonGenerator, object);

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

            } else {
                JsonGenerator jsonGenerator = getObjectMapper().getJsonFactory().createJsonGenerator(outputMessage.getBody(), encoding);
                getObjectMapper().writeValue(jsonGenerator, object);
            }
        } catch (JsonProcessingException ex) {
            Exception originE = new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
            throwChannelException("NCH01008R", originE);

        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (Throwable t) {
                    baos = null;
                }
            }
        }
    }

    /**
     * NddsChannelException을 throw 한다.
     *
     * @param errorCode 오류코드
     * @param e Exception
     */
    private void throwChannelException(String errorCode, Exception e) {

        String message = e.getMessage();
        if (message == null || message.isEmpty()) message = "unknown error";

        logger.debug(message);

        int index = message.indexOf("\n", 1);
        if (index > 0) message = message.substring(0, index);

//        throw new NddsChannelException(errorCode, message, e);
        throw new RuntimeException();
    }
}
