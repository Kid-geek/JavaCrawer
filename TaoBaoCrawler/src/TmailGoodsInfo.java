
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TmailGoodsInfo {
	public static void main(String[] args) throws ClientProtocolException, IOException  {
		String url = "https://mdskip.taobao.com/core/initItemDetail.htm?isForbidBuyItem=false&cartEnable=true&itemId=549004787468&isPurchaseMallPage=false&offlineShop=false&queryMemberRight=true&isSecKill=false&sellerPreview=false&cachedTimestamp=1502846997903&tmallBuySupport=true&isApparel=false&addressLevel=2&service3C=false&showShopProm=false&isUseInventoryCenter=false&isRegionLevel=false&isAreaSell=false&tryBeforeBuy=false&household=false&callback=setMdskip&timestamp=1502848785256&isg=null&isg2=AgkJZHnWtCmLwUg0MZ3S7hxgDTXPRK_NbEaJ26t86vBw8isE86JLWVliQGEq";
		String referer="https://detail.tmall.com/item.htm?id=549004787468&ali_refid=a3_430583_1006:1121371980:N:java:6628cce7b7279c82556f8802f07106ec&ali_trackid=1_6628cce7b7279c82556f8802f07106ec&spm=a230r.1.14.1.76bf523hqYFaJ"; 
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);

		httpGet.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36"
				+ " (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");

		//添加referer
		httpGet.setHeader("Referer",referer);
		
			CloseableHttpResponse response = httpclient.execute(httpGet);
			String entity = EntityUtils.toString(response.getEntity(), "UTF-8");
			entity=entity.substring(12,entity.length()-1);
			
			
			//解析JSON
			JSONObject object = JSON.parseObject(entity);
			JSONObject object2 = (JSONObject)object.get("defaultModel");  
			JSONObject object3 = (JSONObject)object2.get("itemPriceResultDO");  
			JSONObject object4 = (JSONObject)object3.get("priceInfo");  
			JSONObject object5 = (JSONObject)object4.get("def");  
			JSONArray jsonArray = JSON.parseArray(object5.get("promotionList").toString());  
//			
			
			System.out.println(object5);
			if(jsonArray.size()==1){  
	            JSONObject object6 = (JSONObject)jsonArray.get(0);  
	            System.out.println("实际售价为:"+object6.get("price"));  
	        }  

		
	}
}
