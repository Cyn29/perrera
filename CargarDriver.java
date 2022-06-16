
package Ventanas;

import java.sql.Connection;


public class CargarDriver {
    
    public static void cargar(String drv) throws ClassNotFoundException
    {
        Class.forName(drv);
    }
    
}