package util;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zh
 * 
 *         ץȡ��ҳ����
 *
 */
public class HttpCrawer {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpCrawer.class);

	public static String getEntity(String url) {

		// �������
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// �����ַ
		HttpGet httpget = new HttpGet(url);
		String entity = null;
		try {
			// �س�
			CloseableHttpResponse response = httpclient.execute(httpget);
			// ���״̬��Ϊ200,��ʾ�ɹ�
			if (response.getStatusLine().getStatusCode() == 200) {

				// ��ȡ��ҳ����
				entity=EntityUtils.toString(response.getEntity(),"utf-8");

			} else {
				LOGGER.error("��ȡ��ҳʧ��");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

}
