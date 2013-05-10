package main;

import java.awt.Color;

import javax.swing.JFrame;

public class VolvoxSim extends JFrame implements Constants
{
	public VolvoxSim()
	{

		Board board = new Board();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
		setBackground(Color.RED);
        setLocationRelativeTo(null);
        setTitle("Volvox");
        setResizable(true);
		setVisible(true);
		
		
		add(board);
		
		//InputListener inputListener = new InputListener(board, scrollFrame);
		//addKeyListener(inputListener);
		//addMouseListener(inputListener);
		
		
		
	}
	
	public static void main(String [] args)
	{
		System.out.println("hello world");
		new VolvoxSim();
		System.out.println("bye world");
	}
}
