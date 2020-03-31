package graphicMotor;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import playingMotor.Game;

public class StartController {

	public StartController(ViewStart view, Game game) {
		view.getBtnPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				new OptionView(view, game);				
			}
		});
		
		view.getBtnRules().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame() ;
				
				ImageIcon image = new ImageIcon("images/jest.png");
				frame.setIconImage(image.getImage());
				frame.setTitle("Jest rules");	
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
				ImageIcon rulesImg = new ImageIcon("images/rules.png");
				JLabel rules = new JLabel(rulesImg);
				frame.add(rules);				
				Dimension rulesDimension = new Dimension(rulesImg.getIconWidth(),rulesImg.getIconHeight());
				
				
				
				frame.setSize(rulesDimension);
				frame.setLocationRelativeTo(null);
				frame.setResizable(true);
				frame.setVisible(true);
				
			}
		});
	}
}
