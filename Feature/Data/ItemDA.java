package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Item;

public class ItemDA {
	private Connection connection;

	public ItemDA() {
		// TODO Auto-generated constructor stub
		try {
			connection = connectionUlti.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Item> getAll() throws SQLException {
		List<Item> list = new ArrayList<>();

		Statement statement = connection.createStatement();
		String sql = "Select * from item ORDER BY `item`.`itemId` DESC";
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
			list.add(item);
		}

		return list;
	}

	public void addNewItem(String name, int quantity, int sell, int importPrice, String url, String note)
			throws SQLException {
		String sql = "INSERT INTO `item` (`itemId`, `Name`, `quantity`, `sellPrice`, `importPrice`, `imageItemURL`, `note`) VALUES (NULL, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setInt(2, quantity);
		statement.setInt(3, sell);
		statement.setInt(4, importPrice);
		statement.setString(5, url);
		statement.setString(6, note);

		statement.executeUpdate();
	}

	public void deleteItem(int id) throws SQLException {
		String sql = "DELETE FROM `item` WHERE `item`.`itemId` = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}
	
	public void editItem(int itemId,String name, int quantity, int sell, int importPrice, String url, String note) throws SQLException {
		String sql = "UPDATE `item` SET `Name` = ?, `quantity` = ?, `sellPrice` = ?, `importPrice` = ?, imageItemURL = ? , note = ?  WHERE `item`.`itemId` = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setInt(2, quantity);
		statement.setInt(3, sell);
		statement.setInt(4, importPrice);
		statement.setString(5, url);
		statement.setString(6, note);
		statement.setInt(7, itemId);
		
		statement.executeUpdate();
	}
	
	public List<Item> sortItem(String column) throws SQLException {
		List<Item> list = new ArrayList<>();

		String sql = "Select * from item ORDER BY "+column+ " DESC";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
			list.add(item);
		}

		return list;
	}
	
	public List<Item> sortItem(String column, String sequence) throws SQLException {
		List<Item> list = new ArrayList<>();

		String sql = "Select * from item ORDER BY "+column+ " "+ sequence;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Item item = new Item(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4),
					resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
			list.add(item);
		}

		return list;
	}

	public String loadImageURL(String id) throws SQLException {
		String sql = "SELECT item.imageItemURL FROM item WHERE item.itemId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();
		String url = "";
		while (resultSet.next()) {
			url = resultSet.getString(1);
		}
		return url;
	}

	public String getNote(String id) throws SQLException {
		String sql = "SELECT item.note FROM item WHERE item.itemId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id); 
		ResultSet resultSet = statement.executeQuery();
		String note = "";
		while (resultSet.next()) {
			note = resultSet.getString(1);
		}
		if (note == null) {
			return "Không có ghi chú";
		}
		return note;
	}

}
