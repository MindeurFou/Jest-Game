package graphicMotor;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import playingMotor.Card;
import playingMotor.Player;
import playingMotor.StackOfCards;

public class ViewStackOfCards implements Observer {
	
	private JPanel panel;
	private JPanel infoPan;
	private JLabel nameLabel;
	private JLabel scoreLabel;
	
	private StackOfCards stack;
	
	private Player player;
	private ViewPlayer viewPlayer;
	
	public ViewStackOfCards(StackOfCards stack, Player player, ViewPlayer viewPlayer) {
		
		this.viewPlayer = viewPlayer;
		this.player = player;
		this.stack = stack;
		this.stack.addObserver(this);
		
		this.panel = new JPanel();
		
		this.panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		this.panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		this.nameLabel = new JLabel("Jest of "+player.getName()+" :  ");
		this.scoreLabel = new JLabel("Score = "+player.getScore());
		
		infoPan = new JPanel();
		infoPan.setLayout(new GridLayout(2,1));
		infoPan.add(this.nameLabel);
		infoPan.add(scoreLabel);
		panel.add(infoPan);
		panel.add(Box.createRigidArea(new Dimension(1,75)));
				
		
	}
	
	public static ImageIcon getImageOfCard(Card c) {
		String path =  "images/"+ c.getName()+".png";
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(50, 75, Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon getImageOfVersoCard() {
		String path =  "images/carte verso.png";
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(50, 75, Image.SCALE_SMOOTH));
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public JLabel getNameLabel() {
		return this.nameLabel;
	}
	
	public void update(Observable instanceObservable, Object o) {
		
		if (instanceObservable instanceof StackOfCards) {
			
			this.panel.removeAll();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			this.panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			
			this.scoreLabel.setText("Score = "+player.getScore());
			
			panel.add(infoPan);
			
			Iterator<Card> it = ((StackOfCards)instanceObservable).getCardList().iterator();
			
			while(it.hasNext()) {
				Card c = it.next();
				JLabel lbl = new JLabel(ViewStackOfCards.getImageOfCard(c));
				lbl.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
				this.panel.add(lbl); 
				this.panel.add(Box.createHorizontalStrut(5));
			}
			panel.add(Box.createHorizontalGlue());
			
			this.viewPlayer.setJestPanel(panel);
		}
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("Jest");
		frame.setSize(1500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		StackOfCards stack = new StackOfCards();
		stack.generateClassicFullStack();
		
		ViewStackOfCards v = new ViewStackOfCards(stack,new Player("Bosetti"),null);
		
		JPanel panel = new JPanel();
		CardLayout cl = new CardLayout();
		panel.setLayout(cl);
		
		//panel.setBackground(Color.red);
		
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();

		pan1.add(new JLabel("pan1"));
		pan2.add(new JLabel("pan2"));
		
		panel.add(pan1, "pan1");
		panel.add(pan2, "pan2");
		
		cl.show(panel,"pan2");
		frame.setContentPane(panel);
		frame.setVisible(true);
		for(int i=0; i<10; i++) {
			
			if(i%2 == 0) {
				cl.show(panel,"pan2");
			} else {
				cl.show(panel, "pan1");
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch( InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		

	}

}
