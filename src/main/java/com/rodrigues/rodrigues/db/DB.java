package com.rodrigues.rodrigues.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.rodrigues.rodrigues.MainApp;

public class DB {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				try {
					Properties props = loadPropertties();
					String url = props.getProperty("dburl");
					conn = DriverManager.getConnection(url, props);					
				}catch (FileNotFoundException e1) {
	
					throw new DbException(e1.getMessage());
				}				

			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
			
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	private static Properties loadPropertties() throws FileNotFoundException {
			FileInputStream fs = new FileInputStream  ( System.getProperty("user.home").toString() + MainApp.strDiretorioYggDrasil + "\\db.properties" );
			Properties props = new Properties();
			try {
				props.load(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return props;

	}
	public static void closeStatement(Statement st) {
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
