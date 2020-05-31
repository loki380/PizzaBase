package viewandcontroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class newDriver extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfPesel;
    private boolean correct = false;

    public newDriver(Connection cn) {

        setSize(300,300);
        setTitle("New Driver");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        buildPanel();
    }

    private void buildPanel(){
        JLabel lname = new JLabel("Name:");
        JLabel lsurname = new JLabel("Surname:");
        JLabel lpesel = new JLabel("Pesel:");
        lname.setBounds(20,20,100,30);
        lsurname.setBounds(20,70,100,30);
        lpesel.setBounds(20,120,100,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,200,100,30);
        bAdd.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfSurname = new JTextField();
        tfSurname.setBounds(120,70,150,30);
        tfSurname.addKeyListener(this);
        tfPesel = new JTextField();
        tfPesel.setBounds(120,120,150,30);
        tfPesel.addKeyListener(this);

        cp.add(lname);
        cp.add(lsurname);
        cp.add(lpesel);
        cp.add(bAdd);
        cp.add(tfName);
        cp.add(tfSurname);
        cp.add(tfPesel);
    }
    private void addDriver() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String surname = tfSurname.getText();
        String pesel = tfPesel.getText();
        st.executeUpdate("INSERT INTO Driver(name,surname,pesel) VALUES" +
                "('"+tfName.getText()+
                "', '"+tfSurname.getText()+
                "', '"+tfPesel.getText()+"')");
        dispose();
    }
    private boolean checkName(){
        String name = tfName.getText();
        if(name.matches("[A-Za-z]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkSurName(){
        String name = tfSurname.getText();
        if(name.matches("[A-Za-z]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkPesel(){
        String pesel = tfPesel.getText();
        if(pesel.matches("[0-9]{11}") && pesel.length()>0){
            return true;
        }else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                correct = checkName() && checkPesel() && checkSurName();
                if(correct) addDriver();
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
            if(checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfSurname){
            if(checkSurName()) tfSurname.setBackground(Color.GREEN);
            else tfSurname.setBackground(Color.RED);
        }else if(z==tfPesel){
            if(checkPesel()) tfPesel.setBackground(Color.GREEN);
            else tfPesel.setBackground(Color.RED);
        }
    }
}
