package TP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Instant;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Playspace extends JPanel  {
	private Timer timer;
	private int ballSpeed,
			width,//Blackwidth
			height,//BlackHeigth
			ball,//ball width
				
			wBrique,
			hBrique,
			xBrique,
			yBrique,
			xTranslate ,
			yTranslate ,
			ballXpos  ,
			ballYpos ,
			Xwallspace,
			Ywallspace;
	
	
	private int brickWidth;
	private int score,i;//i start of the game
	private  boolean TIME_OVER;
	private int GameDuration;
	private Chrono chrono;
	private Brick[][] bricks;
	private Image brick ,background;
	
	private JOptionPane DialogMessage;
	//Constructor	
	public Playspace() {
		super();
		playspaceInitialize();
		
		for(int l=0;l<3;l++) {
			for(int c=l;c<8-l;c++ ) {
				bricks[l][c]= new Brick(true,Xwallspace+c*(64),Ywallspace+l*64);
			}
		}
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		this.setPreferredSize(new Dimension(width, height));
	
		
		timer = new Timer(ballSpeed,new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Check if the ball touch the brick
				for(int l=0;l<3;l++) {
					for(int c=l;c<8-l;c++ ) {
						if(new Rectangle(ballXpos, ballYpos, ball, ball)
								.intersects(new Rectangle(bricks[l][c].getxCoor(),bricks[l][c].getyCoor() , brickWidth, brickWidth))
								&& bricks[l][c].isActive()) {
							score+=5;
							xTranslate=-xTranslate;
							yTranslate=-yTranslate;
							bricks[l][c].setActive(false);
						}
					}
				}
				
				if(new Rectangle(ballXpos, ballYpos, ball, ball)
						.intersects( new Rectangle(xBrique,yBrique,wBrique , hBrique) ))
				{yTranslate = -yTranslate;
				
				}
				
				
				if(ballXpos >=width-ball || ballXpos <=0) {
					xTranslate = -xTranslate;
				}
				if(ballYpos < 0 /*|| ballYpos >height */ ) {
					yTranslate = -yTranslate;
				}
				ballXpos+=xTranslate;
				ballYpos+=yTranslate;
				if(chrono.getDureeSec()>=120) TIME_OVER=true;
				if(ballYpos >=height || TIME_OVER ) {
					
					endOfGame();
				}
				
		
				repaint();
			}
		});
		
		
			//timer.start();
		
		this.addKeyListener(new KeyAdapter() {
			 
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					xBrique+=15;
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					xBrique-=15;
				}
				if(e.getKeyCode()==KeyEvent.VK_UP && i==0) {
					
					timer.start();
					
					chrono.start();
					//startGame= Instant.now();
					//System.out.println("start  "+startGame);
					i++;
					//System.out.println(i);
				}
				if(xBrique>=width-(width/10)){
				xBrique=width-(width/10);
				}
				if(xBrique<=0){
					xBrique=0;
					}
				
				repaint();
				
				
			}
		});
		
	
	}
	// check if the ball touch a brick
	
	private void endOfGame()
	{	String message="";
		timer.stop();
		chrono.stop();
		if(TIME_OVER)
		{
			message="Good Job, you win!\nYour Score is "+score;
		}
		else { message="Game Over!\nYourScore is "+score;
			
		}
		DialogMessage.showMessageDialog(null, message,
				"Game Over!", 
				JOptionPane.INFORMATION_MESSAGE, null);
		xTranslate=yTranslate=0;
		ballXpos=(width/2)-(ball/2);
				ballYpos = height-(ball+hBrique);
				xBrique=width/2-wBrique/2;
				yBrique=height-hBrique;
	}
	public void paint(Graphics g) {
		
		
		g.drawImage(background, 0, 0, width, height, null);
		g.setColor(Color.RED);
		g.setFont(new Font("Serif", Font.BOLD+getFont().ITALIC, 60));
		g.drawString("Brick Breaker", width/4, g.getFontMetrics().getAscent()+10);
		
		
		g.setColor(Color.cyan);
		g.fillRoundRect(xBrique,yBrique,wBrique , hBrique,15,15);
		g.setColor(Color.white);
	
		g.drawRoundRect(xBrique,yBrique,wBrique , hBrique, 15, 15);
		
		g.setColor(Color.white);
		g.setFont(new Font("Sansserif", Font.BOLD, 20));
		g.drawString("Score: "+score, 10, g.getFontMetrics().getAscent()+10);
		
		if(chrono.getDureeSec()>=100)g.setColor(Color.red);
		g.drawString("Time "+chrono.nowChrono(), width-100, g.getFontMetrics().getAscent()+10);
		//Draw the Bricks
		g.setColor(Color.WHITE);
		
		for(int l=0;l<3;l++) {
			for(int c=l;c<8-l;c++ ) {
				if(bricks[l][c].isActive() )
					g.fillRect(bricks[l][c].getxCoor(),bricks[l][c].getyCoor() , brickWidth, brickWidth);
				//	g.drawImage(brick, bricks[l][c].getxCoor(), bricks[l][c].getyCoor(), brickWidth, brickWidth, null);
				
			}
		}
		//draw Ball
				g.setColor(Color.white);
				g.fillOval(ballXpos, ballYpos, ball, ball);
				g.setColor(Color.white);
				g.drawOval(ballXpos, ballYpos, ball, ball);
		g.dispose();
		
	
	}
	public void playspaceInitialize() {
		ballSpeed = 30;       
		width=700;//Blackwidth
		height=600;//BlackHeigth
		ball=20;//ball width
		wBrique=width/10;// width of the  white skateboard
		hBrique=12;//  height of the white skateboard
		xBrique=width/2-wBrique/2;//the x position of  the  white skateboard
		yBrique=height-hBrique;//the y position of  the  white skateboard
		xTranslate = 5;
		yTranslate = -5;
		ballXpos =(width/2)-(ball/2);
		ballYpos = height-(ball+hBrique);
		brickWidth=60;//the bricks width
		
		GameDuration=2;//2 min
		chrono= new Chrono();
				
		DialogMessage = new JOptionPane();
		score=0;
		i=0;
		Xwallspace=width/7;Ywallspace=90;
		
		
		background= new ImageIcon("resources/backgroung.jpg").getImage();
		bricks= new Brick[3][8];
		
	}
}
