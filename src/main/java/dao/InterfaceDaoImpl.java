/**
 * 2014��11��6�� ����12:41:31
 */
package dao;

import bean.Interface;
import dao.interfaces.InterfaceDao;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public class InterfaceDaoImpl extends BaseDaoImpl<Interface> implements InterfaceDao {

	@Override
	public Class<Interface> getEntityClass() {
		return Interface.class;
	}

}
