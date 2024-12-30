import controller.VentanaController;
import dao.PersonaDAO;
import model.Persona;
import view.Ventana;

import java.sql.SQLException;

public class CRUD {


    public static void main(String[] args) {

        PersonaDAO personaDAO = new PersonaDAO();
        Ventana ventana = new Ventana();
        VentanaController controller = new VentanaController(personaDAO,ventana);

    }
}
