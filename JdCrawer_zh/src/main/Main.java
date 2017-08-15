package main;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.MySQLControl;
import model.BookModel;
import parse.Parse;
import util.DownloadPic;
import util.HttpCrawer;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		String name = null;
		String picSavePath = null;
		
		//初始化数据库
		MySQLControl my = new MySQLControl();
		
		// 读取XML文件,获得document对象
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File("src/crawler-config.xml"));
			Element rootEle = document.getRootElement();// 取得根节点
			// 获取要搜索的商品名称
			Element nameEle = rootEle.element("crawler-name");
			name = nameEle.getText();
			// System.out.println(name);
			// 获取图片保存路径
			Element picSavaPathEle = rootEle.element("download-pic");
			picSavePath = picSavaPathEle.getText() + name;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		
		
		// 抓取网页链接
//		String url = "https://search.jd.com/Search?keyword=" + name
//				+ "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&page=1&s=1&click=1";
//		LOGGER.info("网页链接为: " + url);

		String url="";
		String[] urlList=new String[10];
		for (int i = 0; i < urlList.length; i++) {
			urlList[i] = "https://search.jd.com/Search?keyword=" + name
				+ "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&page="+i+"&s="+i*60+"&click=1";
			url=urlList[i];
		LOGGER.info("网页链接为: " + url);
		
		// 初始化抓取类
		HttpCrawer http = new HttpCrawer();
		String html = http.getEntity(url);
		Parse parse = new Parse();

		parse.getBookData(html);
		List<BookModel> data = parse.getBookData(html);
		for (BookModel jd : data) {
			LOGGER.info("商品ID: " + jd.getID() + "  名称: " + jd.getName() + "  价格: " + jd.getPrice() + "  评价数:"
					+ jd.getEvaluate() + "  出版社: " + jd.getPress() + "  商品图片: " + jd.getPicturePath() + "  购买链接: "
					+ jd.getShopURL());

			
			// 下载图片
			String fileName=jd.getName();
			if(fileName.contains("\\")){   //如果名称出现\  替换为-
				fileName=fileName.replaceAll("\\", "-");
			}
			DownloadPic download = new DownloadPic();
			download.download(jd.getPicturePath(), fileName, picSavePath);
			LOGGER.info("图片名: " + jd.getName() + " 下载成功");
		}

		LOGGER.info("抓取完成");

		
		my.executeInsert(data);
		}
	}
}
