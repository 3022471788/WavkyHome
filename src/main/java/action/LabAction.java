/**
 * 2014��10��25�� ����10:51:51
 */
package action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.core.io.Resource;

import bean.Project;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.interfaces.ProjectDao;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public class LabAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String title;
	private String type;
	private String linkAddr;
	/**
	 * ����Ԥ��ͼƬ
	 */
	private File snapshot;
	private String snapshotContentType;
	private String snapshotFileName;
	/**
	 * ���ձ���·��
	 */
	private Resource savePathResource;
	/**
	 * ����·��
	 */
	private String savePath;
	private List<Project> projectList;
	/**
	 * ��Ҫɾ����project��id
	 */
	private int deleteProjectId;
	private ProjectDao projectDao;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the linkAddr
	 */
	public String getLinkAddr() {
		return linkAddr;
	}

	/**
	 * @param linkAddr
	 *            the linkAddr to set
	 */
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	/**
	 * @return the snapshot
	 */
	public File getSnapshot() {
		return snapshot;
	}

	/**
	 * @param snapshot
	 *            the snapshot to set
	 */
	public void setSnapshot(File snapshot) {
		this.snapshot = snapshot;
	}

	/**
	 * @return the snapshotContentType
	 */
	public String getSnapshotContentType() {
		return snapshotContentType;
	}

	/**
	 * @param snapshotContentType
	 *            the snapshotContentType to set
	 */
	public void setSnapshotContentType(String snapshotContentType) {
		this.snapshotContentType = snapshotContentType;
	}

	/**
	 * @return the snapshotFileName
	 */
	public String getSnapshotFileName() {
		return snapshotFileName;
	}

	/**
	 * @param snapshotFileName
	 *            the snapshotFileName to set
	 */
	public void setSnapshotFileName(String snapshotFileName) {
		this.snapshotFileName = snapshotFileName;
	}

	/**
	 * @return the savePathResource
	 */
	public Resource getSavePathResource() {
		return savePathResource;
	}

	/**
	 * @param savePathResource
	 *            the savePathResource to set
	 */
	public void setSavePathResource(Resource savePathResource) {
		this.savePathResource = savePathResource;
		try {
			setSavePath(savePathResource.getFile().getCanonicalPath());
		} catch (Exception e) {
			LOG.error("save path", e, new String[0]);
		}
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * Automatic invoked by {@link #setSavePathResource(Resource)}
	 * 
	 * @param savePath
	 *            the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @return the projectList
	 */
	public List<Project> getProjectList() {
		return projectList;
	}

	/**
	 * @param projectList
	 *            the projectList to set
	 */
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	/**
	 * @return the deleteProjectId
	 */
	public int getDeleteProjectId() {
		return deleteProjectId;
	}

	/**
	 * @param deleteProjectId
	 *            the deleteProjectId to set
	 */
	public void setDeleteProjectId(int deleteProjectId) {
		this.deleteProjectId = deleteProjectId;
	}

	/**
	 * @return the projectDao
	 */
	public ProjectDao getProjectDao() {
		return projectDao;
	}

	/**
	 * @param projectDao
	 *            the projectDao to set
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * ���project
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addProject() throws Exception {
		final String snapshotPath = saveSnapshot();
		Project project = new Project();
		project.setTitle(title);
		LOG.error("TITLE:"+title,null );
		project.setType(type);
		project.setLinkAddr(linkAddr);
		project.setSnapshotAddr(snapshotPath);
		project.setAddTime(System.currentTimeMillis());
		if (projectDao.save(project))
			return SUCCESS;
		else
			return ERROR;
	}

	/**
	 * �洢�ϴ��Ŀ��գ���jpeg��png��ʽ��׺���Ϊ.pic���ļ���ȡUUID���ֵ
	 * 
	 * @return �����ļ���Ӧ�ø�Ŀ¼�ڵ����Ŀ¼�ļ���
	 * @throws Exception
	 */
	private String saveSnapshot() throws Exception {
		String suffix = null;
		if (snapshotContentType.equals("image/jpeg"))
			suffix = ".jpg";
		if (snapshotContentType.equals("image/png"))
			suffix = ".png";
		if (suffix == null)
			suffix = ".pic";
		final String fileName = UUID.randomUUID().toString() + suffix;
		File outputSnapshot = new File(savePath, fileName);
		FileUtils.copyFile(snapshot, outputSnapshot);
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		String currentPath = sc.getRealPath("/");
		String relativePath = new File(savePath.replace(currentPath, ""),
				fileName).getPath();
		return relativePath;
	}

	/**
	 * �г�project
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listProject() throws Exception {
		projectList = projectDao.listByAddTime();
		return SUCCESS;
	}

	/**
	 * ɾ��project
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteProject() throws Exception {
		Project project = projectDao.get(deleteProjectId);
		if (project != null) {
			if (projectDao.delete(project))
				return SUCCESS;
			else
				return ERROR;
		}
		return ERROR;
	}
}
