package com.qsoft.augen.ui.common;

import com.qsoft.augen.persistence.entity.PropertyDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoUtils
{
	public Connection getConnection(PropertyDB property) throws Exception{
		Class.forName(property.getDriver());
		Connection connection = DriverManager.getConnection(property.getUrl(), property.getUsername(), property.getPassword());
		return connection;
	}
}
