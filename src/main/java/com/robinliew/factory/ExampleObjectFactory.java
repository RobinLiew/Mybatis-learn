package com.robinliew.factory;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * 对象工厂（objectFactory）:
 *		MyBatis 每次创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完成。
 *		 默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认构造方法，要么在参数映射存在的时候通过参数构造方法来实例化。
 *		 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现。
 *		
 *		ObjectFactory 接口很简单，它包含两个创建用的方法，一个是处理默认构造方法的，另外一个是处理带参数的构造方法的。 
 *		最后，setProperties 方法可以被用来配置 ObjectFactory，
 *		在初始化你的 ObjectFactory 实例后， objectFactory 元素体中定义的属性会被传递给 setProperties 方法。
 *
 * @author RobinLiew
 *
 */
public class ExampleObjectFactory extends DefaultObjectFactory {
	  public Object create(Class type) {
	    return super.create(type);
	  }
	  public void setProperties(Properties properties) {
	    super.setProperties(properties);
	  }
	  public <T> boolean isCollection(Class<T> type) {
	    return Collection.class.isAssignableFrom(type);
	  }
}
