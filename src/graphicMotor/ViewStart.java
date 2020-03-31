package graphicMotor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

import playingMotor.Game;


public class ViewStart {

	private JFrame frame;
	private JPanel panel;
	private JButton btnPlay;
	private JButton btnRules;
	
	private StartController startController;

	public ViewStart(Game game) {
		
		frame = new JFrame();
		ImageIcon image = new ImageIcon("images/jest.png");
		this.frame.setIconImage(image.getImage());
		frame.setTitle("Jest");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = screenSize.width*2/3;
		int height = screenSize.height*2/3;
			
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);

		this.panel = new JPanel() {
			public void paintComponent(Graphics g) {

				super.paintComponent(g);

				Image background = new ImageIcon("images/background.png").getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

			}
		};

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(Box.createVerticalGlue());
		this.btnPlay = new JButton("PLAY");
		btnPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(this.btnPlay);

		panel.add(Box.createVerticalStrut(50));
		this.btnRules = new JButton("RULES");
		btnRules.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnRules);
		panel.add(Box.createVerticalGlue());
		
		this.frame.setContentPane(panel);
		
		this.startController = new StartController(this, game);
		
		
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JButton getBtnPlay() {
		return this.btnPlay;
	}
	
	public JButton getBtnRules() {
		return this.btnRules;
	}
	
	public static void main(String[] args) {

		Game game = Game.getInstanceGame();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStart window = new ViewStart(game);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
