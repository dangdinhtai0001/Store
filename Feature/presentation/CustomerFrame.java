package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import business.CustomerB;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtGender;
	private JTextField txtPhone;
	private JTextField txtAdress;
	private JTable table;
	private DefaultTableModel model;
	private CustomerB customerB;
	private JTextField txtDateOfBirth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
		// UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");

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
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	private void initModel() throws SQLException {
		model = customerB.getCustomer();
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
	}

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	public CustomerFrame() {
		customerB = new CustomerB();

		setTitle("Khách Hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelButton = new JPanel();
		contentPane.add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnAdd = new JButton("Add");
		panelButton.add(btnAdd);
		btnAdd.setIcon(new ImageIcon(loadImage("Icon\\add.png")));
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddCustomerFrame frame = new AddCustomerFrame(CustomerFrame.this);
				frame.setVisible(true);

				if (frame.conditional()) {
					String name = frame.getTxtName().getText();
					String gender = frame.getTxtGender().getText();
					String phone = frame.getTxtPhoneNumber().getText();
					String adress = frame.getTxtAdress().getText();
					String date = frame.comboBoxYear() + "-" + frame.comboBoxMonth() + "-" + frame.comboBoxDay();
					try {
						customerB.addNewCustomer(name, gender, date, adress, phone);
						initModel();
					} catch (ParseException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnDelete = new JButton("Delete");
		panelButton.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(loadImage("Icon\\Delete.png")));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String id = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						customerB.deleteItem(id);
						initModel();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();

					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy Chọn hàng để xóa");
				}
			}
		});

		JButton btnEdit = new JButton("Edit");
		panelButton.add(btnEdit);
		btnEdit.setIcon(new ImageIcon(loadImage("Icon\\Edit.png")));
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddCustomerFrame frame = new AddCustomerFrame(CustomerFrame.this);

						frame.getTxtName().setText((String) table.getValueAt(table.getSelectedRow(), 1));
						frame.getTxtGender().setText((String) table.getValueAt(table.getSelectedRow(), 2));
						frame.getTxtPhoneNumber().setText((String) table.getValueAt(table.getSelectedRow(), 4));
						frame.getTxtAdress().setText((String) table.getValueAt(table.getSelectedRow(), 5));

						String string = ((String) table.getValueAt(table.getSelectedRow(), 3)).trim();
						int i = Integer.parseInt(string.substring(3, 5));
						frame.getComboBoxDay().setSelectedItem(string.substring(0, 2));
						if (i > 9) {
							frame.getComboBoxMonth().setSelectedItem("Tháng " + string.substring(3, 5));
						} else {
							frame.getComboBoxMonth().setSelectedItem("Tháng " + string.substring(4, 5));
						}
						frame.getComboBoxYear().setSelectedItem(string.substring(6, 10));

						frame.setVisible(true);

						if (frame.conditional()) {
							String name = frame.getTxtName().getText();
							String gender = frame.getTxtGender().getText();
							String phone = frame.getTxtPhoneNumber().getText();
							String adress = frame.getTxtAdress().getText();
							String date = frame.comboBoxYear() + "-" + frame.comboBoxMonth() + "-"
									+ frame.comboBoxDay();
							String id = (String) table.getValueAt(table.getSelectedRow(), 0);
							try {
								customerB.editCustomer(name, gender, date, adress, phone, id);
								initModel();
								JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
							} catch (SQLException | ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					} catch (ArrayIndexOutOfBoundsException e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Hãy Chọn hàng để sửa");
					}
				
			}
		});

		JButton btnRefresh = new JButton("Refresh");
		panelButton.add(btnRefresh);
		btnRefresh.setIcon(new ImageIcon(loadImage("Icon\\Refresh.png")));
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					initModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnFind = new JButton("Find");
		panelButton.add(btnFind);
		btnFind.addActionListener(new ActionListener() {

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
		btnClose.setIcon(new ImageIcon(loadImage("Icon\\Close.png")));
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		JPanel panelTop = new JPanel();
		panelTop.setBorder(
				new TitledBorder(null, "Danh mục các khách hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new MigLayout("", "[][grow][][][grow][][][grow]", "[][]"));

		JLabel lblName = new JLabel("Tên Khách ");
		panelTop.add(lblName, "cell 0 0,alignx trailing");

		txtName = new JTextField();
		panelTop.add(txtName, "cell 1 0,growx");
		txtName.setColumns(10);

		JLabel lblGender = new JLabel("Giới Tính");
		panelTop.add(lblGender, "cell 3 0,alignx trailing");

		txtGender = new JTextField();
		panelTop.add(txtGender, "cell 4 0,growx");
		txtGender.setColumns(10);

		JLabel lblDateofbirth = new JLabel("Ngày Sinh");
		panelTop.add(lblDateofbirth, "cell 6 0,alignx trailing");

		txtDateOfBirth = new JTextField();
		panelTop.add(txtDateOfBirth, "cell 7 0,growx");
		txtDateOfBirth.setColumns(10);

		JLabel lblPhone = new JLabel("Liên Lạc");
		panelTop.add(lblPhone, "cell 0 1,alignx trailing");

		txtPhone = new JTextField();
		panelTop.add(txtPhone, "cell 1 1,growx");
		txtPhone.setColumns(10);

		JLabel lblAdress = new JLabel("Địa Chỉ");
		panelTop.add(lblAdress, "cell 3 1,alignx trailing");

		txtAdress = new JTextField();
		panelTop.add(txtAdress, "cell 4 1,growx");
		txtAdress.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			initModel();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting()) {
					try {
						txtName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
						txtGender.setText((String) table.getValueAt(table.getSelectedRow(), 2));
						txtDateOfBirth.setText((String) table.getValueAt(table.getSelectedRow(), 3));
						txtPhone.setText((String) table.getValueAt(table.getSelectedRow(), 4));
						txtAdress.setText((String) table.getValueAt(table.getSelectedRow(), 5));
					} catch (ArrayIndexOutOfBoundsException e2) {
						// TODO: handle exception
						txtName.setText((String) table.getValueAt(0, 1));
						txtGender.setText((String) table.getValueAt(0, 2));
						txtDateOfBirth.setText((String) table.getValueAt(0, 3));
						txtPhone.setText((String) table.getValueAt(0, 4));
						txtAdress.setText((String) table.getValueAt(0, 5));
					}
				}
			}
		});
		
	}

}
