package com.ext.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author Nilesh Parshetti
 */
public class MinaClient {
	private static final int PORT = 9999;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		IoConnector connector = new NioSocketConnector();
		connector.getSessionConfig().setReadBufferSize(2048);

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"))));

		connector.setHandler(new MinaClientHandler("DATA|DATA|DATA"));
		ConnectFuture future = connector.connect(new InetSocketAddress(
				"localhost", PORT));
		future.awaitUninterruptibly();

		if (!future.isConnected()) {
			return;
		}
		IoSession session = future.getSession();
		session.getConfig().setUseReadOperation(true);
		session.getCloseFuture().awaitUninterruptibly();
		//System.out.println(session.read().getMessage());

		System.out.println("After Writing");
		connector.dispose();

	}

}