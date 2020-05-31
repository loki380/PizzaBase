package viewandcontroller;

import models.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    private Container cp;
    private Connection cn;
    private BackgroundPanel bg;
    private BufferedImage image;
    private JPanel pLogin;
    private JPanel pRight;
    private JPanel pLeft;
    private JTextField nameField;
    private JPasswordField passField;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton bLogin;

    private JButton bOrder;
    private JButton bMenu;
    private JButton bDrivers;
    private JButton bIngredient;
    private JButton bSupplier;
    private JButton bBack;
    private JButton bExit;
    private JButton bMore;
    private JButton bUsers;

    private JButton bNewOrder;
    private JButton bEditOrder;
    private JButton bDetailsOrder;
    private JButton bDeleteOrder;

    private JButton bNewUser;

    private JButton bNewPizza;
    private JButton bEditPizza;
    private JButton bDetailsPizza;
    private JButton bDeletePizza;

    private JButton bNewDriver;
    private JButton bEditDriver;
    private JButton bDeleteDriver;

    private JButton bNewIngredient;
    private JButton bEditIngredient;
    private JButton bDeleteIngredient;

    private JButton bNewSupplier;
    private JButton bEditSupplier;
    private JButton bDeleteSupplier;

    private ArrayList idList = new ArrayList();

    public Window() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException {

        setSize(925,600);
        setTitle("Application - Pizzeria Base");
        setLocationRelativeTo(null);
        setResizable(false);

        connect();
        cp = this.getContentPane();
        setBG();
        addLoginPanel();

    }
    // ------------- SET IMAGE FOR BACKGROUND
    private void setBG(){
        cp.removeAll();
        try {
            image = ImageIO.read(new File("src/img/BG.jpg"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        ImageIcon BGIcon=new ImageIcon(image);

        bg = new BackgroundPanel(BGIcon);
        cp.add(bg);
        bg.setLayout(null);
        validate();
    }
    // ------------- LOGIN PANEL
    private void addLoginPanel() throws NoSuchAlgorithmException {
        JLabel name = new JLabel("Name: ");
        JLabel password = new JLabel("Password: ");
        pLogin=new JPanel();
        pLogin.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pLogin.setBounds(500,150,300,200);
        pLogin.setLayout(null);
        bg.add(pLogin);

        nameField = new JTextField();
        nameField.setBounds(100,50,150,20);
        passField = new JPasswordField();
        passField.setBounds(100,90,150,20);
        name.setBounds(30,50,100,20);
        name.setForeground(Color.WHITE);
        password.setBounds(30,90,100,20);
        password.setForeground(Color.WHITE);
        bLogin = new JButton("Login");
        bLogin.setBounds(100,150,100,20);
        bLogin.addActionListener(this);


        pLogin.add(name);
        pLogin.add(nameField);
        pLogin.add(password);
        pLogin.add(passField);
        pLogin.add(bLogin);

    }
    // ------------- MAIN PANEL
    private void setRightPanel(){
        pRight = new JPanel();
        pRight.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pRight.setBounds(550,50,300,450);
        pRight.setLayout(null);

        bOrder = new JButton("Orders");
        bOrder.setBounds(30,30,240,40);
        bOrder.setBackground(new Color(0xBDBAA5));
        bOrder.addActionListener(this);

        bMenu = new JButton("Menu");
        bMenu.setBounds(30,100,240,40);
        bMenu.setBackground(new Color(0xBDBAA5));
        bMenu.addActionListener(this);

        bDrivers = new JButton("Drivers");
        bDrivers.setBounds(30,170,240,40);
        bDrivers.setBackground(new Color(0xBDBAA5));
        bDrivers.addActionListener(this);

        bIngredient = new JButton("Ingredients");
        bIngredient.setBounds(30,240,240,40);
        bIngredient.setBackground(new Color(0xBDBAA5));
        bIngredient.addActionListener(this);

        bSupplier = new JButton("Suppliers");
        bSupplier.setBounds(30,310,240,40);
        bSupplier.setBackground(new Color(0xBDBAA5));
        bSupplier.addActionListener(this);

        bMore = new JButton("More");
        bMore.setBounds(30,380,180,40);
        bMore.setBackground(new Color(0xBDBAA5));
        bMore.addActionListener(this);

        bExit = new JButton("X");
        bExit.setBounds(230,380,50,40);
        bExit.setBackground(new Color(0xBDBAA5));
        bExit.addActionListener(this);

        pRight.add(bOrder);
        pRight.add(bMenu);
        pRight.add(bDrivers);
        pRight.add(bIngredient);
        pRight.add(bSupplier);
        pRight.add(bExit);
        pRight.add(bMore);
        bg.add(pRight);
    }
    private void moreRightPanel(){
        pRight = new JPanel();
        pRight.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pRight.setBounds(550,50,300,450);
        pRight.setLayout(null);

        bUsers = new JButton("Users");
        bUsers.setBounds(30,30,240,40);
        bUsers.setBackground(new Color(0xBDBAA5));
        bUsers.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,380,180,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bExit = new JButton("X");
        bExit.setBounds(230,380,50,40);
        bExit.setBackground(new Color(0xBDBAA5));
        bExit.addActionListener(this);

        pRight.add(bUsers);
        pRight.add(bBack);
        pRight.add(bExit);
        bg.add(pRight);
    }
    // ------------- ORDER PANEL
    private void setRightOrder(){
        pRight.removeAll();

        bNewOrder = new JButton("New Order");
        bNewOrder.setBounds(30,30,240,40);
        bNewOrder.setBackground(new Color(0xBDBAA5));
        bNewOrder.addActionListener(this);

        bEditOrder = new JButton("Edit");
        bEditOrder.setBounds(30,100,240,40);
        bEditOrder.setBackground(new Color(0xBDBAA5));
        bEditOrder.addActionListener(this);

        bDetailsOrder = new JButton("Details");
        bDetailsOrder.setBounds(30,170,240,40);
        bDetailsOrder.setBackground(new Color(0xBDBAA5));
        bDetailsOrder.addActionListener(this);

        bDeleteOrder = new JButton("Delete");
        bDeleteOrder.setBounds(30,240,240,40);
        bDeleteOrder.setBackground(new Color(0xBDBAA5));
        bDeleteOrder.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewOrder);
        pRight.add(bEditOrder);
        pRight.add(bDetailsOrder);
        pRight.add(bDeleteOrder);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- DRIVER PANEL
    private void setRightDriver(){
        pRight.removeAll();

        bNewDriver = new JButton("New Driver");
        bNewDriver.setBounds(30,30,240,40);
        bNewDriver.setBackground(new Color(0xBDBAA5));
        bNewDriver.addActionListener(this);

        bEditDriver = new JButton("Edit");
        bEditDriver.setBounds(30,100,240,40);
        bEditDriver.setBackground(new Color(0xBDBAA5));
        bEditDriver.addActionListener(this);

        bDeleteDriver = new JButton("Delete");
        bDeleteDriver.setBounds(30,170,240,40);
        bDeleteDriver.setBackground(new Color(0xBDBAA5));
        bDeleteDriver.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,240,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewDriver);
        pRight.add(bEditDriver);
        pRight.add(bDeleteDriver);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- MENU PANEL
    private void setRightMenu(){
        pRight.removeAll();

        bNewPizza = new JButton("New Pizza");
        bNewPizza.setBounds(30,30,240,40);
        bNewPizza.setBackground(new Color(0xBDBAA5));
        bNewPizza.addActionListener(this);

        bDetailsPizza = new JButton("Details");
        bDetailsPizza.setBounds(30,100,240,40);
        bDetailsPizza.setBackground(new Color(0xBDBAA5));
        bDetailsPizza.addActionListener(this);

        bEditPizza = new JButton("Edit");
        bEditPizza.setBounds(30,170,240,40);
        bEditPizza.setBackground(new Color(0xBDBAA5));
        bEditPizza.addActionListener(this);

        bDeletePizza = new JButton("Delete");
        bDeletePizza.setBounds(30,240,240,40);
        bDeletePizza.setBackground(new Color(0xBDBAA5));
        bDeletePizza.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewPizza);
        pRight.add(bDetailsPizza);
        pRight.add(bEditPizza);
        pRight.add(bDeletePizza);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- INGREDIENT PANEL
    private void setRightIng(){
        pRight.removeAll();

        bNewIngredient = new JButton("New Ingredient");
        bNewIngredient.setBounds(30,30,240,40);
        bNewIngredient.setBackground(new Color(0xBDBAA5));
        bNewIngredient.addActionListener(this);

        bEditIngredient = new JButton("Edit");
        bEditIngredient.setBounds(30,100,240,40);
        bEditIngredient.setBackground(new Color(0xBDBAA5));
        bEditIngredient.addActionListener(this);

        bDeleteIngredient = new JButton("Delete");
        bDeleteIngredient.setBounds(30,170,240,40);
        bDeleteIngredient.setBackground(new Color(0xBDBAA5));
        bDeleteIngredient.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,240,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewIngredient);
        pRight.add(bEditIngredient);
        pRight.add(bDeleteIngredient);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- SUPPLIER PANEL
    private void setRightSupp(){
        pRight.removeAll();

        bNewSupplier = new JButton("New Supplier");
        bNewSupplier.setBounds(30,30,240,40);
        bNewSupplier.setBackground(new Color(0xBDBAA5));
        bNewSupplier.addActionListener(this);

        bEditSupplier = new JButton("Edit");
        bEditSupplier.setBounds(30,100,240,40);
        bEditSupplier.setBackground(new Color(0xBDBAA5));
        bEditSupplier.addActionListener(this);

        bDeleteSupplier = new JButton("Delete");
        bDeleteSupplier.setBounds(30,170,240,40);
        bDeleteSupplier.setBackground(new Color(0xBDBAA5));
        bDeleteSupplier.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,240,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewSupplier);
        pRight.add(bEditSupplier);
        pRight.add(bDeleteSupplier);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- USERS PANEL
    private void setRightUsers(){
        pRight.removeAll();

        bNewUser = new JButton("New User");
        bNewUser.setBounds(30,30,240,40);
        bNewUser.setBackground(new Color(0xBDBAA5));
        bNewUser.addActionListener(this);


        bBack = new JButton("Back");
        bBack.setBounds(30,240,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        pRight.add(bNewUser);
        pRight.add(bBack);
        bg.add(pRight);
    }
    // ------------- FUNCTION TO CREATE TABLE WITH DATA
    private void showTable(String query) throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.last();
        int amountRow=rs.getRow();
        rs.first();
        int amountColumns=rsmd.getColumnCount();

        String[] columnNames = new String[amountColumns];
        Object[][] data = new Object[amountRow][amountColumns];

        for(int i=0;i<amountColumns; i++){
            columnNames[i]=rsmd.getColumnName(i+1);
        }
        for(int j = 0; j < amountRow; j++) {
            for (int i = 0; i < amountColumns; i++) {
                data[j][i] = rs.getString(i+1);
            }
            rs.next();
        }

        table = new JTable(data, columnNames);
        scrollPane = new JScrollPane(table);

        pLeft = new JPanel();
        pLeft.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pLeft.setBounds(50,50,500,450);
        pLeft.setLayout(null);
        scrollPane.setBounds(30,30,400,400);
        pLeft.add(scrollPane);
        bg.add(pLeft);
        validate();
    }
    // ------------- ORDER CRUD
    private void newOrder() throws SQLException {
        newOrder frame = new newOrder(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                            "FROM Orderr inner join Customer " +
                            "ON Customer_idCustomer=idCustomer " +
                            "ORDER BY dateProduction DESC");
                    storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editOrder() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            editOrder frame = new editOrder(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                                "FROM Orderr inner join Customer " +
                                "ON Customer_idCustomer=idCustomer " +
                                "ORDER BY dateProduction DESC");
                        storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Order", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsOrder() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            detailsOrder frame = new detailsOrder(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                                "FROM Orderr inner join Customer " +
                                "ON Customer_idCustomer=idCustomer " +
                                "ORDER BY dateProduction DESC");
                        storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Order", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteOrder() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Order?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Order_has_Pizza WHERE Orderr_idOrder=" + id);
                st.executeUpdate("DELETE FROM Orderr WHERE idOrder=" + id);
                pLeft.remove(scrollPane);
                showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                        "FROM Orderr inner join Customer " +
                        "ON Customer_idCustomer=idCustomer " +
                        "ORDER BY dateProduction DESC");
                storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
                validate();
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Order", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ------------- PIZZA CRUD
    private void newPizza() throws SQLException {
        newPizza frame = new newPizza(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                            "FROM Pizza inner join Category\n" +
                            "ON Category_idCategory=idCategory\n" +
                            "ORDER BY Pizza.name");
                    storeId("SELECT idPizza FROM Pizza ORDER BY name");
                    validate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editPizza() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            editPizza frame = new editPizza(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                                "FROM Pizza inner join Category\n" +
                                "ON Category_idCategory=idCategory\n" +
                                "ORDER BY Pizza.name");
                        storeId("SELECT idPizza FROM Pizza ORDER BY name");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Pizza", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deletePizza() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Pizza?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Pizza_has_Ingredient WHERE Pizza_idPizza=" + id);
                st.executeUpdate("DELETE FROM Pizza WHERE idPizza=" + id);
                pLeft.remove(scrollPane);
                showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                        "FROM Pizza inner join Category\n" +
                        "ON Category_idCategory=idCategory\n" +
                        "ORDER BY Pizza.name");
                storeId("SELECT idPizza FROM Pizza ORDER BY name");
                validate();
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Pizza", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsPizza() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            detailsPizza frame = new detailsPizza(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                                "FROM Pizza inner join Category\n" +
                                "ON Category_idCategory=idCategory\n" +
                                "ORDER BY Pizza.name");
                        storeId("SELECT idPizza FROM Pizza ORDER BY name");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Pizza", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ------------- SUPPLIER CRUD
    private void newSupplier(){
        newSupplier frame = new newSupplier(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                    storeId("SELECT idSupplier FROM Supplier ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editSupplier() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            editSupplier frame = new editSupplier(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                        storeId("SELECT idSupplier FROM Supplier ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Supplier", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteSupplier() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Supplier?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Supplier WHERE idSupplier=" + id);
                showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                storeId("SELECT idSupplier FROM Supplier ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Supplier", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ------------- DRIVER CRUD
    private void newDriver(){
        newDriver frame = new newDriver(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                    storeId("SELECT idDriver FROM Driver ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editDriver() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            editDriver frame = new editDriver(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                        storeId("SELECT idDriver FROM Driver ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose driver", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteDriver() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Driver?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Driver WHERE idDriver=" + id);
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose driver", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ------------- INGREDIENT CRUD
    private void newIngredient() throws SQLException {
        newIngredient frame = new newIngredient(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' FROM Ingredient inner join Supplier " +
                            "ON Supplier_idSupplier=idSupplier\n" +
                            "ORDER BY Ingredient.name");
                    storeId("SELECT idIngredient FROM Ingredient ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void editIngredient() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            editIngredient frame = new editIngredient(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' FROM Ingredient inner join Supplier " +
                                "ON Supplier_idSupplier=idSupplier\n" +
                                "ORDER BY Ingredient.name");
                        storeId("SELECT idIngredient FROM Ingredient ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Ingredient", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteIngredient() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Ingredient?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                st.executeUpdate("DELETE FROM Ingredient WHERE idIngredient=" + id);
                showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' FROM Ingredient inner join Supplier " +
                        "ON Supplier_idSupplier=idSupplier\n" +
                        "ORDER BY Ingredient.name");
                storeId("SELECT idIngredient FROM Ingredient ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Ingredient", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // ------------- USERS CRUD
    private void newUser() throws SQLException {
        newUser frame = new newUser(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT Login FROM Users ORDER BY Login");
                    storeId("SELECT idUser FROM Users ORDER BY Login");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    // ------------- CONNECT WITH SQL
    private void connect() throws SQLException, ClassNotFoundException {
        cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=master;", "sa", "kamil99");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
    // ------------- CHECK LOGIN AND PASSWORD
    private boolean checklogindata() throws SQLException, NoSuchAlgorithmException {
        Statement st = cn.createStatement();
        String name=nameField.getText();
        String password=getPassword();
        User user = new User(name, password);
        ResultSet rs = st.executeQuery("SELECT Password FROM Users WHERE Login='"+user.getLogin()+"'");
        if(rs.next()){
            String hash=rs.getString(1);
            String hash1= User.generateHash(user.getPassword());
            System.out.println(hash);
            System.out.println(hash1);
            if(hash.equals(hash1)){
                System.out.println("TAK");
                return true;
            }else{
                System.out.println("NIE");
                return false;
            }
        }else return false;
    }
    // ------------- PASSWORD TO STRING
    private String getPassword() {
        StringBuilder password = new StringBuilder();
        char[] pass = passField.getPassword();
        for (char c : pass) {
            password.append(c);
        }
        return password.toString();
    }
    // ------------- HELPER FOR HOLDING ID
    public void storeId(String query) throws SQLException {
        idList.clear();
        Statement statement = cn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            idList.add(rs.getInt(1));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if(z==bLogin){
            try {
                if(checklogindata()){
                    setBG();
                    setRightPanel();
                    validate();
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        }
        // MAIN
        else if(z==bOrder){
            setBG();
            setRightOrder();
            try {
                showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                        "FROM Orderr inner join Customer " +
                        "ON Customer_idCustomer=idCustomer " +
                        "ORDER BY dateProduction DESC");
                storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            validate();
        }else if(z==bMenu){
            setBG();
            setRightMenu();
            try {
                showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                        "FROM Pizza inner join Category\n" +
                        "ON Category_idCategory=idCategory\n" +
                        "ORDER BY Pizza.name");
                storeId("SELECT idPizza FROM Pizza ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDrivers){
            setBG();
            setRightDriver();
            try {
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bIngredient){
            setBG();
            setRightIng();
            try {
                showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' FROM Ingredient inner join Supplier " +
                        "ON Supplier_idSupplier=idSupplier\n" +
                        "ORDER BY Ingredient.name");
                storeId("SELECT idIngredient FROM Ingredient ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSupplier){
            setBG();
            setRightSupp();
            try {
                showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                storeId("SELECT idSupplier FROM Supplier ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bExit){
            dispose();
        }else if(z==bMore){
            setBG();
            moreRightPanel();
            validate();
        }
        else if(z==bBack){
            setBG();
            setRightPanel();
        }else if(z==bUsers){
            setBG();
            setRightUsers();
            try {
                showTable("SELECT Login FROM Users ORDER BY Login");
                storeId("SELECT idUser FROM Users ORDER BY Login");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //USERS
        else if(z==bNewUser){
            try {
                newUser();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //ORDER
        else if(z==bNewOrder){
            try {
                newOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditOrder) {
            try {
                editOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDetailsOrder) {
            try {
                detailsOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteOrder) {
            try {
                deleteOrder();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //MENU
        else if(z==bNewPizza){
            try {
                newPizza();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDetailsPizza){
            try {
                detailsPizza();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditPizza){
            try {
                editPizza();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeletePizza){
            try {
                deletePizza();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //DRIVER
        else if(z==bNewDriver){
            newDriver();
        }else if(z==bEditDriver){
            try {
                editDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteDriver){
            try {
                deleteDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //SUPPLIER
        else if(z==bNewSupplier){
            newSupplier();
        }else if(z==bEditSupplier){
            try {
                editSupplier();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteSupplier){
            try {
                deleteSupplier();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //INGREDIENT
        else if(z==bNewIngredient){
            try {
                newIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditIngredient){
            try {
                editIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteIngredient){
            try {
                deleteIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
