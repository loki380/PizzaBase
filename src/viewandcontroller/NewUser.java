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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewUser extends JFrame implements ActionListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JTextField tfLogin;
    private JPasswordField tfPassoword;
    private JPasswordField tfPassoword1;
    private JButton bAdd;
    private ArrayList<String> logins = new ArrayList();

    public NewUser(Connection cn) throws SQLException {

        setSize(350,250);
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
        JLabel lPassword1 = new JLabel("Confirm Password:");
        lLogin.setBounds(20,20,100,30);
        lPassword.setBounds(20,70,100,30);
        lPassword1.setBounds(20,120,150,30);

        tfLogin = new JTextField();
        tfLogin.setBounds(170,20,150,30);
        tfPassoword = new JPasswordField();
        tfPassoword.setBounds(170,70,150,30);
        tfPassoword1 = new JPasswordField();
        tfPassoword1.setBounds(170,120,150,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,170,100,30);
        bAdd.addActionListener(this);

        cp.add(lLogin);
        cp.add(lPassword);
        cp.add(lPassword1);
        cp.add(tfLogin);
        cp.add(tfPassoword);
        cp.add(tfPassoword1);
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
    private boolean checkPasswords(){
        if(getPassword(tfPassoword).equals(getPassword(tfPassoword1))){
            return true;
        }else{
            return false;
        }
    }
    private boolean checkLogin() throws SQLException {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT Login FROM Users");
        if(rs.next()){
            do {
                logins.add(rs.getString(1));
            }while(rs.next());
        }
        String login = tfLogin.getText();
        for(String x : logins){
            if(x.equals(login)) return false;
        }
        return true;
    }
    private String getPassword(JPasswordField passField) {
        StringBuilder password = new StringBuilder();
        char[] pass = passField.getPassword();
        for (char c : pass) {
            password.append(c);
        }
        return password.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                if(checkLogin()){
                    if(checkPasswords()) addUser();
                    else JOptionPane.showMessageDialog(null,"Passwords are different", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else JOptionPane.showMessageDialog(null,"This Login is busy, choose something else", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException | NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
    }
}
