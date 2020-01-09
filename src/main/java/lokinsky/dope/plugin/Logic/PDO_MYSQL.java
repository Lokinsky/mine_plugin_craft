 package lokinsky.dope.plugin.Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;


public class PDO_MYSQL {
	private Connection connection;
	public HashMap<String, PreparedStatement> sql = new HashMap<>();

	
	public PDO_MYSQL() {
		try{
            Class.forName("org.gjt.mm.mysql.Driver").getDeclaredConstructor().newInstance();
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db", "root", "");
            //String users  = connection.nativeSQL();
            
            initilize_prepared_queries();
            System.out.println("Connection succesfull!");
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
             
            System.out.println(ex);
        }
	}
	private void initilize_prepared_queries() throws SQLException {
		sql.put("get_user", connection.prepareStatement("SELECT * FROM Users WHERE name = ?;"));
	}
	
	public String[] executePreparedSql(String name, String args[]) throws SQLException {
		int argcount = 1;
		String res[] = new String[args.length-1];
		while(sql.get(name).getParameterMetaData().getParameterCount()>argcount) {
			argcount+=1;
			sql.get(name).setString(1, args[argcount]);
		}
		if(res[0]!=null) {
			return res;
		}else {
			return null;
		}
		
	}
	
}
