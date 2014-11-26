/**
 * 2014��11��10�� ����1:25:07
 */
package util;

import org.json.JSONObject;

import bean.IpAddress;

/**
 * 
 * 
 * @author wavky.wand
 *
 */
public class JsonIpAddressAdapter {

	/**
	 * �����Ա�ip�ӿ�http://ip.taobao.com/instructions.php
	 * @param jsonIp
	 * @return
	 */
	public static IpAddress getIpAddressForm(JSONObject jsonIp) {
		IpAddress ipAddress = new IpAddress();
		int mark = jsonIp.optInt("code");
		if (mark != 0)
			// ʧ��
			return ipAddress;
		jsonIp = jsonIp.optJSONObject("data");
		ipAddress.setCountry(jsonIp.optString("country"));
		ipAddress.setArea(jsonIp.optString("area"));
		ipAddress.setProvince(jsonIp.optString("region"));
		ipAddress.setCity(jsonIp.optString("city"));
		ipAddress.setIsp(jsonIp.optString("isp"));
		ipAddress.setIp(jsonIp.optString("ip"));
		return ipAddress;
	}
}
