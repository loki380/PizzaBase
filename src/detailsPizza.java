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
    private ArrayList<Integer> idSize = new ArrayList();
    private ArrayList<Integer> idCategory = new ArrayList();
    private ArrayList<Integer> idIng = new ArrayList();
    private ArrayList<JComboBox> listIng = new ArrayList();
    private ArrayList<Integer> idList = new ArrayList();
    private JPanel pIng;
    private JScrollPane scrollPane;
    private Integer i=0;

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
        ResultSet rs = st.executeQuery("SELECT Pizza.name, description, Category.name, Size.name, price " +
                "FROM Pizza inner join Size " +
                "ON Size_idSize=idSize inner join Category " +
                "ON Category_idCategory=idCategory " +
                "WHERE idPizza="+id);
        String name="",description="", category="",size="",price="";
        if(rs.next()) {
            name = rs.getString(1);
            description = rs.getString(2);
            category = rs.getString(3);
            size = rs.getString(4);
            price = rs.getString(5);
        }


        JLabel lname = new JLabel("Name: "+name);
        JLabel ldescription = new JLabel("Description: "+description);
        JLabel lcategory = new JLabel("Category: "+category);
        JLabel lsize = new JLabel("Size: "+size);
        JLabel lprice = new JLabel("Price: "+price);
        lname.setBounds(20,20,100,30);
        ldescription.setBounds(20,70,100,30);
        lcategory.setBounds(20,120,100,30);
        lsize.setBounds(20,170,100,30);
        lprice.setBounds(20,220,100,30);
        JLabel ling = new JLabel("Ingredients");
        ling.setBounds(350,20,100,30);

//        pIng = new JPanel();
//        pIng.setBackground(Color.GRAY);
//        pIng.setPreferredSize(new Dimension(180,300));
//        scrollPane = new JScrollPane(pIng);
//        scrollPane.setBounds(300,50,200,200);
//        pIng.setLayout(null);


        cp.add(lname);
        cp.add(ldescription);
        cp.add(lprice);
        cp.add(lcategory);
        cp.add(lsize);
        validate();
    }
}
