package model;

/**
 * @author zh
 * 
 * Ä£ÐÍ
 * 1.ID  2.name  3.price  4.evaluate  5.press  6.picturePath   7.shopURL
 *
 */
public class BookModel {
	private String ID;
	private String name;
	private String price;
	private String evaluate;
	private String press;
	private String picturePath;
	private String shopURL;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		this.ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getShopURL() {
		return shopURL;
	}
	public void setShopURL(String shopURL) {
		this.shopURL = shopURL;
	}
	
}
