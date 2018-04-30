package Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Customer;

public class CustomerDA {
	private Connection connection;

	public CustomerDA() {
		// TODO Auto-generated constructor stub
		try {
			connection = connectionUlti.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Customer> getCustomer() throws SQLException {
		List<Customer> list = new ArrayList<>();
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM `customer`";
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6));
			list.add(customer);
		}

		return list;
	}

	public void addCustomer(String name, String gender, java.sql.Date date, String adress, String phone) throws SQLException {
		String sql = "INSERT INTO `customer` (`customerId`, `name`, `Gender`, `DateOfBirth`, `adress`, `phoneNumber`) VALUES (NULL,?, ?, ?, ?,?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, gender);
		statement.setDate(3,  date);
		statement.setString(4, adress);
		statement.setString(5, phone);

		statement.executeUpdate();
	}
	
	public void deleteCustomer(int id) throws SQLException {
		String sql = "DELETE FROM `customer` WHERE `customer`.`customerId` = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql );
		preparedStatement.setInt(1, id);
		
		preparedStatement.executeUpdate();
	}
	
	public void editCusstomer(String name, String gender, Date date, String adress, String phone , int id) throws SQLException {
		String sql = "UPDATE `customer` SET `name` = ?, `Gender` = ?, `DateOfBirth` = ?, `adress` = ?, `phoneNumber` = ? WHERE `customer`.`customerId` = ?;";
		PreparedStatement statement = connection.prepareStatement(sql );
		statement.setString(1, name);
		statement.setString(2, gender);
		statement.setDate(3, date);
		statement.setString(4, adress);
		statement.setString(5, phone);
		statement.setInt(6, id);
		
		statement.executeUpdate();
	}
}
