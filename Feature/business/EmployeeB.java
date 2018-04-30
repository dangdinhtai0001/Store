package business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Data.EmployeeDA;
import entity.Employee;

public class EmployeeB {
	private EmployeeDA employeeDA;

	public EmployeeB() {
		// TODO Auto-generated constructor stub
		employeeDA = new EmployeeDA();
	}

	public DefaultTableModel getEmployee() throws SQLException {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Mã Nhân Viên");
		model.addColumn("Tên");
		model.addColumn("Giới Tính");
		model.addColumn("Ngày Sinh");
		model.addColumn("Địa Chỉ");
		model.addColumn("Vị Trí");
		model.addColumn("Ngày Bắt Đầu");
		try {
			List<Employee> list = employeeDA.getEmployee();

			for (Employee employee : list) {
				String row[] = new String[7];

				row[0] = String.valueOf(employee.getEmployeeId());
				row[1] = employee.getName();
				row[2] = employee.getGender();
				row[3] = formatDate(employee.getDateOfBirth());
				row[4] = employee.getAdress();
				row[5] = employee.getPosition();
				row[6] = formatDate(employee.getDateOfBegin());

				model.addRow(row);
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			// JOptionPane.showMessageDialog(null, "Database trống trơn????");

		}

		return model;
	}

	public void addNewEmployee(String name, String gender, String dateOfBirth, String adress, String position,
			String dateOfBegin, String url) throws SQLException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dateOfBirth);
		java.sql.Date sqlDateOfBirth = new java.sql.Date(date.getTime());
		Date date2 = format.parse(dateOfBegin);
		java.sql.Date sqlDateOfBegin = new java.sql.Date(date2.getTime());

		employeeDA.addNewEmployee(name, gender, sqlDateOfBirth, adress, position, sqlDateOfBegin, url);
	}

	public String getImageUrl(String id) throws SQLException {
		return employeeDA.getImageUrl(id);
	}

	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date);
	}

	public void deleteEmployee(String id) throws NumberFormatException, SQLException {
		employeeDA.deleteEmployee(Integer.parseInt(id));
	}

	public void editEmployee(String name, String gender, String dateOfBirth, String adress, String position,
			String dateOfBegin, String url, String id) throws ParseException, NumberFormatException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dateOfBirth);
		java.sql.Date sqlDateOfBirth = new java.sql.Date(date.getTime());
		Date date2 = format.parse(dateOfBegin);
		java.sql.Date sqlDateOfBegin = new java.sql.Date(date2.getTime());
		employeeDA.editEmployee(name, gender, sqlDateOfBirth, adress, position, sqlDateOfBegin, url , Integer.parseInt(id));
	}
}
