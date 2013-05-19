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
				
				if (r > 80)
					getCellAt(i, j).setType(LIVING_CELL);
				else 
				{
					getCellAt(i, j).setType(DEAD_CELL);
				}
			}
		}
		
		myDrawer = new GridDrawer(this);
	}
	
	
	public void tick(int gameTick)
	{
		Grid newGrid = new Grid(width, height, false);
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				int neighbours = getNeighbours(i, j);
				
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
		
		cellGrid = newGrid.getGrid();
	}
	
	private int getNeigboursOnTorus(int x, int y)
	{
		return 0;
	}
	
	private int getNeighbours(int x, int y)
	{
		int neighbours = 0;
		if (x > 0)
		{
			if (getCellAt(x - 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(x - 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		if (x < width - 1)
		{
			if (getCellAt(x + 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(x + 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x + 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		if (y > 0)
		{
			if (getCellAt(x, y - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		if (y < height - 1)
		{
			if (getCellAt(x, y + 1).getType() == LIVING_CELL)
				neighbours++;
		}
		
		return neighbours;
	}
	
	public void setCell(int x, int y, int cellType)
	{
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		getCellAt((int)Math.round(x/8), (int)Math.round(y/8)).setType(cellType);
	}
	
	public Cell getCellAt(int x, int y)
	{
		return cellGrid[x][y];
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		myDrawer.drawGrid(context, panel);
	}
	
	private Cell[][] getGrid()
	{
		return cellGrid;
	}
	
}
