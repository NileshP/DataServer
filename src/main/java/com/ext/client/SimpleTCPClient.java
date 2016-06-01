package com.ext.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTCPClient {
	
	static Logger logger = LoggerFactory.getLogger(SimpleTCPClient.class);

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		
		Socket socket = new Socket("ec2-54-213-143-165.us-west-2.compute.amazonaws.com",9999);
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
		//BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
		
		
		
		dataOutputStream.writeBytes("04/12/2016|21:31:44|+919028576234|L|12323|1234 \n 05/12/2016|21:31:44|+919028576234|L|12323|1234 \n");
		
		
		//Thread.sleep(100000);
		
		socket.close();
		
		
		
	}
}
