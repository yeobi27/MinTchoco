package com.barmgn.www;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	// Image Load
	// background
	ImageIcon mainbg_img = new ImageIcon("images/mainbg_img.jpg");
	// button
	ImageIcon games_img = new ImageIcon("images/games_img.png");
	ImageIcon order_img = new ImageIcon("images/order_img.png");
	ImageIcon talking_img = new ImageIcon("images/talking_img.png");
	ImageIcon bell_img = new ImageIcon("images/bell_img.png");
	ImageIcon adbtn_img = new ImageIcon("images/adbtn_img.png");
	ImageIcon menulink = new ImageIcon("images/menulink.png");
	JLabel M_menuBar = new JLabel(new ImageIcon("images/M_menuBar.png"));
	int mouseX, mouseY;
	
	// Music Load
	private Music introMusic = new Music("music/introMusic.mp3", true);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setLocationRelativeTo(null);
		setBounds(100, 100, 800, 600);

		introMusic.start();

		contentPane = new JPanel() {
	   	   	public void paintComponent(Graphics g) {
	   	      super.paintComponents(g); 

	   	      setOpaque(false);
	   	      g.drawImage(mainbg_img.getImage(), 0, 0, this); // toy location
//	   	      g.drawImage(games_img.getImage(), 334, 10, this); // toy location
//	   	      g.drawImage(order_img.getImage(), 12, 10, this); // toy location
//	   	      g.drawImage(talking_img.getImage(), 12, 10, this); // toy location
//	   	      g.drawImage(bell_img.getImage(), 12, 10, this); // toy location
	   	   }
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		M_menuBar.setBounds(0, 0, 1280, 30); 

		M_menuBar.addMouseListener(new MouseAdapter() {
			@Override
 
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX(); 
				mouseY = e.getY(); 
			}
		});

		M_menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override

			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen(); 
				int y = e.getYOnScreen(); 

				setLocation(x - mouseX, y - mouseY);
			}
		});
		contentPane.add(M_menuBar);
		
		JPanel p_North = new JPanel();
		p_North.setBackground(new Color(255, 0, 0, 0)); 

		p_North.setBounds(5, 5, 785, 97);
		p_North.setPreferredSize(new Dimension(300, 34));
		contentPane.add(p_North);
		p_North.setLayout(null);
		
		JButton btnCall = new JButton("호출", bell_img);
		// Border remove
		btnCall.setBorderPainted(false);
		// don't do Text to fill 
		btnCall.setContentAreaFilled(false);
		// focus border x
		btnCall.setFocusPainted(false);
		// 투명
		btnCall.setOpaque(false);
		
		btnCall.setBounds(666, 0, 96, 92);
		btnCall.setPreferredSize(new Dimension(85, 92));
		btnCall.setMargin(new Insets(0, 0, 0, 0));
		btnCall.setAlignmentX(1.0f);
		p_North.add(btnCall);
		
		JButton btnManuLink = new JButton("메뉴링크", menulink);
		btnManuLink.setFocusPainted(false);
		btnManuLink.setContentAreaFilled(false);
		btnManuLink.setBorderPainted(false);
		btnManuLink.setBounds(5, 37, 234, 50);
		btnManuLink.setPreferredSize(new Dimension(230, 50));
		btnManuLink.setMargin(new Insets(0, 0, 0, 0));
		btnManuLink.setAlignmentY(0.0f);
		p_North.add(btnManuLink);
		
		JPanel p_West = new JPanel();
		p_West.setBackground(new Color(255,0,0,0));
		p_West.setBounds(5, 104, 247, 484);
		p_West.setPreferredSize(new Dimension(220, 300));
		contentPane.add(p_West);
		p_West.setLayout(null);
		
		JButton btnWating = new JButton("게임대기");
		btnWating.setIcon(new ImageIcon("images/waitingTableImage.png"));
		btnWating.setBorderPainted(false);
		btnWating.setContentAreaFilled(false);
		btnWating.setFocusPainted(false);
		btnWating.setBounds(6, 285, 231, 133);
		btnWating.setPreferredSize(new Dimension(100, 25));
		p_West.add(btnWating);
		
		JButton btnVS = new JButton("VS");
		
		btnVS.setIcon(new ImageIcon("images/eventlink.png"));
		btnVS.setFocusPainted(false);
		btnVS.setContentAreaFilled(false);
		btnVS.setBorderPainted(false);
		btnVS.setBounds(6, 235, 234, 50);
		p_West.add(btnVS);
		
		JButton btnMenuCF = new JButton("CF");
		btnMenuCF.setBounds(5, 5, 230, 220);
		p_West.add(btnMenuCF);
		btnMenuCF.setPreferredSize(new Dimension(220, 220));
		btnMenuCF.setIcon(new ImageIcon("images/menuCF.png"));
		btnMenuCF.setFocusPainted(false);
		btnMenuCF.setContentAreaFilled(false);
		btnMenuCF.setBorderPainted(false);
		
		JPanel p_Center = new JPanel();
		p_Center.setBackground(new Color(255,0,0,0));
		p_Center.setBounds(250, 104, 540, 484);
		contentPane.add(p_Center);
		p_Center.setLayout(null);
		
		JButton btnOrder = new JButton("주문", order_img);
		btnOrder.setBounds(3, 5, 174, 410);
		btnOrder.setFocusPainted(false);
		btnOrder.setContentAreaFilled(false);
		btnOrder.setBorderPainted(false);
		btnOrder.setPreferredSize(new Dimension(170, 410));
		p_Center.add(btnOrder);
		
		JButton btnGame = new JButton("게임", games_img);
		btnGame.setBounds(185, 5, 174, 410);
		btnGame.setContentAreaFilled(false);
		btnGame.setBorderPainted(false);
		btnGame.setFocusPainted(false);
		btnGame.setPreferredSize(new Dimension(170, 410));
		p_Center.add(btnGame);
		
		JButton btnChatting = new JButton("채팅", talking_img);
		btnChatting.setBounds(364, 5, 174, 410);
		btnChatting.setFocusPainted(false);
		btnChatting.setContentAreaFilled(false);
		btnChatting.setBorderPainted(false);
		btnChatting.setPreferredSize(new Dimension(170, 410));
		p_Center.add(btnChatting);
		
		JButton btnEvent = new JButton("광고&이벤트",adbtn_img );
		btnEvent.setBounds(396, 421, 140, 53);
		p_Center.add(btnEvent);
		btnEvent.setContentAreaFilled(false);
		btnEvent.setBorderPainted(false);
		btnEvent.setFocusPainted(false);
		btnEvent.setPreferredSize(new Dimension(140, 53));
		
		MainListener l = new MainListener(this);
		btnOrder.addActionListener(l);
		btnGame.addActionListener(l);
		btnChatting.addActionListener(l);
	}
	
   class MainListener implements ActionListener{

	      MainFrame frame;
	      MainListener(MainFrame frame)
	      {
	         this.frame = frame;
	      }
	      
	      @Override
	      public void actionPerformed(ActionEvent arg0) {
	         // TODO Auto-generated method stub
         
         String m_cmd = arg0.getActionCommand();
         
         switch(m_cmd)
         {
         case "주문":
            setVisible(false);
            OrderFrame orderFrame = new OrderFrame(frame);
            orderFrame.setOrderFrame(orderFrame);
            break;
         case "게임":
            setVisible(false);
            new GameMenuFrame(frame);
            break;
         case "채팅":
            setVisible(false);
//            new ChattingFrame(frame);
            new ChattingFrame();
            break;
         default:
            break;
         }
      }
      
}  
}
