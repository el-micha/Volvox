package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		if (grid == null)
			return;
		
		int x = e.getX();
		int y = e.getY();
		System.out.println("Click at " + x + "/" + y);
		
		grid.setCell(x, y);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
