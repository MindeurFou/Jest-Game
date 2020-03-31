package graphicMotor;

import java.awt.*;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import playingMotor.ChooseCardStrategy0;
import playingMotor.ChoosePlayerStrategy0;
import playingMotor.Game;
import playingMotor.MakingOfferStrategy;
import playingMotor.MakingOfferStrategy0;
import playingMotor.Player;

public class ViewPlayer implements Observer {
	
	private static int nbPlayersCreated = 0;
	
	private Player player;
	private ViewStackOfCards viewCard;
	private CardLayout cl;
	
	private JPanel pan;
	private JPanel jestPanel;
		
	private JButton btnPlayer;
	private JLabel scoreLabel;
	
	private JButton card1Btn;
	private JButton card2Btn;
	
	private PlayerController playerController;
	
	private ViewGame viewGame;
	private Game game;
	
	
	public ViewPlayer(Player player, ViewGame viewGame) {
		
		this.viewGame = viewGame;
		this.game = viewGame.getGame();
		this.player = player;
		player.addObserver(this);
		
		
		this.viewCard = new ViewStackOfCards(player.getJest(), player, this);
		this.jestPanel = viewCard.getPanel();
		
		this.cl = (CardLayout)this.viewGame.getSouthPanel().getLayout();		
		this.viewGame.getSouthPanel().add(jestPanel,player.getName());
		System.out.println("jest de "+player.getName()+" a été ajouté");
		
		
		this.pan = new JPanel();
		this.pan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel name = new JLabel(player.getName());
		
		JPanel imgPanel = new JPanel();
		this.btnPlayer = new JButton(ViewPlayer.getImageOfPerson());
		//btnPlayer.setOpaque(false);
		
		JPanel cardPanel = new JPanel();
		
		card1Btn = new JButton(ViewStackOfCards.getImageOfVersoCard());
		card2Btn = new JButton(ViewStackOfCards.getImageOfVersoCard());
		
		JLabel jestLabel = new JLabel(ViewStackOfCards.getImageOfVersoCard());
		
		
		cardPanel.setOpaque(false);
		imgPanel.setOpaque(false);
		pan.setOpaque(false);
		
		this.playerController = new PlayerController(this, player);
		
		if (ViewPlayer.nbPlayersCreated == 0) {
			
			this.pan.setLayout(new GridLayout());
			
			imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
						
			name.setAlignmentX(Component.CENTER_ALIGNMENT);	
			btnPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
			jestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			imgPanel.add(Box.createVerticalGlue());
			imgPanel.add(name);
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(btnPlayer);
			imgPanel.add(Box.createVerticalStrut(20));
			imgPanel.add(jestLabel);
			imgPanel.add(Box.createVerticalGlue());
			
			
			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));	
				
			card1Btn.setAlignmentX(Component.LEFT_ALIGNMENT);
			card2Btn.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			
			
