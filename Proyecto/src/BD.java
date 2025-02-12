import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BD {
    //Datos conexion
    private static String URL="jdbc:mysql://u4zf9cohzt75xlec:b1YzT3lojN2VUWUNzpzo@byjvcwpzkzjv16hgasof-mysql.services.clever-cloud.com:3306/byjvcwpzkzjv16hgasof";
    private static String USUARIO="u4zf9cohzt75xlec";
    private static String PASSWORD="b1YzT3lojN2VUWUNzpzo";

    public static Connection conectar(){
        Connection conexion =null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        conexion=DriverManager.getConnection(URL,USUARIO,PASSWORD);
        System.out.println("Conexion exitosa a la base de datos");
    } catch (ClassNotFoundException e) {
        System.err.println("No se encuentra el driver");
    } catch (SQLException e) {
        System.err.println("EEROR sin conexion a base de datos");
        throw new RuntimeException(e);
    }
        return conexion;
    }
}

