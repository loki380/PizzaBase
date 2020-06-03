package viewandcontroller;

import models.Size;
import models.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsSize extends JFrame{
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsSize(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details Size");
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
        ResultSet rs = st.executeQuery("SELECT name,diameter " +
                "FROM Size "+
                "WHERE idSize="+id);

        JLabel lname = new JLabel("Name:");
        JLabel ldiameter = new JLabel("Diameter:");
        lname.setBounds(20,20,100,30);
        ldiameter.setBounds(20,70,100,30);

        Size size = new Size();
        if(rs.next()){
            size.setName(rs.getString(1));
            size.setDiameter(rs.getString(2));
        }
        JLabel lname1 = new JLabel(size.getName());
        JLabel ldiameter1 = new JLabel(size.getDiameter());
        lname1.setBounds(120,20,100,30);
        ldiameter1.setBounds(120,70,100,30);

        cp.add(lname);
        cp.add(ldiameter);
        cp.add(lname1);
        cp.add(ldiameter1);
    }
}
