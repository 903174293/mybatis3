package cn.mldn.mldnmybatis.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.mldnmybatis.vo.News;

public interface INewsDAO {
	public boolean doCreate(News vo) ;
	public List<News> findByIds(Object ids) ; 
	public List<News> findSplit(Map<String,Object> params) ;
}
