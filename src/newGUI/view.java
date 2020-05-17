package newGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.TitledBorder;

public class view extends JPanel {
	private JPanel currentherohand,attckground,playground;
	private JButton attack,endturn,usespell;
	private JPanel currentplayerground,opponentplayerground;
	private JPanel attacker,target,opponentherocard,currentherocard;
	private JButton currentheropower,opponentheropower;
	private JPanel currentherofield,opponentherofield,opponentherohand;
	private JPanel currentherohcrystles,opponentherohcrystels;
	private JPanel currentheroHealth,opponetherohealth;
	private ArrayList<JButton>currherofield,oppherofield,currherohand,oppherohand;
	private JPanel hc,ho;
	private JTextArea mc,mo; private JPanel nc,no;
	public view()  {
		setLayout(null);
		
		currentherohand=new JPanel(null);
		opponentherohand=new JPanel(null);
		attckground=new JPanel(new GridLayout(0,2));
		currentplayerground=new JPanel(null);
		opponentplayerground=new JPanel(null);
		attacker=new JPanel(new BorderLayout());
		target=new JPanel(new BorderLayout());
		endturn=new JButton(new ImageIcon(new File("others/end turn.jpg").getAbsolutePath()));
		attack=new JButton("Attack");
		usespell = new JButton("Use Spell");
		opponentherocard=new JPanel(new BorderLayout());
		currentherocard=new JPanel(new BorderLayout());
		mc=new JTextArea();
		mo=new JTextArea();
		nc=new JPanel(new FlowLayout(FlowLayout.LEFT));
		no=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		hc=new JPanel(new BorderLayout());
		ho=new JPanel(new BorderLayout());
		
		currentherofield=new JPanel(new GridLayout(0,4));
		opponentherofield=new JPanel(new GridLayout(0,4));
		currentherohcrystles=new JPanel(new BorderLayout());
		opponentherohcrystels=new JPanel(new BorderLayout());
		currentheroHealth=new JPanel(new BorderLayout());
		opponetherohealth=new JPanel(new BorderLayout());
		currherohand=new ArrayList<JButton>();
		currherofield=new ArrayList<JButton>();
		oppherohand=new ArrayList<JButton>();
		oppherofield=new ArrayList<JButton>();
		currentheropower=new JButton("Power");
		
		opponentheropower=new JButton("Power");
		
		Font font=new Font("Jobeel",Font.BOLD,15);
		//currentherohand.add(new JButton())
		TitledBorder t=BorderFactory.createTitledBorder("Current Player Hand");
		currentherohand.setBorder(t);
	    t.setTitleColor(Color.WHITE);
	    
	    t=BorderFactory.createTitledBorder("Hand");
		opponentherohand.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Opponent Hand");
		attckground.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Current Player Ground");
		currentplayerground.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Opponent Player Ground");
		opponentplayerground.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Attacker");
		attacker.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Target");
		target.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("opponent hero Card");
		opponentherocard.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("current hero Card");
		currentherocard.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Field");
		currentherofield.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("Field");
		opponentherofield.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("P");
		opponentheropower.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		t=BorderFactory.createTitledBorder("P");
		currentheropower.setBorder(t);
		t.setTitleColor(Color.WHITE);
		
		
		File f=new File("others/mana.png");
		currentherohcrystles.add(new JLabel(new ImageIcon(f.getAbsolutePath())));
		opponentherohcrystels.add(new JLabel(new ImageIcon(f.getAbsolutePath())));
		
		currentheroHealth.setBounds(305, 745, 50, 70);
		opponetherohealth.setBounds(305, 320, 50, 70);
		
		currentherohcrystles.setBounds(600, 775, 450, 60);
		opponentherohcrystels.setBounds(600, 350, 450, 60);
		mc.setBounds(620, 785, 80, 50);
		mo.setBounds(620, 360, 80, 50);
		no.setBounds(705, 365, 330, 40);
		nc.setBounds(705, 790,330, 40);
		
		hc.setBounds(318, 765, 40, 40);
		ho.setBounds(318, 340, 40, 40);
		
		currentherofield.setBounds(600, 445,600,330);
		opponentherofield.setBounds(600, 20, 600,330);
		
		opponentherohand.setBounds(600, 10, 600, 100);
		
		currentheropower.setBounds(360, 735, 100, 100);
		opponentheropower.setBounds(360, 310, 100, 100);
		opponentherocard.setBounds(305,20,200,300);
		currentherocard.setBounds(305,445,200,300);

		currentherohand.setBounds(0, 0,300, 840);
		
		attckground.setBounds(1230, 0, 300, 840);
		
		opponentplayerground.setBounds(300, 0, 930,425);
		currentplayerground.setBounds(300, 425, 930,420);
		
		endturn.setBounds(1128,400, 100, 50);
		
		
		currentheropower.setFont(font);
		opponentheropower.setFont(font);
		mc.setFont(new Font("J", font.BOLD, 30));
		mo.setFont(new Font("J", font.BOLD, 30));
		

		add(ho);
		ho.setOpaque(false);
		add(hc);
		hc.setOpaque(false);
		add(mc);
		mc.setOpaque(false);
		add(mo);
		mo.setOpaque(false);
		add(no);
		no.setOpaque(false);
		add(nc);
		nc.setOpaque(false);
		add(currentheroHealth);
		currentheroHealth.setOpaque(false);
		add(opponetherohealth);
		opponetherohealth.setOpaque(false);
		add(endturn);
		endturn.setOpaque(false);
		add(currentherohcrystles);
		currentherohcrystles.setOpaque(false);
		add(opponentherohcrystels);
		opponentherohcrystels.setOpaque(false);
	//	add(opponentherohand);
		add(currentherofield);
		currentherofield.setOpaque(false);
		add(opponentherofield);
		opponentherofield.setOpaque(false);
		add(opponentheropower);
		opponentheropower.setOpaque(false);
		add(currentheropower);
		currentheropower.setOpaque(false);
		add(currentherocard);
		currentherocard.setOpaque(false);
		add(opponentherocard);
		opponentherocard.setOpaque(false);
		add(opponentplayerground);
		opponentplayerground.setOpaque(false);
		add(currentplayerground);
		currentplayerground.setOpaque(false);
		add(currentherohand);
		currentherohand.setOpaque(false);
		add(attckground);
		attckground.setOpaque(false);
		
		endturn.setOpaque(false);
		endturn.setFocusable(false);
		opponentheropower.setOpaque(false);
		opponentheropower.setBorderPainted(false);
		opponentheropower.setContentAreaFilled(false);
		opponentplayerground.setFocusable(false);
		currentheropower.setOpaque(false);
		currentheropower.setBorderPainted(false);
		currentheropower.setContentAreaFilled(false);
		currentheropower.setFocusable(false);
		
		mc.setOpaque(false);
		mo.setOpaque(false);
		nc.setOpaque(false);
		no.setOpaque(false);
		currentheroHealth.setOpaque(false);
		opponetherohealth.setOpaque(false);
		hc.setOpaque(false);
		ho.setOpaque(false);
		
		currentherofield.setOpaque(false);
		opponentherofield.setOpaque(false);
		nc.setOpaque(false);
		no.setOpaque(false);
		
		
		mc.setForeground(Color.WHITE);
		mo.setForeground(Color.WHITE);
		int r=(int)(Math.random()*2);
		JLabel back=new JLabel(new ImageIcon(new File("others/b3.jpg").getAbsolutePath()));
//		if(r==0)
//			back=new JLabel(new ImageIcon(new File("others/b3.jpg").getAbsolutePath()));
		add(back);
		back.setBounds(0, 0, 1800, 900);
		
		repaint();
		revalidate();
		
	}
	public static void main(String[] args) {
	  view view=new view();
	  JFrame frame=new JFrame();
	  frame.setSize(1920,1080);
	  frame.setVisible(true);
	  frame.add(view);
	}
	
