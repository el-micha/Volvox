package main;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Grid implements Constants
{
	private Cell [] [] cellGrid;
	public int width;
	public int height;
	private GridDrawer myDrawer;
	
	public Grid(int mWidth, int mHeight)
	{
		width = mWidth;
		height = mHeight;
		cellGrid = new Cell [width][height];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				cellGrid[i][j] = new Cell(i, j, 0);
			}
		}
		
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				if (j < height/2)
					getCellAt(i, j).setType(0);
				else 
				{
					getCellAt(i, j).setType(1);
				}
			}
		}
		//intGrid[7][10] = 1;
		
		myDrawer = new GridDrawer(this);
	}
	
	public void setCell(int x, int y, int cellType)
	{
		//TODO: mehr abfangen
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		getCellAt((int)Math.round(x/8), (int)Math.round(y/8)).setType(cellType);
	}
	 
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		myDrawer.drawGrid(context, panel);
	}
	
	public Cell getCellAt(int x, int y)
	{
		//TODO: abfangen!
		return cellGrid[x][y];
	}
	
	/*
	public void integrateCells(Volvox aVolvox)
	{
		int[][] cells = aVolvox.getCells();
		for (int i = 0; i < aVolvox.getCellCounter() && i < MAX_CELL_NUMBER; i++)
		{
			if (cells[i][0] == 0)
				continue;
			
			cellGrid[cells[i][0]][cells[i][1]] = cells[i][2];
			
		}
	}
	*/
}
