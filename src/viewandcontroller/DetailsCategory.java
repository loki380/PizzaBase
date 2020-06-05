package viewandcontroller;

import models.Category;
import models.Sauce;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailsCategory extends JFrame{
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    public DetailsCategory(Connection cn, Integer id) throws SQLException {

        setSize(300,250);
        setTitle("Details Category");
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
        ResultSet rs = st.executeQuery("SELECT name " +
                "FROM Category "+
                "WHERE idCategory="+id);

        JLabel lname = new JLabel("Name:");
        lname.setBounds(20,20,100,30);

        Category cat = new Category();
        if(rs.next()){
            cat.setName(rs.getString(1));
        }

        JLabel lname1 = new JLabel(cat.getName());
        lname1.setBounds(120,20,100,30);

        cp.add(lname);
        cp.add(lname1);
    }
}
