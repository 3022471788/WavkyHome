/**
 * 2014��11��6�� ����12:39:40
 */
package dao.interfaces;

import bean.User;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public interface UserDao extends BaseDao<User> {

	/**
	 * �����û��������û�
	 * 
	 * @param name
	 * @return ������ʱ����null
	 */
	public abstract User get(String name);
}
