package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AddCustomerFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isOk;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtGender;
	private JTextField txtAdress;
	private JTextField txtPhoneNumber;
	private JComboBox<String> comboBoxDay;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;

	/**
	 * Launch the application.
	 */
	public AddCustomerFrame(JFrame parent) {
		super(parent, "", true);
		isOk = false;

		setBounds(100, 100, 435, 300);
		setTitle("Thông Tin Khách Hàng");
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Information",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][grow][grow][]", "[][][][][]"));
		{
			JLabel lblName = new JLabel("Tên Khách Hàng");
			contentPanel.add(lblName, "cell 0 0,alignx trailing");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 0,growx");
			txtName.setColumns(10);
		}
		{
			JLabel lblGender = new JLabel("Giới Tính");
			contentPanel.add(lblGender, "cell 0 1,alignx trailing");
		}
		{
			txtGender = new JTextField();
			contentPanel.add(txtGender, "cell 1 1,growx");
			txtGender.setColumns(10);
		}
		{
			JLabel lblDateOfBirth = new JLabel("Ngày Sinh");
			contentPanel.add(lblDateOfBirth, "cell 0 2,alignx trailing");
		}
		{
			comboBoxDay = new JComboBox<String>();
			contentPanel.add(comboBoxDay, "flowx,cell 1 2,growx");
			for (int i = 1; i <= 31; i++) {
				if (i < 10) {
					comboBoxDay.addItem("0" + String.valueOf(i));
				} else
					comboBoxDay.addItem(String.valueOf(i));
			}
		}
		{
			comboBoxMonth = new JComboBox<>();
			contentPanel.add(comboBoxMonth, "cell 1 2,growx");
			for (int i = 1; i <= 12; i++) {
				comboBoxMonth.addItem("Tháng " + String.valueOf(i));
			}
		}
		{
			comboBoxYear = new JComboBox<String>();
			contentPanel.add(comboBoxYear, "cell 1 2,growx");
			for (int i = java.time.LocalDate.now().getYear() - 80; i <= java.time.LocalDate.now().getYear(); i++) {
				comboBoxYear.addItem(String.valueOf(i));
			}
		}
		{
			JLabel lblAdress = new JLabel("Địa Chỉ");
			contentPanel.add(lblAdress, "cell 0 3,alignx trailing");
		}
		{
			txtAdress = new JTextField();
			contentPanel.add(txtAdress, "cell 1 3,growx");
			txtAdress.setColumns(10);
		}
		{
			JLabel lblPhoneNumber = new JLabel("Liên Lạc");
			contentPanel.add(lblPhoneNumber, "cell 0 4,alignx trailing");
		}
		{
			txtPhoneNumber = new JTextField();
			contentPanel.add(txtPhoneNumber, "cell 1 4,growx");
			txtPhoneNumber.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (txtAdress.getText().trim().equals("") || txtGender.getText().trim().equals("")
								|| txtName.getText().trim().equals("") || txtPhoneNumber.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Nhập Đầy Đủ các trường");

						} else {
							if (!dateConditional()) {
								JOptionPane.showMessageDialog(null, "SAI ĐỊNH DẠNG NGÀY THÁNG");
							} else {
								isOk = true;
								dispose();
							}
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						isOk = false;
						dispose();
					}
				});
			}
		}
	}

	public boolean conditional() {
		return isOk;
	}

	private boolean dateConditional() {
		if (comboBoxDay.getSelectedItem() == null || comboBoxMonth.getSelectedItem() == null
				|| comboBoxYear.getSelectedItem() == null) {
			return false;
		}
		int year = Integer.parseInt(comboBoxYear.getSelectedItem().toString());
		int month = Integer.parseInt(comboBoxMonth.getSelectedItem().toString().substring(6));
		int day = Integer.parseInt(comboBoxDay.getSelectedItem().toString());

		if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
			if (day > 30) {
				return false;
			}
		}

		if (month == 2 && !((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))) {
			if (day > 28) {
				return false;
			}
		}
		return true;

	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtGender() {
		return txtGender;
	}

	public JTextField getTxtAdress() {
		return txtAdress;
	}

	public JTextField getTxtPhoneNumber() {
		return txtPhoneNumber;
	}

	public String comboBoxDay() {
		return comboBoxDay.getSelectedItem().toString();
	}

	public String comboBoxMonth() {
		return comboBoxMonth.getSelectedItem().toString().substring(6);
	}

	public String comboBoxYear() {
		return comboBoxYear.getSelectedItem().toString();
	}

	public JComboBox<String> getComboBoxDay() {
		return comboBoxDay;
	}

	public JComboBox<String> getComboBoxMonth() {
		return comboBoxMonth;
	}

	public JComboBox<String> getComboBoxYear() {
		return comboBoxYear;
	}

}
