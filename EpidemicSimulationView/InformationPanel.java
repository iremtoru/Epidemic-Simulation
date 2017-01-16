package EpidemicSimulationView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import EpidemicSimulationController.MenuController;

public class InformationPanel extends MenuPanel {
	MenuButton backButton;
	MenuController controller;

	public InformationPanel(MenuController controller) {
		super();
		this.controller = controller;
		this.setLayout(null);
		this.setBackground();
		initialize();
	}

	public void initialize(){
		Color titleColor = new Color(243, 247, 118);
		Color textColor = new Color(252, 212, 131);
		Font titleFont = new Font("SANS_SERIF", Font.BOLD + Font.ITALIC, 17);
		Font textFont = new Font("Sitka Display", Font.PLAIN, 15);
		
//		To see the fonts in your system
		
//		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
//	    Font[] fonts = e.getAllFonts(); // Get the fonts
//	    for (Font f : fonts) {
//	      System.out.println(f.getFontName());
//	    }

		JLabel infoTitle = new JLabel("How it started...");
		infoTitle.setForeground(titleColor);
		infoTitle.setFont(titleFont);
		infoTitle.setSize(150, 130);
		infoTitle.setLocation(80, 10);

		JLabel infoText = new JLabel("<html><b>We left the store and were walking back to our car."
				+ " A man was backing his car out of his space."
				+ " He didn't see us and nearly hit us. We avoided him."
				+ " I'm pretty sure he never saw us because he wasn't really looking as he was backing out."
				+ " Why? He was looking at his cell phone. I assume he was checking his text messages."
				+ "  The zombies are everywhere...</b></html>");

		infoText.setForeground(textColor);
		infoText.setFont(textFont);
		infoText.setSize(500, 270);
		infoText.setLocation(80, 20);

		JLabel simInfoTitle = new JLabel("About");
		simInfoTitle.setForeground(titleColor);
		simInfoTitle.setFont(titleFont);
		simInfoTitle.setSize(150, 130);
		simInfoTitle.setLocation(80, 170);

		JLabel simInfoText = new JLabel("<html><b>A contagious virus is making people sick. When a person becomes sick, she looks unhealthy."
				+ " When a person becomes infected, she does not immediately get sick, though, but enters a phase of incubation in which she is infectious (i.e. can transmit the virus to other people) but not sick (so she looks as if she is healthy)."
				+ " People infected with the virus (whether visibly or not) may transmit it to other people in the same country."
				+ " To avoid the virus, people travel across countries. They avoid countries that hold visibly sick people."
				+ " Unfortunately, travelling people means the virus finds opportunity to spread to the whole world.</b></html>");

		simInfoText.setForeground(textColor);
		simInfoText.setFont(textFont);
		simInfoText.setSize(500, 270);
		simInfoText.setLocation(80, 210);

		backButton = new MenuButton(20, MainView.FRAME_HEIGHT - MainView.BUTTON_HEIGHT * 2, "BACK", new ModularActionListener(controller){
			public void actionPerformed(ActionEvent e) {
				controller.switchToMainMenu();
			}
		});

		this.add(infoTitle);
		this.add(infoText);
		this.add(simInfoTitle);
		this.add(simInfoText);
		this.add(backButton);
	}
}
