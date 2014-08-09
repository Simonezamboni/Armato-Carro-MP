import java.awt.*;

public class SysInfos extends Frame
{	
	public SysInfos(int remainShootN)
	{		
		super("Informazioni");
	    setLocation(1000,100);
	    setSize(1400,100);

	    Label numero = new Label ("ancora\t" + remainShootN);
	    
	    add(numero);
	    pack();
	    
	    setVisible(true);
	}
	
	public void update(int newValue)
	{
		new SysInfos (newValue);
	}
	
	

}
