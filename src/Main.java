import viewandcontroller.Window;

import javax.swing.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Main extends JFrame {
    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, IOException {
        Window app = new Window();
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
