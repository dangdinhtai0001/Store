package business;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Data.CustomerDA;
import entity.Customer;

public class CustomerB {
	private CustomerDA customerDA;

	public CustomerB() {
		// TODO Auto-generated constructor stub
		customerDA = new CustomerDA();
	}
	
	public DefaultTableModel getCustomer() throws SQLException {
		List<Customer> list = customerDA.getCustomer();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Mã Khách Hàng");
		model.addColumn("Tên Khách Hàng");
		model.addColumn("Giới Tính");
		model.addColumn("Ngày Sinh");
		model.addColumn("Liên Lạc");
		model.addColumn("Địa Chỉ");
		
		for (Customer customer : list) {
			String[] row = new String[6];
			
			row[0] = String.valueOf(customer.getCustomerId());
			row[1] = customer.getName();
			row[2] = customer.getGender();
			row[3] = formatDate(customer.getDateOfBirth());
			row[4] = customer.getPhoneNumber();
			row[5] = customer.getAdress();
			
			model.addRow(row);
		}
		return model;
	}
	
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(date);
	}
	
	public void addNewCustomer(String name, String gender, String date, String adress, String phone) throws ParseException, SQLException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format.parse(date);
		java.sql.Date sql = new java.sql.Date(date2.getTime());
		customerDA.addCustomer(name, gender, sql, adress, phone);
	}

	public void deleteItem(String id) throws NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		customerDA.deleteCustomer(Integer.parseInt(id));
	}

	public void editCustomer(String name, String gender, String date, String adress, String phone,String id) throws ParseException, NumberFormatException, SQLException {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = format.parse(date);
		java.sql.Date sql = new java.sql.Date(date2.getTime());
		customerDA.editCusstomer(name, gender, sql, adress, phone, Integer.parseInt(id));
	}
}
