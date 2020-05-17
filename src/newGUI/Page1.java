package newGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Page1  extends JPanel{
	private JButton play,about,exit;
	private JLabel backgroud;
	public Page1() {
		play=new JButton("Play");
		about=new JButton("About");
		exit=new JButton("Exit");
		File file=new File("others/start page.jpg");
		backgroud=new JLabel(new ImageIcon(file.getAbsolutePath()));
		Font font=new Font("Jobeel",Font.BOLD,30);
		
		play.setFont(font);
		about.setFont(font);
		exit.setFont(font);
		
		
		
		play.setForeground(Color.WHITE);
		about.setForeground(Color.WHITE);
		exit.setForeground(Color.WHITE);
		
		backgroud.setBounds(0, 0, 1550, 850);
		play.setBounds(550, 600, 120, 40);
		about.setBounds(700, 600, 140, 40);
		exit.setBounds(880, 600, 120, 40);
		
		//play.setOpaque(false);
		//play.setBorderPainted(false);
        play.setContentAreaFilled(false); 
        about.setContentAreaFilled(false); 
        exit.setContentAreaFilled(false); 

		add(play);
		add(about);
		add(exit);
		add(backgroud);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String aboutGame
	            = "<html>"
	            + "<big>Hearth Stone</big><br><br>"
	            + "<p>Prepared by a <b>Mahmoud Jobeel,Marwan Mohsen,Mena Safwat</b><br><br>"
	            + "If you have any comments, ideas.. just let know<br><br>"
	            + "email: mahmoudjobeel@gmail.com<br>"
	            + "email: marwan.mohsen1000@gmail.com<br>"
	            +" email: msafwat631@gmail.com<br><br>"
	            + "<u>Note</u><br>"
	            + "We used JDK 1.8 to compile the source code.<br><br><br>"
	            + "<p><i>© Copyright 2020 Team152 - All Rights Reserved</i></p>"
	            + "<html>";
				JOptionPane.showMessageDialog(null, aboutGame, "Hearth Stone", JOptionPane.PLAIN_MESSAGE);

			}
		});
//		play.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					Page2 page2=new Page2();
//					JFrame frame=GUI.gui;
//					frame.removeAll();
//					frame.repaint();
//					frame.revalidate();
//					frame.add(page2);
//					frame.repaint();
//					frame.revalidate();
//				} catch (IOException | CloneNotSupportedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}
//		});
		setLayout(null);
		setBounds(0, 0, 1920, 1080);
		setBorder(BorderFactory.createTitledBorder("Start Page"));
		
	}
	public static void main(String[] args) {
		Page1 page1=new Page1();
		JFrame frame=new JFrame();
		frame.add(page1);
		frame.setSize(1920, 1080);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public JButton getPlay() {
		return play;
	}
	public JButton getAbout() {
		return about;
	}
	public JButton getExit() {
		return exit;
	}
}
