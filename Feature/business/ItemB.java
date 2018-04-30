package business;

import java.sql.SQLException;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Data.ItemDA;
import entity.Item;

public class ItemB {
	private ItemDA itemDA;

	public ItemB() {
		// TODO Auto-generated constructor stub
		itemDA = new ItemDA();
	}

	public DefaultTableModel getAllItem() throws SQLException {
		List<Item> items = itemDA.getAll();
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Mã Hàng");
		model.addColumn("Tên Hàng");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá nhập");
		model.addColumn("Đơn giá bán");

		for (Item item : items) {
			String[] row = new String[5];
			row[0] = String.valueOf(item.getItemId());
			row[1] = item.getName();
			row[2] = String.valueOf(item.getQuantity());
			row[3] = String.valueOf(item.getImportPrice());
			row[4] = String.valueOf(item.getSellPrice());
			model.addRow(row);
		}
		return model;
	}
	
	public String loadImageURL(String id) throws SQLException {
		return itemDA.loadImageURL(id);
	}
	
	public String loadNote(String id) throws SQLException {
		return itemDA.getNote(id);
	}
	
	public void addNewItem(String name, int quantity, int sell, int importPrice, String url, String note) throws SQLException {
		itemDA.addNewItem(name, quantity, sell, importPrice, url, note);
	}
	
	public void deleteItem(String id) throws SQLException {
		itemDA.deleteItem(Integer.parseInt(id));
	}
	
	public void editItem(int itemId,String name, int quantity, int sell, int importPrice, String url, String note) throws SQLException {
		itemDA.editItem(itemId, name, quantity, sell, importPrice, url, note);
	}
	
	public DefaultTableModel sortItem(String column) throws SQLException {
		List<Item> items = itemDA.sortItem(column);
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Mã Hàng");
		model.addColumn("Tên Hàng");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá nhập");
		model.addColumn("Đơn giá bán");

		for (Item item : items) {
			String[] row = new String[5];
			row[0] = String.valueOf(item.getItemId());
			row[1] = item.getName();
			row[2] = String.valueOf(item.getQuantity());
			row[3] = String.valueOf(item.getImportPrice());
			row[4] = String.valueOf(item.getSellPrice());
			model.addRow(row);
		}
		return model;
	}
	
	public DefaultTableModel sortItem(String column,String sequence) throws SQLException {
		List<Item> items = itemDA.sortItem(column);
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Mã Hàng");
		model.addColumn("Tên Hàng");
		model.addColumn("Số lượng");
		model.addColumn("Đơn giá nhập");
		model.addColumn("Đơn giá bán");

		for (Item item : items) {
			String[] row = new String[5];
			row[0] = String.valueOf(item.getItemId());
			row[1] = item.getName();
			row[2] = String.valueOf(item.getQuantity());
			row[3] = String.valueOf(item.getImportPrice());
			row[4] = String.valueOf(item.getSellPrice());
			model.addRow(row);
		}
		return model;
	}
	
}
