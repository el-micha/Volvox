package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class InputListener implements MouseListener
{
	private Grid grid;
	
	public InputListener()
	{
		
	}
	
	public void registerGrid(Grid aGrid)
	{
		grid = aGrid;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (grid == null)
			return;
		
		int x = e.getX() - 4;	//window offsets
		int y = e.getY() - 30;
		Ulf.out("Click at " + x + "/" + y);
		
		grid.setCell(x, y, 2);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
		
	}
	
	
}
