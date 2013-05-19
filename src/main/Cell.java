package main;

public class Cell implements Constants
{
	public int type;
	public int x;
	public int y;
	
	public Cell(int iX, int iY, int iType)
	{
		type = iType;
		x = iX;
		y = iY;
	}
	
	public int getType()
	{
		return type;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
	public void setType(int nType)
	{
		type = nType;
	}
}
