package cn.mldn.mldnmybatis.dsql;

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
	public void testNewsSplit() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("column","title") ;
		map.put("keyWord", "%新闻%") ;
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findSplit",
				map);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}
 
	@Test
	public void testNewsList() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("title","约翰的一生都在约汉");
		map.put("nid", 5) ;
		List<News> newsList = MyBatisSessionFactory.getSession().selectList("cn.mldn.mapping.NewsNS.findAll",
				map);
		Iterator<News> iter = newsList.iterator();
		while (iter.hasNext()) {
			News vo = iter.next();
			System.out.println(vo);
		}
		MyBatisSessionFactory.close();
	}
}
