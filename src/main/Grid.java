package main;

import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class Grid implements Constants
{
	private Cell [] [] cellGrid;
	public int width;
	public int height;
	private GridDrawer myDrawer;
	
	private static final int LIVING_CELL = 1;
	private static final int DEAD_CELL = 0;
	
	public Grid(int mWidth, int mHeight, boolean filled)
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
		
		if (!filled)
			{myDrawer = new GridDrawer(this); return;}
		
		Random random = new Random();
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int r = random.nextInt(100);
				
				if (r > 90)
					getCellAt(i, j).setType(LIVING_CELL);
				else 
				{
					getCellAt(i, j).setType(DEAD_CELL);
				}
			}
		}
		//intGrid[7][10] = 1;
		
		myDrawer = new GridDrawer(this);
	}
	
	
	public void tick(int gameTick)
	{
		Grid newGrid = new Grid(width, height, false);
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int neighbours = getNeigbours(i, j);
				
				if (getCellAt(i, j).getType() == LIVING_CELL)
					if ((neighbours < 2) || (neighbours > 3))
						newGrid.getCellAt(i, j).setType(DEAD_CELL);
					else 
					{
						newGrid.getCellAt(i, j).setType(LIVING_CELL);
					}
				else if (getCellAt(i, j).getType() == DEAD_CELL)
				{
					if (neighbours == 3)
						newGrid.getCellAt(i, j).setType(LIVING_CELL);
					else 
					{
						newGrid.getCellAt(i, j).setType(DEAD_CELL);
					}
				}
				else
				{
					Ulf.out("strange cell type");
					newGrid.getCellAt(i, j).setType(getCellAt(i, j).getType());
				}
			}
		}
	}
	
	private int getNeigbours(int x, int y)
	{
		return 0;
	}
	
	private int getNeighboursOnTorus(int x, int y)
	{
		return 0;
	}
	
	public void setCell(int x, int y, int cellType)
	{
		//TODO: mehr abfangen
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		getCellAt((int)Math.round(x/8), (int)Math.round(y/8)).setType(cellType);
	}
	
	public Cell getCellAt(int x, int y)
	{
		//TODO: abfangen!
		return cellGrid[x][y];
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		myDrawer.drawGrid(context, panel);
	}
	
}
