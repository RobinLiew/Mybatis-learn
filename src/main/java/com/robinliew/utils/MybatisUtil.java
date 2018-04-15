package com.robinliew.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 作用域（Scope）和生命周期
 * 
 *  SqlSessionFactoryBuilder
 *  
 *	这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
 *	因此 SqlSessionFactoryBuilder 实例的最佳作用域是方法作用域（也就是局部方法变量）。
 *	你可以重用 SqlSessionFactoryBuilder 来创建多个 SqlSessionFactory 实例，
 *	但是最好还是不要让其一直存在以保证所有的 XML 解析资源开放给更重要的事情。
 *  
 *  
 *  SqlSessionFactory
 *  
 *	SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。
 *	使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，
 *	多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。
 *	因此 SqlSessionFactory 的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
 *  
 *  
 *  SqlSession
 *  
 *	每个线程都应该有它自己的 SqlSession 实例。
 *	SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
 *	绝对不能将 SqlSession 实例的引用放在一个类的静态域，甚至一个类的实例变量也不行。
 *	也绝不能将 SqlSession 实例的引用放在任何类型的管理作用域中，比如 Servlet 架构中的 HttpSession。
 *	如果你现在正在使用一种 Web 框架，要考虑 SqlSession 放在一个和 HTTP 请求对象相似的作用域中。
 *	换句话说，每次收到的 HTTP 请求，就可以打开一个 SqlSession，返回一个响应，就关闭它。
 *	这个关闭操作是很重要的，你应该把这个关闭操作放到 finally 块中以确保每次都能执行关闭。
 * 	SqlSession session = sqlSessionFactory.openSession();
 *	try {
 *	  // do work
 *	} finally {
 *	  session.close();
 *	}
 *  
 * @author RobinLiew
 *
 */
public class MybatisUtil {  
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();  
    private static SqlSessionFactory sqlSessionFactory;  
    /** 
     * 加载位于src/mybatis-config.xml配置文件 
     */  
    static{  
        try {  
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");  
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }  
    }  
    /** 
     * 禁止外界通过new方法创建  
     */  
    private MybatisUtil(){}  
    /** 
     * 获取SqlSession 
     */  
    public static SqlSession getSqlSession(){  
        //从当前线程中获取SqlSession对象  
        SqlSession sqlSession = threadLocal.get();  
        //如果SqlSession对象为空  
        if(sqlSession == null){  
            //在SqlSessionFactory非空的情况下，获取SqlSession对象  
            sqlSession = sqlSessionFactory.openSession();  
            //将SqlSession对象与当前线程绑定在一起  
            threadLocal.set(sqlSession);  
        }  
        //返回SqlSession对象  
        return sqlSession;  
    }  
    /** 
     * 关闭SqlSession与当前线程分开 
     */  
    public static void closeSqlSession(){  
        //从当前线程中获取SqlSession对象  
        SqlSession sqlSession = threadLocal.get();  
        //如果SqlSession对象非空  
        if(sqlSession != null){  
            //关闭SqlSession对象  
            sqlSession.close();  
            //分开当前线程与SqlSession对象的关系，目的是让GC尽早回收  
            threadLocal.remove();  
        }  
    }  
}