package graphicMotor;

import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JTextField;

import java.awt.*;

import playingMotor.Game;
import playingMotor.StackOfCards;
import playingMotor.Strategies;

public class OptionController {
	
	public OptionController(OptionView view, Game game) {
		
		view.getPlayersCB().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object o = view.getPlayersCB().getSelectedItem();
				
				if(o instanceof String) {
					String str = (String)o;
					if(!str.equals("Choose the number of players")) {
						view.generatePlayers(str);
					}					
				}
				
			}
		});
		
		view.getStackCB().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// rien a faire je crois
			}
		});
		
		view.getConfirmBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object o = view.getPlayersCB().getSelectedItem();
				
				
				if(o instanceof String) {
					String str = (String)o;
					
					if(!str.equals("Choose the number of players")) {
						
						Iterator<ViewProfilePlayer> it = view.getPlayers().iterator();
						
						while(it.hasNext()) {
							
							ViewProfilePlayer player = it.next();
							String name = player.getNameField().getText();					
							String type = (String)player.getTypeCB().getSelectedItem();
							
			
							
							
							if (!name.equals("Enter the name of the player")) {
								if (type.equals("real player")) {
									game.addPlayer(name, Strategies.strat0);
								} else if(type.equals("bot with strategy 1")) {
									game.addPlayer(name, Strategies.strat1);
								} else {
									game.addPlayer(name, Strategies.strat2);
								}
							}
						}
						
						if(game.getPlayers().size() == view.getPlayers().size()) { // si tout les joueurs ont bien été rentrés
							
							StackOfCards mainStack = game.getMainStack();
							
							if(view.getStackCB().getSelectedItem().equals("Classic stack")) {
								mainStack.generateClassicFullStack();
							} else {
								mainStack.generateFullStack();
							}
							ViewGame viewGame = new ViewGame(view,game);		
							game.setViewGame(viewGame);
						}
					}					
				}
				
				
				
				

			}
		});
	}

}
