package main;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GridDrawer implements Constants
{
	static Image air = (new ImageIcon(IMG_PATH + "air.png")).getImage();
	static Image soil = (new ImageIcon(IMG_PATH + "soil.png")).getImage();
	static Image water = (new ImageIcon(IMG_PATH + "water.png")).getImage();
	static Image innerCell;
	static Image borderCell;
	static Grid grid;
	
	public GridDrawer(Grid aGrid)
	{
		grid = aGrid;
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		int currentCellType;
		for (int i = 0; i < grid.width; i++)
		{
			for (int j = 0; j < grid.height; j++)
			{
				currentCellType = grid.getCellAt(i, j).getType();
				
				if (currentCellType == 0)
				{
					context.drawImage(air, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else if (currentCellType == 1)
				{
					context.drawImage(water, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else if (currentCellType == 2)
				{
					context.drawImage(soil, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else
				{
					System.out.println("Unknown cell type: " + currentCellType);
				}
			}
		}
	}
	
}
