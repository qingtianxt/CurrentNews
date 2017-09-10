package service;

import java.sql.SQLException;
import java.util.List;

import dao.newsTypeDao;
import domain.newsType;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * 添加缓存的时机：添加分类，修改分类，删除分类，查询最高级分类
 * 添加分类,修改和删除分类是删除缓存，查询得时候，先判断是否有缓存，有的话从缓存获取，没有的话从数据库中获取
 * @author wzw
 *
 */
public class newsTypeService {
	/**
	 * 添加分类
	 * @param n
	 * @throws SQLException 
	 */
	public void add(newsType n) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		//创建缓存管理器
		CacheManager c = CacheManager.create(newsTypeService.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = c.getCache("newsType");
		cache.remove("nlist");
		nd.add(n);
	}

	public List<newsType> getByParentId(int i) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		return nd.getByParentId(i);
	}
/**
 * 获取一个分类和他的所有一级子类
 * @param id
 * @return
 * @throws SQLException
 */
	public newsType getList(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		newsType  n= new newsType();
		if(id!=0){
			n= nd.getById(id);
		}
		List<newsType> list = nd.getByParentId(id);
		for (newsType l : list) {
			System.out.println(l.toString());
			
		}
		n.setChildBeans(list);
		return n;
	}
/**
 * 根据id获取分类信息
 * @param id
 * @return
 * @throws SQLException 
 */
	public newsType getById(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		return nd.getById(id);
	}
/**
 * 修改一个分类的信息
 * @param n
 * @throws SQLException 
 */
	public void update(newsType n) throws SQLException {
	
		newsTypeDao nd = new newsTypeDao();
		nd.update(n);
		CacheManager cacheManager = CacheManager.create(newsTypeService.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = cacheManager.getCache("newsType");
		cache.remove("clist");
	}
/**
 * 删除一个分类，如果分类下有子类，则递归删除他的所有子类
 * @param id
 * @throws SQLException
 */
	public void delete(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		//递归删除他的所有子类
		newsType list = getList(id);
		if(list.getChildBeans()!=null){
			for (newsType c : list.getChildBeans()) {
				delete(c.getId());
			}
		}
		//删除这个分类
		nd.delete(id);
		CacheManager c = CacheManager.create(newsTypeService.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = c.getCache("newsType");
		cache.remove("clist");
	}
	public List<newsType> getByCache() throws SQLException{
		newsTypeDao nd = new newsTypeDao();
		CacheManager c = CacheManager.create(newsTypeService.class.getClassLoader().getResourceAsStream("ehcache.xml"));
		Cache cache = c.getCache("newsType");
		Element element = cache.get("clist");
		List<newsType> list  = null;
		
		if(element==null){//从数据库中获取
			list = nd.getByParentId(0);
			cache.put(new Element("clist",list));
			System.out.println("缓存中没有数据，已从数据库获取");
		}
		else{
			list =(List<newsType>) element.getObjectValue();
			System.out.println("从缓存中获取");
		}
		return list;
	}
}
