package cn.mldn.mldnmybatis.cache;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import cn.mldn.mldnmybatis.util.MyBatisSessionFactory;
import cn.mldn.mldnmybatis.vo.News;
import junit.framework.TestCase;

public class TestNewsCRUD {
	private static Random random = new Random();
	private static int rand;
	static {
		rand = random.nextInt(Integer.MAX_VALUE);
	}

	@Test
	public void testFindById() throws Exception {
		News voA = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.findById", 3L);
		System.err.println(voA);
		MyBatisSessionFactory.getSession().clearCache(); 	// 清空缓存
		System.out.println("================================================");
		News voB = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.findById", 3L);
		System.err.println(voB);
	}
 
	@Test
	public void testNewsList() throws Exception {
		List<News> newsListA = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll");
		System.out.println("================================================");
		List<News> newsListB = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll");
		
		MyBatisSessionFactory.close();
	}
}
