
package Ventanas;
import java.awt.*;
import java.awt.event.*;

public class MiVentana extends Frame implements ActionListener{
    
    //Declarar los componentes
    Button b;
    TextField txt;
    MiVentana()
    {
        this.setTitle("Mi Ventana");
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        //crear los componentes
        b=new Button("OK");
        txt=new TextField(20);
        //añadir
        this.add(b);
        this.add(txt);
        //añadir source a listener
        b.addActionListener(this);
        
        this.setResizable(false); //para que no se pueda redimensionar la ventana
        this.setVisible(true);//last line
    
}
    public static void main (String[] args) {
        new MiVentana();
}
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b)
        {
            txt.setText("Botón pulsado");
        }
    }
}
