package cn.mldn.mldnmybatis.base;

import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.mldn.mldnmybatis.vo.Member;

public class TestMemberAdd {
	private static Random random = new Random() ;
	private static int rand ;
	static {
		rand = random.nextInt(Integer.MAX_VALUE) ;
	}
	@Test
	public void testAddMember() throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis.cfg.xml");
		// 获取一个SQLSessionFactory接口对象，表示所有的数据库连接处理
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		// 通过SQLSessionFactory获取SqlSession接口，该接口主要用于进行数据库的操作
		SqlSession session = sessionFactory.openSession() ;	// 获取连接
		Member vo = new Member() ;
		vo.setMid("mldn - " + rand);
		vo.setName("你好 - " + rand);
		vo.setBirthday(new Date());
		vo.setSalary(8000.0);
		vo.setNote("是一个不错的好人 - " + rand);
		// 找到命名空间之中定义的具体的SQL语句，而后执行追加
		System.out.println(session.insert("cn.mldn.mapping.MemberNS.doCreate",vo));
		session.commit(); // 提交更新事务
		session.close(); 
		inputStream.close(); 
	}
}
