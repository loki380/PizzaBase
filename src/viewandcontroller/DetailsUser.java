package viewandcontroller;

import models.Driver;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsUser extends JFrame{
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsUser(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details User");
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
        ResultSet rs = st.executeQuery("SELECT Login " +
                "FROM Users "+
                "WHERE idUser="+id);

        JLabel lLogin = new JLabel("Login:");
        lLogin.setBounds(20,20,100,30);

        User user = new User();
        if(rs.next()){
            user.setLogin(rs.getString(1));
        }
        JLabel lLogin1 = new JLabel(user.getLogin());
        lLogin1.setBounds(120,20,100,30);

        cp.add(lLogin);
        cp.add(lLogin1);
    }
}
