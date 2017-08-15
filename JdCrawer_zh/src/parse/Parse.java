package parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.BookModel;

/**
 * @author zh 匹配内容规则,用Jsoup
 *
 */
public class Parse {
	public static List<BookModel> getBookData(String html) {
		String evaluate = "";
		// 创建列表
		List<BookModel> data = new ArrayList<>();
		// 获取网页内容,准备抓取
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
		// 遍历Element 把数据存入列表
		for (Element element : elements) {
			String ID = element.attr("data-sku");
			String name = element.select("div[class=p-name p-name-type-2]").select("em").text();
			if (name.equals("")) { // 如果为空匹配另一套规则
				name = element.select("div[class=p-name]").text();
			}
			String price = element.select("div[class=p-price]").select("strong").select("i").text();
			if (price.equals("")) {
				price = "无";
			}

			evaluate = element.select("div[class=p-commit]").select("a[target=_blank]").text();
			if(evaluate.contains("二手有售")){//出现二手有售时用另一套规则匹配
				evaluate = element.select("div[class=p-commit]").select("strong").select("a").text();
				evaluate = evaluate.replace("+", "");// 除去+号
				if (evaluate.contains("万")) {
					evaluate = evaluate.replace("万", "0000");// 除去万
					if (evaluate.contains(".")) {
						evaluate = evaluate.replace(".", "");// 除去. 末位去掉一个0
						evaluate = evaluate.substring(0, evaluate.length() - 1);
					}
				}
			}
			evaluate = evaluate.replace("+", "");// 除去+号
			if (evaluate.contains("万")) {
				evaluate = evaluate.replace("万", "0000");// 除去万
				if (evaluate.contains(".")) {
					evaluate = evaluate.replace(".", "");// 除去. 末位去掉一个0
					evaluate = evaluate.substring(0, evaluate.length() - 1);
				}
			}
			
			String press = element.select("div[class=p-bookdetails]").select("span[class=p-bi-store]").text();
			if (press.equals("")) {
				press = "无";
			}

			String picture = element.select("div[class=p-img]").select("img").attr("src");
			if (picture.equals("")) {
				picture = element.select("div[class=p-img]").select("img").attr("data-lazy-img");
			}
			String shopURL = element.select("div[class=p-img]").select("a").attr("href");
			// System.out.println("商品ID: "+ID+" 名称: "+name+" 价格: "+price+"
			// 评价数:"+evaluate);
			// System.out.println(picture);

			BookModel jdmodel = new BookModel();
			jdmodel.setID(ID);
			jdmodel.setName(name);
			jdmodel.setPrice(price);
			jdmodel.setEvaluate(evaluate);
			jdmodel.setPress(press);
			jdmodel.setPicturePath(picture);
			jdmodel.setShopURL(shopURL);

			// 添加List
			data.add(jdmodel);
		}

		return data;

	}
}
