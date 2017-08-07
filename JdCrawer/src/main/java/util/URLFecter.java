package util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import model.JdModel;
import parse.JdParse;

public class URLFecter {
		public static List<JdModel> URLParser(HttpClient client , String url) throws Exception{
			List<JdModel> JingdongData=new ArrayList<JdModel>();
			HttpResponse response=HttpUtils.getRawHtml(client,url);
			int statusCode=response.getStatusLine().getStatusCode();
			if(statusCode==200){
				String entity=EntityUtils.toString(response.getEntity(),"utf-8");
				JingdongData=JdParse.getData(entity);
			}
			
			return JingdongData;
			
		}
}
