package cn.mldn.mldnmybatis.crud;

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
		// 查询数据并且直接以VO的类型返回
		News vo = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.findById", 3L);
		TestCase.assertNotNull(vo);
		System.err.println(vo);
	}
	

	@Test
	public void testNewsRemove() throws Exception {
		long nid = 1;
		int len = MyBatisSessionFactory.getSession().delete("cn.mldn.mapping.NewsNS.doRemove", nid);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
		TestCase.assertEquals(len, 1); // 如果更新行数为1表示成功
	}
	@Test
	public void testNewsList() throws Exception {
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll") ;
		Iterator<News> iter = newsList.iterator() ;
		while (iter.hasNext()) {
			News vo = iter.next() ;
			System.out.println(vo); 
		}
		MyBatisSessionFactory.close();
	}
	@Test
	public void testSplitCount() {
		String column = "title" ;	// 传递进来的处理参数
		String keyWord = "新闻" ;	// 传递进来的处理参数
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%") ;
		Long count = MyBatisSessionFactory.getSession().selectOne("cn.mldn.mapping.NewsNS.getSplitCount",map) ;
		System.err.println(count); 
		MyBatisSessionFactory.close();
	} 
	
	@Test
	public void testSplit() {
		Long currentPage = 1L ;	// 传递进来的处理参数
		Integer lineSize = 2 ;	// 传递进来的处理参数
		String column = "title" ;	// 传递进来的处理参数
		String keyWord = "新闻" ;	// 传递进来的处理参数
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("startPage", (currentPage - 1) * lineSize) ;// 一定要在外部处理
		map.put("lineSize", lineSize) ;
		map.put("column", column);
		map.put("keyWord", "%" + keyWord + "%") ;
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findSplit",map) ;
		Iterator<News> iter = newsList.iterator() ;
		while (iter.hasNext()) {
			News vo = iter.next() ;
			System.out.println(vo); 
		}
		MyBatisSessionFactory.close();
	}
	
	@Test
	public void testNewsMap() throws Exception {
		// 使用Map集合进行处理，首先要设置使用的SQL语句，而后要设置一个描述key的列名称（此处的key为nid）
		Map<Long,Map<String,Object>> map = MyBatisSessionFactory.getSession().selectMap("cn.mldn.mapping.NewsNS.findMap","nid") ;
		Iterator<Map.Entry<Long, Map<String, Object>>> newsMap = map.entrySet().iterator() ;
		while (newsMap.hasNext()) {
			Map.Entry<Long, Map<String, Object>> newsME = newsMap.next() ;
			System.err.println("key = " + newsME.getKey() + "、value：");
			Map<String,Object> newsTemp = newsME.getValue() ;	// 获取每一组数据 
			Iterator<Map.Entry<String,Object>> iter = newsTemp.entrySet().iterator() ;
			while (iter.hasNext()) {
				Map.Entry<String,Object> me = iter.next() ;
				System.out.println("\t|- " + me.getKey() + " = " + me.getValue());
			}
		}
		MyBatisSessionFactory.close();
	} 
	
	 
	@Test
	public void testNewsEdit() throws Exception {
		News vo = new News();
		vo.setNid(3L);
		vo.setTitle("约翰的一生都在约汉");
		vo.setNote("感染了许多的疾病，人生很困苦，人生一定要保守。");
		int len = MyBatisSessionFactory.getSession().update("cn.mldn.mapping.NewsNS.doEdit", vo);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
		TestCase.assertEquals(len, 1); // 如果更新行数为1表示成功
	}

	@Test
	public void testNewsAdd() throws Exception {
		News vo = new News();
		vo.setTitle("新闻标题 - " + rand);
		vo.setPubdate(new Date());
		vo.setNote("新闻内容 - " + rand);
		int len = MyBatisSessionFactory.getSession().update("cn.mldn.mapping.NewsNS.doCreate", vo);
		MyBatisSessionFactory.getSession().commit(); // 提交更新事务
		TestCase.assertEquals(len, 1); // 如果更新行数为1表示成功
		System.out.println(vo);
		MyBatisSessionFactory.close();
	}
}
