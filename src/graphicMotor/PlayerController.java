package graphicMotor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import playingMotor.ChoosePlayerStrategy0;
import playingMotor.Player;

public class PlayerController {
	
	public PlayerController(ViewPlayer view, Player player) {
		
		view.getBtnPlayer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Iterator<Player> it = view.getGame().getPlayers().iterator();
				Player isChoosingPlayer = null;
				
				while(it.hasNext()) { // trouve le joueur qui choisissait et signale qu'il a finit de choisir
					Player p = it.next();
					
					if (p.isChoosingPlayer()) {
						isChoosingPlayer = p;
					}
				}
				
				if( view.getGame().getChosenPlayer() == null && ( !player.equals(isChoosingPlayer) || (player.equals(isChoosingPlayer) && player.getCanSelfDraw()))) {
					view.getGame().setChosenPlayer(player);
					
					isChoosingPlayer.setIsChoosingPlayer(false);			
				}
								
			}
		});
		
		view.getCard1Btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.isMakingOffer()) {				
					player.putCardsIntoOffer(player.getHand().getCard(0));
				}
				else if (view.getGame().getChosenPlayer().equals(player) && view.getGame().isInExchangingPhase()) { // si on est dans la phase d'échange et que le joueur peut choisir
					
					view.getGame().setChosenCard(player.getOffer().getCard(0));
					view.getCard1Btn().setIcon(null);
					
					Iterator<Player> it = view.getGame().getPlayers().iterator();
					
					
					while(it.hasNext()) { // trouve le joueur qui choisissait et signale qu'il a finit de choisir
						Player p = it.next();
						
						if (p.isChoosingCard()) {
							p.setIsChoosingCard(false);
						}
					}
				} 
				
			}
		});
		
		view.getCard2Btn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(player.isMakingOffer()) {
					player.putCardsIntoOffer(player.getHand().getCard(1));
				}
				else if (view.getGame().getChosenCard() == null && view.getGame().getChosenPlayer() != null) {
					
					view.getGame().setChosenCard(player.getOffer().getCard(1));
					view.getCard2Btn().setIcon(null);
					
					
					Iterator<Player> it = view.getGame().getPlayers().iterator();
					
					
					while(it.hasNext()) { // trouve le joueur qui choisissait et signale qu'il a finit de choisir
						Player p = it.next();
						
						if (p.isChoosingCard()) {
							p.setIsChoosingCard(false);
						}
					}
				} 
				
			}
		});
		
	
	}
}
