import java.awt.*;


class frame extends Frame
{
	Choice c1;
	
	frame(String s)
	{
		super(s);
		
		setLayout(new BorderLayout());
		Panel p1 = new Panel();
		p1.setLayout(new GridLayout(1,1));
		c1 = new Choice();
		p1.add(c1);
		add(p1,BorderLayout.NORTH);
		
		
		
	}
	
	public static void main(String args[])
	{
		frame f1 = new frame("APPS");
		f1.setVisible(true);
		f1.setSize(300,300);
	}
}