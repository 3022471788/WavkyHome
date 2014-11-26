/**
 * 2014��11��6�� ����12:39:57
 */
package dao.interfaces;

import java.util.List;

import bean.Project;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public interface ProjectDao extends BaseDao<Project> {
	
	/**
	 * ��AddTime�����г������¼��루ʱ�俿�󣩵�����
	 * @return
	 */
	public abstract List<Project> listByAddTime();
	
	/**
	 * �г�AddTime�������6��
	 * @return
	 */
	public abstract List<Project> listLast6();

}
