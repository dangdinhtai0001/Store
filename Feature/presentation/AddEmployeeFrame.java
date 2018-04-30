package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class AddEmployeeFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	// private final BackGround contentPanel = new BackGround("icon\\BG.jpg");
	private JTextField txtName;
	private JTextField txtAdress;
	private JTextField txtImageURL;
	private boolean isOk;
	private JComboBox<String> comboBoxBeginYear;
	private JComboBox<String> comboBoxBeginMonth;
	private JComboBox<String> comboBoxBeginDay;
	private JComboBox<String> comboBoxBirthYear;
	private JComboBox<String> comboBoxBirthMonth;
	private JComboBox<String> comboBoxBirthDay;
	private JRadioButton rdbtnMale;
	private JRadioButton rdbtnFemale;
	private JComboBox<String> comboBoxPosition;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	public AddEmployeeFrame(JFrame parents) {
		super(parents, "", true);

		isOk = false;
		setTitle("Thông Tin Nhân Viên");
		setBounds(100, 100, 400, 640);
		setLocationRelativeTo(parents);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][][100px:n,fill]"));
		{
			JLabel lblName = new JLabel("Tên Nhân Viên");
			contentPanel.add(lblName, "cell 0 1,alignx trailing");
		}
		{
			JLabel lblGender = new JLabel("Giới Tính");
			contentPanel.add(lblGender, "cell 0 2,alignx right");
		}
		{
			rdbtnMale = new JRadioButton("Nam");
			contentPanel.add(rdbtnMale, "flowx,cell 1 2,growx");
			rdbtnMale.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (rdbtnMale.isSelected()) {
						rdbtnFemale.setSelected(false);
					}
				}
			});
		}
		{
			JLabel lblDateofbirth = new JLabel("Ngày Sinh");
			contentPanel.add(lblDateofbirth, "cell 0 3,alignx trailing");
		}
		{
			JLabel lblPosition = new JLabel("Vị Trí");
			contentPanel.add(lblPosition, "cell 0 4,alignx trailing");
		}
		{
			comboBoxPosition = new JComboBox<String>();
			contentPanel.add(comboBoxPosition, "cell 1 4,growx");
			comboBoxPosition.addItem("Nhân Viên");
			comboBoxPosition.addItem("Quản Lí");
		}
		{
			JLabel lblDateofbegin = new JLabel("Ngày Làm Việc");
			contentPanel.add(lblDateofbegin, "cell 0 5,alignx trailing");
		}
		{
			JLabel lblAdress = new JLabel("Địa Chỉ");
			contentPanel.add(lblAdress, "cell 0 6,alignx trailing");
		}
		{
			txtAdress = new JTextField();
			contentPanel.add(txtAdress, "cell 1 6,growx");
			txtAdress.setColumns(10);
		}
		{
			JLabel lblImageurl = new JLabel("URL Ảnh");
			contentPanel.add(lblImageurl, "cell 0 7,alignx trailing");
		}
		{
			{
				txtImageURL = new JTextField();
				contentPanel.add(txtImageURL, "flowx,cell 1 7,growx");
				txtImageURL.setColumns(10);
			}
		}
		{
			rdbtnFemale = new JRadioButton("Nữ");
			contentPanel.add(rdbtnFemale, "cell 1 2,growx");
			rdbtnFemale.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (rdbtnFemale.isSelected()) {
						rdbtnMale.setSelected(false);
					}
				}
			});
		}
		{
			comboBoxBirthDay = new JComboBox<String>();
			contentPanel.add(comboBoxBirthDay, "flowx,cell 1 3,growx");
			for (int i = 1; i <= 31; i++) {
				if (i < 10) {
					comboBoxBirthDay.addItem("0" + String.valueOf(i));
				} else
					comboBoxBirthDay.addItem(String.valueOf(i));
			}
		}
		{
			comboBoxBirthMonth = new JComboBox<String>();
			contentPanel.add(comboBoxBirthMonth, "cell 1 3,growx");
			for (int i = 1; i <= 12; i++) {
				comboBoxBirthMonth.addItem("Tháng " + String.valueOf(i));
			}
		}
		{
			comboBoxBirthYear = new JComboBox<String>();
			contentPanel.add(comboBoxBirthYear, "cell 1 3,growx");
			for (int i = java.time.LocalDate.now().getYear() - 80; i <= java.time.LocalDate.now().getYear(); i++) {
				comboBoxBirthYear.addItem(String.valueOf(i));
			}
		}
		JButton btnOpen = new JButton("Mở");
		contentPanel.add(btnOpen, "cell 1 7");
		btnOpen.setIcon(new ImageIcon(loadImage("Icon\\Open.jpg")));
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileFilter fileFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
				chooser.setFileFilter(fileFilter);
				int value = chooser.showOpenDialog(null);
				if (value == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getCurrentDirectory().toString() + "\\"
							+ chooser.getSelectedFile().getName();
					txtImageURL.setText(filePath);
				}
			}
		});
		{
			comboBoxBeginDay = new JComboBox<String>();
			contentPanel.add(comboBoxBeginDay, "flowx,cell 1 5,growx");
			for (int i = 1; i <= 31; i++) {
				if (i < 10) {
					comboBoxBeginDay.addItem("0" + String.valueOf(i));
				} else
					comboBoxBeginDay.addItem(String.valueOf(i));
			}
		}
		{
			comboBoxBeginMonth = new JComboBox<String>();
			contentPanel.add(comboBoxBeginMonth, "cell 1 5,growx");
			for (int i = 1; i <= 12; i++) {
				comboBoxBeginMonth.addItem("Tháng " + String.valueOf(i));
			}
		}
		{
			comboBoxBeginYear = new JComboBox<String>();
			contentPanel.add(comboBoxBeginYear, "cell 1 5,growx");
			for (int i = java.time.LocalDate.now().getYear() - 80; i <= java.time.LocalDate.now().getYear(); i++) {
				comboBoxBeginYear.addItem(String.valueOf(i));
			}
		}
		{
			JLabel lblDance = new JLabel("");
			contentPanel.add(lblDance, "flowx,cell 1 9");
			ImageIcon icon = new ImageIcon("Icon\\AnimeGirlDance.gif");

			lblDance.setIcon(icon);
		}

		{
			JLabel lblTop = new JLabel("");
			contentPanel.add(lblTop, "flowx,cell 0 0 2 1,alignx center,aligny center");
			ImageIcon icon = new ImageIcon("Icon\\loading_12.gif");
//			icon.setImage(icon.getImage().getScaledInstance(120, 120, Image.SCALE_AREA_AVERAGING));
			lblTop.setIcon(icon);
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
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
						if (txtAdress.getText().trim().equals("") || txtImageURL.getText().trim().equals("")
								|| txtName.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Nhập đầy đủ các trường","Thông Báo",JOptionPane.WARNING_MESSAGE);
						} else {
							if (!dateConditional(comboBoxBeginDay, comboBoxBeginMonth, comboBoxBeginYear)) {
								JOptionPane.showMessageDialog(null, "Ngày bắt đầu Không hợp lí","Lỗi",JOptionPane.ERROR_MESSAGE);
							} else if (!dateConditional(comboBoxBirthDay, comboBoxBirthMonth, comboBoxBirthYear)) {
								JOptionPane.showMessageDialog(null, "Ngày Sinh Không hợp lí","Lỗi",JOptionPane.ERROR_MESSAGE);
							} else if (!urlConditional(txtImageURL.getText())) {
								JOptionPane.showMessageDialog(null, "Chỉ chấp nhận định dạng jpg, jpeg,png","Lỗi",JOptionPane.ERROR_MESSAGE);
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

	private boolean dateConditional(JComboBox<String> comboBoxDay, JComboBox<String> comboBoxMonth,
			JComboBox<String> comboBoxYear) {
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

	private boolean urlConditional(String url) {
		if (url == null) {
			return false;
		}
		if (url.trim().equals("")) {
			return false;
		}
		if (!(url.substring(url.trim().length() - 4, url.trim().length()).contains("jpg")
				|| url.trim().substring(url.length() - 4, url.trim().length()).contains("png")
				|| url.trim().substring(url.length() - 4, url.trim().length()).contains("jpeg"))) {
			return false;
		}
		return true;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtAdress() {
		return txtAdress;
	}

	public JTextField getTxtImageURL() {
		return txtImageURL;
	}

	public JComboBox<String> getComboBoxBeginYear() {
		return comboBoxBeginYear;
	}
	
	public String getBeginYear() {
		return comboBoxBeginYear.getSelectedItem().toString();
	}

	public JComboBox<String> getComboBoxBeginMonth() {
		return comboBoxBeginMonth;
	}
	
	public String getBeginMonth() {
		return comboBoxBeginMonth.getSelectedItem().toString().substring(6);
	}

	public JComboBox<String> getComboBoxBeginDay() {
		return comboBoxBeginDay;
	}
	
	public String getBeginDay() {
		return comboBoxBeginDay.getSelectedItem().toString();
	}

	public JComboBox<String> getComboBoxBirthYear() {
		return comboBoxBirthYear;
	}
	
	public String getBirthYear() {
		return comboBoxBirthYear.getSelectedItem().toString();
	}

	public JComboBox<String> getComboBoxBirthMonth() {
		return comboBoxBirthMonth;
	}
	
	public String getBirthMonth() {
		return comboBoxBirthMonth.getSelectedItem().toString().substring(6);
	}

	public JComboBox<String> getComboBoxBirthDay() {
		return comboBoxBirthDay;
	}
	
	public String getBirthDay() {
		return comboBoxBirthDay.getSelectedItem().toString();
	}

	public String getGender() {
		if (rdbtnFemale.isSelected()) {
			return "Nữ";
		}else
			return "Nam";
	}

	public JComboBox<String> getComboBoxPosition() {
		return comboBoxPosition;
	}

	public JRadioButton getRdbtnMale() {
		return rdbtnMale;
	}

	public JRadioButton getRdbtnFemale() {
		return rdbtnFemale;
	}


	
	
}
