package main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Drawer implements Constants
{
	private static ArrayList<Drawer> allDrawers;
	
	public Entity mParent;
	private Image image;
	
	public Drawer(String imageName, Entity parent) 
	{
		ImageIcon ii = new ImageIcon(IMG_PATH + imageName);
        image = ii.getImage();
		
		mParent = parent;
		
		allDrawers.add(this);
	}
	
	public static void drawAll(Graphics2D context, JPanel panel)
	{
		for (int i = 0; i < allDrawers.size(); i++)
		{
			allDrawers.get(i).draw(context, panel);
		}
	}
	
	private void draw(Graphics2D context, JPanel panel)
	{
		context.drawImage(image, (int) Math.round(mParent.mPhysics.x), (int) Math.round(mParent.mPhysics.y), panel);
	}
}
