package com.student.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
private static ComboPooledDataSource dataSource=null;
static {
	dataSource=new ComboPooledDataSource();
}
public static DataSource getDataSource() {
	return dataSource;
}
public static Connection getConnection() throws SQLException {
	return dataSource.getConnection();
}
public static void close(DataSource dataSource) throws SQLException {
	if(dataSource.getConnection()!=null)dataSource.getConnection().close();
}

}
