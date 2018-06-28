package TP;

import javax.swing.JFrame;

public class Main extends JFrame {
	
	public Main(String ch) {
		super(ch);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	//	this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(new Playspace());
		this.pack();
		this.setVisible(true);
	}
	public static void main(String[] args) {
		
		Main m = new Main("Brick Breaker");
	}

}
