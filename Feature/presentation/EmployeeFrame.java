package presentation;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import business.EmployeeB;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EmployeeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmployeeId;
	private JTextField txtDateOfBirth;
	private JTextField txtAdress;
	private JTextField txtPosition;
	private JTextField txtDateOfBegin;
	private JTextField txtImageUrl;
	private JTable table;
	private DefaultTableModel model;
	private EmployeeB employeeB;
	private JTextField txtGender;
	private JPanel panelEast;
	private JLabel lblImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeFrame frame = new EmployeeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	private void initImage(String path) {
		panelEast.remove(lblImage);
		panelEast.add(lblImage, "cell 0 0 2 5,grow");
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(250, 180, Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imageCell));
		panelEast.validate();
	}

	private void initTable() throws SQLException {
		model = employeeB.getEmployee();
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
	}

	public EmployeeFrame() {
		employeeB = new EmployeeB();

		pack();
		setTitle("Thông Tin Khách Hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel panelInfor = new JPanel();
		contentPane.add(panelInfor);
		panelInfor.setLayout(new BoxLayout(panelInfor, BoxLayout.X_AXIS));

		JPanel panelWest = new JPanel();
		panelWest.setBorder(new TitledBorder(null, "Animation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfor.add(panelWest);
		panelWest.setLayout(new MigLayout("", "[1px]", "[1px]"));

		JLabel lblNewLabel = new JLabel("");
		panelWest.add(lblNewLabel, "cell 0 0,alignx left,aligny top");
		Icon icon = new ImageIcon("Icon\\ezgif.com-resize.gif");
		lblNewLabel.setIcon(icon);

		JPanel panelCenter = new JPanel();
		panelCenter
				.setBorder(new TitledBorder(null, "Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfor.add(panelCenter);
		panelCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));

		JLabel lblEmployeeid = new JLabel("EmployeeId");
		panelCenter.add(lblEmployeeid, "cell 0 0,alignx right");

		txtEmployeeId = new JTextField();
		panelCenter.add(txtEmployeeId, "cell 1 0,growx");
		txtEmployeeId.setColumns(10);
		txtEmployeeId.setEditable(false);

		JLabel lblName = new JLabel("Name");
		panelCenter.add(lblName, "cell 0 1,alignx right");

		txtName = new JTextField();
		panelCenter.add(txtName, "cell 1 1,growx");
		txtName.setColumns(10);
		txtName.setEditable(false);

		JLabel lblGender = new JLabel("Gender");
		panelCenter.add(lblGender, "cell 0 2,alignx trailing");

		txtGender = new JTextField();
		txtGender.setEditable(false);
		panelCenter.add(txtGender, "cell 1 2,growx");
		txtGender.setColumns(10);

		JLabel lblDateofbirth = new JLabel("DateOfBirth");
		panelCenter.add(lblDateofbirth, "cell 0 3,alignx right");

		txtDateOfBirth = new JTextField();
		panelCenter.add(txtDateOfBirth, "cell 1 3,growx");
		txtDateOfBirth.setColumns(10);
		txtDateOfBirth.setEditable(false);
		;

		JLabel lblAdress = new JLabel("Adress");
		panelCenter.add(lblAdress, "cell 0 4,alignx right");

		txtAdress = new JTextField();
		panelCenter.add(txtAdress, "cell 1 4,growx");
		txtAdress.setColumns(10);
		txtAdress.setEditable(false);

		JLabel lblPosition = new JLabel("Position");
		panelCenter.add(lblPosition, "cell 0 5,alignx trailing");

		txtPosition = new JTextField();
		panelCenter.add(txtPosition, "cell 1 5,growx");
		txtPosition.setColumns(10);
		txtPosition.setEditable(false);

		JLabel lblDateofbegin = new JLabel("DateOfBegin");
		panelCenter.add(lblDateofbegin, "cell 0 6,alignx trailing");

		txtDateOfBegin = new JTextField();
		panelCenter.add(txtDateOfBegin, "cell 1 6,growx");
		txtDateOfBegin.setColumns(10);
		txtDateOfBegin.setEditable(false);

		panelEast = new JPanel();
		panelEast.setBorder(new TitledBorder(null, "Image", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfor.add(panelEast);
		panelEast.setLayout(new MigLayout("", "[][grow]", "[][][][][][]"));

		lblImage = new JLabel("");
		panelEast.add(lblImage, "cell 0 0 2 5,grow");
		initImage("icon\\BG.jpg");

		JLabel lblImageurl = new JLabel("ImageURL");
		panelEast.add(lblImageurl, "cell 0 5,alignx trailing");

		txtImageUrl = new JTextField();
		panelEast.add(txtImageUrl, "flowx,cell 1 5,growx");
		txtImageUrl.setColumns(10);
		txtImageUrl.setEditable(false);

		JButton btnOpen = new JButton("Open");
		panelEast.add(btnOpen, "cell 1 5");
		btnOpen.setIcon(new ImageIcon(loadImage("icon\\Open.png")));
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().open(new File(txtImageUrl.getText()));
				} catch (IOException | IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File not found", "Errors", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel panelTable = new JPanel();
		contentPane.add(panelTable);
		panelTable.setLayout(new BorderLayout(0, 0));

		JPanel panelButton = new JPanel();
		panelButton
				.setBorder(new TitledBorder(null, "Button Panel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.add(panelButton, BorderLayout.NORTH);

		JButton btnAdd = new JButton("Add");
		panelButton.add(btnAdd);
		btnAdd.setIcon(new ImageIcon(loadImage("icon\\add.png")));
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddEmployeeFrame frame = new AddEmployeeFrame(EmployeeFrame.this);
				frame.setVisible(true);
				if (frame.conditional()) {
					String name = frame.getTxtName().getText();
					String gender = frame.getGender();
					String dateOfBirth = frame.getBirthYear() + "-" + frame.getBirthMonth() + "-" + frame.getBirthDay();
					String adress = frame.getTxtAdress().getText();
					String position = frame.getComboBoxPosition().getSelectedItem().toString();
					String dateOfBegin = frame.getBeginYear() + "-" + frame.getBeginMonth() + "-" + frame.getBeginDay();
					String imageUrl = frame.getTxtImageURL().getText();

					try {
						employeeB.addNewEmployee(name, gender, dateOfBirth, adress, position, dateOfBegin, imageUrl);
						initTable();
						JOptionPane.showMessageDialog(null, "Thêm Thành Công", "Thông Báo",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		panelButton.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(loadImage("icon\\Delete.png")));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String id = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						File file = new File(employeeB.getImageUrl(id));
						// panelImage.remove(image);
						// employeeB.deleteItem(id);
						employeeB.deleteEmployee(id);
						initTable();
						initImage(employeeB.getImageUrl((String) table.getValueAt(0, 0)));
						boolean b = file.delete();
						if (b) {
							JOptionPane.showMessageDialog(null, "Xóa Thành công");
						} else {
							JOptionPane.showMessageDialog(null, "Vì 1 lí do nào đó mà dell xóa đc");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng để xóa", "Thông Báo",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JButton btnEdit = new JButton("Edit");
		panelButton.add(btnEdit);
		btnEdit.setIcon(new ImageIcon(loadImage("icon\\Edit.png")));
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddEmployeeFrame frame = new AddEmployeeFrame(EmployeeFrame.this);

					frame.getTxtName().setText(txtName.getText());
					frame.getTxtAdress().setText(txtAdress.getText());
					frame.getTxtImageURL().setText(txtImageUrl.getText());

					if (txtGender.getText().equals("Nam")) {
						frame.getRdbtnMale().setSelected(true);
					} else {
						frame.getRdbtnFemale().setSelected(true);
					}

					if (txtPosition.getText().equals("Quản Lí")) {
						frame.getComboBoxPosition().setSelectedItem("Quản Lí");
					} else {
						frame.getComboBoxPosition().setSelectedItem("Nhân Viên");
					}

					String date = txtDateOfBirth.getText().trim();
					int i = Integer.parseInt(date.substring(3, 5));

					frame.getComboBoxBirthDay().setSelectedItem(date.substring(0, 2));
					if (i > 9) {
						frame.getComboBoxBirthMonth().setSelectedItem("Tháng " + date.substring(3, 5));
					} else {
						frame.getComboBoxBirthMonth().setSelectedItem("Tháng " + date.substring(4, 5));
					}
					frame.getComboBoxBirthYear().setSelectedItem(date.substring(6, 10));

					date = txtDateOfBegin.getText().trim();
					i = Integer.parseInt(date.substring(3, 5));

					frame.getComboBoxBeginDay().setSelectedItem(date.substring(0, 2));
					if (i > 9) {
						frame.getComboBoxBeginMonth().setSelectedItem("Tháng " + date.substring(3, 5));
					} else {
						frame.getComboBoxBeginMonth().setSelectedItem("Tháng " + date.substring(4, 5));
					}
					frame.getComboBoxBeginYear().setSelectedItem(date.substring(6, 10));

					frame.setVisible(true);

					if (frame.conditional()) {
						String name = frame.getTxtName().getText();
						String gender = frame.getGender();
						String dateOfBirth = frame.getBirthYear() + "-" + frame.getBirthMonth() + "-"
								+ frame.getBirthDay();
						String adress = frame.getTxtAdress().getText();
						String position = frame.getComboBoxPosition().getSelectedItem().toString();
						String dateOfBegin = frame.getBeginYear() + "-" + frame.getBeginMonth() + "-"
								+ frame.getBeginDay();
						String imageUrl = frame.getTxtImageURL().getText();
						String id = txtEmployeeId.getText();

						try {
							employeeB.editEmployee(name, gender, dateOfBirth, adress, position, dateOfBegin, imageUrl,
									id);
							initTable();
							JOptionPane.showMessageDialog(null, "Chỉnh Sửa Thành Công", "Thông Báo",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException | ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				} catch (IndexOutOfBoundsException e1) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy Chọn Hàng", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		panelButton.add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(loadImage("icon\\Refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					initTable();
					initImage("icon\\BG.jpg");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnSearch = new JButton("Search");
		panelButton.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(loadImage("icon\\search-icon.png")));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String text = JOptionPane.showInputDialog(null, "Tìm cái giề?", "Find in table",
						JOptionPane.QUESTION_MESSAGE);
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
				try {

					table.setRowSorter(rowSorter);
					if (text.trim().length() == 0) {
						rowSorter.setRowFilter(null);
					} else {
						rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim().toLowerCase()));
					}
				} catch (NullPointerException e2) {
					// TODO: handle exception
//					JOptionPane.showMessageDialog(null, "Nhập Kí Tự Cần Tìm ", "Thông Báo", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JButton btnClose = new JButton("Close");
		panelButton.add(btnClose);
		btnClose.setIcon(new ImageIcon(loadImage("icon\\Close.png")));
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn thoát?", "Thông báo",
						JOptionPane.YES_NO_OPTION, JOptionPane.CLOSED_OPTION);
				if (value == 0) {
					dispose();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		panelTable.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			initTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					String id;
					try {
						id = (String) table.getValueAt(table.getSelectedRow(), 0);
					} catch (IndexOutOfBoundsException e2) {
						// TODO: handle exception
						id = (String) table.getValueAt(0, 0);
					}
					try {
						txtEmployeeId.setText((String) table.getValueAt(table.getSelectedRow(), 0));
						txtName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
						txtGender.setText((String) table.getValueAt(table.getSelectedRow(), 2));
						txtDateOfBirth.setText((String) table.getValueAt(table.getSelectedRow(), 3));
						txtAdress.setText((String) table.getValueAt(table.getSelectedRow(), 4));
						txtPosition.setText((String) table.getValueAt(table.getSelectedRow(), 5));
						txtDateOfBegin.setText((String) table.getValueAt(table.getSelectedRow(), 6));
					} catch (IndexOutOfBoundsException e1) {
						// TODO: handle exception
					}
					try {
						txtImageUrl.setText(employeeB.getImageUrl(id));
						initImage(employeeB.getImageUrl(id));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
	}

}
