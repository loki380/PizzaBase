import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class newIngredient extends JFrame implements ActionListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfWeight;
    private JTextField tfPrice;
    private JComboBox cbSupp;
    private ArrayList<Integer> idSupp = new ArrayList();

    public newIngredient(Connection cn) throws SQLException {

        setSize(300,300);
        setTitle("New Ingredient");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        buildPanel();
    }

    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT idSupplier, name " +
                "FROM Supplier");
        JLabel lname = new JLabel("Name:");
        JLabel lweight = new JLabel("Weight:");
        JLabel lprice = new JLabel("Price:");
        JLabel lsupplier = new JLabel("Supplier:");
        lname.setBounds(20,20,100,30);
        lweight.setBounds(20,70,100,30);
        lprice.setBounds(20,120,100,30);
        lsupplier.setBounds(20,170,100,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(100,220,100,30);
        bAdd.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfWeight = new JTextField();
        tfWeight.setBounds(120,70,150,30);
        tfPrice = new JTextField();
        tfPrice.setBounds(120,120,150,30);

        cbSupp = new JComboBox();
        cbSupp.setBounds(120,170,150,30);
        if(rs.next()) {
            do{
                idSupp.add(rs.getInt(1));
                cbSupp.addItem(rs.getString(2));
            }while (rs.next());
        }

        cp.add(lname);
        cp.add(lweight);
        cp.add(lprice);
        cp.add(lsupplier);
        cp.add(tfName);
        cp.add(tfWeight);
        cp.add(tfPrice);
        cp.add(cbSupp);
        cp.add(bAdd);
    }
    private void addIngredient() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String weight = tfWeight.getText();
        String price = tfPrice.getText();
        Integer supp = idSupp.get((cbSupp.getSelectedIndex()));

        st.executeUpdate("INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price) VALUES" +
                "("+supp+
                ", '"+name+
                "',"+weight+
                ","+price+")");
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                addIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
