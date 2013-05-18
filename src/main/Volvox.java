package main;

public class Volvox implements Constants
{
	private int [][] cells;
	private int cellCounter = 0;	//counts every cell that ever lived
	
	public Volvox(int x, int y)
	{
		cells = new int [MAX_CELL_NUMBER][3];
		for (int i = 0; i < MAX_CELL_NUMBER; i++)
		{
			cells[i][0] = 0;	//x coord
			cells[i][1] = 0;	//y coord
			cells[i][2] = 0;	//cell type
		}
		
	}
	
	public void tick(int gameTick)
	{
		
	}
	
	
	
	public int [][] getCells()
	{
		return cells;
	}
	public int getCellCounter()
	{
		return cellCounter;
	}
	
}
