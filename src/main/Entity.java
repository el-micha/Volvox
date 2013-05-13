package main;

public class Entity 
{
	public Physics mPhysics;
	public Drawer mDrawer;
	
	public Entity()
	{
		mPhysics = new Physics(50, 50, this);
		mDrawer = new Drawer("testcell.png", this);
	}
	
	
}
