package viewandcontroller;

import models.Size;

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

public class NewSize extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JComboBox cbName;
    private JTextField tfDiameter;
    private ArrayList<Integer> idName = new ArrayList();
    Size size = new Size();
    public NewSize(Connection cn) throws SQLException {

        setSize(300,300);
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
        JLabel ldiameter = new JLabel("Diameter:");
        lname.setBounds(20,20,100,30);
        ldiameter.setBounds(20,70,100,30);

        cbName = new JComboBox();
        cbName.setBounds(120,20,150,30);
        cbName.addItem("Large");
        cbName.addItem("Medium");
        cbName.addItem("Small");
        cbName.addItem("Extra Large");
        tfDiameter = new JTextField();
        tfDiameter.setBounds(120,70,150,30);
        tfDiameter.addKeyListener(this);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,200,100,30);
        bAdd.addActionListener(this);

        cp.add(lname);
        cp.add(ldiameter);
        cp.add(cbName);
        cp.add(tfDiameter);
        cp.add(bAdd);
    }
    private void addSize() throws SQLException {
        Statement st = cn.createStatement();
        String name = (String) cbName.getSelectedItem();
        System.out.println(name);
        size = new Size(name,tfDiameter.getText());
        st.executeUpdate("INSERT INTO Size(name,diameter) VALUES" +
                "('"+size.getName()+
                "', "+size.getDiameter()+")");
        dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = size.checkDiameter();
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
        if(z==tfDiameter){
            size.setDiameter(tfDiameter.getText());
            if(size.checkDiameter()) tfDiameter.setBackground(Color.GREEN);
            else tfDiameter.setBackground(Color.RED);
        }
    }
}
