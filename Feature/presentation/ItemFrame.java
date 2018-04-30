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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import business.ItemB;
import entity.LoadImage;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class ItemFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private ItemB itemB;
	private JTextField txtNote;
	private JTextField txtImage;
	private JPanel panelImage;
	private LoadImage image;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// here you can put the selected theme class name in JTattoo
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ItemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ItemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ItemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ItemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		// System.setProperty("Quaqua.tabLayoutPolicy", "wrap");
		// UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemFrame frame = new ItemFrame();
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

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}

	private void initTable() throws SQLException {
		model = itemB.getAllItem();
		table.setModel(model);
		table.setDefaultEditor(Object.class, null);
		table.setAutoCreateRowSorter(true);
	}

	private void initImage(String path) {
		panelImage.remove(image);
		this.image = new LoadImage(path, 0, 0);
		panelImage.add(image, BorderLayout.CENTER);
		panelImage.validate();
	}

	public ItemFrame() {
		itemB = new ItemB();

		setTitle("Sản Phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelButton = new JPanel();
		contentPane.add(panelButton, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Thêm");
		panelButton.add(btnAdd);
		btnAdd.setIcon(new ImageIcon(loadImage("Icon\\add.png")));
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddItemFrame frame = new AddItemFrame(ItemFrame.this);
				frame.setVisible(true);
				if (frame.conditional()) {
					String name = frame.txtName.getText();
					String quantity = frame.txtQuantity.getText();
					String sell = frame.txtSell.getText();
					String importPrice = frame.txtImport.getText();
					String url = frame.txtURL.getText();
					String note = frame.txtNote.getText();
					try {
						itemB.addNewItem(name, Integer.parseInt(quantity), Integer.parseInt(sell),
								Integer.parseInt(importPrice), url, note);
						initTable();
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnDelete = new JButton("Xóa");
		panelButton.add(btnDelete);
		btnDelete.setIcon(new ImageIcon(loadImage("Icon\\Delete.png")));
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String id = (String) table.getValueAt(table.getSelectedRow(), 0);
					try {
						File file = new File(itemB.loadImageURL(id));
						panelImage.remove(image);
						itemB.deleteItem(id);
						initTable();
						boolean b =file.delete();
						if (b) {
							JOptionPane.showMessageDialog(null, "Xóa Thành công");
						}else {
							JOptionPane.showMessageDialog(null, "Vì 1 lí do nào đó mà dell xóa đc");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng để xóa");
				}
			}
		});

		JButton btnEdit = new JButton("Sửa");
		panelButton.add(btnEdit);
		btnEdit.setIcon(new ImageIcon(loadImage("Icon\\Edit.png")));
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AddItemFrame frame = new AddItemFrame(ItemFrame.this);
					String id = (String) table.getValueAt(table.getSelectedRow(), 0);

					frame.txtName.setText((String) table.getValueAt(table.getSelectedRow(), 1));
					frame.txtQuantity.setText((String) table.getValueAt(table.getSelectedRow(), 2));
					frame.txtSell.setText((String) table.getValueAt(table.getSelectedRow(), 3));
					frame.txtImport.setText((String) table.getValueAt(table.getSelectedRow(), 4));
					try {
						frame.txtURL.setText(itemB.loadImageURL(id));
						frame.txtNote.setText(itemB.loadNote(id));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					frame.setVisible(true);

					if (frame.conditional()) {
						String name = frame.txtName.getText();
						String quantity = frame.txtQuantity.getText();
						String sell = frame.txtSell.getText();
						String importPrice = frame.txtImport.getText();
						String url = frame.txtURL.getText();
						String note = frame.txtNote.getText();
						try {
							itemB.editItem(Integer.parseInt(id), name, Integer.parseInt(quantity),
									Integer.parseInt(sell), Integer.parseInt(importPrice), url, note);
							initTable();
						} catch (NumberFormatException | SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Nhập số đàng hoàng đêiiii");
						}
					}

				} catch (ArrayIndexOutOfBoundsException e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Hãy chọn hàng");
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
					initTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnSearch = new JButton("Tìm Kiếm");
		panelButton.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(loadImage("Icon\\search-icon.png")));
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

		JComboBox<String> sortBy = new JComboBox<String>();
		panelButton.add(sortBy);
		sortBy.addItem("Tên");
		sortBy.addItem("Số Lượng");
		sortBy.addItem("Đơn Giá Bán");
		sortBy.addItem("Đơn Giá Nhập");
		sortBy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (sortBy.getSelectedItem().toString().equals("Tên")) {
						table.setModel(itemB.sortItem("Name"));
					}
					if (sortBy.getSelectedItem().toString().equals("Số Lượng")) {
						table.setModel(itemB.sortItem("quantity"));
					}
					if (sortBy.getSelectedItem().toString().equals("Đơn Giá Bán")) {
						table.setModel(itemB.sortItem("sellPrice"));
					}
					if (sortBy.getSelectedItem().toString().equals("Đơn Giá Nhập")) {
						table.setModel(itemB.sortItem("importPrice"));
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnExit = new JButton("Đóng");
		panelButton.add(btnExit);
		btnExit.setIcon(new ImageIcon(loadImage("Icon\\Close.png")));
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int value =JOptionPane.showConfirmDialog(null, "Do You Really Want To Exit?");
				if (value ==0) {
					dispose();
				}
			}
		});

		JPanel panelInfor = new JPanel();
		contentPane.add(panelInfor, BorderLayout.WEST);
		panelInfor.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panelInfor.add(scrollPane, BorderLayout.CENTER);

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
					} catch (ArrayIndexOutOfBoundsException e2) {
						// TODO: handle exception
						id = (String) table.getValueAt(table.getSelectedRow() + 1, 0);
					}
					try {
						// System.out.println(itemB.loadImageURL(id));
						initImage(itemB.loadImageURL(id));
						txtNote.setText(itemB.loadNote(id));
						txtImage.setText(itemB.loadImageURL(id));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
				}
			}
		});

		panelImage = new JPanel();
		panelImage
				.setBorder(new TitledBorder(null, "Ảnh Sản Phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelImage, BorderLayout.CENTER);
		panelImage.setLayout(new BorderLayout(0, 0));
		image = new LoadImage();
		panelImage.add(image, BorderLayout.CENTER);
		initImage("itemImage\\Default.jpg");

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Notes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfor.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[37px][46px,grow][]", "[14px][][]"));

		JLabel lblNote = new JLabel("Ghi Chú");
		panel.add(lblNote, "cell 0 0,alignx trailing,aligny top");

		txtNote = new JTextField();
		panel.add(txtNote, "cell 1 0,growx");
		txtNote.setColumns(10);

		JLabel lblURL = new JLabel("Ảnh");
		panel.add(lblURL, "cell 0 2,alignx trailing,aligny top");

		txtImage = new JTextField();
		panel.add(txtImage, "cell 1 2,growx");
		txtImage.setColumns(10);

		JButton btnOpen = new JButton("Open");
		panel.add(btnOpen, "cell 2 2");
		btnOpen.setIcon(new ImageIcon(loadImage("Icon\\Open.png")));
		btnOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Desktop.getDesktop().open(new File(txtImage.getText()));
				} catch (IOException | IllegalArgumentException er) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "File not found");
				}
			}
		});
	}

}
