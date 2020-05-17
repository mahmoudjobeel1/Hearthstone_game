package newGUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JFrame;

import exceptions.FullHandException;


public class GUI extends JFrame{
	private static final URL Player = null;
	private Controller controller;
	private Page1 page1;
	public static Page2 page2;
	public static GUI gui;
	private CardLayout card;
	private Clip clip1,clip2;
	public GUI() throws FullHandException, IOException, CloneNotSupportedException {
		gui=this;
		try {
			File file=new File("others/tricks-of-the-trade.wav");
			AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(file.getAbsoluteFile());
			clip1=AudioSystem.getClip();
			clip1.open(audioInputStream);
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
			//clip.start();
			FloatControl gainControl = (FloatControl) clip1.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
		    }catch (Exception e) {
		}
		
		try {
			File file=new File("others/got.wav");
			AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(file.getAbsoluteFile());
			clip2=AudioSystem.getClip();
			clip2.open(audioInputStream);
			clip2.loop(Clip.LOOP_CONTINUOUSLY);
			clip2.start();
//			FloatControl gainControl = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
//			gainControl.setValue(-20.0f);
		    }catch (Exception e) {
		}
//		VideoPlayer video = new VideoPlayer("filename");
//		getContentPane().add(video);
//		video.play();
		page1=new Page1();
		page2=new Page2();
		
		
		 card=new CardLayout();
		setLayout(card);
		
		add(page1);
		add(page2);
		
		card.addLayoutComponent(page1,"1");
		card.addLayoutComponent(page2, "2");
		
		
		card.show(getContentPane(), "1");
		
		JButton play=page1.getPlay();
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(getContentPane(), "2");
			}
		});
		
		setTitle("HearthStone");
		setSize(new Dimension(1920, 1080));
		setResizable(false);
		setVisible(true);
		//setUndecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
		revalidate();
	}
	public void stop1() {
		clip1.stop();
	}
	public void play1() {
		clip1.start();
	}
	public void stop2() {
		clip2.stop();
	}
	public void play2() {
		clip2.start();
	}
	public static void main(String[] args) throws FullHandException, IOException, CloneNotSupportedException {
		
	}
	public CardLayout getCard() {
		return card;
	}
	
}
