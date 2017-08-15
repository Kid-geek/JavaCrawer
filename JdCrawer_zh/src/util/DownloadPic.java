package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zh
 *
 *         ����ͼƬ urlString ͼƬURL filename ͼƬ���� Ҫ��.jpg savePath ����·��
 *
 */
public class DownloadPic {
	public void download(String urlString, String filename, String savePath) {
		try {
			// ����URL
			URL url = new URL("http:" + urlString);
			// ������
			URLConnection con = url.openConnection();
			// ��������ʱΪ5s
			con.setConnectTimeout(5 * 1000);
			// ������
			InputStream input = con.getInputStream();

			// 1K�����ݻ���
			byte[] bs = new byte[1024];
			int len;
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream out = new FileOutputStream(sf.getPath() + "\\" + filename + ".jpg");
			while ((len = input.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			out.close();
			input.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
