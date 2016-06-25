package com.ext.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	Connection con;

	public DBUtil() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager
				.getConnection(
						"jdbc:mysql://fuelsensordata.cjwwwbtznkmd.us-west-2.rds.amazonaws.com:3306/sensordata",
						"admin", "Rahul.1436");

	}

	public boolean insertRecord(String DATE, String TIME, String MOBILENUMBER,
			String CODE, String COUNT, String CRC) {

		try {
			Statement statement = con.createStatement();

			statement
					.execute("insert into masterdata (DATE,TIME,MOBILENUMBER,CODE,COUNT,CRC) VALUES ('"
							+ DATE
							+ "','"
							+ TIME
							+ "','"
							+ MOBILENUMBER
							+ "','" + CODE + "','" + COUNT + "','" + CRC + "')");

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
