
package Ventanas;
import java.sql.*;

public class Conexion {
   
    protected static Connection conectar(String bd, String user, String pass) 
            throws SQLException
    {
        Connection con=null;
        con=DriverManager.getConnection(bd, user, pass);
        return con;
        

    }
}
