/*STUDENT MANAGEMENT SYSTEM
	@El Hackeado
*/

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class sms extends Frame implements ActionListener,ItemListener
{
	Label l1,l2,l3,l4;
	TextField t1,t2,t3,t4;
	Button b1,b2,b3,b4;
	List ls;
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	sms(String s)
	{
		super(s);
		setLayout(new BorderLayout());
		
		l1 = new Label("RollNo");
		l2 = new Label("Name");
		l3 = new Label("Address");
		l4 = new Label("Marks");
		
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		
		b1 = new Button("SAVE");
		b2 = new Button("VIEW");
		b3 = new Button("DELETE");
		b4 = new Button("UPDATE");
		
		ls = new List();
		
		Panel p = new Panel(new GridLayout(6,2));
		
		p.add(l1);	p.add(t1);
		p.add(l2);	p.add(t2);
		p.add(l3);	p.add(t3);
		p.add(l4);	p.add(t4);
		p.add(b1);	p.add(b2);
		p.add(b3);	p.add(b4);
		
		add(p,BorderLayout.CENTER);
		add(ls,BorderLayout.EAST);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		ls.addItemListener(this);
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/data";
			con = DriverManager.getConnection(url,"root","");
			stmt = con.createStatement();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b1)
		{
			save();
		}
		else if(ae.getSource()==b2)
		{
			view();
		}
		else if(ae.getSource()==b3)
		{
			delete();
		}
		else
		{
			update();
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		try
		{
			String qry = "select * from sms where rollno=" + ls.getSelectedItem();
			
			rs = stmt.executeQuery(qry);
			if(rs.next())
			{
			t1.setText(rs.getString("rollno"));
			t2.setText(rs.getString("name"));
			t3.setText(rs.getString("address"));
			t4.setText(rs.getString("marks"));
			}
			t1.setEditable(false);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void save()
	{
		try 
		{
			String r = t1.getText();
			String nm = t2.getText();
			String ad = t3.getText();
			String m = t4.getText();
			String qry = "insert into sms values(" + r + ", '" + nm + "', '" + ad + "', " + m + ")";
			
			stmt.executeUpdate(qry);
			
			clear();
			view();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void view()
	{
		try 
		{
			String qry = "select rollno from sms";
			rs=stmt.executeQuery(qry);
			ls.removeAll();
			while(rs.next())
			{
				ls.add(rs.getString("rollno"));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void update()
	{
		try
		{
			String r = t1.getText();
			String nm = t2.getText();
			String ad = t3.getText();
			String m = t4.getText();
			
			String qry = "update sms set name='" + nm + "',address='" + ad + "',marks=" + m + " where rollno=" + r;
			stmt.executeUpdate(qry);
			clear();
			 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	void delete()
	{
		try 
		{
			String qry = "delete from sms where rollno=" + t1.getText();
			
			stmt.executeUpdate(qry);
			clear();
			view();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void clear()
	{
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		
		t1.setEditable(true);
	}
	
	public static void main(String args[])
	{
		sms obj = new sms("STUDENT MANAGEMENT SYSTEM");
		obj.setVisible(true);
		obj.setSize(300,300);
	}

}	