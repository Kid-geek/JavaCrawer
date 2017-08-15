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
		
		//��ʼ�����ݿ�
		MySQLControl my = new MySQLControl();
		
		// ��ȡXML�ļ�,���document����
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File("src/crawler-config.xml"));
			Element rootEle = document.getRootElement();// ȡ�ø��ڵ�
			// ��ȡҪ��������Ʒ����
			Element nameEle = rootEle.element("crawler-name");
			name = nameEle.getText();
			// System.out.println(name);
			// ��ȡͼƬ����·��
			Element picSavaPathEle = rootEle.element("download-pic");
			picSavePath = picSavaPathEle.getText() + name;
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		
		
		// ץȡ��ҳ����
//		String url = "https://search.jd.com/Search?keyword=" + name
//				+ "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&page=1&s=1&click=1";
//		LOGGER.info("��ҳ����Ϊ: " + url);

		String url="";
		String[] urlList=new String[10];
		for (int i = 0; i < urlList.length; i++) {
			urlList[i] = "https://search.jd.com/Search?keyword=" + name
				+ "&enc=utf-8&qrst=1&rt=1&stop=1&vt=2&wtype=1&page="+i+"&s="+i*60+"&click=1";
			url=urlList[i];
		LOGGER.info("��ҳ����Ϊ: " + url);
		
		// ��ʼ��ץȡ��
		HttpCrawer http = new HttpCrawer();
		String html = http.getEntity(url);
		Parse parse = new Parse();

		parse.getBookData(html);
		List<BookModel> data = parse.getBookData(html);
		for (BookModel jd : data) {
			LOGGER.info("��ƷID: " + jd.getID() + "  ����: " + jd.getName() + "  �۸�: " + jd.getPrice() + "  ������:"
					+ jd.getEvaluate() + "  ������: " + jd.getPress() + "  ��ƷͼƬ: " + jd.getPicturePath() + "  ��������: "
					+ jd.getShopURL());

			
			// ����ͼƬ
			String fileName=jd.getName();
			if(fileName.contains("\\")){   //������Ƴ���\  �滻Ϊ-
				fileName=fileName.replaceAll("\\", "-");
			}
			DownloadPic download = new DownloadPic();
			download.download(jd.getPicturePath(), fileName, picSavePath);
			LOGGER.info("ͼƬ��: " + jd.getName() + " ���سɹ�");
		}

		LOGGER.info("ץȡ���");

		
		my.executeInsert(data);
		}
	}
}
