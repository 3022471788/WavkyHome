/**
 * 2014��11��6�� ����3:19:01
 */
package dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 
 * @author wavky.wand
 *
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * ���ش���ʵ����Class����
	 * 
	 * @return
	 */
	public abstract Class<T> getEntityClass();

	/**
	 * ��ȡ��id�Ķ���
	 * 
	 * @param id
	 * @return ������ʱ����null
	 */
	public abstract T get(Serializable id);

	/**
	 * �������м�¼
	 * @return
	 * û�����ݼ�¼ʱ���ؿյ�list����
	 */
	public List<T> getAll();
	
	/**
	 * ��������һ��ʵ���¼
	 * 
	 * @param entity
	 * @return �ɹ�ʱ����true��ʧ��ʱ�׳� DataAccessException
	 */
	public abstract boolean save(T entity);

	/**
	 * ɾ��һ��ʵ���¼
	 * 
	 * @param entity
	 * @return �ɹ�ʱ����true��ʧ��ʱ�׳� DataAccessException
	 */
	public abstract boolean delete(T entity);

}