			cardPanel.add(Box.createVerticalGlue());
			cardPanel.add(card1Btn);
			cardPanel.add(Box.createVerticalStrut(20));
			cardPanel.add(card2Btn);			
			cardPanel.add(Box.createVerticalGlue());
			
			
			pan.add(imgPanel);
			pan.add(cardPanel);
						
			
		} else if (ViewPlayer.nbPlayersCreated == 1) {
			
			this.pan.setLayout(new GridLayout());
			
			imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
			
			
			name.setAlignmentX(Component.CENTER_ALIGNMENT);	
			btnPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
			jestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			imgPanel.add(Box.createVerticalGlue());
			imgPanel.add(name);
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(btnPlayer);
			imgPanel.add(Box.createVerticalStrut(20));
			imgPanel.add(jestLabel);
			imgPanel.add(Box.createVerticalGlue());
			
			
			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));	
				
			card1Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
			card2Btn.setAlignmentX(Component.RIGHT_ALIGNMENT);

			
			cardPanel.add(Box.createVerticalGlue());
			cardPanel.add(card1Btn);
			cardPanel.add(Box.createVerticalStrut(20));
			cardPanel.add(card2Btn);			
			cardPanel.add(Box.createVerticalGlue());
			
			pan.add(cardPanel);
			pan.add(imgPanel);
			
			
		} else if (ViewPlayer.nbPlayersCreated == 2) {
			
			this.pan.setLayout(new GridLayout(2,1));
			imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
			
			name.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
			jestLabel.setAlignmentY(Component.TOP_ALIGNMENT);
			
			JPanel anotherImgPanel = new JPanel();
			
			anotherImgPanel.add(btnPlayer);
			anotherImgPanel.add(Box.createHorizontalStrut(20));
			anotherImgPanel.add(jestLabel);
			anotherImgPanel.setOpaque(false);
			
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(name);
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(anotherImgPanel);
			imgPanel.add(Box.createVerticalStrut(5));

			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));

			card1Btn.setAlignmentY(Component.TOP_ALIGNMENT);
			card2Btn.setAlignmentY(Component.TOP_ALIGNMENT);		
			
			cardPanel.add(Box.createHorizontalGlue());
			cardPanel.add(card1Btn);
			cardPanel.add(Box.createHorizontalStrut(20));
			cardPanel.add(card2Btn);
			cardPanel.add(Box.createHorizontalStrut(20));
			
			cardPanel.add(Box.createHorizontalGlue());
			
			pan.add(imgPanel);
			pan.add(cardPanel);
			
		}  else if (ViewPlayer.nbPlayersCreated == 3) {
			
			this.pan.setLayout(new GridLayout(2,1));
			imgPanel.setLayout(new BoxLayout(imgPanel, BoxLayout.Y_AXIS));
			
			name.setAlignmentX(Component.RIGHT_ALIGNMENT);
			btnPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
			jestLabel.setAlignmentY(Component.TOP_ALIGNMENT);
			
			JPanel anotherImgPanel = new JPanel();			
			
			anotherImgPanel.add(btnPlayer);
			anotherImgPanel.add(Box.createHorizontalStrut(20));
			anotherImgPanel.add(jestLabel);
			anotherImgPanel.setOpaque(false);
			
			imgPanel.add(Box.createVerticalStrut(5));
			
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(anotherImgPanel);
			imgPanel.add(Box.createVerticalStrut(5));
			imgPanel.add(name);

			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.X_AXIS));

			card1Btn.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			card2Btn.setAlignmentY(Component.BOTTOM_ALIGNMENT);		
			
			cardPanel.add(Box.createHorizontalGlue());
			cardPanel.add(card1Btn);
			cardPanel.add(Box.createHorizontalStrut(20));
			cardPanel.add(card2Btn);
			cardPanel.add(Box.createHorizontalStrut(20));
			cardPanel.add(Box.createHorizontalGlue());
			
			pan.add(cardPanel);
			pan.add(imgPanel);
			
			
		}

		ViewPlayer.nbPlayersCreated++;
	}
	
	public static ImageIcon getImageOfSelectedPerson() {		
		String path =  "images/person icon selected.png";
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
	}
	
	public static ImageIcon getImageOfPerson() {		
		String path =  "images/person icon.png";
		return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));
	}
	
	public JButton getBtnPlayer() {
		return this.btnPlayer;
	}
	
	public JButton getCard1Btn() {
		return this.card1Btn;
	}
	
	public JButton getCard2Btn() {
		return this.card2Btn;
	}
	
	public JPanel getPanel() {
		return this.pan;
	}
	
	public void setJestPanel(JPanel panel) {
		this.jestPanel = panel;
	}
	
	public Game getGame() {
		return this.game;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void update(Observable instanceObservable, Object o) {
		
		if (instanceObservable instanceof Player) {
			
			Player p = (Player)instanceObservable;
			
			if(o instanceof MakingOfferStrategy0) {
				
				this.card1Btn.setIcon(ViewStackOfCards.getImageOfCard(p.getHand().getCardList().get(0)));
				this.card2Btn.setIcon(ViewStackOfCards.getImageOfCard(p.getHand().getCardList().get(1)));
				
				Iterator<ViewPlayer> it = this.viewGame.getPlayersView().iterator();
				
				while(it.hasNext()) {
					ViewPlayer vp = it.next();
					vp.getBtnPlayer().setIcon(getImageOfPerson());
				}
				
				this.btnPlayer.setIcon(ViewPlayer.getImageOfSelectedPerson());		
				
				cl.show(viewGame.getSouthPanel(), this.player.getName());
				
				
				
			} else if (o instanceof ChoosePlayerStrategy0) {
				
				Iterator<ViewPlayer> it = this.viewGame.getPlayersView().iterator();
				
				while(it.hasNext()) {
					ViewPlayer vp = it.next();
					
					vp.getBtnPlayer().setIcon(getImageOfPerson());
				}
				this.btnPlayer.setIcon(ViewPlayer.getImageOfSelectedPerson());
				
				this.cl.show(viewGame.getSouthPanel(), this.player.getName());
				
				/*this.viewGame.getMainPanel().remove(viewGame.getSouthPanel());
				this.viewGame.getMainPanel().add(jestPanel);
				JPanel test = new JPanel();
				
				JButton btn = new JButton(this.player.getName());
				test.add(btn);
				this.viewGame.getMainPanel().add(test, BorderLayout.SOUTH);
				this.viewGame.getMainPanel().revalidate();*/
				
				
				
				
			} else if (o instanceof ChooseCardStrategy0) {
				this.viewGame.getTextLabel().setText("Quelle carte voulez-vous prendre à ce joueur ? ");
			}
			
			else if (o.equals("hide hand")) {
				this.card1Btn.setIcon(ViewStackOfCards.getImageOfVersoCard());
				this.card2Btn.setIcon(ViewStackOfCards.getImageOfVersoCard());
			}
				
			else if (o.equals("hide a card") ) { // MAJ de la visibilité des cartes de ViewPlayer
				
				if (p.getOffer().getCard(0).getVisible() == false) {
					this.card1Btn.setIcon(ViewStackOfCards.getImageOfVersoCard());
					
				} else {
					this.card2Btn.setIcon(ViewStackOfCards.getImageOfVersoCard());
					
				}
				
				this.btnPlayer.setIcon(ViewPlayer.getImageOfPerson());
			}	
			
			

			
		
		}
		
		
	}


}
