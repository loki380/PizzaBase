package viewandcontroller;

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

public class newPizza extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JButton bAdd;
    private JButton bNext;
    private JButton bBack;
    private JTextField tfName;
    private JTextField tfDescription;
    private JTextField tfPrice;
    private JComboBox cbSize;
    private JComboBox cbCategory;
    private ArrayList<Integer> idSize = new ArrayList();
    private ArrayList<Integer> idCategory = new ArrayList();
    private ArrayList<Integer> idIng = new ArrayList();
    private ArrayList<JComboBox> listIng = new ArrayList();
    private ArrayList<Integer> idList = new ArrayList();
    private JPanel pIng;
    private JScrollPane scrollPane;
    private Integer i=0;

    public newPizza(Connection cn) throws SQLException {

        setSize(550,350);
        setTitle("New Pizza");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        this.cn=cn;
        buildPanel();
    }

    private void buildPanel() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT idSize, name " +
                "FROM Size");
        ResultSet rs1 = st1.executeQuery("SELECT idCategory, name " +
                "FROM Category");
        JLabel lname = new JLabel("Name:");
        JLabel ldescription = new JLabel("Description:");
        JLabel lcategory = new JLabel("Category:");
        JLabel lsize = new JLabel("Size:");
        JLabel lprice = new JLabel("Price:");
        lname.setBounds(20,20,100,30);
        ldescription.setBounds(20,70,100,30);
        lcategory.setBounds(20,120,100,30);
        lsize.setBounds(20,170,100,30);
        lprice.setBounds(20,220,100,30);
        JLabel ling = new JLabel("Ingredients");
        ling.setBounds(350,20,100,30);

        bAdd = new JButton("Add");
        bAdd.setBounds(240,270,100,30);
        bAdd.addActionListener(this);

        tfName = new JTextField();
        tfName.setBounds(120,20,150,30);
        tfName.addKeyListener(this);
        tfDescription = new JTextField();
        tfDescription.setBounds(120,70,150,30);
        tfDescription.addKeyListener(this);
        cbCategory = new JComboBox();
        cbCategory.setBounds(120,120,150,30);
        cbSize = new JComboBox();
        cbSize.setBounds(120,170,150,30);
        tfPrice = new JTextField();
        tfPrice.setBounds(120,220,150,30);
        tfPrice.addKeyListener(this);

        pIng = new JPanel();
        pIng.setBackground(Color.GRAY);
        pIng.setPreferredSize(new Dimension(180,300));
        scrollPane = new JScrollPane(pIng);
        scrollPane.setBounds(300,50,200,200);
        pIng.setLayout(null);

        bNext = new JButton("+");
        bNext.setBounds(0,30,50,30);
        bNext.addActionListener(this);
        bBack = new JButton("-");
        bBack.setBounds(50,30,50,30);
        bBack.addActionListener(this);

        pIng.add(bNext);
        pIng.add(bBack);

        addIngredient();
        addIngredient();
        addIngredient();

        if(rs.next()) {
            do{
                idSize.add(rs.getInt(1));
                cbSize.addItem(rs.getString(2));
            }while (rs.next());
        }
        if(rs1.next()) {
            do{
                idCategory.add(rs1.getInt(1));
                cbCategory.addItem(rs1.getString(2));
            }while (rs1.next());
        }

        cp.add(lname);
        cp.add(ldescription);
        cp.add(lprice);
        cp.add(lcategory);
        cp.add(lsize);
        cp.add(ling);
        cp.add(tfName);
        cp.add(tfDescription);
        cp.add(tfPrice);
        cp.add(cbSize);
        cp.add(cbCategory);
        cp.add(bAdd);
        cp.add(scrollPane);
    }
    private void addPizza() throws SQLException {
        Statement st = cn.createStatement();
        String name = tfName.getText();
        String description = tfDescription.getText();
        String price = tfPrice.getText();
        Integer size = idSize.get((cbSize.getSelectedIndex()));
        Integer category = idCategory.get((cbCategory.getSelectedIndex()));

        st.executeUpdate("INSERT INTO Pizza(Category_idCategory,Size_idSize,name,description,price) VALUES" +
                "("+category+
                ", "+size+
                ",'"+name+
                "','"+description+
                "',"+price+
                ")");
        storeid("SELECT idPizza FROM Pizza ORDER BY idPizza");
        int id=0;
        for(int c : idList){
            if(c>id) id=c;
        }
        for(JComboBox x: listIng){
            Integer Ing = idIng.get((x.getSelectedIndex()));
            st.executeUpdate("INSERT INTO Pizza_has_Ingredient(Pizza_idPizza, Ingredient_idIngredient) VALUES" +
                    "("+id+
                    ","+Ing+")");
        }
        dispose();
    }
    public void storeid(String query) throws SQLException {
        idList.clear();
        Statement statement = cn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
    }
    private void addIngredient() throws SQLException {
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT idIngredient, name " +
                "FROM Ingredient");
        JComboBox cbIng = new JComboBox();
        if(rs1.next()) {
            do{
                idIng.add(rs1.getInt(1));
                cbIng.addItem(rs1.getString(2));
            }while (rs1.next());
        }
        cbIng.setBounds(0,i,185,30);
        bNext.setBounds(0,i+30,50,30);
        bBack.setBounds(50,i+30,50,30);
        i += 30;
        pIng.add(cbIng);
        listIng.add(cbIng);
        validate();
    }
    private void deleteIngredient() throws SQLException {
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT idIngredient, name " +
                "FROM Ingredient");
        if(i==0){
            JOptionPane.showMessageDialog(null,"No more Ingredients to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            JComboBox cbIng = listIng.get(listIng.size()-1);
            listIng.remove(listIng.size()-1);
            pIng.remove(cbIng);
            i -= 30;
            bNext.setBounds(0,i,50,30);
            bBack.setBounds(50,i,50,30);
        }
        validate();
    }
    private boolean checkName(){
        String name = tfName.getText();
        if(name.matches("[a-zA-ZęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkDes(){
        String name = tfDescription.getText();
        if(name.matches("[a-zA-Z0-9ęółśążźćńĘÓŁŚĄŻŹĆŃ]*") && name.length()>0){
            return true;
        }else return false;
    }
    private boolean checkPrice(){
        String name = tfPrice.getText();
        if(name.matches("[0-9]+\\.*[0-9]*") && name.length()>0){
            return true;
        }else return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bAdd){
            try {
                boolean correct = checkName() && checkDes() && checkPrice();
                if(correct) addPizza();
                else JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bNext){
            try {
                addIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bBack){
            try {
                deleteIngredient();
                validate();
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
            if(checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfDescription){
            if(checkDes()) tfDescription.setBackground(Color.GREEN);
            else tfDescription.setBackground(Color.RED);
        }else if(z==tfPrice){
            if(checkPrice()) tfPrice.setBackground(Color.GREEN);
            else tfPrice.setBackground(Color.RED);
        }
    }
}