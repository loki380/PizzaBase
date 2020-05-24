import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class editIngredient extends JFrame implements ActionListener {
    private Container cp; //Main Panel
    private Integer id;
    private Connection cn;
    private JButton bEdit;
    private JTextField tfName;
    private JTextField tfWeight;
    private JTextField tfPrice;
    private JComboBox cbSupp;
    private ArrayList<Integer> idSupp = new ArrayList();

    public editIngredient(Connection cn, Integer id) throws SQLException {

        setSize(300,300);
        setTitle("Edit Driver");
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
        ResultSet rs = st.executeQuery("SELECT Ingredient.name,mass,price, Supplier.name, Supplier_idSupplier " +
                "FROM Ingredient inner join Supplier " +
                "ON Supplier_idSupplier=idSupplier " +
                "WHERE idIngredient="+id);
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT idSupplier, name FROM Supplier WHERE idSupplier!=(SELECT Supplier_idSupplier FROM Ingredient WHERE idIngredient="+id+")");

        JLabel lname = new JLabel("Name:");
        JLabel lweight = new JLabel("Weight:");
        JLabel lprice = new JLabel("Price:");
        JLabel lsupplier = new JLabel("Supplier:");
        lname.setBounds(20,20,100,30);
        lweight.setBounds(20,70,100,30);
        lprice.setBounds(20,120,100,30);
        lsupplier.setBounds(20,170,100,30);

        bEdit = new JButton("Edit");
        bEdit.setBounds(100,220,100,30);
        bEdit.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfWeight = new JTextField();
        tfWeight.setBounds(120,70,150,30);
        tfPrice = new JTextField();
        tfPrice.setBounds(120,120,150,30);
        cbSupp = new JComboBox();
        cbSupp.setBounds(120,170,150,30);
        if(rs.next()) {
            tfName.setText(rs.getString(1));
            tfWeight.setText(rs.getString(2));
            tfPrice.setText(rs.getString(3));
            cbSupp.addItem(rs.getString(4));
            idSupp.add(rs.getInt(5));
        }
        if(rs1.next()) {
            do{
                idSupp.add(rs1.getInt(1));
                cbSupp.addItem(rs1.getString(2));
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
        cp.add(bEdit);
    }
    private void update() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String weight = tfWeight.getText();
        String price = tfPrice.getText();
        Integer supp = idSupp.get((cbSupp.getSelectedIndex()));
        st.executeUpdate("UPDATE Ingredient SET " +
                "name='"+name+
                "', mass="+weight+
                ", price="+price+
                ", Supplier_idSupplier="+supp+
                " WHERE idIngredient="+id);
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bEdit){
            try {
                update();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
