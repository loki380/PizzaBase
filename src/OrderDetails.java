import javax.swing.*;
import java.awt.*;

public class OrderDetails extends JFrame {
    private Container cp; //Main Panel

    public OrderDetails() {

        setSize(400,500);
        setTitle("Order-Details");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        build();
    }
    private void build(){
        JLabel lnr = new JLabel("Number: ");
        JLabel lname = new JLabel(": ");
        JLabel lsurname = new JLabel("Imię: ");
    }

}
