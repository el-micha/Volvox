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
	static Grid theGrid;
	
	public GridDrawer(Grid grid)
	{
		theGrid = grid;
	}
	
	public void drawGrid(Graphics2D context, JPanel panel)
	{
		int currentCellInt;
		for (int i = 0; i < theGrid.width; i++)
		{
			for (int j = 0; j < theGrid.height; j++)
			{
				currentCellInt = theGrid.getGrid()[i][j];
				
				if (currentCellInt == 0)
				{
					context.drawImage(air, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else if (currentCellInt == 1)
				{
					context.drawImage(water, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else if (currentCellInt == 2)
				{
					context.drawImage(soil, i * CELL_SIZE, j * CELL_SIZE, panel);
				}
				else
				{
					System.out.println("Unknown cell type: " + currentCellInt);
				}
			}
		}
	}
	
}
