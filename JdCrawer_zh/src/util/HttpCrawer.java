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
 *         抓取网页内容
 *
 */
public class HttpCrawer {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpCrawer.class);

	public static String getEntity(String url) {

		// 打开浏览器
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 输入地址
		HttpGet httpget = new HttpGet(url);
		String entity = null;
		try {
			// 回车
			CloseableHttpResponse response = httpclient.execute(httpget);
			// 如果状态码为200,表示成功
			if (response.getStatusLine().getStatusCode() == 200) {

				// 获取网页内容
				entity=EntityUtils.toString(response.getEntity(),"utf-8");

			} else {
				LOGGER.error("读取网页失败");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return entity;
	}

}
