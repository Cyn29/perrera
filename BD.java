package ventanas;


import java.sql.*;



public class BD {
    public static void main(String[] args) {
        
        //1. cargar el DRV
        Connection con=null;
        Statement st=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver cargado");
                    
        }catch (ClassNotFoundException cne){
        
            System.out.println("Driver no cargado" + cne.getMessage());
        }
        
        //2. Establecer conexión
        try{
            
            con=DriverManager.getConnection("jdbc:mysql://localhost/academia", "root", "");
            System.out.println("Conectado a academia");
        }catch(SQLException sqe){
        
            System.out.println("no se pudo conectar" + sqe.getMessage());
        }
        
        //insertar un registro
        /*
        
        try{
            
            st=con.createStatement();//creamos un objeto sentencia sql
            
            String sql=" insert into usuarios values ('antonio@gmail.com', sha('elmundodeyupi2022'))";
            
            int n=st.executeUpdate(sql);// devuelve 0, -1 afecta a toda la tabla, n registros afectados,
            
            
            if(n==1){
                
                System.out.println("registro insertado");
            }else{
                System.out.println("registro no insertado");
            }
                
            
            
            
        }catch (SQLException sqe){
            
            System.out.println(sqe.getMessage());
            
        }finally{
            
            
            try{
                
                st.close();
                
            }catch(SQLException sqe){
                
                
            }
            
        }
*/
        
        //recuperar registros
        
        ResultSet rs=null;
        String sq12="select * from alumnos";
        Statement st2=null;
        
        try{
            
            st2=con.createStatement();
            rs=st2.executeQuery(sq12);
            
            while(rs.next()){
                
                System.out.println(rs.getString(1)+ "\t" + rs.getString(2)+ "\t" + rs.getString(3)+"\t" + rs.getString(4)+"\t" + rs.getString(5)+"\t" );
            }
            
            
        }catch (SQLException sqe){
            
        }
        
        //Modificar
        try{
            
            st=con.createStatement();//creamos un objeto sentencia sql
            
            String sql="UPDATE ALUMNOS set apellidos='Gómez de la Serna', set email='de_laserna@yahoo.es where dni='280303G'";
            
            int n=st.executeUpdate(sql);// devuelve 0, -1 afecta a toda la tabla, n registros afectados,
            
            
            if(n==1){
                
                System.out.println("Registro modificado");
            }else{
                System.out.println("Registro no modificado");
            }
                
            
            
            
        }catch (SQLException sqe){
            
            System.out.println(sqe.getMessage());
            
        }finally{
            
            
            try{
                
                st.close();
                
            }catch(SQLException sqe){
                
                
            }
            
        }
        
        //sentencias precompiladas
        
        PreparedStatement pst=null;
        try{
            
           pst=con.prepareStatement("insert into alumnos values(?,?,?,?,?)"); 
           
           //darle el valor a las interrogaciones(?)
           
           pst.setString(1, "012");
           pst.setString(2, "Ana");
           pst.setString(3, "Sanz Sanz");
           pst.setString(4, "s_ana@yahoo.com");
           pst.setString(5,"012334F");
           
           //ejecutar la sentencia
           pst.executeUpdate();
           
        }catch(SQLException sqe){}
        
        
        //3. desconectarse
        try{
            
            con.close();
            System.out.println("desconectado  de academia");
        
        }catch(SQLException sqe){
        
            System.out.println("no se pudo desconectar" + sqe.getMessage());
        }
        
        
        
        
    }//metodo main
    
    
}//clase