	public JPanel gethc() {
		return hc;
	}

	public JPanel getho() {
		return ho;
	}

	public JTextArea getMc() {
		return mc;
	}

	public JTextArea getMo() {
		return mo;
	}

	public JPanel getNc() {
		return nc;
	}

	public JPanel getNo() {
		return no;
	}

	public ArrayList<JButton> getCurrherofield() {
		return currherofield;
	}

	public void setCurrherofield(ArrayList<JButton> currherofield) {
		this.currherofield = currherofield;
	}

	public ArrayList<JButton> getOppherofield() {
		return oppherofield;
	}

	public void setOppherofield(ArrayList<JButton> oppherofield) {
		this.oppherofield = oppherofield;
	}

	public ArrayList<JButton> getCurrherohand() {
		return currherohand;
	}

	public void setCurrherohand(ArrayList<JButton> currherohand) {
		this.currherohand = currherohand;
	}

	public ArrayList<JButton> getOppherohand() {
		return oppherohand;
	}

	public void setOppherohand(ArrayList<JButton> oppherohand) {
		this.oppherohand = oppherohand;
	}

	public JPanel getCurrentherohand() {
		return currentherohand;
	}

	public void setCurrentherohand(JPanel currentherohand) {
		this.currentherohand = currentherohand;
	}

