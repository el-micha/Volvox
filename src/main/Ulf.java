package main;

public class Ulf implements Constants
{
	static public void out(String s)
	{
		if (!OUTPUT)
			return;
		System.out.println(s);
	}
}