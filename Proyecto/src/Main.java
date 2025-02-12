import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conexion=BD.conectar();

           if(conexion !=null){
               System.out.println("Conexion Exitosa");
               new Login();
           }else {
               System.out.println("Conexion Fallida");
           }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al iniciar la aplicacion");
        }
    }
}