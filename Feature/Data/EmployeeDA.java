package Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;

public class EmployeeDA {
	private Connection connection;

	public EmployeeDA() {
		// TODO Auto-generated constructor stub
		try {
			connection = connectionUlti.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Employee> getEmployee() throws SQLException {
		List<Employee> list = new ArrayList<>();
		Statement statement = connection.createStatement();
		String sql = "SELECT * FROM `employee` order by employeeid DESC";
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			Employee employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getDate(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7),
					resultSet.getString(8));
			list.add(employee);
		}
		return list;
	}

	public void addNewEmployee(String name, String gender, Date dateOfBirth, String adress, String position,
			Date dateOfBegin, String url) throws SQLException {
		String sql = "INSERT INTO `employee` (`employeeId`, `name`, `gender`, `DateOfBirth`, `adress`, `Position`, `DateOfBegin`, `EmployeeImageURL`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, gender);
		statement.setDate(3, dateOfBirth);
		statement.setString(4, adress);
		statement.setString(5, position);
		statement.setDate(6, dateOfBegin);
		statement.setString(7, url);

		statement.executeUpdate();
	}

	public String getImageUrl(String id) throws SQLException {
		String sql = "SELECT employee.EmployeeImageURL FROM employee WHERE employee.employeeId = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();
		String url = "";
		while (resultSet.next()) {
			url = resultSet.getString(1);
		}
		return url;
	}

	public void deleteEmployee(int id) throws SQLException {
		String sql = "DELETE FROM `employee` WHERE `employeeId` = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.executeUpdate();
	}

	public void editEmployee(String name, String gender, Date sqlDateOfBirth, String adress, String position,
			Date sqlDateOfBegin, String url, int id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE `employee` SET `name` = ?, `gender` = ?, `DateOfBirth` = ?, `adress` = ?, `Position` = ?, `DateOfBegin` = ?, `EmployeeImageURL` = ? WHERE `employee`.`employeeId` = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setString(2, gender);
		statement.setDate(3, sqlDateOfBirth);
		statement.setString(4, adress);
		statement.setString(5, position);
		statement.setDate(6, sqlDateOfBegin);
		statement.setString(7, url);
		statement.setInt(8, id);

		statement.executeUpdate();

	}
}
