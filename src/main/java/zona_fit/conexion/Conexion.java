package zona_fit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConnection(){
        Connection conexion = null;
        var baseDatos = "zona_fit_db";
        var host = "localhost";
        var port = "3306";
        var url = "jdbc:mysql://"+host+":"+port+"/"+baseDatos;
        var user = "root";
        var pass = "pass123";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println("Error al conectarnos a la base de datos " + baseDatos + "\n"+e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConnection();
        if(conexion !=null){
            System.out.println("Conexión exitosa: \n" + conexion);
        }else{
            System.out.println("Error al conectarse");
        }
    }
}
