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
	private double secondaryWeight;
	
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
				//int r = random.nextInt((300*i)/width + 1);
				int r = random.nextInt(100);
				
				if (r < INIT_DENSITY)
					getCellAt(i, j).setType(LIVING_CELL);
				else 
				{
					getCellAt(i, j).setType(DEAD_CELL);
				}
			}
		}
		
		myDrawer = new GridDrawer(this);
	}
	
	
	public void tick(int gameTick, double secWeight)
	{
		secondaryWeight = secWeight;
		
		Grid newGrid = new Grid(width, height, false);
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				
				//int neighbours = getSphericalNeighbours(i, j);
				int neighbours = getSecondNeighbours(i, j);
				//int neighbours = getNeighbours(i, j);
				
				
				if (getCellAt(i, j).getType() == LIVING_CELL)
					if ((neighbours >= RULE_SURVIVE_MIN) && (neighbours <= RULE_SURVIVE_MAX))
						newGrid.getCellAt(i, j).setType(LIVING_CELL);
					else 
					{
						newGrid.getCellAt(i, j).setType(DEAD_CELL);
					}
				else if (getCellAt(i, j).getType() == DEAD_CELL)
				{
					if ((neighbours >= RULE_BIRTH_MIN) && (neighbours <= RULE_BIRTH_MAX))
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
			else 
			{
				if (getCellAt(x - 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(x - 1, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else 
		{
			if (getCellAt(width - 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(width - 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(width - 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(width - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt(width - 1, 0).getType() == LIVING_CELL)
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
			else 
			{
				if (getCellAt(x + 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x + 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(x + 1, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else
		{
			if (getCellAt(0, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(0, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt(0, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(0, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(0, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		if (y > 0)
		{
			if (getCellAt(x, y - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else 
		{
			if (getCellAt(x, height - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		if (y < height - 1)
		{
			if (getCellAt(x, y + 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else
		{
			if (getCellAt(x, 0).getType() == LIVING_CELL)
				neighbours++;
		}
		
		return neighbours;
	}
	
	
	private int getSphericalNeighbours(int x, int y)
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
			else 
			{
				if (getCellAt((x - 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt((x - 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else 
		{
			if (getCellAt(width - 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(width - 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt((x - 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(width - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt((x - 1 + (width/2))%width, y).getType() == LIVING_CELL)
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
			else 
			{
				if (getCellAt((x + 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x + 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt((x + 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else
		{
			if (getCellAt(0, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(0, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt((x - 1 + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(0, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt((x + (width/2))%width, y).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		if (y > 0)
		{
			if (getCellAt(x, y - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else 
		{
			if (getCellAt((x + (width/2))%width, y).getType() == LIVING_CELL)
				neighbours++;
		}
		if (y < height - 1)
		{
			if (getCellAt(x, y + 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else
		{
			if (getCellAt((x + (width/2))%width, y).getType() == LIVING_CELL)
				neighbours++;
		}
		
		return neighbours;
	}
	
	private int getSecondNeighbours(int x, int y)
	{
		double neighbours = 0;
		if (x > 0)
		{
			if (getCellAt(x - 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(x - 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(x - 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(x - 1, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else 
		{
			if (getCellAt(width - 1, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(width - 1, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(width - 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(width - 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt(width - 1, 0).getType() == LIVING_CELL)
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
			else 
			{
				if (getCellAt(x + 1, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(x + 1, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(x + 1, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		else
		{
			if (getCellAt(0, y).getType() == LIVING_CELL)
				neighbours++;
			if (y > 0)
			{
				if (getCellAt(0, y - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else
			{
				if (getCellAt(0, height - 1).getType() == LIVING_CELL)
					neighbours++;
			}
			if (y < height - 1)
			{
				if (getCellAt(0, y + 1).getType() == LIVING_CELL)
					neighbours++;
			}
			else 
			{
				if (getCellAt(0, 0).getType() == LIVING_CELL)
					neighbours++;
			}
		}
		if (y > 0)
		{
			if (getCellAt(x, y - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else 
		{
			if (getCellAt(x, height - 1).getType() == LIVING_CELL)
				neighbours++;
		}
		if (y < height - 1)
		{
			if (getCellAt(x, y + 1).getType() == LIVING_CELL)
				neighbours++;
		}
		else
		{
			if (getCellAt(x, 0).getType() == LIVING_CELL)
				neighbours++;
		}
		//##########################################################
		if (x > 1)
		{
			if (getCellAt(x - 2, y).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (y > 0)
			{
				if (getCellAt(x - 2, y - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x - 2, height - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 1)
			{
				if (getCellAt(x - 2, y + 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x - 2, 0).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y > 1)
			{
				if (getCellAt(x - 2, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x - 2, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 2)
			{
				if (getCellAt(x - 2, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x - 2, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		else 
		{
			if (getCellAt(width - 2, y).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (y > 0)
			{
				if (getCellAt(width - 2, y - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 2, height - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 1)
			{
				if (getCellAt(width - 2, y + 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 2, 0).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y > 1)
			{
				if (getCellAt(width - 2, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 2, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 2)
			{
				if (getCellAt(width - 2, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 2, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		if (x < width - 2)
		{
			if (getCellAt(x + 2, y).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (y > 0)
			{
				if (getCellAt(x + 2, y - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x + 2, height - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 1)
			{
				if (getCellAt(x + 2, y + 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(x + 2, 0).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y > 1)
			{
				if (getCellAt(x + 2, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(x + 2, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 2)
			{
				if (getCellAt(x + 2, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(x + 2, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		else
		{
			if (getCellAt(1, y).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (y > 0)
			{
				if (getCellAt(1, y - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(1, height - 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 1)
			{
				if (getCellAt(1, y + 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(1, 0).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y > 1)
			{
				if (getCellAt(1, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(1, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (y < height - 2)
			{
				if (getCellAt(1, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(1, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		if (y > 1)
		{
			if (getCellAt(x, y - 2).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (x > 0)
			{
				if (getCellAt(x - 1, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(width - 1, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (x < width - 1)
			{
				if (getCellAt(x + 1, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(0, y - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		else 
		{
			if (getCellAt(x, height - 2).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (x > 0)
			{
				if (getCellAt(x - 1, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(width - 1, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (x < width - 1)
			{
				if (getCellAt(x + 1, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(0, height - 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		if (y < height - 2)
		{
			if (getCellAt(x, y + 2).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (x > 0)
			{
				if (getCellAt(x - 1, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 1, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (x < width - 1)
			{
				if (getCellAt(x + 1, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(0, y + 2).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		else 
		{
			if (getCellAt(x, 1).getType() == LIVING_CELL)
				neighbours+=secondaryWeight;
			if (x > 0)
			{
				if (getCellAt(x - 1, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else 
			{
				if (getCellAt(width - 1, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			if (x < width - 1)
			{
				if (getCellAt(x + 1, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
			else
			{
				if (getCellAt(0, 1).getType() == LIVING_CELL)
					neighbours+=secondaryWeight;
			}
		}
		
		return (int)Math.round(neighbours);
	}
	
	
	public void setCell(int x, int y, int cellType)
	{
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		getCellAt((int)Math.round(x/CELL_SIZE), (int)Math.round(y/CELL_SIZE)).setType(cellType);
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


	public void invertCell(int x, int y) 
	{
		if (x < 0 || x > GAMEBOARD_WIDTH || y < 0 || y > GAMEBOARD_HEIGHT)
			return;
		int type = getCellAt((int)Math.round(x/CELL_SIZE), (int)Math.round(y/CELL_SIZE)).getType();
		if (type == 0)
			type = 1;
		else if (type == 1)
			type = 0;
		else {type = 0;}
		getCellAt((int)Math.round(x/CELL_SIZE), (int)Math.round(y/CELL_SIZE)).setType(type);
	}
	
}
