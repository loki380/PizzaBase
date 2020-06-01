package viewandcontroller;


import models.Category;

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

public class NewCategory extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    Category category = new Category();

    public NewCategory(Connection cn) throws SQLException {

        setSize(300,200);
        setTitle("New Size");
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
        lname.setBounds(20,20,100,30);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,100,100,30);
        bAdd.addActionListener(this);

        cp.add(lname);
        cp.add(tfName);
        cp.add(bAdd);
    }
    private void addCategory() throws SQLException {
        Statement st = cn.createStatement();
        category = new Category(tfName.getText());
        st.executeUpdate("INSERT INTO Category(name) VALUES" +
                "('"+category.getName()+"')");
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = category.checkName();
                if(correct) addCategory();
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
            category.setName(tfName.getText());
            if(category.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }
    }
}
