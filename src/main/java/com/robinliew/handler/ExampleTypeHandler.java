package com.robinliew.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * 你可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。 
 * 具体做法为：实现 org.apache.ibatis.type.TypeHandler 接口，
 * 或继承一个很便利的类 org.apache.ibatis.type.BaseTypeHandler， 然后可以选择性地将它映射到一个 JDBC 类型。
 * @author RobinLiew
 *
 */
@MappedTypes(value = { String.class })/*(JavaType)*/
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler extends BaseTypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
	 ps.setString(i, parameter);
	}
	
	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
	 return rs.getString(columnName);
	}
	
	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
	 return rs.getString(columnIndex);
	}
	
	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
	 return cs.getString(columnIndex);
	}
}
