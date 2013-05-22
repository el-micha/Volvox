package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, Constants
{
	private VolvoxSim parent;
	private Thread simulator;
	public boolean running = true;
	private int tick = 0;
	public double secondaryWeight = SECONDARY_WEIGHT;
	
	private Grid theGrid;
	
	public Board()
	{
		setBackground(Color.DARK_GRAY);
		setDoubleBuffered(true);
		setSize(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
		//setLocation(100, 100);
		
		theGrid = new Grid(GAMEBOARD_WIDTH / CELL_SIZE, GAMEBOARD_HEIGHT / CELL_SIZE, true);
		
	}
	
	public void registerParent(VolvoxSim aParent)
	{
		parent = aParent;
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
		parent.getInputListener().registerGrid(theGrid);
		parent.getInputListener().registerBoard(this);
		
		Ulf.out("running now");
		sleepMS(400);
		//initializeEntities();
		//better do that in constructor of Board
		
		long timeDiff, sleep, beforeTime = System.currentTimeMillis();
		while (true)
		{
			tick++; //System.out.println("Tick: " + tick);
			
			//secondaryWeight = -2 * Math.sin(tick/(2*Math.PI*300));
			Ulf.out("Weight: " + secondaryWeight);
			
			//tickEntities(tick);
			if (running)
			{
				theGrid.tick(tick, secondaryWeight);				
			}
			
			repaint();
			
			/*
			//pause on buttonclick
			while (!running)
			{
				try { Thread.sleep(100); }
				catch (InterruptedException e)
				{e.printStackTrace(); }
			}
			*/
			
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY_MS - timeDiff;
			if (sleep <= 0)
				sleep = 2;
			sleepMS(sleep);
			beforeTime = System.currentTimeMillis();
			
		}

	}
	
	private void sleepMS(long delay)
	{
		try
		{
			Thread.sleep(delay, DELAY_NS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
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