/**
 * 2014��10��25�� ����10:51:51
 */
package action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private static final SimpleDateFormat dateHTMLReadFormater = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat dateHTMLWriteFormater = new SimpleDateFormat(
			"yyyy.MM.dd");
	private static final SimpleDateFormat datetimeHTMLWriteFormater = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss");
	private static final SimpleDateFormat dateFileNameFormater = new SimpleDateFormat(
			"yyyyMMdd");
	private static final DecimalFormat decimalFormater = new DecimalFormat(
			"#,##0.00");

	private String title;
	private String type;
	private String webLinkAddr;
	/**
	 * ����Ԥ��ͼƬ
	 */
	private File snapshot;
	private String snapshotContentType;
	private String snapshotFileName;
	/**
	 * ���ձ���·��
	 */
	private Resource snapshotSavePathResource;
	/**
	 * ����·����Resourceע��ʱ��������
	 */
	private String snapshotSavePath;

	private String releaseDateForEditing;
	private String releaseDateForShowing;
	private String updatingDate;
	private String sourceLinkAddr;

	/**
	 * ������listTargetProjectInfoչʾ
	 */
	private String snapshotAddr;
	private String downloadAddr;
	private String downloadFileName;
	private int download;
	private String version;
	private float price;
	private String priceForShowing;
	private String introduction;

	/**
	 * ��Ŀ��ִ���ļ��ϴ�
	 */
	private File projectFile;
	private String projectFileContentType;
	private String projectFileFileName;
	/**
	 * ��Ŀ��ִ���ļ�����·��
	 */
	private Resource projectFileSavePathResource;
	/**
	 * ����·����Resourceע��ʱ��������
	 */
	private String projectFileSavePath;

	private List<Project> projectList;
	/**
	 * ��Ҫ�����project��id��ɾ�����޸ģ�
	 */
	private int targetProjectId;
	private ProjectDao projectDao;

	private static final String rootPath;

	static {
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		rootPath = sc.getRealPath("/");
	}

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
	 * @return the webLinkAddr
	 */
	public String getWebLinkAddr() {
		return webLinkAddr;
	}

	/**
	 * @param webLinkAddr
	 *            the webLinkAddr to set
	 */
	public void setWebLinkAddr(String webLinkAddr) {
		this.webLinkAddr = webLinkAddr;
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
	 * @return the snapshotSavePathResource
	 */
	public Resource getSnapshotSavePathResource() {
		return snapshotSavePathResource;
	}

	/**
	 * @param snapshotSavePathResource
	 *            the snapshotSavePathResource to set
	 */
	public void setSnapshotSavePathResource(Resource snapshotSavePathResource) {
		this.snapshotSavePathResource = snapshotSavePathResource;
		try {
			setSnapshotSavePath(snapshotSavePathResource.getFile()
					.getCanonicalPath());
		} catch (Exception e) {
			LOG.error("save path", e, new String[0]);
		}
	}

	/**
	 * @return the snapshotSavePath
	 */
	public String getSnapshotSavePath() {
		return snapshotSavePath;
	}

	/**
	 * @param snapshotSavePath
	 *            the snapshotSavePath to set
	 */
	public void setSnapshotSavePath(String snapshotSavePath) {
		this.snapshotSavePath = snapshotSavePath;
	}

	/**
	 * @return the projectFile
	 */
	public File getProjectFile() {
		return projectFile;
	}

	/**
	 * @param projectFile
	 *            the projectFile to set
	 */
	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	/**
	 * @return the projectFileContentType
	 */
	public String getProjectFileContentType() {
		return projectFileContentType;
	}

	/**
	 * @param projectFileContentType
	 *            the projectFileContentType to set
	 */
	public void setProjectFileContentType(String projectFileContentType) {
		this.projectFileContentType = projectFileContentType;
	}

	/**
	 * @return the projectFileFileName
	 */
	public String getProjectFileFileName() {
		return projectFileFileName;
	}

	/**
	 * @param projectFileFileName
	 *            the projectFileFileName to set
	 */
	public void setProjectFileFileName(String projectFileFileName) {
		this.projectFileFileName = projectFileFileName;
	}

	/**
	 * @return the projectFileSavePathResource
	 */
	public Resource getProjectFileSavePathResource() {
		return projectFileSavePathResource;
	}

	/**
	 * @param projectFileSavePathResource
	 *            the projectFileSavePathResource to set
	 */
	public void setProjectFileSavePathResource(
			Resource projectFileSavePathResource) {
		this.projectFileSavePathResource = projectFileSavePathResource;
		try {
			setProjectFileSavePath(projectFileSavePathResource.getFile()
					.getCanonicalPath());
		} catch (Exception e) {
			LOG.error("project save path", e, new String[0]);
		}
	}

	/**
	 * @return the projectFileSavePath
	 */
	public String getProjectFileSavePath() {
		return projectFileSavePath;
	}

	/**
	 * @param projectFileSavePath
	 *            the projectFileSavePath to set
	 */
	public void setProjectFileSavePath(String projectFileSavePath) {
		this.projectFileSavePath = projectFileSavePath;
	}

	/**
	 * @return the releaseDateForEditing
	 */
	public String getReleaseDateForEditing() {
		return releaseDateForEditing;
	}

	/**
	 * @param releaseDateForEditing
	 *            the releaseDateForEditing to set
	 */
	public void setReleaseDateForEditing(String releaseDateForEditing) {
		this.releaseDateForEditing = releaseDateForEditing;
	}

	public String getReleaseDateForShowing() {
		return releaseDateForShowing;
	}

	public void setReleaseDateForShowing(String releaseDateForShowing) {
		this.releaseDateForShowing = releaseDateForShowing;
	}

	/**
	 * @return the updatingDate
	 */
	public String getUpdatingDate() {
		return updatingDate;
	}

	/**
	 * @param updatingDate
	 *            the updatingDate to set
	 */
	public void setUpdatingDate(String updatingDate) {
		this.updatingDate = updatingDate;
	}

	/**
	 * @return the sourceLinkAddr
	 */
	public String getSourceLinkAddr() {
		return sourceLinkAddr;
	}

	/**
	 * @param sourceLinkAddr
	 *            the sourceLinkAddr to set
	 */
	public void setSourceLinkAddr(String sourceLinkAddr) {
		this.sourceLinkAddr = sourceLinkAddr;
	}

	/**
	 * @return the snapshotAddr
	 */
	public String getSnapshotAddr() {
		return snapshotAddr;
	}

	/**
	 * @param snapshotAddr
	 *            the snapshotAddr to set
	 */
	public void setSnapshotAddr(String snapshotAddr) {
		this.snapshotAddr = snapshotAddr;
	}

	/**
	 * @return the downloadAddr
	 */
	public String getDownloadAddr() {
		return downloadAddr;
	}

	/**
	 * @param downloadAddr
	 *            the downloadAddr to set
	 */
	public void setDownloadAddr(String downloadAddr) {
		this.downloadAddr = downloadAddr;
	}

	public String getDownloadFileName() {
		// ����ո�ͷ�ASCII�����ļ�������
		try {
			downloadFileName = URLEncoder.encode(downloadFileName, "utf-8"); 
			downloadFileName = downloadFileName.replace("+","%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	public String getPriceForShowing() {
		return priceForShowing;
	}

	public void setPriceForShowing(String priceForShowing) {
		this.priceForShowing = priceForShowing;
	}

	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * @param introduction
	 *            the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
	 * @return the targetProjectId
	 */
	public int getTargetProjectId() {
		return targetProjectId;
	}

	/**
	 * @param targetProjectId
	 *            the targetProjectId to set
	 */
	public void setTargetProjectId(int targetProjectId) {
		this.targetProjectId = targetProjectId;
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
	 * ��ʼ������ĿĬ�ϲ���
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initNewProject() throws Exception {
		// android/web/window
		// type = "";
		// title = "";
		// version = "1.0";
		// price = 0f;
		// introduction = "";
		releaseDateForEditing = dateHTMLReadFormater.format(new Date(System
				.currentTimeMillis()));
		return SUCCESS;
	}

	/**
	 * ����project
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProject() throws Exception {
		if (targetProjectId == 0) {
			return saveNewProject();
		} else {
			return updateProject();
		}
	}

	/**
	 * ������project
	 * 
	 * @return
	 * @throws Exception
	 */
	private String saveNewProject() throws Exception {
		final String snapshotPath = saveSnapshot();
		final String projectFilePath = saveProjectFile();

		Project project = new Project();
		project.setTitle(title);
		project.setType(type);
		project.setWebLinkAddr(webLinkAddr);
		project.setSnapshotAddr(snapshotPath);

		project.setSourceLinkAddr(sourceLinkAddr);
		project.setProjectFileAddr(projectFilePath);
		project.setDownload(0);
		project.setVersion(version);
		project.setPrice(price);
		project.setIntroduction(introduction);

		project.setReleaseDate(dateHTMLReadFormater
				.parse(releaseDateForEditing).getTime());
		project.setAddTime(System.currentTimeMillis());
		project.setUpdateTime(System.currentTimeMillis());
		if (projectDao.save(project))
			return SUCCESS;
		else
			return ERROR;
	}

	/**
	 * ����project��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	private String updateProject() throws Exception {
		Project project = projectDao.get(targetProjectId);
		if (project != null) {
			final String snapshotPath = saveSnapshot();
			final String projectFilePath = saveProjectFile();

			project.setTitle(title);
			project.setType(type);
			project.setWebLinkAddr(webLinkAddr);
			if (!snapshotPath.isEmpty()) {
				deleteProjectSnapshot(project);
				project.setSnapshotAddr(snapshotPath);
			}

			project.setSourceLinkAddr(sourceLinkAddr);
			if (!projectFilePath.isEmpty()) {
				deleteProjectFile(project);
				project.setProjectFileAddr(projectFilePath);
			}
			project.setVersion(version);
			project.setPrice(price);
			project.setIntroduction(introduction);

			project.setReleaseDate(dateHTMLReadFormater.parse(
					releaseDateForEditing).getTime());
			project.setUpdateTime(System.currentTimeMillis());
			if (projectDao.save(project))
				return SUCCESS;
			else
				return ERROR;
		}
		return ERROR;
	}

	/**
	 * �г�Ŀ��project��Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listTargetProjectInfo() throws Exception {
		Project project = projectDao.get(targetProjectId);
		if (project != null) {
			type = project.getType();
			title = project.getTitle();
			snapshotAddr = project.getSnapshotAddr();
			webLinkAddr = project.getWebLinkAddr();
			downloadAddr = project.getProjectFileAddr();
			download = project.getDownload();
			sourceLinkAddr = project.getSourceLinkAddr();
			version = project.getVersion();
			price = project.getPrice();
			priceForShowing = decimalFormater.format(project.getPrice());
			introduction = project.getIntroduction();
			releaseDateForEditing = dateHTMLReadFormater.format(new Date(
					project.getReleaseDate()));
			releaseDateForShowing = dateHTMLWriteFormater.format(new Date(
					project.getReleaseDate()));
			updatingDate = datetimeHTMLWriteFormater.format(new Date(project
					.getUpdateTime()));
			return SUCCESS;
		}
		return ERROR;
	}

	public String checkProjectFileAddr() {
		Project project = projectDao.get(targetProjectId);
		if (project != null) {
			downloadAddr = project.getProjectFileAddr();
			if (downloadAddr == null || downloadAddr.isEmpty()) {
				return ERROR;
			}
			downloadFileName = downloadAddr.substring(downloadAddr
					.lastIndexOf('\\') + 1);
			return SUCCESS;
		}
		return ERROR;
	}

	public InputStream getDownloadProjectFile() {
		if (downloadAddr == null || downloadAddr.isEmpty()) {
			return null;
		}
		Project project = projectDao.get(targetProjectId);
		if (project != null) {
			int download = project.getDownload() + 1;
			project.setDownload(download);
			projectDao.save(project);
		}
		return ServletActionContext.getServletContext().getResourceAsStream(
				downloadAddr);
	}

	/**
	 * �洢�ϴ��Ŀ��գ��ļ���ȡUUID���ֵ
	 * 
	 * @return �����ļ���Ӧ�ø�Ŀ¼�ڵ����Ŀ¼�ļ���
	 * @throws Exception
	 */
	private String saveSnapshot() throws Exception {
		if (snapshot == null) {
			return "";
		}
		String suffix = snapshotFileName.substring(snapshotFileName
				.lastIndexOf('.'));
		final String fileName = UUID.randomUUID().toString() + suffix;
		File outputSnapshot = new File(snapshotSavePath, fileName);
		FileUtils.copyFile(snapshot, outputSnapshot);
		String relativePath = new File(snapshotSavePath.replace(rootPath, ""),
				fileName).getPath();
		return relativePath;
	}

	/**
	 * �洢�ϴ�����Ŀ��ִ���ļ����ļ���ȡUUID���ֵ
	 * 
	 * @return ��Ŀ��ִ���ļ���Ӧ�ø�Ŀ¼�ڵ����Ŀ¼�ļ���
	 * @throws Exception
	 */
	private String saveProjectFile() throws Exception {
		if (projectFile == null) {
			return "";
		}
		String prefix = projectFileFileName.substring(0,
				projectFileFileName.lastIndexOf('.'));
		String suffix = projectFileFileName.substring(projectFileFileName
				.lastIndexOf('.'));
		String date = dateFileNameFormater.format(new Date(System
				.currentTimeMillis()));
		final String fileName = prefix + '_' + date + suffix;
		File outputProjectFile = new File(projectFileSavePath, fileName);
		FileUtils.copyFile(projectFile, outputProjectFile);
		String relativePath = new File(
				projectFileSavePath.replace(rootPath, ""), fileName).getPath();
		return relativePath;
	}

	/**
	 * �г�����projects
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listProjects() throws Exception {
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
		Project project = projectDao.get(targetProjectId);
		if (project != null) {
			if (projectDao.delete(project)) {
				deleteProjectSnapshot(project);
				deleteProjectFile(project);
				return SUCCESS;
			} else
				return ERROR;
		}
		return ERROR;
	}

	private void deleteProjectSnapshot(Project project) {
		try {
			File snapshot = new File(rootPath + project.getSnapshotAddr());
			snapshot.delete();
		} catch (Exception e) {
		}
	}

	private void deleteProjectFile(Project project) {
		try {
			File projectFile = new File(rootPath + project.getProjectFileAddr());
			projectFile.delete();
		} catch (Exception e) {
		}
	}
}
