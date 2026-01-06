package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConnection(){
        Connection conexion = null;
        var baseDatos = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "zona_fit_db"; // Change this line with your DB name
        var url = "jdbc:mysql://localhost:3306/" +  baseDatos; // localhost:yourdbport
        var usuario = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root"; // Change these two lines with your DB connection credentials
        var password = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.println("Error al conectarnos a la base de datos: " + e);
        }
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConnection();
        if(conexion != null)
            System.out.println("Conecxion exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }

}
