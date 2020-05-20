import javax.swing.*;
import java.awt.*;

public class newOrder extends JFrame {
    private Container cp; //Main Panel

    public newOrder() {

        setSize(420,530);
        setTitle("Order-Details");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        buildPanel();
    }
    private void buildPanel(){
        JLabel l = new JLabel("Wybierz Pizze",SwingConstants.CENTER);
    }
}
