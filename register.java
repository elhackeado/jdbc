//new user
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class register extends JFrame implements ActionListener
{
	JLabel jl1,jl2;
	JTextField jt1;
	JPasswordField jp1;
	JButton jb1,jb2;
	
	register(String s)
	{
		super(s);
		
		setLayout(new FlowLayout());
		
		jl1 = new JLabel("USERNAME");
		jl2 = new JLabel("PASSWORD");
		jt1 = new JTextField(15);
		jp1 = new JPasswordField(15);
		jb1 = new JButton("REGISTER");
		jb2 = new JButton("CANCEL");
		
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
			String qry = "insert into user values ('" + un + "' , '" + up +"')";

			stmt.executeUpdate(qry);
			stmt.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"REGISTERED SUCCESSFULLY");
			
			login l1 = new login("LOGIN");
			l1.setVisible(true);
			l1.setSize(300,300);
			this.dispose();
			
			
		}
		else
		{
			dispose();
		}
		
		
		
		}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
		
		
	}
}