
package Ventanas;
import java.awt.*;
import java.awt.event.*;

public class Ventana2 extends Frame{
    Ventana2()
    {
     this.setTitle("MiVentana2");
     this.setSize(500,500);
     this.addWindowListener (new WindowAdapter()
     {
         public void windowClosing(WindowEvent e)
         {
             Ventana1.v2=null;
             dispose();
             System.exit(0);
         }
     }//clase anónima
     );
     //siempre se cierra la ventana así: ); con clases anónimas
     
     this.setVisible(true);//last line
    }
}
