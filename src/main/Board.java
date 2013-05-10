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
	
	public Board()
	{
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		setSize(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
		setLocation(100, 100);
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
		//repaint();
		
		long timeDiff, sleep, beforeTime = System.currentTimeMillis();
		while (true)
		{
			tick++; //System.out.println("Tick: " + tick);
			
			
			//tickEntities(tick);
			//repaint();
			
			
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
		
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
}