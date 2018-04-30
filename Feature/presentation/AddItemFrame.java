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

public class AddItemFrame extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel(); 
	private boolean isOk;
	public JTextField txtName;
	public JTextField txtQuantity;
	public JTextField txtSell;
	public JTextField txtImport;
	public JTextField txtURL;
	public JTextField txtNote;

	private Image loadImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image imageCell = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

		return imageCell;
	}
	
	private boolean urlConditional(String url) {
		if (url == null) {
			return false;
		}
		if (url.trim().equals("")) {
			return false;
		}
		if (!(url.substring(url.trim().length() - 4, url.trim().length()).contains("jpg")
				&& url.trim().substring(url.length() - 4, url.trim().length()).contains("png")
				&& url.trim().substring(url.length() - 4, url.trim().length()).contains("jpeg"))) {
			return false;
		}
		return true;
	}
	public AddItemFrame(JFrame parents) {
		super(parents, "", true);
		isOk = false;

		setTitle("Thông Tin Sản Phẩm");
		setBounds(100, 100, 380, 280);
		setLocationRelativeTo(parents);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][]"));
		{
			JLabel lblNhpThngTin = new JLabel("Nhập Thông Tin Về Sản Phẩm");
			contentPanel.add(lblNhpThngTin, "cell 1 0,alignx center");
		}
		{
			JLabel lblName = new JLabel("Tên");
			contentPanel.add(lblName, "cell 0 1,alignx left");
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName, "cell 1 1,growx");
			txtName.setColumns(10);
		}
		{
			JLabel lblQuantity = new JLabel("Số Lượng");
			contentPanel.add(lblQuantity, "cell 0 2,alignx left");
		}
		{
			txtQuantity = new JTextField();
			contentPanel.add(txtQuantity, "cell 1 2,growx");
			txtQuantity.setColumns(10);
		}
		{
			JLabel lblSell = new JLabel("Giá Bán Ra");
			contentPanel.add(lblSell, "cell 0 3,alignx left");
		}
		{
			txtSell = new JTextField();
			contentPanel.add(txtSell, "cell 1 3,growx");
			txtSell.setColumns(10);
		}
		{
			JLabel lblImport = new JLabel("Giá Nhập Vào");
			contentPanel.add(lblImport, "cell 0 4,alignx left");
		}
		{
			txtImport = new JTextField();
			contentPanel.add(txtImport, "cell 1 4,growx");
			txtImport.setColumns(10);
		}
		{
			JLabel lblImageURL = new JLabel("URL Ảnh");
			contentPanel.add(lblImageURL, "cell 0 5,alignx left");
		}
		{
			txtURL = new JTextField();
			contentPanel.add(txtURL, "cell 1 5,growx");
			txtURL.setColumns(10);
		}
		{
			JButton btnOpen = new JButton("Mở");
			contentPanel.add(btnOpen, "cell 2 5");
			btnOpen.setIcon(new ImageIcon(loadImage("Icon\\Open.png")));
			btnOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					FileFilter fileFilter = new FileNameExtensionFilter( "Image files", ImageIO.getReaderFileSuffixes());
					chooser.setFileFilter(fileFilter);
					int value = chooser.showOpenDialog(null);
					if (value == JFileChooser.APPROVE_OPTION) {
						String filePath = chooser.getCurrentDirectory().toString() + "\\" + chooser.getSelectedFile().getName();
						txtURL.setText(filePath);
					}
				}
			});
		}
		{
			JLabel lblNote = new JLabel("Note");
			contentPanel.add(lblNote, "cell 0 6,alignx left");
		}
		{
			txtNote = new JTextField();
			contentPanel.add(txtNote, "cell 1 6,growx");
			txtNote.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.setIcon(new ImageIcon(loadImage("Icon\\Ok.png")));
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (txtName.getText().trim().equals("") || txtImport.getText().trim().equals("")
								|| txtQuantity.getText().trim().equals("") || txtSell.getText().trim().equals("")
								|| txtURL.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(null, "Nhập đầy đủ các trường");
						}else if (!urlConditional(txtURL.getText().trim())) {
							JOptionPane.showMessageDialog(null, "Chỉ cho phép định dạng jpg , png , jpeg");
						} else {
							isOk = true;
							dispose();
						}
					}
				});

			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setIcon(new ImageIcon(loadImage("Icon\\Cancel.png")));
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

}
