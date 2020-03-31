package graphicMotor;

import java.awt.Component;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


public class ViewProfilePlayer implements Observer {
	
	private JTextField nameField;
	private JComboBox typeCB;
	private JLabel pImg;
	
	private JPanel pan;
	
	public ViewProfilePlayer(JPanel panelPlayer) {
		
		ImageIcon img = new ImageIcon (new ImageIcon("images/person icon.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));			
		pImg = new JLabel(img);
		pImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pan = new JPanel();
		pan.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		pan.setLayout(new BoxLayout(pan,BoxLayout.Y_AXIS));
		
		nameField = new JTextField("Enter the name of the player");
		
		String[] strat = {"real player","bot with strategy 1","bot with strategy 2"};
		
		typeCB = new JComboBox(strat);

		
		pan.add(pImg);
		pan.add(Box.createVerticalStrut(20));
		pan.add(nameField);
		pan.add(Box.createVerticalStrut(20));
		pan.add(typeCB);
		pan.add(Box.createVerticalStrut(400));

		panelPlayer.add(pan);
	}
	
	public JTextField getNameField() {
		return this.nameField;
	}
	
	public JComboBox getTypeCB() {
		return this.typeCB;
	}
	
	
	public void update(Observable instanceObservable, Object o) {
		// A FAIRE
	}
	

	
	

}
