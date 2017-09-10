package service;

import java.sql.SQLException;
import java.util.List;

import dao.newsTypeDao;
import domain.newsType;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 * ��ӻ����ʱ������ӷ��࣬�޸ķ��࣬ɾ�����࣬��ѯ��߼�����
 * ��ӷ���,�޸ĺ�ɾ��������ɾ�����棬��ѯ��ʱ�����ж��Ƿ��л��棬�еĻ��ӻ����ȡ��û�еĻ������ݿ��л�ȡ
 * @author wzw
 *
 */
public class newsTypeService {
	/**
	 * ��ӷ���
	 * @param n
	 * @throws SQLException 
	 */
	public void add(newsType n) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		//�������������
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
 * ��ȡһ���������������һ������
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
 * ����id��ȡ������Ϣ
 * @param id
 * @return
 * @throws SQLException 
 */
	public newsType getById(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		return nd.getById(id);
	}
/**
 * �޸�һ���������Ϣ
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
 * ɾ��һ�����࣬��������������࣬��ݹ�ɾ��������������
 * @param id
 * @throws SQLException
 */
	public void delete(int id) throws SQLException {
		newsTypeDao nd = new newsTypeDao();
		//�ݹ�ɾ��������������
		newsType list = getList(id);
		if(list.getChildBeans()!=null){
			for (newsType c : list.getChildBeans()) {
				delete(c.getId());
			}
		}
		//ɾ���������
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
		
		if(element==null){//�����ݿ��л�ȡ
			list = nd.getByParentId(0);
			cache.put(new Element("clist",list));
			System.out.println("������û�����ݣ��Ѵ����ݿ��ȡ");
		}
		else{
			list =(List<newsType>) element.getObjectValue();
			System.out.println("�ӻ����л�ȡ");
		}
		return list;
	}
}
