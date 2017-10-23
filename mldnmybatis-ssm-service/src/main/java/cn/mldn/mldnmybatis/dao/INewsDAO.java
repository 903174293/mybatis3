package cn.mldn.mldnmybatis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.mldnmybatis.vo.News;

public interface INewsDAO {
	public boolean doCreate(News vo) ;
	public List<News> findByIds(Set<Long> ids) ;
	public List<News> findSplit(Map<String,Object> params) ;
}
