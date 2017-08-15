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
 *         下载图片 urlString 图片URL filename 图片名称 要加.jpg savePath 保存路径
 *
 */
public class DownloadPic {
	public void download(String urlString, String filename, String savePath) {
		try {
			// 构造URL
			URL url = new URL("http:" + urlString);
			// 打开链接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream input = con.getInputStream();

			// 1K的数据缓冲
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
