package newGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.AEADBadTagException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.border.TitledBorder;

import org.hamcrest.core.IsInstanceOf;

import engine.Game;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class Controller  {
	public static Game game;
	private view view;
	private Page2 page2;
	private Hero p1,p2;
	private String player1_name,player2_name;
	private Winner winner;
	static boolean f1=true,f2=false;
	public Controller(Hero p1,Hero p2,String n1,String n2) throws IOException, CloneNotSupportedException, FullHandException {
		this.p1=p1; this.p2=p2;
		f1=true; f2=false;
		player1_name=n1; player2_name=n2;
		game=new Game(p1, p2);
		
		view=new view();
		winner=new Winner();
		firstplay();
		
		getView().repaint();
	    getView().revalidate();
	}
	

	public static void main(String[] args) throws FullHandException, IOException, CloneNotSupportedException {
		
	}
	public void update_fieldbuttons() {
		JPanel panel=view.getCurrentherofield();
		panel.removeAll();
		ArrayList<JButton>list=view.getCurrherofield();
		for(int i=0;i<list.size();i++)  {
			JButton button=list.get(i);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			panel.add(button);
		}
		for(int i=0;i<7-list.size();i++) {
			JButton button=new JButton();
			panel.add(button);
			button.setVisible(false);
			
		}
		panel=view.getOpponentherofield();
		panel.removeAll();
		list=view.getOppherofield();
		for(int i=0;i<list.size();i++)  {
			JButton button=list.get(i);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			Minion minion=game.getOpponent().getField().get(i);
			panel.add(button);
			JLabel label=new JLabel(new ImageIcon(new File("minions and spells/"+minion.getName()+".png").getAbsolutePath()));
			JLabel label2=new JLabel(minion.toString());
			label2.setForeground(Color.WHITE);
			CardLayout c=new CardLayout();
			button.setLayout(c);
			button.add(label);
			button.add(label2);
			c.addLayoutComponent("1", label);
			c.addLayoutComponent("2", label2);
			c.show(button, "1");
			button.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					c.show(button, "1");
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					c.show(button, "2");
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		for(int i=0;i<7-list.size();i++) {
			JButton button=new JButton();
			panel.add(button);
			button.setVisible(false);
		}
	}
	public void update_handbuttons(){
		ArrayList<JButton> list=view.getCurrherohand();
		JPanel panel =view.getCurrentherohand();
		panel.setLayout(null);
		panel.removeAll();
		int x=0,y=20;
		for(int i=0;i<list.size();i++) {
			JButton button=list.get(i);
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			if(i%2==0) {
				button.setBounds(x, y,150,163);
			}else {
				button.setBounds(x+150, y,150,163);
				y+=163;
			}
			panel.add(button);	
		}
	}
	public void update_opponenthandbuttons(){
        
        JPanel panel =view.getAttckground();
        panel.removeAll();
        for(int i=0;i<game.getOpponent().getHand().size();i++) {
        	File file =new File("others/card.jpg");
            JLabel label=new JLabel(new ImageIcon(file.getAbsolutePath()));
            label.setOpaque(false);
            panel.add(label);
        }
        for(int i=0;i<10-game.getOpponent().getHand().size();i++) {
        	JLabel label=new JLabel("ads");
            panel.add(label);
            label.setVisible(false);
        }
    }
	public void update_currentherohand() {
		view.getCurrentherohand().removeAll();
		view.getCurrherohand().clear();
		for (int i = 0; i < game.getCurrentHero().getHand().size(); i++) {
			Card card=game.getCurrentHero().getHand().get(i);
			JButton button =new JButton();
			view.getCurrentherohand().add(button);
			view.getCurrherohand().add(button);
			CardLayout c=new CardLayout();
			button.setLayout(c);
			
			JLabel label=new JLabel(new ImageIcon(new File("minions and spells/"+card.getName()+".png").getAbsolutePath()));
			JLabel label2=new JLabel(card.toString());
			label2.setForeground(Color.WHITE);
			label2.setForeground(Color.WHITE);
			button.add(label);
			button.add(label2);
			c.addLayoutComponent("1", label);
			c.addLayoutComponent("2",label2 );
			c.show(button, "1");
			
			button.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if(card instanceof Minion) {
						try {
							game.getCurrentHero().playMinion((Minion)card);
						} catch(IndexOutOfBoundsException e1) {
							
						} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
							String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
							playaudio(path);
							JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
						}
					}else {
						 if(card instanceof FieldSpell || card instanceof AOESpell) {
							 String [] options=new String[] {"OK","Cancel"};
							int result= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
							if(result>=0) {
								if(result ==0) { 
									if(card instanceof FieldSpell) {
										
										
											try {
												game.getCurrentHero().castSpell(((FieldSpell)card));
											} catch(IndexOutOfBoundsException e1) {
												
											} catch (NotYourTurnException | NotEnoughManaException e1) {
												String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
												playaudio(path);
												JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
											}
											
									
									}
									else {
										try {
											game.getCurrentHero().castSpell(((AOESpell)card), game.getOpponent().getField());
										} catch(IndexOutOfBoundsException e1) {
											
										} catch (Exception e2) {
											String path=game.getCurrentHero().getName()+"/"+e2.getMessage()+".wav";
											playaudio(path);
											JOptionPane.showMessageDialog(null,e2.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
										}
										
									}
										
								}
							}
						 }else if(card instanceof MinionTargetSpell && card instanceof HeroTargetSpell) {
							 String [] options1=new String[] {"Your Cards","Opponent Cards","Cancel"};
			        			int result= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
							 	if(result>=0) {
				        			if(result==1) {
					        			String [] options=new String[game.getOpponent().getField().size()+2];
									 	options[0]=game.getOpponent().getName();
									 	for(int i=1;i<=game.getOpponent().getField().size();i++) {
									 		options[i]=game.getOpponent().getField().get(i-1).getName();
									 	}
									 	options[game.getOpponent().getField().size()+1]="Cancel";
					        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
									 	if(result1>=0) {
						        			if(result1==0) {
										 		try {
													game.getCurrentHero().castSpell(((HeroTargetSpell)card),game.getOpponent());
												} catch(IndexOutOfBoundsException e1) {
													
												} catch (NotYourTurnException | NotEnoughManaException e1) {
													String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
													playaudio(path);
													JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}else if(result1 !=game.getOpponent().getField().size()+1) {
										 		try {
													try {
														game.getCurrentHero().castSpell(((MinionTargetSpell)card), game.getOpponent().getField().get(result-1));
													} catch(IndexOutOfBoundsException e1) {
														
													} catch (NotYourTurnException | NotEnoughManaException e1) {
														String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
														playaudio(path);
														JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
													}
												} catch(IndexOutOfBoundsException e1) {
													
												} catch (InvalidTargetException e1) {
													String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
													playaudio(path);
													JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}
									 	}
								 	}else if(result==0) {
								 		String [] options=new String[game.getCurrentHero().getField().size()+2];
									 	options[0]=game.getCurrentHero().getName();
									 	for(int i=1;i<=game.getCurrentHero().getField().size();i++) {
									 		options[i]=game.getCurrentHero().getField().get(i-1).getName();
									 	}
									 	options[game.getCurrentHero().getField().size()+1]="Cancel";
					        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
									 	if(result1>=0) {
						        			if(result1==0) {
										 		try {
													game.getCurrentHero().castSpell(((HeroTargetSpell)card),game.getCurrentHero());
												} catch(IndexOutOfBoundsException e1) {
													
												} catch (NotYourTurnException | NotEnoughManaException e1) {
													String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
													playaudio(path);
													JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}else if(result1 !=game.getCurrentHero().getField().size()+1) {
										 		try {
													try {
														game.getCurrentHero().castSpell(((MinionTargetSpell)card),game.getCurrentHero().getField().get(result));
													} catch(IndexOutOfBoundsException e1) {
														
													} catch (NotYourTurnException | NotEnoughManaException e1) {
														String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
														playaudio(path);
														JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
													}
												} catch(IndexOutOfBoundsException e1) {
													
												} catch (InvalidTargetException e1) {
													String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
													playaudio(path);
													JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}
									 	}
								 	}
							 	}
						 }else if (card instanceof MinionTargetSpell || card instanceof LeechingSpell) {
							   String [] options1=new String[] {"Opponent Cards","Your Cards","Cancel"};
			        			int result= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
			        			if(result>=0) {
				        			if(result ==0) {
				        				String [] options=new String[game.getOpponent().getField().size()+1];
									 	for(int i=0;i<game.getOpponent().getField().size();i++) {
									 		options[i]=game.getOpponent().getField().get(i).getName();
									 	}
									 	options[game.getOpponent().getField().size()]="Cancel";
					        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
									 	if(result1>=0) {
						        			if(result1 !=game.getOpponent().getField().size()) {
										 		try {
										 			if(card instanceof MinionTargetSpell) {
															try {
																game.getCurrentHero().castSpell(((MinionTargetSpell)card), game.getOpponent().getField().get(result1));
															}catch(IndexOutOfBoundsException e1) {
																
															}  catch (NotYourTurnException | NotEnoughManaException e1) {
																String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
																playaudio(path);
																JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
															}
										 			}
										 			else {
										 				try {
															game.getCurrentHero().castSpell(((LeechingSpell)card), game.getOpponent().getField().get(result));
														} catch(IndexOutOfBoundsException e1) {
															
														} catch (NotYourTurnException | NotEnoughManaException e1) {
															String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
															playaudio(path);
															JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
														}
										 			}
										 		}catch(IndexOutOfBoundsException e1) {
													
												}  catch (InvalidTargetException e1) {
										 			String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
													playaudio(path);
													JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}
									 	}
				        			}else if(result==1) {
				        				String [] options=new String[game.getCurrentHero().getField().size()+1];
									 	for(int i=0;i<game.getCurrentHero().getField().size();i++) {
									 		options[i]=game.getCurrentHero().getField().get(i).getName();
									 	}
									 	options[game.getCurrentHero().getField().size()]="Cancel";
					        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+card.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
									 	if(result1>=0) {
						        			if(result1 !=game.getCurrentHero().getField().size()) {
										 		try {
										 			if(card instanceof MinionTargetSpell)
														try {
															game.getCurrentHero().castSpell(((MinionTargetSpell)card),game.getCurrentHero().getField().get(result1));
														} catch(IndexOutOfBoundsException e1) {
															
														} catch (NotYourTurnException | NotEnoughManaException e1) {
															String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
															playaudio(path);
															JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
														}
													else {
										 							
										 				try {
															game.getCurrentHero().castSpell(((LeechingSpell)card), game.getCurrentHero().getField().get(result1));
														} catch(IndexOutOfBoundsException e1) {
															
														} catch (NotYourTurnException | NotEnoughManaException e1) {
															String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
															playaudio(path);
															JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
														}
										 				
										 			}	} catch(IndexOutOfBoundsException e1) {
														
													} catch (InvalidTargetException e1) {
										 				String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
														playaudio(path);
										 				JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
												}
										 	}
					        			}
				        			}
							 }
						 }
					}
					updateview();
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					c.show(button, "1");
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					c.show(button, "2");
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					//c.show(button, "2");
				}
			});
		}
		
	}
	public void updateheros_card() {
		view.getCurrentherocard().removeAll();
		view.getOpponentherocard().removeAll();
		
		File file1=new File("Heros/"+game.getCurrentHero().getName()+".png");
		File file2=new File("Heros/"+game.getOpponent().getName()+".png");
		JLabel label1=new JLabel(new ImageIcon(file1.getAbsolutePath()));
		JLabel label2=new JLabel(new ImageIcon(file2.getAbsolutePath()));
		label1.setOpaque(false);
		label2.setOpaque(false);
		view.getCurrentherocard().setOpaque(false);
		view.getOpponentherocard().setOpaque(false);
		view.getCurrentherocard().add(label1);
		view.getOpponentherocard().add(label2);
		
	}
	public void updateview() {
		
		
		updateheros_card();
		
		updateoppherohp();
		update_currentherohand();
		updateoppfield();
		update_handbuttons();
		update_opponenthandbuttons();
		update_mana();
		updatecurrherohp();
		
		update_currentherofield();
		update_fieldbuttons();
		update_powerbuttons();
		update_titles();
		
		view.repaint();
		view.revalidate();
		
	}
	public void update_currentherofield() {
		ArrayList<JButton> list=view.getCurrherofield();
		list.clear();
		//view.getCurrentherofield().removeAll();
		for(int i=0;i<game.getCurrentHero().getField().size();i++) {
			
			Minion minion=game.getCurrentHero().getField().get(i);
			JButton button =new JButton();	
			list.add(button);
			JLabel label=new JLabel(new ImageIcon(new File("minions and spells/"+minion.getName()+".png").getAbsolutePath()));
			JLabel label2=new JLabel(minion.toString());
			label2.setForeground(Color.WHITE);
			CardLayout c=new CardLayout();
			button.setLayout(c);
			button.add(label);
			button.add(label2);
			c.addLayoutComponent("1", label);
			c.addLayoutComponent("2", label2);
			c.show(button, "1");
			button.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					if(!game.getOpponent().getField().isEmpty()) {
						String [] options=new String[game.getOpponent().getField().size()+2];
					 	options[0]=game.getOpponent().getName();
					 	for(int i=1;i<=game.getOpponent().getField().size();i++) {
					 		options[i]=game.getOpponent().getField().get(i-1).getName();
					 	}
					 	options[game.getOpponent().getField().size()+1]="Cancel";
	        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+minion.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					 	if(result1>=0) {
		        			if(result1==0) {
						 		try {
									game.validateAttack(minion,game.getOpponent());
									minion.attack(game.getOpponent());
								} catch(IndexOutOfBoundsException e1) {
									
								} catch (TauntBypassException | NotSummonedException | InvalidTargetException
										| CannotAttackException e1) {
									String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
									playaudio(path);
									JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
								}
						 	}else if(result1 !=game.getOpponent().getField().size()+1) {
		                        if(game.getOpponent().getField().size()!=0) {
		                        try {
		                           game.validateAttack(minion,game.getOpponent().getField().get(result1-1));
		                           minion.attack(game.getOpponent().getField().get(result1-1));
		                       } catch(IndexOutOfBoundsException e1) {
									
								} catch (TauntBypassException | InvalidTargetException | NotSummonedException
		                               | CannotAttackException e1) {
		                    	   String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
									playaudio(path);
		                    	   JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
		                       }
		                         }else {
		                            JOptionPane.showMessageDialog(null, "Hero Field is Empty");
		                         }
						 	}
					 	}
					}else {
						String [] options=new String[] {"Opponent Hero","Cancel"};
	        			int result1= JOptionPane.showOptionDialog(null,"Use !",""+minion.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
	        			if(result1==0) {
	        				try {
								game.validateAttack(minion,game.getOpponent());
								minion.attack(game.getOpponent());
							} catch(IndexOutOfBoundsException e1) {
								
							} catch (TauntBypassException | NotSummonedException | InvalidTargetException
									| CannotAttackException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
							}
	        			}
					}
					
				 	updateview();
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					c.show(button, "1");
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					c.show(button, "2");
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
	}
	public void firstplay() throws FullHandException, CloneNotSupportedException {
		String path=game.getCurrentHero().getName()+"/begin.wav";
		if(f2) {playaudio(path); f2=false; }
		if(f1 && f2==false) {playaudio(path); f1=false; f2=true;}
		useheropower();
		endturn();
		updateview();
		
	}

	public void update_mana() {
		JTextArea j1=view.getMc();
		JTextArea j2=view.getMo();
		JPanel p1=view.getNc();
		JPanel p2=view.getNo();
		p1.removeAll();
		p2.removeAll();
		
		j1.setEditable(true);
		j2.setEditable(true);
		
		int x=game.getCurrentHero().getCurrentManaCrystals();
		int y=game.getCurrentHero().getTotalManaCrystals();
		
		int x1=game.getOpponent().getCurrentManaCrystals();
		int y1=game.getOpponent().getTotalManaCrystals();
		
		j1.setText(x+"/"+y);
		j2.setText(x1+"/"+y1);
		
		j1.setEditable(false);
		j2.setEditable(false);
		
		for(int i=0;i<x;i++) {
			File file=new File("others/m.jpg");
			JLabel label=new JLabel(new ImageIcon(file.getAbsolutePath()));
			p1.add(label);
			label.setOpaque(false);
			//label.set
		}
		for(int i=0;i<x1;i++) {
			File file=new File("others/m.jpg");
			JLabel label=new JLabel(new ImageIcon(file.getAbsolutePath()));
			p2.add(label);
			label.setOpaque(false);
			//label.set
		}
		view.revalidate();
		view.repaint();

	}

	public void useheropower() {
		view.getCurrentheropower().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Hero hero=game.getCurrentHero();
				if(hero instanceof Mage) {
					String [] options=new String[] {"Hero","Minion","Cancel"};
					int result= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				 	if(result==0) {
				 		String [] options1=new String[3];
				 		options1[0]="Your"+game.getCurrentHero().getName();
				 		options1[1]="Opponent"+game.getOpponent().getName();
				 		options1[2]="Cancel";
				 		int result0= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
				 		if(result0==0) {
				 			try {
								((Mage)(game.getCurrentHero())).useHeroPower(game.getCurrentHero());
							}catch(IndexOutOfBoundsException e1) {
								
							}  catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
									| FullHandException | FullFieldException | CloneNotSupportedException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
							}
				 		}else if(result0==1) {
				 			try {
								((Mage)(game.getCurrentHero())).useHeroPower(game.getOpponent());
							}catch(IndexOutOfBoundsException e1) {
								
							}  catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
									| FullHandException | FullFieldException | CloneNotSupportedException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
							}
				 		}
				 	}else if(result==1) {
				 		String[]option2=new String[] {"Your Minions","Opponent Minions","Cancel"};
				 		int result1= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option2,null);
				 		if(result1==0) {
				 			String[]option3=new String[game.getCurrentHero().getField().size()+1];
				 			for(int i=0;i<game.getCurrentHero().getField().size();i++) {
				 				option3[i]=game.getCurrentHero().getField().get(i).getName();
				 			}
				 			option3[game.getCurrentHero().getField().size()]="Cancel";
				 			int result2= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option3,null);
				 			if(!(result2==game.getCurrentHero().getField().size())) {
				 				try {
									((Mage)(game.getCurrentHero())).useHeroPower(game.getCurrentHero().getField().get(result2));
								} catch(IndexOutOfBoundsException e1) {
									
								} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
										| FullHandException | FullFieldException | CloneNotSupportedException e1) {
									String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
									playaudio(path);
									JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
								}
				 			}
				 		}else if(result1==1) {
				 			String[]option3=new String[game.getOpponent().getField().size()+1];
				 			for(int i=0;i<game.getOpponent().getField().size();i++) {
				 				option3[i]=game.getOpponent().getField().get(i).getName();
				 			}
				 			option3[game.getCurrentHero().getField().size()]="Cancel";
				 			int result2= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option3,null);
				 			if(result2!=game.getOpponent().getField().size()) {
				 			try {
								((Mage)(game.getCurrentHero())).useHeroPower(game.getOpponent().getField().get(result2));
							} catch(IndexOutOfBoundsException e1) {
								
							} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
									| FullHandException | FullFieldException | CloneNotSupportedException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
							}
				 			}
				 		}
				 	}
					
				}else if(hero instanceof Hunter) {
					String [] options=new String[] {"Use Power","Cancel"};
					int result= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					if(result==0) {
						try {
							((Hunter)(game.getCurrentHero())).useHeroPower();
						} catch(IndexOutOfBoundsException e1) {
							
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
							playaudio(path);
							JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
						}
					}
					
				}else if(hero instanceof Priest) {
					
					String [] options=new String[] {"Hero","Minion","Cancel"};
					int result= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
				 	if(result==0) {
				 		String [] options1=new String[3];
				 		options1[0]=game.getCurrentHero().getName();
				 		options1[1]=game.getOpponent().getName();
				 		options1[2]="Cancel";
				 		int result0= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options1,null);
				 		if(result0==0) {
				 			try {
								((Priest)(game.getCurrentHero())).useHeroPower(game.getCurrentHero());
							} catch(IndexOutOfBoundsException e1) {
								
							} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
									| FullHandException | FullFieldException | CloneNotSupportedException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);

							}
				 		}else if(result0==1) {
				 			try {
								((Priest)(game.getCurrentHero())).useHeroPower(game.getOpponent());
							}catch(IndexOutOfBoundsException e1) {
								
							}  catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
									| FullHandException | FullFieldException | CloneNotSupportedException e1) {
								String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
								playaudio(path);
								JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);

							}
				 		}
				 	}else if(result==1) {
				 		String[]option2=new String[] {"Your Minions","Opponent Minions","Cancel"};
				 		int result1= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option2,null);
				 		if(result1==0) {
				 			String[]option3=new String[game.getCurrentHero().getField().size()+1];
				 			for(int i=0;i<game.getCurrentHero().getField().size();i++) {
				 				option3[i]=game.getCurrentHero().getField().get(i).getName();
				 			}
				 			option3[game.getCurrentHero().getField().size()]="Cancel";
				 			int result2= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option3,null);
				 			if(!(result2==game.getCurrentHero().getField().size())) {
				 				try {
									((Priest)(game.getCurrentHero())).useHeroPower(game.getCurrentHero().getField().get(result2));
								} catch(IndexOutOfBoundsException e1) {
									
								} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
										| FullHandException | FullFieldException | CloneNotSupportedException e1) {
									String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
									playaudio(path);
									JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);

								}
				 			}
				 		}else if(result1==1) {
				 			String[]option3=new String[game.getOpponent().getField().size()+1];
				 			for(int i=0;i<game.getOpponent().getField().size();i++) {
				 				option3[i]=game.getOpponent().getField().get(i).getName();
				 			}
				 			option3[game.getCurrentHero().getField().size()]="Cancel";
				 			int result2= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,option3,null);
				 			if(result2!=game.getCurrentHero().getField().size()) {
					 			try {
									((Priest)(game.getCurrentHero())).useHeroPower(game.getOpponent().getField().get(result2));
								}catch(IndexOutOfBoundsException e1) {
									
								} 
					 			catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
										| FullHandException | FullFieldException | CloneNotSupportedException e1) {
									String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
									playaudio(path);
									JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
	
								}
				 			}
				 		}
				 	}
					
				}else if(hero instanceof Warlock) {
					String [] options=new String[] {"Use Power","Cancel"};
					int result= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					if(result==0) {
						try {
							((Warlock)(game.getCurrentHero())).useHeroPower();
						} catch(IndexOutOfBoundsException e1) {
							
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
							playaudio(path);
							JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);

						}
					}
					
				}else if(hero instanceof Paladin) {
					String [] options=new String[] {"Use Power","Cancel"};
					int result= JOptionPane.showOptionDialog(null,"Use !",""+hero.getName(), 0,JOptionPane.INFORMATION_MESSAGE,null,options,null);
					if(result==0) {
						try {
							((Paladin)(game.getCurrentHero())).useHeroPower();
						} catch(IndexOutOfBoundsException e1) {
							
						} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
								| FullHandException | FullFieldException | CloneNotSupportedException e1) {
							String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
							playaudio(path);
							JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
						}
					}
					
				}
			updateview();
			}
		});
			// TODO Auto-generated method stub
		}	
	
	public void update_powerbuttons() {
		File file =new File("powers/"+game.getCurrentHero().getName()+".png");
		if(game.getCurrentHero().isHeroPowerUsed())
			file =new File("powers/Power.png");
		view.getCurrentheropower().setIcon(new ImageIcon(file.getAbsolutePath()));
		File file1 =new File("powers/"+game.getOpponent().getName()+".png");
		if(game.getOpponent().isHeroPowerUsed())
			file1 =new File("powers/Power.png");
		view.getOpponentheropower().setIcon(new ImageIcon(file1.getAbsolutePath()));
		
	}
	public void updatecurrherohp() {
		view.getCurrentheroHealth().removeAll();
		JLabel area =new JLabel(game.getCurrentHero().getCurrentHP()+"");
		view.getCurrentheroHealth().add(area);
		JLabel label=new JLabel(new ImageIcon(new File("others/health.jpg").getAbsolutePath()));
		label.setOpaque(false);
		view.getCurrentheroHealth().add(label);
		JPanel panel=view.gethc();
		panel.removeAll();
		panel.add(area);
		area.setOpaque(false);
		area.setForeground(Color.WHITE);
		Font font=new Font("J",Font.BOLD,20);
		area.setFont(font);
		view.repaint();
		view.revalidate();
	}	
		
	public void updateoppherohp() {
		JPanel panel=view.getho();
		panel.removeAll();
		view.getOpponetherohealth().removeAll();
		JLabel area =new JLabel(game.getOpponent().getCurrentHP()+"");
		view.getOpponetherohealth().add(area);
		panel.add(area);
		JLabel label=new JLabel(new ImageIcon(new File("others/health.jpg").getAbsolutePath()));
		label.setOpaque(false);
		area.setOpaque(false);
		area.setForeground(Color.WHITE);
		view.getOpponetherohealth().add(label);
		Font font=new Font("J",Font.BOLD,20);
		area.setFont(font);
		view.repaint();
		view.revalidate();

	}
	
	public void endturn() throws FullHandException, CloneNotSupportedException {
			view.getEndturn().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						game.endTurn();
						String path=game.getCurrentHero().getName()+"/begin.wav";
						if(f2) {playaudio(path); f2=false; }
						if(f1 && f2==false) {playaudio(path); f1=false; f2=true;}
					} catch(IndexOutOfBoundsException e1) {
						
					} catch (FullHandException | CloneNotSupportedException e1) {
						String path=game.getCurrentHero().getName()+"/"+e1.getMessage()+".wav";
						playaudio(path);
						JOptionPane.showMessageDialog(null,e1.getMessage(), "Warning", JOptionPane.PLAIN_MESSAGE);
					}
					updateview();
				}
				
			});;	
		}
	
	
	
