package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Constants
{
	private Thread simulator;
	public boolean running = true;
	private int tick = 0;
	
	private Grid theGrid;
	
	public Board()
	{
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		setSize(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
		setLocation(100, 100);
		
		theGrid = new Grid(GAMEBOARD_WIDTH / CELL_SIZE, GAMEBOARD_HEIGHT / CELL_SIZE);
		
	}

	public void addNotify()
	{
		super.addNotify();
		simulator = new Thread(this);
		simulator.start();
	}
	
	@Override
	public void run()
	{
		System.out.println("running now");
		//initializeEntities();
		//better do that in constructor of Board
		
		long timeDiff, sleep, beforeTime = System.currentTimeMillis();
		while (true)
		{
			tick++; //System.out.println("Tick: " + tick);
			
			
			//tickEntities(tick);
			repaint();
			
			//pause on buttonclick
			while (!running)
			{
				try { Thread.sleep(100); }
				catch (InterruptedException e)
				{e.printStackTrace(); }
			}
			
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY_MS - timeDiff;
			if (sleep <= 0)
				sleep = 2;
			try
			{
				Thread.sleep(sleep, DELAY_NS);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			beforeTime = System.currentTimeMillis();
			
		}

	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		theGrid.drawGrid(g2d, this);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
}