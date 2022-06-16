
package Ventanas;
import java.awt.*;
import java.awt.event.*;

/*
public interface MouseListener{
public void mouseClicked(MouseEvent e);
public void mousePressed(MouseEvent e);
public void mouseReleased(MouseEvent e);
public void mouseEntered(MouseEvent e);
public void mouseExited(MouseEvent e);

public interface WindowListener{
 public void windowClosing(WindowEvent e);
 public void windowClosed(WindowEvent e);
 public void windowIconified(WindowEvent e);
 public void windowDeiconified(WindowEvent e);
 public void windowActivated(WindowEvent e);
 public void windowDeactivated(WindowEvent e);
 public void windowOpened(WindowEvent e);
}
dispose();: hace que la ventana se cierre
*/

public class Coordenadas extends Frame{
   
    Label lx,ly;
    TextField tx,ty;
    Panel p;
    Coordenadas()
    {
        this.setTitle("Coordenadas");
        this.setSize(500,500);
        this.setLayout(new BorderLayout());
        
        lx=new Label("X:");
        ly=new Label("Y:");
        tx=new TextField("10");
        ty=new TextField("10");
        p=new Panel();
        p.setBackground(Color.orange);
;        
        p.add(lx);
        p.add(tx);
        p.add(ty);
        p.add(ly);
        p.add(ty);
        this.add("North",p);
        this.addMouseListener(new Raton());
        this.addWindowListener(new Ventana());
        this.setResizable(false);
        this.setVisible(true);
    }
        
        public static void main (String[] args){
            new Coordenadas();
        }
        
        class Raton extends MouseAdapter{
            public void mouseClicked(MouseEvent e){
            tx.setText(""+e.getX());
            ty.setText (String.valueOf(e.getY()));//convierte de entero a cadena
        }
        }
            
        class Ventana extends WindowAdapter{
            public void windowClosing(WindowEvent e){
              dispose();  
            }
                
            }
        
        
}//clase interna

