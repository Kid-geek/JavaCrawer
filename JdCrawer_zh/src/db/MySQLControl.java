package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.Main;
import model.BookModel;

public class MySQLControl {
	private static final Logger LOGGER = LoggerFactory.getLogger(MySQLControl.class);
	PreparedStatement statement;

	public void executeInsert(List<BookModel> data) {

		String url = "jdbc:mysql://127.0.0.1:3306/jd";
		String user = "root";
		String password = "zh633019";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed()) {
				System.out.println("���ӳɹ�");
				
				//����������ͻ  ������ظ�����
				statement = conn.prepareStatement(
						"insert ignore into book (id, name ,price ,evaluate ,press ,picture ,shopURL) values (?, ?, ?, ?, ?, ?, ?)");

				Object[][] params=new Object[data.size()][7];
				for (int i = 0; i < params.length; i++) {
					params[i][0] = data.get(i).getID();
					params[i][1] = data.get(i).getName();
					params[i][2] = data.get(i).getPrice();
					params[i][3] = data.get(i).getEvaluate();
					params[i][4] = data.get(i).getPress();
					params[i][5] = data.get(i).getPicturePath();
					params[i][6] = data.get(i).getShopURL();

					statement.clearParameters();
					statement.setString(1, (String) params[i][0]);
					statement.setString(2, (String) params[i][1]);
					statement.setString(3, (String) params[i][2]);
					statement.setString(4, (String) params[i][3]);
					statement.setString(5, (String) params[i][4]);
					statement.setString(6, (String) params[i][5]);
					statement.setString(7, (String) params[i][6]);
					statement.execute();
				}
				System.out.println("����ɹ�,������:" + data.size() + "��");
				
			}

		} catch (ClassNotFoundException e) {
			LOGGER.error("δ�ܳɹ������������������Ƿ�����������");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
