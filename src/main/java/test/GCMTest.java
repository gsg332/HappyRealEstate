/*****************************************************************************
 * Copyright(c) 2017 INCON. All rights reserved.
 * This software is the proprietary information of INCON. 
 * 
 * Description : 
 * Create on 2017. 3. 30. jincheol
*****************************************************************************/
package test;

import java.io.IOException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class GCMTest {

	private static String API_KEY = "AIzaSyD3P9lHklWGwr7tLVFMQIbVpHjnglrxE30"; // 프로젝트 api key
	private static String REG_ID = "APA91bHPZahkFqcSIFjavRm_ZV_UfTDDyZteEFy3MywbJv8MJ3AAKqbHb5XoHbkhBqAvtx3dlIO0NnlaQNqV5aSQwfXu7vd8rIiodrzGx9AZJT06spEeAzDhYLj8C0WZxysSDjAbdxS_";  // 단말
	
	public static void main ( String args[] ) throws InterruptedException, IOException {
		sendPush();
	}
	
	public static void sendPush() throws IOException  {
		Sender sender = new Sender( API_KEY );
		Message msg = new Message.Builder().addData(API_KEY, "Push Message ( from Jaehoon2 )")
					.addData("Find_ID", "11111")
					.addData("Car_Number", "55555")
					.addData("Noti_Type", "2")
					.addData("delay_while_idle", "44444")
					.addData("collapse_key", "55555")
					.addData("time_to_live", "66666")
				.build();
		
		Result result = sender.send( msg, REG_ID, 5 );
		
		if( result.getMessageId() != null ) {
			System.out.println("push success");
		}
		else {
			String error = result.getErrorCodeName( ) ;
			System.out.println( error );
		}
	}

}