	public JPanel getAttckground() {
		return attckground;
	}

	public void setAttckground(JPanel attckground) {
		this.attckground = attckground;
	}

	public JPanel getPlayground() {
		return playground;
	}

	public void setPlayground(JPanel playground) {
		this.playground = playground;
	}

	public JButton getAttack() {
		return attack;
	}

	public void setAttack(JButton attack) {
		this.attack = attack;
	}
	public JButton getUsespell() {
		return usespell;
	}

	public void setUsespell(JButton usespell) {
		this.attack = usespell;
	}
	public JButton getEndturn() {
		return endturn;
	}

	public void setEndturn(JButton endturn) {
		this.endturn = endturn;
	}

	public JPanel getCurrentplayerground() {
		return currentplayerground;
	}

	public void setCurrentplayerground(JPanel currentplayerground) {
		this.currentplayerground = currentplayerground;
	}

	public JPanel getOpponentplayerground() {
		return opponentplayerground;
	}

	public void setOpponentplayerground(JPanel opponentplayerground) {
		this.opponentplayerground = opponentplayerground;
	}

	public JPanel getAttacker() {
		return attacker;
	}

	public void setAttacker(JPanel attacker) {
		this.attacker = attacker;
	}

	public JPanel getTarget() {
		return target;
	}

	public void setTarget(JPanel target) {
		this.target = target;
	}

	public JPanel getOpponentherocard() {
		return opponentherocard;
	}

	public void setOpponentherocard(JPanel opponentherocard) {
		this.opponentherocard = opponentherocard;
	}

	public JPanel getCurrentherocard() {
		return currentherocard;
	}

	public void setCurrentherocard(JPanel currentherocard) {
		this.currentherocard = currentherocard;
	}

	public JButton getCurrentheropower() {
		return currentheropower;
	}

	public void setCurrentheropower(JButton currentheropower) {
		this.currentheropower = currentheropower;
	}

	public JButton getOpponentheropower() {
		return opponentheropower;
	}

	public void setOpponentheropower(JButton opponentheropower) {
		this.opponentheropower = opponentheropower;
	}

	public JPanel getCurrentherofield() {
		return currentherofield;
	}

	public void setCurrentherofield(JPanel currentherofield) {
		this.currentherofield = currentherofield;
	}

	public JPanel getOpponentherofield() {
		return opponentherofield;
	}

	public void setOpponentherofield(JPanel opponentherofield) {
		this.opponentherofield = opponentherofield;
	}

	public JPanel getOpponentherohand() {
		return opponentherohand;
	}

	public void setOpponentherohand(JPanel opponentherohand) {
		this.opponentherohand = opponentherohand;
	}

	public JPanel getCurrentherohcrystles() {
		return currentherohcrystles;
	}

	public void setCurrentherohcrystles(JPanel currentherohcrystles) {
		this.currentherohcrystles = currentherohcrystles;
	}

	public JPanel getOpponentherohcrystels() {
		return opponentherohcrystels;
	}

	public void setOpponentherohcrystels(JPanel opponentherohcrystels) {
		this.opponentherohcrystels = opponentherohcrystels;
	}
	
	public JPanel getCurrentheroHealth() {
		return currentheroHealth;
	}

	public void setCurrentheroHealth(JPanel currentheroHealth) {
		this.currentheroHealth = currentheroHealth;
	}

	public JPanel getOpponetherohealth() {
		return opponetherohealth;
	}

	public void setOpponetherohealth(JPanel opponetherohealth) {
		this.opponetherohealth = opponetherohealth;
	}

	
}
