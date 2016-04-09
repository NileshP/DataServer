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

		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		session.setAttribute("Values: ");

	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

		logger.info("Message received in the server.."
				+ message.toString());

	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close();

	}

}
