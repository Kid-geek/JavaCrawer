package parse;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.BookModel;

/**
 * @author zh ƥ�����ݹ���,��Jsoup
 *
 */
public class Parse {
	public static List<BookModel> getBookData(String html) {
		String evaluate = "";
		// �����б�
		List<BookModel> data = new ArrayList<>();
		// ��ȡ��ҳ����,׼��ץȡ
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
		// ����Element �����ݴ����б�
		for (Element element : elements) {
			String ID = element.attr("data-sku");
			String name = element.select("div[class=p-name p-name-type-2]").select("em").text();
			if (name.equals("")) { // ���Ϊ��ƥ����һ�׹���
				name = element.select("div[class=p-name]").text();
			}
			String price = element.select("div[class=p-price]").select("strong").select("i").text();
			if (price.equals("")) {
				price = "��";
			}

			evaluate = element.select("div[class=p-commit]").select("a[target=_blank]").text();
			if(evaluate.contains("��������")){//���ֶ�������ʱ����һ�׹���ƥ��
				evaluate = element.select("div[class=p-commit]").select("strong").select("a").text();
				evaluate = evaluate.replace("+", "");// ��ȥ+��
				if (evaluate.contains("��")) {
					evaluate = evaluate.replace("��", "0000");// ��ȥ��
					if (evaluate.contains(".")) {
						evaluate = evaluate.replace(".", "");// ��ȥ. ĩλȥ��һ��0
						evaluate = evaluate.substring(0, evaluate.length() - 1);
					}
				}
			}
			evaluate = evaluate.replace("+", "");// ��ȥ+��
			if (evaluate.contains("��")) {
				evaluate = evaluate.replace("��", "0000");// ��ȥ��
				if (evaluate.contains(".")) {
					evaluate = evaluate.replace(".", "");// ��ȥ. ĩλȥ��һ��0
					evaluate = evaluate.substring(0, evaluate.length() - 1);
				}
			}
			
			String press = element.select("div[class=p-bookdetails]").select("span[class=p-bi-store]").text();
			if (press.equals("")) {
				press = "��";
			}

			String picture = element.select("div[class=p-img]").select("img").attr("src");
			if (picture.equals("")) {
				picture = element.select("div[class=p-img]").select("img").attr("data-lazy-img");
			}
			String shopURL = element.select("div[class=p-img]").select("a").attr("href");
			// System.out.println("��ƷID: "+ID+" ����: "+name+" �۸�: "+price+"
			// ������:"+evaluate);
			// System.out.println(picture);

			BookModel jdmodel = new BookModel();
			jdmodel.setID(ID);
			jdmodel.setName(name);
			jdmodel.setPrice(price);
			jdmodel.setEvaluate(evaluate);
			jdmodel.setPress(press);
			jdmodel.setPicturePath(picture);
			jdmodel.setShopURL(shopURL);

			// ���List
			data.add(jdmodel);
		}

		return data;

	}
}
