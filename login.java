//login 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class login extends JFrame implements ActionListener
{
	JTextField jt1;
	JPasswordField jp1;
	JLabel jl1,jl2;
	JButton jb1,jb2;
	
	login(String s)
	{
		super(s);
		
		setLayout(new FlowLayout());
		
		jl1 = new JLabel("USERNAME");
		jl2 = new JLabel("PASSWORD");
		jt1 = new JTextField(15);
		jp1 = new JPasswordField(15);
		jb1 = new JButton("LOGIN");
		jb2 = new JButton("NEW USER");
		
		add(jl1);
		add(jt1);
		add(jl2);
		add(jp1);
		add(jb1);
		add(jb2);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		
		}
	
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
		if(ae.getSource()==jb1)
		{
		
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost/data";
				Connection con = DriverManager.getConnection(url,"root","");
				Statement stmt = con.createStatement();
				String un = jt1.getText();
				String up = jp1.getText();
				String qry = "select * from user where username='" + un + "' and password='" + up +"'";
				ResultSet rs = stmt.executeQuery(qry);
				
				if(rs.next())
				{
					JOptionPane.showMessageDialog(null,"WELCOME USER");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"USERNAME OR PASSWoRD INCORRECT");
				}
				
		}
			else
			{
				register r1 = new register("REGISTER");
				r1.setVisible(true);
				r1.setSize(300,300);
				r1.setLocation(400,400);
				this.dispose();
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	
	
	}
	
	public static void main(String args[])
	{
		login l1=new login("LOGIN");
		l1.setVisible(true);
		l1.setSize(300,300);
	}
}