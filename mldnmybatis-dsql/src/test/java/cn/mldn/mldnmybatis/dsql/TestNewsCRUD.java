package cn.mldn.mldnmybatis.dsql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	public void testEdit() throws Exception {
		News vo = new News();
		vo.setTitle("晚上小强请吃饭，蒸蛋糕。");
		vo.setNid(2L);
		vo.setNote("不吃白不吃， 吃了给银子，每人3两");
		int len = MyBatisSessionFactory.getSession().update("cn.mldn.mapping.NewsNS.doEdit", vo);
		System.out.println(len);
	}

	@Test
	public void testNewsSplit() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", "title");
		map.put("keyWord", "%新闻%");
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findSplit", map);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}

	@Test
	public void testFindIds() throws Exception {
		List<Long> ids = new ArrayList<Long>();
		ids.add(1L);
		ids.add(2L); 
		ids.add(3L);
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findByIds", ids);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}

	@Test
	public void testNewsList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "约翰的一生都在约汉");
		map.put("nid", 5);
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll", map);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}
}
