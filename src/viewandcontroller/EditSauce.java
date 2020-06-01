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

public class EditSauce extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private JTextField tfPrice;
    private ArrayList<Integer> idName = new ArrayList();
    private Integer id;
    Sauce sauce = new Sauce();
    public EditSauce(Connection cn, Integer id) throws SQLException {

        setSize(300,220);
        setTitle("New Sauce");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        this.id=id;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT name, price FROM Sauce WHERE idSauce="+id);
        JLabel lname = new JLabel("Name:");
        JLabel lprice = new JLabel("Price:");
        lname.setBounds(20,20,100,30);
        lprice.setBounds(20,70,100,30);
        if(rs.next()){
            sauce.setName(rs.getString(1));
            sauce.setPrice(rs.getString(2));
        }

        tfName = new JTextField(sauce.getName());
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfPrice = new JTextField(sauce.getPrice());
        tfPrice.setBounds(120,70,150,30);
        tfPrice.addKeyListener(this);

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,140,100,30);
        bEdit.addActionListener(this);

        cp.add(lname);
        cp.add(lprice);
        cp.add(tfName);
        cp.add(tfPrice);
        cp.add(bEdit);
    }
    private void addSauce() throws SQLException {
        Statement st = cn.createStatement();
        sauce = new Sauce(tfName.getText(),tfPrice.getText());
        st.executeUpdate("UPDATE Sauce SET " +
                "name='"+sauce.getName()+
                "', price="+sauce.getPrice()+" WHERE idSauce="+id);
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                boolean correct = sauce.checkName() && sauce.checkPrice();
                if(correct) addSauce();
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