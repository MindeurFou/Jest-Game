package graphicMotor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import playingMotor.Game;

public class OptionView implements Observer {
	
	private ViewStart viewGame;
	private JFrame frame;
	private JPanel comboPanel;
	private JPanel panelPlayer;
	private JPanel panel;
	private JComboBox playersCB;
	private JComboBox stackCB;
	private JButton confirmBtn;
	private HashSet<ViewProfilePlayer> profilePlayers;

	
	private OptionController oc;
	
	private int nbPlayers;
	
	public OptionView(ViewStart view, Game game) {
		
		this.viewGame = view;
		this.frame = view.getFrame();
		this.comboPanel = new JPanel();
		this.profilePlayers = new HashSet<ViewProfilePlayer>();
		this.comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.Y_AXIS));
		comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		String[] nbPlayer = {"Choose the number of players","3","4"};		
		this.playersCB = new JComboBox(nbPlayer);
		
		String[] existingStacks = {"Classic stack","Random condition stack"};
		this.stackCB = new JComboBox(existingStacks);
		
		this.comboPanel.add(new JLabel("Choose your stack :"));
		this.comboPanel.add(Box.createVerticalStrut(10));
		this.comboPanel.add(stackCB);
		
		this.comboPanel.add(Box.createVerticalStrut(20));
		this.comboPanel.add(new JLabel("Choose the number of players :"));
		comboPanel.add(Box.createVerticalStrut(10));
		this.comboPanel.add(this.playersCB);
		
		this.panelPlayer = new JPanel();
		panelPlayer.setLayout(new GridLayout());
		
		this.panel = new JPanel();
		this.panel.setLayout(new BorderLayout());
		this.panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
		
		this.panel.add(comboPanel, BorderLayout.NORTH);
		this.panel.add(panelPlayer, BorderLayout.CENTER);
		
		this.confirmBtn = new JButton("Confirm");	
		this.panel.add(confirmBtn, BorderLayout.SOUTH);
		
		frame.setContentPane(panel);
		frame.validate();
		
		this.oc = new OptionController(this, game);
			
	}
	
	public void update(Observable instanceObservable, Object o) {
		//a faire
	}
	
	public JComboBox getPlayersCB() {
		return this.playersCB;
	}
	
	public JComboBox getStackCB() {
		return this.stackCB;
	}
	
	public JButton getConfirmBtn() {
		return this.confirmBtn;
	}
	
	public int getNbPlayer() {
		return this.nbPlayers;
	}
	
	public HashSet<ViewProfilePlayer> getPlayers() {
		return this.profilePlayers;
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	public void generatePlayers(String nbPlayer) {
		
		if(!this.profilePlayers.isEmpty()) {
			this.profilePlayers.clear();
			this.panelPlayer.removeAll();
		}	
		
		if (nbPlayer.equals("3")) {
						
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			
	
		} else if (nbPlayer.equals("4")) {
			
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			this.profilePlayers.add(new ViewProfilePlayer(this.panelPlayer));
			
		}
		
		this.panelPlayer.validate();
	}
	

	
	
}
