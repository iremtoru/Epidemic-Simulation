package EpidemicSimulationView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import EpidemicSimulationController.MenuController;

public class MenuButton extends JButton {
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 45;
	private static final Font font = new Font("Comic Sans MS", Font.BOLD, 17);
	private static final ImageIcon buttonBackground = ImageResizer.getScaledImage(new ImageIcon("Images/buttonBackground.png"),
			BUTTON_WIDTH, BUTTON_HEIGHT);

	public MenuButton(int xLoc, int yLoc, String text,ActionListener listener) {
		super(buttonBackground);
		setText(text);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		addActionListener(listener);
		setForeground(Color.red);
		setFont(font);
		setLocation(xLoc, yLoc);
		setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		this.setVisible(true);
	}
	
	public MenuButton(int xLoc, int yLoc, String text, JComponent component, MainView view) {
		super(buttonBackground);
		setText(text);
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
		setForeground(Color.red);
		setFont(font);
		setLocation(xLoc, yLoc);
		setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		component.add(this);
		this.setVisible(true);
	}

}
