package EpidemicSimulationView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public abstract class MenuPanel extends JPanel {
	private BufferedImage background;
	
	public void setBackground() {
		try {
			this.background = ImageIO.read(new File("Images/logo_evolved3.jpg"));
		} catch (IOException e) {
			System.out.println("Failed to read background image");
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}

}
