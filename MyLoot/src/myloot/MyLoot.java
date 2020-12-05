package myloot;
import views.Login;
import bd.Conexion;

public class MyLoot {

 
public static void main(String[] args) {
      Login login = new Login();
      Conexion con = new Conexion();
      con.getConnection();
      login.setVisible(true);
    }

}