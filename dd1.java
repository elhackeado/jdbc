import java.sql.*;

class DB
{
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	public DB() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/dbname";
		Connection con = DriverManager.getConnection(url,"uname","pswd");
		stmt = con.createStatement();
	}
	
	public void update(String qry) throws Exception
	{
		stmt.executeUpdate(qry);
	}
	
	public ResultSet execute(String qry) throws Exception
	{
		rs=stmt.executeQuery(qry);
		return rs;
	}
}