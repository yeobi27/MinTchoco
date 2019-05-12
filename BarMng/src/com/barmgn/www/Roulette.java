package com.barmgn.www;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Roulette extends JFrame {

	private bottlePanel bp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Roulette frame = new Roulette();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	class bottlePanel extends JPanel{
		int i=0;
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
	
			BufferedImage bottle=LoadImage("images/bottle.png");
			BufferedImage deck=LoadImage("images/deck.jpg");
	
			AffineTransform at=AffineTransform.getTranslateInstance(100, 100);
			at.rotate(Math.toRadians(i+=10), bottle.getWidth()/2, bottle.getHeight()/2);	
			
			Graphics2D g2d = (Graphics2D) g;

			g2d.drawImage(deck, 0, 0, null);
			g2d.drawImage(bottle, at , null);
			
			g2d.dispose();
			
			repaint();
		}
	}
	
	private BufferedImage LoadImage(String bottlerun) {
		// TODO Auto-generated method stub
		
		BufferedImage img=null;
		
		try {
			img=ImageIO.read(new File(bottlerun));
			
		}catch(Exception e) {
			
		}
		return img;
	}

	/**
	 * Create the frame.
	 */
	public Roulette() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		bp = new bottlePanel();
		
		bp.setBorder(new EmptyBorder(5, 5, 5, 5));
		bp.setLayout(new BorderLayout(0, 0));
		setContentPane(bp);

		setVisible(true);
	}

}