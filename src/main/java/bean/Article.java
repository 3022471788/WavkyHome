/**
 * 2014��10��27�� ����4:27:42
 */
package bean;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public class Article {
	private String title;
	private String summary;
	private String linkAddr;
	/**
	 * �����
	 */
	private int views;
	/**
	 * ������
	 */
	private int comments;
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the linkAddr
	 */
	public String getLinkAddr() {
		return linkAddr;
	}
	/**
	 * @param linkAddr the linkAddr to set
	 */
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	/**
	 * @param views the views to set
	 */
	public void setViews(int views) {
		this.views = views;
	}
	/**
	 * @return the comments
	 */
	public int getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(int comments) {
		this.comments = comments;
	}

	
}
