import viewandcontroller.Window;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main extends JFrame {
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {
        Window app = new Window();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
