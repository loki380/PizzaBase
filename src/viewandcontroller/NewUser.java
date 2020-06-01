package viewandcontroller;

import models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class NewUser extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JTextField tfLogin;
    private JTextField tfPassoword;
    private JButton bAdd;

    public NewUser(Connection cn) throws SQLException {

        setSize(300,225);
        setTitle("New User");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        JLabel lLogin = new JLabel("Login:");
        JLabel lPassword = new JLabel("Password:");
        lLogin.setBounds(20,20,100,30);
        lPassword.setBounds(20,70,100,30);

        tfLogin = new JTextField();
        tfLogin.setBounds(120,20,150,30);
        tfLogin.addKeyListener(this);
        tfPassoword = new JTextField();
        tfPassoword.setBounds(120,70,150,30);
        tfPassoword.addKeyListener(this);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,120,100,30);
        bAdd.addActionListener(this);

        cp.add(lLogin);
        cp.add(lPassword);
        cp.add(tfLogin);
        cp.add(tfPassoword);
        cp.add(bAdd);
    }
    private void addUser() throws SQLException, NoSuchAlgorithmException {
        Statement st = cn.createStatement();

        User user = new User();
        user.setLogin(tfLogin.getText());
        user.setPassword(tfPassoword.getText());
        String hash = User.generateHash(user.getPassword());

        st.executeUpdate("INSERT INTO Users(Login, Password) VALUES" +
                "('"+user.getLogin()+
                "', '"+hash+"')");
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                addUser();
            } catch (SQLException | NoSuchAlgorithmException ex) {
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

    }
}
