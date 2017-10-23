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
	public void testNewsAdd() throws Exception {
		News vo = new News();
		vo.setTitle("新闻标题 - " + rand);
		vo.setPubdate(new Date());
		vo.setNote("新闻内容 - " + rand);
		int len = MyBatisSessionFactory.getSession().insert("cn.mldn.mapping.NewsNS.doCreate", vo);
		MyBatisSessionFactory.getSession().commit(); // 提交更新事务
		TestCase.assertEquals(len, 1); // 如果更新行数为1表示成功
		System.out.println(vo);
		MyBatisSessionFactory.close();
	}
}
