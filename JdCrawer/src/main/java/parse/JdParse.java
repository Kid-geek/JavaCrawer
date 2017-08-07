package parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.JdModel;

public class JdParse {

	public static List<JdModel> getData(String html) throws Exception {
		List<JdModel> data = new ArrayList<JdModel>();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
		for (Element ele : elements) {
			String bookID = ele.attr("data-sku");
			String bookPrice = ele.select("div[class=p-price]").select("strong").select("i").text();
			String bookName = ele.select("div[class=p-name]").select("em").text();
			JdModel jdModel=new JdModel();
			jdModel.setBookID(bookID);
			jdModel.setBookName(bookName);
			jdModel.setBookPrice(bookPrice);
			data.add(jdModel);
		}

		return data;
	}

}
