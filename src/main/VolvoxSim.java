package main;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class VolvoxSim extends JFrame implements Constants
{
	Board board;
	InputListener listener;
	
	public VolvoxSim()
	{
		board = new Board();
		board.registerParent(this);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
		setBackground(Color.RED);
        setLocationRelativeTo(null);
        setTitle("Volvox");
        setResizable(true);
		setVisible(true);
		
		
		
		listener = new InputListener();
		
		
		add(board);
		addMouseListener(listener);
		
		//InputListener inputListener = new InputListener(board, scrollFrame);
		//addKeyListener(inputListener);
		//addMouseListener(inputListener);
		
		
		
	}
	
	public InputListener getInputListener()
	{
		return listener;
	}
	public Board getBoard()
	{
		return board;
	}
	
	public static void main(String [] args)
	{
		System.out.println("hello world");
		new VolvoxSim();
		System.out.println("bye world");
	}
}
