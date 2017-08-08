package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.JdModel;

public class MYSQLControl {

	PreparedStatement statement;

	public void executeInsert(List<JdModel> jingdongdata) throws SQLException {

		String url = "jdbc:mysql://127.0.0.1:3306/jd";
		String user = "root";
		String password = "zh633019";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("连接成功");
				
				
				//插入多条数据
				statement = conn
						.prepareStatement("insert into book (bookID, bookName ,bookPrice) values (?, ?, ?)");

				Object[][] params = new Object[jingdongdata.size()][3];
				for (int i = 0; i < params.length; i++) {
					params[i][0] = jingdongdata.get(i).getBookID();
					params[i][1] = jingdongdata.get(i).getBookName();
					params[i][2] = jingdongdata.get(i).getBookPrice();

					statement.clearParameters();
					statement.setString(1,  (String) params[i][0]);
					statement.setString(2,  (String) params[i][1]);
					statement.setString(3,  (String) params[i][2]);
					statement.execute();
				}
				System.out.println("插入成功,共插入:"+jingdongdata.size()+"条");
			}

		} catch (ClassNotFoundException e) {
			System.out.println("未能成功加载驱动程序，请检查是否导入驱动程序！");
			e.printStackTrace();
		}

	}
}