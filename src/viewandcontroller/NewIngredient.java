package viewandcontroller;

import models.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewIngredient extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JTextField tfName;
    private JTextField tfWeight;
    private JTextField tfPrice;
    private JComboBox cbSupp;
    private ArrayList<Integer> idSupp = new ArrayList();
    Ingredient ing = new Ingredient();

    public NewIngredient(Connection cn) throws SQLException {

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
        tfName.addKeyListener(this);
        tfWeight = new JTextField();
        tfWeight.setBounds(120,70,150,30);
        tfWeight.addKeyListener(this);
        tfPrice = new JTextField();
        tfPrice.setBounds(120,120,150,30);
        tfPrice.addKeyListener(this);

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
        ing.setName(tfName.getText());
        ing.setMass(tfWeight.getText());
        ing.setPrice(tfPrice.getText());
        ing.setIdSupp(idSupp.get((cbSupp.getSelectedIndex())));

        st.executeUpdate("INSERT INTO Ingredient(Supplier_idSupplier,name,mass,price) VALUES" +
                "("+ing.getIdSupp()+
                ", '"+ing.getName()+
                "',"+ing.getMass()+
                ","+ing.getPrice()+")");
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = ing.checkName() && ing.checkMass() && ing.checkPrice();
                if(correct) addIngredient();
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
            ing.setName(tfName.getText());
            if(ing.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfWeight){
            ing.setMass(tfWeight.getText());
            if(ing.checkMass()) tfWeight.setBackground(Color.GREEN);
            else tfWeight.setBackground(Color.RED);
        }else if(z==tfPrice){
            ing.setPrice(tfPrice.getText());
            if(ing.checkPrice()) tfPrice.setBackground(Color.GREEN);
            else tfPrice.setBackground(Color.RED);
        }
    }
}
