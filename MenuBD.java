package Ventanas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenuBD extends JFrame implements ActionListener{
    URL url;
    ImageProducer ip;
    Image img;
    TextArea ta;
    Label estado;
    MenuBar mbarra;
    Menu m1,m2,m3,m4;
    MenuItem m11,m12,m13,m14,m21,m22,m23,m24,m31,m41,m42;
    static Connection con;
    
    String driver;

    
    MenuBD()
    {

        this.setTitle("Base de Datos");
        this.setLayout(new BorderLayout());
        driver="com.mysql.jdbc.Driver";

        //cambiar icono**********************************************
        try{  
          url=new URL("file:///c:\\fullstack profe\\java\\database.png") ;
        }catch(MalformedURLException mue){
            System.out.println("URL mal formada");
        
        }
        try{
        ip=(ImageProducer)url.getContent();
        img=createImage(ip);
        }catch(Exception ex){}
        this.setIconImage(img);
        //**********************************************************
        //******************************************
         this.addWindowListener(new WindowAdapter()
         {
                public void windowClosing(WindowEvent e)
                {
                 JOptionPane.showMessageDialog(null,"Vas a cerrar la APP");
                 System.exit(0);
               
                }
         }//clase anónima
         );
         estado=new Label("Estado:                               ");
         estado.setBackground(Color.yellow);
         ta=new TextArea();
         mbarra=new MenuBar();
    //M1**************************************************************
         m1 = new Menu("Base de Datos");

        m11 = new MenuItem("Cargar DRV");
        m12 = new MenuItem("Conectar");
        m12.setEnabled(false);
        m13 = new MenuItem("Desconectar");
        m13.setEnabled(false);
        m14 = new MenuItem("SALIR");

        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.addSeparator();
        m1.add(m14);

        mbarra.add(m1);
    //Menu m2***************************************************
        m2 = new Menu("CRUD");

        m21 = new MenuItem("Insertar Registro");
        m22 = new MenuItem("Borrar Registro");
        m23 = new MenuItem("Modificar Registro");
        m24 = new MenuItem("Ver Tabla");

        m2.add(m21);
        m2.add(m22);
        m2.add(m23);
        m2.add(m24);
         
        mbarra.add(m2);
        
   //Menu m3***************************************************
        m3=new Menu("Sentencias Precompiladas");
         
        m31=new MenuItem("Buscar Registro");
        m3.add(m31);
        mbarra.add(m3);
         //Menu m4**************************************************
        m4=new Menu("Transacciones"); 
        m41=new MenuItem("Insertar Registro");
        m4.add(m41);
        m42=new MenuItem("COMMIT");
        m4.add(m42);
        mbarra.add(m4);
        
        //********************************************************
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m21.addActionListener(this);
        m22.addActionListener(this);
        m23.addActionListener(this);
        m24.addActionListener(this);
        m31.addActionListener(this);
        m41.addActionListener(this);
        m42.addActionListener(this);
        //**********************************************************
        this.add("Center",ta);
        this.add("South",estado);
        this.setMenuBar(mbarra);
        this.setSize(800,600);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new MenuBD();
    }
    public void actionPerformed(ActionEvent e)
    {
        
        if (e.getSource()==m11)
        {
            try{
           CargarDriver.cargar(driver);
           estado.setText("Driver Cargado");
           m12.setEnabled(true);
            }catch(ClassNotFoundException cne){
                
            estado.setText("Driver no cargado");
            
            }
        }// if m11
        if (e.getSource() == m12) {
            //conectar 
            try {
                con = Conexion.conectar("jdbc:mysql://localhost/academia", "root", "");
                estado.setText("Conectados a Academia");
            } catch (SQLException sqe) {
                estado.setText("No conectados");
            }

        }
        if (e.getSource() == m13) {
            // 3. Desconectar
            try {
                con.close();
                estado.setText("Desconectados de Academia");
            } catch (SQLException sqe) {
                estado.setText("No se pudo desconectar "
                        + sqe.getMessage());

            }
        }//m13
        if (e.getSource() == m14) {

        }
        if (e.getSource() == m21) {
            new InsertarAlumno().setVisible(true);

        }
        if (e.getSource() == m22) {
            new BorrarAlumno().setVisible(true);

        }
        if (e.getSource() == m23) {
            new ModificarAlumno().setVisible(true);

        }
        if (e.getSource() == m24) {
            //VER TABLA
            ResultSet rs = null;
            String sq12 = "select * from alumnos";
            Statement st2 = null;

            try {

                st2 = con.createStatement();
                rs = st2.executeQuery(sq12);

                while (rs.next()) {

                    ta.append(rs.getString(1)
                            + "\t" + rs.getString(2)
                            + "\t" + rs.getString(3)
                            + "\t" + rs.getString(4)
                            + "\t" + rs.getString(5)
                            + "\n");
                }

            } catch (SQLException sqe) {

            }

        }

        if (e.getSource() == m31) {
            new BuscarPre().setVisible(true);

        }
        if (e.getSource() == m41) {
            //insertar
            try {
                con.setAutoCommit(false);
                Statement st = con.createStatement();//creamos un objeto sentencia SQL
                String sql = "Insert into alumnos values('005','Luis','Sánchez García','luis@gmail.com','232323G')";
                int n = st.executeUpdate(sql);

                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "Registro insertado");
                } else {
                    JOptionPane.showMessageDialog(null, "Registro no insertado");
                }
            } catch (SQLException sqe) {
                JOptionPane.showMessageDialog(null, "Registro no insertado" + sqe.getMessage());
            }
        }
        if (e.getSource() == m42) {
            try {
                con.commit();
                con.setAutoCommit(true);

            } catch (SQLException sqe) {
            }
        }

    }

}
