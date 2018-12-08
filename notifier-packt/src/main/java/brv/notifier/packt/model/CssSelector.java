package brv.notifier.packt.model;

import java.io.Serializable;

public class CssSelector implements Serializable {

	private static final long serialVersionUID = 8082154013556499941L;
	
	private String title;
	private String imageUrl;
	private String bookUrl;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getBookUrl() {
		return bookUrl;
	}
	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}
	
	
}
