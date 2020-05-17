package newGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import exceptions.FullHandException;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class Page2 extends JPanel implements ActionListener{
	private JLabel background,player1_n,player2_n;
	private JPanel player1,player2;
	private JTextField player1_name,player2_name;
	private JPanel heros1,heros2;
	private ButtonGroup group1,group2;
    private ArrayList<JRadioButton> list1,list2;
    private JPanel hero1_image,hero2_image,hero1_d,hero2_d;
    private JButton start;
    private Hero p1,p2;
    private GUI gui;
    public static Controller controller;
	public Page2() throws IOException, CloneNotSupportedException, FullHandException {
		gui=GUI.gui;
		gui.stop1();
		gui.play2();
		player1=new JPanel(null);
		player2=new JPanel(null);
		heros1=new JPanel(null);
		heros2=new JPanel(null);
		
		JLabel label1=new JLabel();
		start=new JButton(new ImageIcon(new File("others/play.png").getAbsolutePath()));
		
		group1=new ButtonGroup();
		group2=new ButtonGroup();
		list1=new ArrayList<>();
		list2=new ArrayList<>();
		player1_n=new JLabel("Enter Your Name");
		player2_n=new JLabel("Enter Your Name");
		hero1_image=new JPanel(new BorderLayout());
		hero2_image=new JPanel(new BorderLayout());
		hero1_d=new JPanel(new BorderLayout());
		hero2_d=new JPanel(new BorderLayout());
		player1_name=new JTextField("Player 1");
		player2_name=new JTextField("Player 2");
		
		Font font=new Font("Jobeel",Font.BOLD, 20);
		TitledBorder player1_title=BorderFactory.createTitledBorder("Player 1");
		TitledBorder player2_title=BorderFactory.createTitledBorder("Player 2");
		TitledBorder heros1_title=BorderFactory.createTitledBorder("Heros");
		TitledBorder heros2_title=BorderFactory.createTitledBorder("Heros");

		player1_title.setTitleColor(Color.WHITE);
		player2_title.setTitleColor(Color.WHITE);
		heros1_title.setTitleColor(Color.WHITE);
		heros2_title.setTitleColor(Color.WHITE);
		
		player1_title.setTitleFont(font);
		player2_title.setTitleFont(font);
		heros1_title.setTitleFont(font);
		heros2_title.setTitleFont(font);
		
		player1.setBorder(player1_title);
		player2.setBorder(player2_title);
		heros1.setBorder(heros1_title);
		heros2.setBorder(heros2_title);
		start.setLayout(new BorderLayout());
		
		player1_n.setForeground(Color.WHITE);
		player2_n.setForeground(Color.WHITE);
		
		
		player1_n.setFont(font);
		player2_n.setFont(font);
		player1_name.setFont(font);
		player2_name.setFont(font);
		
		
		File file=new File("others/page 2.jpg");
		background=new JLabel(new ImageIcon(file.getAbsolutePath()));
		
		
		
		hero1_image.setBounds(255, 100, 250, 285);
		hero2_image.setBounds(255, 503, 250, 285);
		background.setBounds(0, 0, 1550, 900);
		player1.setBounds(0,0,850, 400);
		player2.setBounds(0,400 , 850, 400);
		player1_n.setBounds(50, 50, 200, 40);
		player2_n.setBounds(50, 450, 200, 40);
		player1_name.setBounds(250, 55, 200, 30);
		player2_name.setBounds(250, 455, 200, 30);
		heros1.setBounds(50, 90, 200, 300);
		heros2.setBounds(50, 490, 200, 300);
		hero1_d.setBounds(440, 60, 400, 330);
		hero2_d.setBounds(440, 460, 400, 330);
		start.setBounds(1090, 600, 150, 80);
		
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		
		player1.setOpaque(false);
		player2.setOpaque(false);
		heros1.setOpaque(false);
		heros2.setOpaque(false);
		hero1_image.setOpaque(false);
		hero2_image.setOpaque(false);
		hero1_d.setOpaque(false);
		hero2_d.setOpaque(false);
		
		
		
		String [] names=new String[] {"Anduin Wrynn","Gul'dan","Jaina Proudmoore","Rexxar","Uther Lightbringer"};
		int y1=130,y2=530;
		for(int i=0;i<5;i++) {
			
			JRadioButton radioButton1=new JRadioButton(names[i],false);
			JRadioButton radioButton2=new JRadioButton(names[i],false);
			
			radioButton1.setBounds(70, y1, 150 , 40);
			radioButton2.setBounds(70, y2, 150, 40);
			radioButton1.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					File file=new File("Heros/"+radioButton1.getText()+".png");
					JLabel label=new JLabel(new ImageIcon(file.getAbsolutePath()));
					hero1_image.removeAll();
					hero1_image.add(label);
					File file1=new File("Heros2/"+radioButton1.getText()+".png");
					JLabel label1=new JLabel(new ImageIcon(file1.getAbsolutePath()));
					hero1_d.removeAll();
					hero1_d.add(label1);
					repaint();
					revalidate();
				}
			});
			radioButton2.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					File file=new File("Heros/"+radioButton1.getText()+".png");
					JLabel label=new JLabel(new ImageIcon(file.getAbsolutePath()));
					hero2_image.removeAll();
					hero2_image.add(label);
					File file1=new File("Heros2/"+radioButton2.getText()+".png");
					JLabel label1=new JLabel(new ImageIcon(file1.getAbsolutePath()));
					hero2_d.removeAll();
					hero2_d.add(label1);
					repaint();
					revalidate();
				}
			});
			
			if(i==0) {
				radioButton1.setSelected(true);
				radioButton2.setSelected(true);
			 }
			add(radioButton1);
			add(radioButton2);
			y1+=50; y2+=50;
			group1.add(radioButton1);
			group2.add(radioButton2);
			list1.add(radioButton1);
			list2.add(radioButton2);
		}
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<JRadioButton> list=list1;
			      
				if(list.get(0).isSelected())
				try {
					p1=new Priest();
				} catch (IOException | CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			if(list.get(1).isSelected())
				try {
					p1=(new Warlock());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(2).isSelected())
				try {
					p1=(new Mage());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(3).isSelected())
				try {
					p1=(new Hunter());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(4).isSelected())
				try {
					p1=(new Paladin());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			list=list2;
			if(list.get(0).isSelected())
				try {
					p2=new Priest();
				} catch (IOException | CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			if(list.get(1).isSelected())
				try {
					p2=(new Warlock());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(2).isSelected())
				try {
					p2=(new Mage());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(3).isSelected())
				try {
					p2=(new Hunter());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(list.get(4).isSelected())
				try {
					p2=(new Paladin());
				} catch (IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if(p1!=null && p2!=null) {
				try {
					controller=new Controller(p1, p2,player1_name.getText(),player2_name.getText());
					CardLayout card=GUI.gui.getCard();
					GUI.gui.add(controller.getView());
					card.addLayoutComponent(controller.getView(), "3");
					card.show(GUI.gui.getContentPane(), "3");
					GUI.gui.setSize(new Dimension(1920, 1080));
				} catch (FullHandException | IOException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
			
		});
		
		add(start);
		add(hero1_image);
		add(hero2_image);
		add(heros1);
		add(heros2);
		add(hero1_d);
		add(hero2_d);
		add(player1_name);
		add(player2_name);
		add(player1_n);
		add(player2_n);
		add(player2);
		add(player1);
		add(background);
		
		
		
		repaint();
		revalidate();
		setLayout(null);
		setBounds(0, 0, 1920, 1080);
	}
	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException {
		Page2 page2=new Page2();
		JFrame frame=new JFrame();
		frame.add(page2);
		frame.setSize(1920, 1080);
		frame.setVisible(true);
		frame.repaint();
		frame.revalidate();
	}
	
	
	public Hero getP1() {
		return p1;
	}
	public void setP1(Hero p1) {
		this.p1 = p1;
	}
	public Hero getP2() {
		return p2;
	}
	public void setP2(Hero p2) {
		this.p2 = p2;
	}
	public JLabel getPlayer1_n() {
		return player1_n;
	}
	public JLabel getPlayer2_n() {
		return player2_n;
	}
	public JPanel getPlayer1() {
		return player1;
	}
	public JPanel getPlayer2() {
		return player2;
	}
	public JTextField getPlayer1_name() {
		return player1_name;
	}
	public JTextField getPlayer2_name() {
		return player2_name;
	}
	public JPanel getHeros1() {
		return heros1;
	}
	public JPanel getHeros2() {
		return heros2;
	}
	public ButtonGroup getGroup1() {
		return group1;
	}
	public ButtonGroup getGroup2() {
		return group2;
	}
	public ArrayList<JRadioButton> getList1() {
		return list1;
	}
	public ArrayList<JRadioButton> getList2() {
		return list2;
	}
	public JPanel getHero1_image() {
		return hero1_image;
	}
	public JPanel getHero2_image() {
		return hero2_image;
	}
	public JPanel getHero1_d() {
		return hero1_d;
	}
	public JPanel getHero2_d() {
		return hero2_d;
	}
	public JButton getStart() {
		return start;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
}
