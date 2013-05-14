package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Grid 
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
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				intGrid[i][j] = 0;
			}
		}
		//intGrid[7][10] = 1;
		
		myDrawer = new GridDrawer(this);
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		myDrawer.drawGrid(context, panel);
	}
	
	public int [][] getGrid()
	{
		return intGrid;
	}
}
