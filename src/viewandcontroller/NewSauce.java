package viewandcontroller;

import models.Sauce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewSauce extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfPrice;
    Sauce sauce = new Sauce();
    public NewSauce(Connection cn) throws SQLException {

        setSize(300,220);
        setTitle("New Sauce");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        JLabel lname = new JLabel("Name:");
        JLabel lprice = new JLabel("Price:");
        lname.setBounds(20,20,100,30);
        lprice.setBounds(20,70,100,30);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfPrice = new JTextField();
        tfPrice.setBounds(120,70,150,30);
        tfPrice.addKeyListener(this);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,140,100,30);
        bAdd.addActionListener(this);

        cp.add(lname);
        cp.add(lprice);
        cp.add(tfName);
        cp.add(tfPrice);
        cp.add(bAdd);
    }
    private void addSize() throws SQLException {
        Statement st = cn.createStatement();
        sauce = new Sauce(tfName.getText(),tfPrice.getText());
        st.executeUpdate("INSERT INTO Sauce(name,price) VALUES" +
                "('"+sauce.getName()+
                "', "+sauce.getPrice()+")");
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = sauce.checkName() && sauce.checkPrice();
                if(correct) addSize();
                else JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        Object z = keyEvent.getSource();
        if(z==tfName){
            sauce.setName(tfName.getText());
            if(sauce.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfPrice){
            sauce.setPrice(tfPrice.getText());
            if(sauce.checkPrice()) tfPrice.setBackground(Color.GREEN);
            else tfPrice.setBackground(Color.RED);
        }
    }
}
