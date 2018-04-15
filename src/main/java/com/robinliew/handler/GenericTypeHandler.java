package com.robinliew.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.robinliew.domain.MyObject;

/**
 * 注意在使用自动检索（autodiscovery）功能的时候，只能通过注解方式来指定 JDBC 的类型。
 *
 * 你可以创建一个能够处理多个类的泛型类型处理器。为了使用泛型类型处理器，
 * 需要增加一个接受该类的 class 作为参数的构造器，
 * 这样在构造一个类型处理器的时候 MyBatis 就会传入一个具体的类。
 * 
 * 例：EnumTypeHandler 和 EnumOrdinalTypeHandler 都是泛型类型处理器（generic TypeHandlers）
 * @author RobinLiew
 *
 * @param <E>
 */
public class GenericTypeHandler<E extends MyObject> extends BaseTypeHandler<E> {

	  private Class<E> type;

	  public GenericTypeHandler(Class<E> type) {
	    if (type == null) throw new IllegalArgumentException("Type argument cannot be null");
	    this.type = type;
	  }

	@Override
	public E getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, E arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
