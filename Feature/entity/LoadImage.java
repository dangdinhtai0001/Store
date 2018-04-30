package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class LoadImage extends JComponent {
	private static final long serialVersionUID = 1L;
	private String path;
	private BufferedImage bufferedImage;

	public LoadImage(String path, int width, int height) {
		// TODO Auto-generated constructor stub
		this.path = path;
		this.setSize(width, height);
		this.setBorder(new LineBorder(Color.BLACK));
		try {
			bufferedImage = ImageIO.read(new File(this.path));
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LoadImage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
