package com.barmgn.www;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Crocodile extends JFrame {
   private JPanel contentPane;
   JButton[] btn; // 9 teeths
   JButton btnOk; // 2 ok_cancel
   JButton btnCancel;
   JLabel la_CrocoDefault;
   JLabel la_CrocoDeBite;
   JLabel lblresult;
   ImagePanel pn;
   BufferedImage myImage;

   int ran=(int)(Math.random()*9);
   GameMenuFrame gamemenuFrame;
   int row = 0;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
//               Crocodile frame = new Crocodile();
//               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public Crocodile(GameMenuFrame gamemenuFrame) {
	  this.gamemenuFrame = gamemenuFrame;
	  
	  while(true)
	  {
		  ran = (int)(Math.random()*9);
		  if(!(ran==4))
		  {
			  break;
		  }
	  }
	  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 800, 600);

      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
      contentPane.setLayout(new BorderLayout());
      setContentPane(contentPane);
      
      ImageIcon icon=new ImageIcon("images/Crocodile.png");
      la_CrocoDefault =new JLabel(icon);
      la_CrocoDefault.setBounds(150,-10,470,564); //절대레이아웃(null) : 계단처럼 
   
      //Call Background image
      myImage = null;
      try {
         myImage = ImageIO.read(new File("images/deck.jpg"));
      } catch (IOException e1) {
         e1.printStackTrace();
      }
      
      btn = new JButton[9]; // 9 teeths
      btnOk = new JButton("Game Over, Restart!"); // 2 ok_cancel
      
      OKCancelLisenter l = new OKCancelLisenter();
      btnOk.addActionListener(l);
      
      btnCancel = new JButton("You are Lucky!, Bye"); // 2 ok_cancel
      
      btnCancel.addActionListener(l);
      
      // Button teeth location
      for (int i = 0; i < btn.length; i++) 
      {
    	  if (i < 5) 
    	  {
         // 0, 1, 2, 3, 5, 6, 7, 8
    		  if(i==4) 
    		  {  //center teeth
    			  continue;
    		  }
    		  	
    		  btn[i] = new JButton();
    		  btn[i].setBounds(230 + i * 33, 310 + i * 46, 25, 30);
    		  add(btn[i]);
    	  } 
    	  else 
    	  {
	         btn[i] = new JButton();
	         row += 2;
	         btn[i].setLocation(230 + i * 33, 310 + (i - row) * 46);
	         btn[i].setSize(25, 30);
	         add(btn[i]);         
    	  }
          // when click buttons
          btn[i].addActionListener(new MyActionListener());
      }

      pn = new ImagePanel(myImage);   
      contentPane.add(pn);	// deck
      pn.add(la_CrocoDefault);			// croco
      //ButtonPanel pb = new ButtonPanel();
      //contentPane.add(pb);
      
      setVisible(true);
   }

   class OKCancelLisenter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
				
		switch(cmd)
		{
		case "Game Over, Restart!":
			dispose();
			new Crocodile(gamemenuFrame);
			break;
		case "You are Lucky!, Bye":
			dispose();
			gamemenuFrame.setVisible(true);
			break;
		}
	}
	   
   }
   
   class MyActionListener implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton) e.getSource();
         System.out.println(ran);
         
         for (int i = 0; i < btn.length; i++) 
         {
            if (b == btn[i]) 
            {
               if(i==ran)
               {

	             // 버튼이미지 안보이게
	             for( int x = 0 ; x < btn.length ; x++ )
	             {
		       		  if(x==4) 
		    		  {  //center teeth
		    			  continue;
		    		  }	
		       		  btn[x].setVisible(false); 
	             }
	             // Label 이미지 변경 : 악어가 입다문이미지
	             // 패널을 먼저 그리고 Label 이랑 Button 그려야함
	             
	             pn.remove(la_CrocoDefault);
	             
	             ImageIcon icon=new ImageIcon("images/Crocodile_bite.png");
	             la_CrocoDeBite =new JLabel(icon);
	             la_CrocoDeBite.setBounds(150,-10,470,564); //절대레이아웃(null) : 계단처럼 
	             pn.add(la_CrocoDeBite);
	             
	             // 버튼등장
            	 btnOk.setLocation(600, 410);
            	 btnOk.setSize(150, 30);
	             pn.add(btnOk);

              	 btnCancel.setLocation(600, 450);
              	 btnCancel.setSize(150, 30);
  	             pn.add(btnCancel);	             
	             
	             pn.repaint();
               }

            }
         }         
      }
   }

class ImagePanel extends JComponent {
   // Show Background image
   private Image deck;

   public ImagePanel(Image deck) {
      this.deck = deck;
      this.setLayout(null);
   }
   protected void paintComponent(Graphics g) {
      super.paintComponents(g); 
      
      g.drawImage(deck, 0, 0, this); // toy location
   }
}

}