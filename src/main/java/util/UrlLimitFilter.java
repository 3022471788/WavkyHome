/**
 * 2014��11��26�� ����12:53:24
 */
package util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * ������URL���й������ƣ���ֱֹ�ӷ���JSPҳ��
 * 
 * @author wavky.wand
 *
 */
public class UrlLimitFilter extends StrutsPrepareAndExecuteFilter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		if (url.toLowerCase().endsWith(".jsp")) {
			StringBuilder sb = new StringBuilder();
			sb.append(request.getRemoteAddr());
			sb.append(" : ");
			sb.append(request.getMethod());
			sb.append(" ");
			sb.append(request.getRequestURI());
			sb.append(" ");
			sb.append(request.getProtocol());
			sb.append(" : ��ֱֹ������jsp");
			System.out.println(sb.toString());
			// ��ֱֹ������jsp���ڲ�ת�����404ҳ�棬���޸�URL
			request.getRequestDispatcher("/not_found.html").forward(req, res);
			// �ض���404ҳ�棬Ч��������.html/.asp����Դʱ��һ��
			// ((HttpServletResponse)res).sendRedirect("404");
			return;
		}
		// ����Ĭ�Ϲ������ַ���Դ
		super.doFilter(req, res, chain);
	}
}