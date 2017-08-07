package util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

public class HttpUtils {
	public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
		HttpGet getMethod = new HttpGet(personalUrl);
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		try {
			response = client.execute(getMethod);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return response;
	}
}
