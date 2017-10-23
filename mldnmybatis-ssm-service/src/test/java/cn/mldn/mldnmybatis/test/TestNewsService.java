package cn.mldn.mldnmybatis.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.mldnmybatis.service.INewsService;
import cn.mldn.mldnmybatis.vo.News;

@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestNewsService {
	@Resource
	private INewsService newsService;

	@Test
	public void testAdd() {
		News vo = new News();
		vo.setTitle("世界和平，约翰幸福");
		vo.setNote("世界的和平很重要");
		vo.setPubdate(new Date());
		System.err.println(this.newsService.add(vo));
	}

	@Test
	public void testListByIds() {
		Set<Long> ids = new HashSet<Long>();
		ids.add(1L);
		ids.add(3L);
		ids.add(5L);
		System.err.println(this.newsService.listByIds(ids));
	}

	@Test
	public void testListSplit() {
		System.err.println(this.newsService.listSplit(1, 2, "", null));
	}
}
