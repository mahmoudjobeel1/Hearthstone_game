package newGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import engine.Game;
import engine.GameListener;
import exceptions.FullHandException;

public class Winner extends JPanel implements GameListener{
	private JPanel hero;
	private JLabel label;
	private JLabel n;
	private Game game;
	private Controller controller;
	private JButton exit,playagain;
	public Winner() {
		Font font1=new Font("Jobeel", Font.BOLD, 20);
		game=Controller.game;
		game.setListener(this);
		exit=new JButton("Exit");
		playagain=new JButton("Play Again");
		exit.setBackground(Color.ORANGE);
		playagain.setBackground(Color.ORANGE);
		exit.setFont(font1);
		playagain.setFont(font1);
		Font font =new Font("Jobeel", Font.BOLD, 40);
		setLayout(null);
		
		hero=new JPanel(new BorderLayout());
		n=new JLabel("Jobeel");
		n.setLayout(new BorderLayout());
		
		n.setFont(font);
		JPanel panel=new JPanel(new BorderLayout());
		
		label=new JLabel(new ImageIcon(new File("victory/Rexxar.png").getAbsolutePath()));
		hero.add(label);
		JLabel background=new JLabel(new ImageIcon(new File("others/Victory.png").getAbsolutePath()));
		panel.add(background);
		
		hero.setBounds(380, 250, 250, 250);
		panel.setBounds(0, 0, 1000, 900);
		n.setBounds(420, 500, 200, 50);
		exit.setBounds(800, 725, 80, 40);
		playagain.setBounds(80, 725, 150, 40);
		exit.setBorderPainted(false);
		playagain.setBorderPainted(false);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		playagain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUI gui=GUI.gui;
				CardLayout card=gui.getCard();
				card.show(gui.getContentPane(), "2");
				gui.setSize(1920, 1080);
				gui.repaint();
				gui.revalidate();
			}
		});
		add(playagain);
		add(exit);
		add(n);
		add(hero);
		add(panel);
		
		hero.setOpaque(false);
		
		setSize(new Dimension(1000, 900));
		repaint();
		revalidate();
		
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLayout(null);
		frame.add(new Winner());
		frame.pack();
		frame.setVisible(true);
	}
	public JPanel getHero() {
		return hero;
	}
	public JLabel getN() {
		return n;
	}
	public JLabel getLabel() {
		return label;
	}
	@Override
	public void onGameOver() {
		GUI.gui.stop1();
		playaudio("others/victory.wav");
		controller=Page2.controller;
	
		//System.out.println(controller);
		if(game.getCurrentHero().getCurrentHP()==0) {
			Winner winner=new Winner();
			winner.getLabel().setIcon(new ImageIcon(new File("victory/"+game.getOpponent().getName()+".png").getAbsolutePath()));
			String x=controller.getP1()==game.getOpponent() ? controller.getPlayer1_name():controller.getPlayer2_name();
			winner.getN().setText(x);
			GUI gui=GUI.gui;
			CardLayout card=gui.getCard();
			gui.add(winner);
			card.addLayoutComponent(winner, "4");
			card.show(gui.getContentPane(), "4");
			gui.setSize(1000,800);
			gui.setResizable(false);
		}
		if(game.getOpponent().getCurrentHP()==0) {
			Winner winner=new Winner();
			winner.getLabel().setIcon(new ImageIcon(new File("victory/"+game.getCurrentHero().getName()+".png").getAbsolutePath()));
			String x=controller.getP1()==game.getCurrentHero() ? controller.getPlayer1_name():controller.getPlayer2_name();
			winner.getN().setText(x);
			GUI gui=GUI.gui;
			CardLayout card=gui.getCard();
			gui.add(winner);
			card.addLayoutComponent(winner, "4");
			card.show(gui.getContentPane(), "4");
			gui.setSize(1000,800);
			gui.setResizable(false);
		}
		controller.getView().repaint();
		controller.getView().revalidate();
	}
	public void playaudio(String path) {
		GUI.gui.stop1();
		
	    try {
		File file=new File(path);
		AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(file.getAbsoluteFile());
		Clip clip=AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	    }catch (Exception e) {
			// TODO: handle exception
		}
	    try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			
		}
	    GUI.gui.play1();
	}

}
