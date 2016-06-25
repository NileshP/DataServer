package com.ext.server;

/**
 * @author Nilesh Parshetti
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.sql.SQLException;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ext.db.DBUtil;

public class MinaServer {
	private static final int PORT = 9999;

	static DBUtil dbUtil;
	static Logger logger = LoggerFactory.getLogger(MinaServer.class);

	public static void main(String[] args) {
		try {
		logger.info("START EXECUTION");
		
		logger.info("INITIALIZING DATABASE");
		
		dbUtil = new DBUtil();
		
		logger.info("INITIALIZING PORT");

		IoAcceptor acceptor = new NioSocketAcceptor();

		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		acceptor.setHandler(new MinaServerHandler(dbUtil));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		
			acceptor.bind(new InetSocketAddress(PORT));
			
			logger.info("INITIALIZATION SUCCESSFUL");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("CAN'T INITIALIZE TCP PORT");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ERROR IN DB CONNECTION - 1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ERROR IN DB CONNECTION - 2");
		}
	}
}