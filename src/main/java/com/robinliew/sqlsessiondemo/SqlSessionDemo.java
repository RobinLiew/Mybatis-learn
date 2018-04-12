package com.robinliew.sqlsessiondemo;

import org.apache.ibatis.session.SqlSession;

import com.robinliew.domain.Blog;
import com.robinliew.mapper.BlogMapper;
import com.robinliew.utils.MybatisUtil;

/**
 * 映射器实例（Mapper Instances）
 *
 * 映射器是一个你创建来绑定你映射的语句的接口。映射器接口的实例是从 SqlSession 中获得的。
 * 因此从技术层面讲，任何映射器实例的最大作用域是和请求它们的 SqlSession 相同的。
 * 尽管如此，映射器实例的最佳作用域是方法作用域。也就是说，映射器实例应该在调用它们的方法中被请求，用过之后即可废弃。
 * 并不需要显式地关闭映射器实例，尽管在整个请求作用域（request scope）保持映射器实例也不会有什么问题，但是很快你会发现，像 SqlSession 一样，在这个作用域上管理太多的资源的话会难于控制。
 * 所以要保持简单，最好把映射器放在方法作用域（method scope）内。
 * 
 * 	SqlSession session = sqlSessionFactory.openSession();
 *	try {
 *	  BlogMapper mapper = session.getMapper(BlogMapper.class);
 *	  // do work
 *	} finally {
 *	  session.close();
 *	}
 * 
 * @author RobinLiew
 *
 */
public class SqlSessionDemo {
	
	/**
	 * 通过 XML 定义SQL语句的形式
	 */
	public void selectByXml(){
		SqlSession session = MybatisUtil.getSqlSession();
		try {
		  Blog blog = (Blog) session.selectOne("com.robinliew.mapper.BlogMapper.selectBlog", 101);
		} finally {
		  session.close();
		}
	}
	/**
	 * 通过注解定义SQL语句的形式
	 */
	public void selectByAnno(){
		SqlSession session = MybatisUtil.getSqlSession();
		try {
		  BlogMapper mapper = session.getMapper(BlogMapper.class);
		  Blog blog = mapper.selectBlog(101);
		} finally {
		  session.close();
		}
	}
	
}
