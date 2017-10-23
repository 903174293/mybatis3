package cn.mldn.mldnmybatis.cache;

import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import cn.mldn.mldnmybatis.util.MyBatisSessionFactory;
import cn.mldn.mldnmybatis.vo.News;

public class TestNewsCRUD {
	private static Random random = new Random();
	private static int rand;
	static {
		rand = random.nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void testFindById() throws Exception {
		SqlSessionFactory factory = MyBatisSessionFactory.getSessionFactory() ;	// 获取SqlSessionFactory
		SqlSession sessionA = factory.openSession() ;	// 开启SqlSession
		News voA = sessionA.selectOne("cn.mldn.mapping.NewsNS.findById", 3L);
		System.err.println(voA);
		sessionA.close();	// SqlSession关闭时表示缓存写入
		System.out.println("================================================");
		SqlSession sessionB = factory.openSession() ;	// 开启SqlSession
		News voB = sessionB.selectOne("cn.mldn.mapping.NewsNS.findById", 3L);
		System.err.println(voB);
		sessionB.close();
	}
 
	@Test
	public void testNewsList() throws Exception {
		List<News> newsListA = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll");
		System.out.println("================================================");
		List<News> newsListB = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll");
		
		MyBatisSessionFactory.close();
	}
}
