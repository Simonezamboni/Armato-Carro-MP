package gameObjs;

public class Carro 
{
	private int x = 0; 
	private int y = 0;
	private int colorIndex = 0;
	private boolean shooting = false;
	private boolean canShoot = true;
	
	String[] AvailableColors = new String[] {"Blue", "Red", "Yellow", "White", "Green", "Brown"};
	String color = AvailableColors[colorIndex];
	
	public Carro (int x, int y, int colorIndex, boolean canShoot)
	{
		this.x = x; this.y = y; this.color = AvailableColors[colorIndex];
		
		this.canShoot = canShoot;
	}
	
	public Carro (int x, int y, int colorIndex)
	{
		this (x, y, colorIndex, true);
	}
	
	public Carro (int x, int y, boolean canShoot)
	{
		this (x, y, 0, canShoot);
	}
	
	public Carro (int x, int y)
	{
		this (x, y, 0);
	}
	
	public Carro (int colorIndex)
	{
		this (300, 300, colorIndex);
	}
	
	public Carro (boolean canShoot)
	{
		this (300, 300, canShoot);
	}
	
	public Carro ()
	{
		this (true);
	}
	
	public void Draw ()
	{
		
	}
	
}
