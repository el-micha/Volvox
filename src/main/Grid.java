package main;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class Grid implements Constants
{
	private int [] [] intGrid;
	public int width;
	public int height;
	private GridDrawer myDrawer;
	
	public Grid(int mWidth, int mHeight)
	{
		width = mWidth;
		height = mHeight;
		intGrid = new int [width][height];
		Random random = new Random();
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int r = random.nextInt(100);
				
				if (r > 90)
					intGrid[i][j] = 0;
				else 
				{
					intGrid[i][j] = 1;
				}
			}
		}
		//intGrid[7][10] = 1;
		
		myDrawer = new GridDrawer(this);
	}
	
	public Grid(int mWidth, int mHeight, boolean filled)
	{
		width = mWidth;
		height = mHeight;
		intGrid = new int [width][height];
		
		myDrawer = new GridDrawer(this);
	}
	
	public void tick(int gameTick)
	{
		Grid newGrid = new Grid(width, height, false);
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int neighbours = 0;
				
				
				if (neighbours > 90)
					intGrid[i][j] = 0;
				else 
				{
					intGrid[i][j] = 1;
				}
			}
		}
	}
	
	
	
	public void setCell(int x, int y, int cellType)
	{
		//TODO: mehr abfangen
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		intGrid[(int)Math.round(x/8)][(int)Math.round(y/8)] = cellType;
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		myDrawer.drawGrid(context, panel);
	}
	
	public int getCellAt(int x, int y)
	{
		//TODO: abfangen!
		return intGrid[x][y];
	}
	
	public int [][] getGrid()
	{
		return intGrid;
	}
	
}
