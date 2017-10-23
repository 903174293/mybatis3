package cn.mldn.util.service.abs;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractService {
	public Map<String,Object> paramToMap(long currentPage, int lineSize, String column, String keyWord) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start", (currentPage - 1) * lineSize) ;
		map.put("lineSize", lineSize) ;
		if ("".equals(column)) {
			map.put("column", null) ;	// 明确设置null
		} else {
			map.put("column", column) ;
		}
		if ("".equals(keyWord)) {
			map.put("keyWord", null) ;	// 明确设置null
		} else {
			map.put("keyWord", column) ; 
		}
		return map ;
	}
}
