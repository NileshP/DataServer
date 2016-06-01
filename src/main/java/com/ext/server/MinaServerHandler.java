package com.ext.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinaServerHandler extends IoHandlerAdapter {

	Logger logger = LoggerFactory.getLogger(MinaServerHandler.class);
	
	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		session.close();

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

		System.out.println("NEW SESSION OPENED");
		
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 1000);
		session.setAttribute("Values: ");

	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		System.out.println("Message received in the server.."
				+ message.toString());
		
		logger.info("Message received in the server.."
				+ message.toString());
		
		String messageString = message.toString();
		String[] arrayParsed = messageString.split("\\|");
		
		System.out.println("DATE : "+arrayParsed[0]);
		System.out.println("TIME : "+arrayParsed[1]);
		System.out.println("MOBILE NUMBER : "+arrayParsed[2]);
		System.out.println("CODE : "+arrayParsed[3]);
		System.out.println("COUNT : "+arrayParsed[4]);
		System.out.println("CRC : "+arrayParsed[5]);

		
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close();
		
		cause.printStackTrace();

	}

}
