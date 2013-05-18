package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class InputListener implements MouseListener, Constants
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
		
		int x = e.getX() - WINDOW_FRAME_OFFSET_X;	//window offsets
		int y = e.getY() - WINDOW_FRAME_OFFSET_Y;
		Ulf.out("Click at " + x + "/" + y);
		
		grid.setCell(x, y, 2);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
		
	}
	
	
}
