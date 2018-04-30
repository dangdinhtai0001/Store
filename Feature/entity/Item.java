package entity;

public class Item {
	private int itemId, quantity, sellPrice, importPrice;
	private String name, imageItemURL, note;

	public Item(int itemId, String name, int quantity, int sellPrice, int importPrice, String imageItemURL,
			String note) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
		this.importPrice = importPrice;
		this.name = name;
		this.imageItemURL = imageItemURL;
		this.note = note;
	}

	public Item() {
		super();
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getImportPrice() {
		return importPrice;
	}

	public void setImportPrice(int importPrice) {
		this.importPrice = importPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageItemURL() {
		return imageItemURL;
	}

	public void setImageItemURL(String imageItemURL) {
		this.imageItemURL = imageItemURL;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", quantity=" + quantity + ", sellPrice=" + sellPrice + ", importPrice="
				+ importPrice + ", name=" + name + ", imageItemURL=" + imageItemURL + ", note=" + note + "]";
	}

}
