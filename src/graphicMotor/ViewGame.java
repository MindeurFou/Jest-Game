package graphicMotor;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
/*import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;*/

import playingMotor.*;


import javax.swing.border.EtchedBorder;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observer;
import java.util.Observable;
import java.awt.event.ActionEvent;

public class ViewGame implements Observer {

	private JFrame frame; //fenetre principale
	private JPanel mainPanel; // panneau principal
	private JPanel southPanel;
	private JPanel northPanel;
	private JPanel centerPanel;
		
	private JLabel turnLabel;
	private JLabel textLabel;
	private JLabel trophie1Lbl;
	private JLabel trophie2Lbl;
		
	private ArrayList<ViewPlayer> playersView; // vue des joueurs, répartie dans les différents panneaux
	//private ViewConsole consoleView; // vue d'un jeu de cartes, situé dans le panneau du bas
	
	private GameController gc;
	private Game game;

	/**
	 * Create the application.
	 */
	public ViewGame(OptionView view,Game game) {
		
		this.game = game;
		this.game.addObserver(this);
		this.game.setViewGame(this);
		this.game.setViewConsole(new ViewConsole(game));
		
						
		this.initializeTrophies();
		this.initialize(view);
		
		this.gc = new GameController(this, game);
		
		Thread mainThread = new Thread(game,"main thread"); // fait tourner le jeu	
		mainThread.start();
	
	}

	
	public void initializeTrophies() { // semble inutile
				
		trophie1Lbl = new JLabel(ViewStackOfCards.getImageOfVersoCard());
		trophie2Lbl = new JLabel(ViewStackOfCards.getImageOfVersoCard());
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(OptionView view) {
		
		
		this.frame = view.getFrame();
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		
		this.playersView = new ArrayList<ViewPlayer>();
		
		this.southPanel = new JPanel(new CardLayout()); // pour visualiser les paquets

		southPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));		
			
			Iterator<Player> it = game.getPlayers().iterator();
			
			while(it.hasNext()) {
				Player p = it.next();
				ViewPlayer vp = new ViewPlayer(p,this);
				playersView.add(vp);
	
		}
				
		this.centerPanel = new JPanel() {
			public void paintComponent(Graphics g) {

				super.paintComponent(g);
				
				Image casinoTable = new ImageIcon("images/casino-table.png").getImage();
				g.drawImage(casinoTable, (int)(this.getWidth()*0.15), (int)(this.getHeight()*0.15), (int)(this.getWidth()*0.7), (int)(this.getHeight()*0.7), this);
				

			}
		};
		
		
		// création des panneaux
		this.centerPanel.setLayout(new GridLayout(1,3));
		this.northPanel = new JPanel(); // pour les indications de jeu
		
		
		
		// remplissage des panneaux
		this.turnLabel = new JLabel();
		this.textLabel = new JLabel("I'm here to help you !");
		northPanel.add(turnLabel);
		northPanel.add(Box.createHorizontalStrut(200));
		northPanel.add(this.textLabel);
		northPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JPanel midPanel = new JPanel(); // zone de jeu
		
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		midPanel.setOpaque(false);
		
		JPanel panTrophies = new JPanel();
		panTrophies.setLayout(new FlowLayout());
		panTrophies.setOpaque(false);
		
		panTrophies.add(this.trophie1Lbl);
		panTrophies.add(this.trophie2Lbl);
		
		midPanel.add(playersView.get(2).getPanel(), BorderLayout.NORTH);
		
		midPanel.add(panTrophies, BorderLayout.CENTER);
		
		
		
		if(playersView.size() == 4) {
			midPanel.add(playersView.get(3).getPanel(), BorderLayout.SOUTH);
		}

				
		//placement des panneaux
		this.mainPanel.add(centerPanel, BorderLayout.CENTER);
		this.mainPanel.add(northPanel, BorderLayout.NORTH);
		this.mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		centerPanel.add(playersView.get(0).getPanel());	// panneau de gauche	
		centerPanel.add(midPanel);
		centerPanel.add(playersView.get(1).getPanel()); // panneau de droite
		
		
		this.frame.setContentPane(mainPanel);
		this.frame.validate();
		
	}
	
	public void update(Observable instanceObservable, Object o) {
		
		if(instanceObservable instanceof Game) {			
			Game game = (Game)instanceObservable;
			
			if (o.equals("update turn")) {
				this.turnLabel.setText("Turn "+(game.getTurn()));	
			} 
			else if( o.equals("display trophies")) { //si les trophées ont été mis à jour
				this.trophie1Lbl.setIcon(ViewStackOfCards.getImageOfCard(game.getTrophies().get(0)));
				this.trophie2Lbl.setIcon(ViewStackOfCards.getImageOfCard(game.getTrophies().get(1)));
			} 
			else if (o.equals("end of game")) {		
				
				
				JPanel panelVictory = new JPanel() {
					public void paintComponent(Graphics g) {

						super.paintComponent(g);

						Image background = new ImageIcon("images/background.png").getImage();
						g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

					}
				};
				
				
				
				JLabel victoryLabel = new JLabel("Victory of "+this.game.winner()+" ! Well played !");
				
				Font font = new Font("Arial",Font.BOLD,35);
				victoryLabel.setFont(font);
				
				panelVictory.setLayout(new BoxLayout(panelVictory,BoxLayout.Y_AXIS));
				victoryLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
				panelVictory.add(Box.createVerticalGlue());
				panelVictory.add(victoryLabel);
				panelVictory.add(Box.createVerticalGlue());
				frame.setContentPane(panelVictory);
				frame.validate();
			}
			
		}
		
	}
	
	public ArrayList<ViewPlayer> getPlayersView(){
		return this.playersView;
	}
	
	public Game getGame() {
		return this.game;
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
	public JLabel getTextLabel() {
		return this.textLabel;
	}
	
	public void setSouthPanel(JPanel panel) {
		this.southPanel = panel;
		this.southPanel.validate();
	}
	
	public JPanel getSouthPanel() {
		return this.southPanel;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}	

}
