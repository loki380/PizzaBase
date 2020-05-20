import javax.swing.*;
import java.awt.*;

public class OrderDetails extends JFrame {
    private Container cp; //Main Panel

    public OrderDetails() {

        setSize(420,530);
        setTitle("Order-Details");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        build();
    }
    private void build(){
        JLabel lorder = new JLabel("Order",SwingConstants.CENTER);
        JLabel lnr = new JLabel("Number:");
        JLabel ldata = new JLabel("Data Production:");
        JLabel lPrice = new JLabel("Price:");
        JLabel lPizza = new JLabel("Pizza:");
        JLabel lcustomer = new JLabel("Customer Data",SwingConstants.CENTER);
        JLabel lname = new JLabel("Name: ");
        JLabel lsurname = new JLabel("Surname: ");
        JLabel laddress = new JLabel("Address: ");
        JLabel ltel = new JLabel("Tel.: ");

        JPanel panel = new JPanel();
        panel.setBounds(25,25,350,450);
        panel.setLayout(new GridLayout(10,0));
        cp.add(panel);

        panel.add(lorder);
        panel.add(lnr);
        panel.add(ldata);
        panel.add(lPrice);
        panel.add(lPizza);
        panel.add(lcustomer);
        panel.add(lname);
        panel.add(lsurname);
        panel.add(laddress);
        panel.add(ltel);
    }

}
