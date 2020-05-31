package viewandcontroller;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class detailsPizza extends JFrame {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private Integer i=0;
    private ArrayList<String> listLabel = new ArrayList();

    public detailsPizza(Connection cn, Integer id) throws SQLException {

        setSize(550,350);
        setTitle("Details Pizza");
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
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT Pizza.name, description, Category.name, Size.name, price " +
                "FROM Pizza inner join Size " +
                "ON Size_idSize=idSize inner join Category " +
                "ON Category_idCategory=idCategory " +
                "WHERE idPizza="+id);
        ResultSet rs1 = st1.executeQuery("SELECT Ingredient.name " +
                "FROM Pizza_has_Ingredient inner join Ingredient " +
                "ON Ingredient_idIngredient=idIngredient " +
                "WHERE Pizza_idPizza="+id);
        String name="",description="", category="",size="",price="";
        if(rs.next()) {
            name = rs.getString(1);
            description = rs.getString(2);
            category = rs.getString(3);
            size = rs.getString(4);
            price = rs.getString(5);
        }


        JLabel lname = new JLabel("Name: ");
        JLabel ldescription = new JLabel("Description: ");
        JLabel lcategory = new JLabel("Category: ");
        JLabel lsize = new JLabel("Size: ");
        JLabel lprice = new JLabel("Price: ");

        JLabel lname1 = new JLabel(name);
        JLabel ldescription1 = new JLabel(description);
        JLabel lcategory1 = new JLabel(category);
        JLabel lsize1 = new JLabel(size);
        JLabel lprice1 = new JLabel(price);

        lname.setBounds(20,20,100,30);
        ldescription.setBounds(20,70,100,30);
        lcategory.setBounds(20,120,100,30);
        lsize.setBounds(20,170,100,30);
        lprice.setBounds(20,220,100,30);

        lname1.setBounds(120,20,100,30);
        ldescription1.setBounds(120,70,100,30);
        lcategory1.setBounds(120,120,100,30);
        lsize1.setBounds(120,170,100,30);
        lprice1.setBounds(120,220,100,30);

        JLabel ling = new JLabel("Ingredients");
        ling.setBounds(350,20,100,30);
        int j=0;
        if(rs1.next()) {
            do{
                JLabel ling1 = new JLabel(rs1.getString(1));
                ling1.setBounds(300+j,70+i,100,20);
                cp.add(ling1);
                i += 30;
                if(i>200) j = 100;
            }while (rs1.next());
        }

        cp.add(lname);
        cp.add(ldescription);
        cp.add(lprice);
        cp.add(lcategory);
        cp.add(lsize);

        cp.add(lname1);
        cp.add(ldescription1);
        cp.add(lprice1);
        cp.add(lcategory1);
        cp.add(lsize1);

        cp.add(ling);

        validate();
    }
}