public void updateoppfield(){
	view.getOpponentherofield().removeAll();
	view.getOppherofield().clear();
	for(int i=0;i<game.getOpponent().getField().size();i++) {
		Minion minion=game.getOpponent().getField().get(i);
		JButton button =new JButton();	
		view.getOpponentherofield().add(button);
		view.getOppherofield().add(button);
	}
	view.repaint();
	view.revalidate();
}
public void update_titles() {
	int x=p1.getDeck().size(),y=p2.getDeck().size();
	TitledBorder t=BorderFactory.createTitledBorder(player1_name+"'s Hand /"+x+" C left in Deck");
	if(game.getCurrentHero()==p1) {
		
		view.getCurrentherohand().setBorder(t);
		t.setTitleColor(Color.WHITE);
		t=BorderFactory.createTitledBorder(player2_name+"'s Hand /"+y+" C left in Deck");
		view.getAttckground().setBorder(t);
		t.setTitleColor(Color.WHITE);
		t=BorderFactory.createTitledBorder(player1_name+"'s Ground");
		view.getCurrentplayerground().setBorder(t);
		t.setTitleColor(Color.WHITE);

		t=BorderFactory.createTitledBorder(player2_name+"'s Ground");
		view.getOpponentplayerground().setBorder(t);
		t.setTitleColor(Color.WHITE);

	}else {
		t=BorderFactory.createTitledBorder(player2_name+"'s Hand /"+y+" C left in Deck");
		view.getCurrentherohand().setBorder(t);
		t.setTitleColor(Color.WHITE);
		t=BorderFactory.createTitledBorder(player1_name+"'s Hand /"+x+" C left in Deck");
		view.getAttckground().setBorder(t);
		t.setTitleColor(Color.WHITE);
		t=BorderFactory.createTitledBorder(player2_name+"'s Ground");
		view.getCurrentplayerground().setBorder(t);
		t.setTitleColor(Color.WHITE);
		t=BorderFactory.createTitledBorder(player1_name+"'s Ground");
		view.getOpponentplayerground().setBorder(t);
		t.setTitleColor(Color.WHITE);
	}
}

	
public void playaudio(String path) {
	GUI.gui.stop2();
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
   
    GUI.gui.play1();
}

public String getPlayer1_name() {
	return player1_name;
}


public String getPlayer2_name() {
	return player2_name;
}


public Game getGame() {
	return game;
}
public void setGame(Game game) {
	this.game = game;
}
public view getView() {
	return view;
}
public void setView(view view) {
	this.view = view;
}	
public Page2 getPage2() {
	return page2;
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


public void setPage2(Page2 page2) {
	this.page2 = page2;
}

}
