package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements MouseListener, KeyListener, Constants
{
	private Board board;
	private Grid grid;
	
	public InputListener()
	{
		
	}
	
	public void registerGrid(Grid aGrid)
	{
		grid = aGrid;
	}
	public void registerBoard(Board aBoard)
	{
		board = aBoard;
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
		
		//grid.setCell(x, y, 1);
		grid.invertCell(x, y);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (grid == null)
			return;
		Ulf.out("" + e.getKeyChar());
		if (e.getKeyChar() == 'p')
			board.running = !board.running;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
	
	
}
