package cn.mldn.mldnmybatis.crud;

import java.util.Date;
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
	public void testNewsRemove() throws Exception {
		long nid = 1 ;
		int len = MyBatisSessionFactory.getSession().delete("cn.mldn.mapping.NewsNS.doRemove", nid);
		MyBatisSessionFactory.getSession().commit();
		MyBatisSessionFactory.close();
		TestCase.assertEquals(len, 1); // 如果更新行数为1表示成功
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
