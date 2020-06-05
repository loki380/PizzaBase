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
import java.io.*;
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
    private String basename;
    private String user;
    private String pw;

    private JButton bOrder;
    private JButton bMenu;
    private JButton bDrivers;
    private JButton bIngredient;
    private JButton bSupplier;
    private JButton bBack;
    private JButton bExit;
    private JButton bMore;
    private JButton bUsers;
    private JButton bSize;
    private JButton bSauce;
    private JButton bCategory;
    private JButton bStatistics;

    private JButton bNewOrder;
    private JButton bEditOrder;
    private JButton bDetailsOrder;
    private JButton bDeleteOrder;
    private JButton bSearchOrder;
    private JTextField tfSearchOrder;
    private JComboBox cbSearchOrder;
    private ArrayList<String> listSearchOrder;

    private JButton bNewUser;
    private JButton bEditUser;
    private JButton bDeleteUser;
    private JButton bDetailsUser;
    private JButton bSearchUser;
    private JTextField tfSearchUser;
    private JComboBox cbSearchUser;
    private ArrayList<String> listSearchUser;

    private JButton bNewPizza;
    private JButton bEditPizza;
    private JButton bDetailsPizza;
    private JButton bDeletePizza;
    private JButton bSearchPizza;
    private JTextField tfSearchPizza;
    private JComboBox cbSearchPizza;
    private ArrayList<String> listSearchPizza;

    private JButton bNewDriver;
    private JButton bEditDriver;
    private JButton bDeleteDriver;
    private JButton bDetailsDriver;
    private JButton bSearchDriver;
    private JTextField tfSearchDriver;
    private JComboBox cbSearchDriver;
    private ArrayList<String> listSearchDriver;

    private JButton bNewIngredient;
    private JButton bEditIngredient;
    private JButton bDeleteIngredient;
    private JButton bDetailsIngredient;
    private JButton bSearchIngredient;
    private JTextField tfSearchIngredient;
    private JComboBox cbSearchIngredient;
    private ArrayList<String> listSearchIngredient;

    private JButton bNewSupplier;
    private JButton bEditSupplier;
    private JButton bDeleteSupplier;
    private JButton bDetailsSupplier;
    private JButton bSearchSupplier;
    private JTextField tfSearchSupplier;
    private JComboBox cbSearchSupplier;
    private ArrayList<String> listSearchSupplier;

    private JButton bNewSize;
    private JButton bEditSize;
    private JButton bDeleteSize;
    private JButton bDetailsSize;
    private JButton bSearchSize;
    private JTextField tfSearchSize;
    private JComboBox cbSearchSize;
    private ArrayList<String> listSearchSize;

    private JButton bNewSauce;
    private JButton bEditSauce;
    private JButton bDeleteSauce;
    private JButton bDetailsSauce;
    private JButton bSearchSauce;
    private JTextField tfSearchSauce;
    private JComboBox cbSearchSauce;
    private ArrayList<String> listSearchSauce;

    private JButton bNewCategory;
    private JButton bEditCategory;
    private JButton bDeleteCategory;
    private JButton bDetailsCategory;
    private JButton bSearchCategory;
    private JTextField tfSearchCategory;
    private JComboBox cbSearchCategory;
    private ArrayList<String> listSearchCategory;

    private ArrayList idList = new ArrayList();

    public Window() throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, IOException {

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
    // ------------- MORE MAIN PANEL
    private void moreRightPanel(){
        pRight = new JPanel();
        pRight.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        pRight.setBounds(550,50,300,450);
        pRight.setLayout(null);

        bSize = new JButton("Size");
        bSize.setBounds(30,30,240,40);
        bSize.setBackground(new Color(0xBDBAA5));
        bSize.addActionListener(this);

        bSauce = new JButton("Sauce");
        bSauce.setBounds(30,100,240,40);
        bSauce.setBackground(new Color(0xBDBAA5));
        bSauce.addActionListener(this);

        bCategory = new JButton("Category");
        bCategory.setBounds(30,170,240,40);
        bCategory.setBackground(new Color(0xBDBAA5));
        bCategory.addActionListener(this);

        bUsers = new JButton("Users");
        bUsers.setBounds(30,240,240,40);
        bUsers.setBackground(new Color(0xBDBAA5));
        bUsers.addActionListener(this);

        bStatistics = new JButton("Statistics");
        bStatistics.setBounds(30,310,240,40);
        bStatistics.setBackground(new Color(0xBDBAA5));
        bStatistics.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,380,180,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bExit = new JButton("X");
        bExit.setBounds(230,380,50,40);
        bExit.setBackground(new Color(0xBDBAA5));
        bExit.addActionListener(this);

        pRight.add(bCategory);
        pRight.add(bSauce);
        pRight.add(bSize);
        pRight.add(bUsers);
        pRight.add(bStatistics);
        pRight.add(bBack);
        pRight.add(bExit);
        bg.add(pRight);
    }
    // ------------- Statistiscs
    private void setStatistics() throws SQLException {
        setBG();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100,50,700,400);
        panel.setBackground(new Color(0xBDBAA5));

        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT TOP 1 name, price FROM Pizza ORDER BY price DESC");

        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT TOP 1 name, price FROM Pizza ORDER BY price");

        Statement st4 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs4 = st4.executeQuery("SELECT CAST(ROUND(AVG(price),2) as numeric(4,2)) FROM Pizza");

        JLabel expensive = new JLabel();
        if(rs.next()){
            expensive.setText("Most expensive pizza: "+rs.getString(1)+" - "+rs.getString(2)+" PLN");
        }
        expensive.setBounds(50,50, 300,30);

        JLabel cheap = new JLabel();
        if(rs1.next()){
            cheap.setText("The cheapest pizza: "+rs1.getString(1)+" - "+rs1.getString(2)+" PLN");
        }
        cheap.setBounds(50,100, 300,30);

        JLabel avgpizza = new JLabel();
        if(rs4.next()){
            avgpizza.setText("Average price of pizza: "+rs4.getString(1)+" PLN");
        }
        avgpizza.setBounds(50,150, 300,30);

        panel.add(expensive);
        panel.add(cheap);
        panel.add(avgpizza);

        Statement st2 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs2 = st2.executeQuery("SELECT TOP 1 name, price FROM Sauce ORDER BY price DESC");

        Statement st3 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs3 = st3.executeQuery("SELECT TOP 1 name, price FROM Sauce ORDER BY price");

        Statement st5 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs5 = st5.executeQuery("SELECT CAST(ROUND(AVG(price),2) as numeric(4,2)) FROM Sauce");

        JLabel expensives = new JLabel();
        if(rs2.next()){
            expensives.setText("Most expensive sauce: "+rs2.getString(1)+" - "+rs2.getString(2)+" PLN");
        }
        expensives.setBounds(50,200, 300,30);

        JLabel cheaps = new JLabel();
        if(rs3.next()){
            cheaps.setText("The cheapest sauce: "+rs3.getString(1)+" - "+rs3.getString(2)+" PLN");
        }
        cheaps.setBounds(50,250, 300,30);

        JLabel avgsauce = new JLabel();
        if(rs5.next()){
            avgsauce.setText("Average price of sauce: "+rs5.getString(1)+" PLN");
        }
        avgsauce.setBounds(50,300, 300,30);

        panel.add(expensives);
        panel.add(cheaps);
        panel.add(avgsauce);

        Statement st6 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs6 = st6.executeQuery("SELECT name FROM Supplier WHERE idSupplier=(SELECT MAX(Supplier_idSupplier) FROM Ingredient)");

        JLabel supp = new JLabel();
        if(rs6.next()){
            supp.setText("The prime Supplier: "+rs6.getString(1));
        }
        supp.setBounds(350,50, 300,30);

        panel.add(supp);

        Statement st7 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs7 = st7.executeQuery("SELECT name FROM Ingredient WHERE idIngredient=(SELECT MAX(Ingredient_idIngredient) FROM Pizza_has_Ingredient)");

        JLabel ing = new JLabel();
        if(rs7.next()){
            ing.setText("The most popular Ingredient: "+rs7.getString(1));
        }
        ing.setBounds(350,100, 300,30);

        panel.add(ing);

        Statement st8 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs8 = st8.executeQuery("SELECT TOP 1 name, price FROM Ingredient ORDER BY price DESC");

        Statement st9 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs9 = st9.executeQuery("SELECT TOP 1 name, price FROM Ingredient ORDER BY price");

        Statement st10 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs10 = st10.executeQuery("SELECT CAST(ROUND(AVG(price),2) as numeric(4,2)) FROM Ingredient");

        JLabel expensives1 = new JLabel();
        if(rs8.next()){
            expensives1.setText("Most expensive Ingredient: "+rs8.getString(1)+" - "+rs8.getString(2)+" PLN");
        }
        expensives1.setBounds(350,150, 300,30);

        JLabel cheaps1 = new JLabel();
        if(rs9.next()){
            cheaps1.setText("The cheapest Ingredient: "+rs9.getString(1)+" - "+rs9.getString(2)+" PLN");
        }
        cheaps1.setBounds(350,200, 300,30);

        JLabel avging = new JLabel();
        if(rs10.next()){
            avging.setText("Average price of Ingredient: "+rs10.getString(1)+" PLN");
        }
        avging.setBounds(350,250, 300,30);

        panel.add(expensives1);
        panel.add(cheaps1);
        panel.add(avging);

        bBack = new JButton("Back");
        bBack.setBounds(375,475,180,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bg.add(panel);
        bg.add(bBack);

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

        bSearchOrder = new JButton("Search");
        bSearchOrder.setBounds(30,30,140,30);
        bSearchOrder.setBackground(new Color(0xBDBAA5));
        bSearchOrder.addActionListener(this);

        tfSearchOrder = new JTextField();
        tfSearchOrder.setBounds(180,30,140,30);

        cbSearchOrder = new JComboBox();
        cbSearchOrder.setBounds(340,30,100,30);
        cbSearchOrder.addItem("Nr");
        cbSearchOrder.addItem("Customer Name");
        cbSearchOrder.addItem("Date");

        listSearchOrder= new ArrayList<>();
        listSearchOrder.add("idOrder");
        listSearchOrder.add("Customer.name");
        listSearchOrder.add("dateProduction");

        pLeft.add(bSearchOrder);
        pLeft.add(tfSearchOrder);
        pLeft.add(cbSearchOrder);

        pRight.add(bNewOrder);
        pRight.add(bEditOrder);
        pRight.add(bDetailsOrder);
        pRight.add(bDeleteOrder);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
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

        bDetailsDriver = new JButton("Details");
        bDetailsDriver.setBounds(30,170,240,40);
        bDetailsDriver.setBackground(new Color(0xBDBAA5));
        bDetailsDriver.addActionListener(this);

        bDeleteDriver = new JButton("Delete");
        bDeleteDriver.setBounds(30,240,240,40);
        bDeleteDriver.setBackground(new Color(0xBDBAA5));
        bDeleteDriver.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchDriver = new JButton("Search");
        bSearchDriver.setBounds(30,30,140,30);
        bSearchDriver.setBackground(new Color(0xBDBAA5));
        bSearchDriver.addActionListener(this);

        tfSearchDriver = new JTextField();
        tfSearchDriver.setBounds(180,30,140,30);

        cbSearchDriver = new JComboBox();
        cbSearchDriver.setBounds(340,30,100,30);
        cbSearchDriver.addItem("Name");
        cbSearchDriver.addItem("Surname");
        cbSearchDriver.addItem("Pesel");

        listSearchDriver= new ArrayList<>();
        listSearchDriver.add("name");
        listSearchDriver.add("surname");
        listSearchDriver.add("pesel");

        pLeft.add(bSearchDriver);
        pLeft.add(tfSearchDriver);
        pLeft.add(cbSearchDriver);

        pRight.add(bNewDriver);
        pRight.add(bEditDriver);
        pRight.add(bDetailsDriver);
        pRight.add(bDeleteDriver);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
    }
    // ------------- MENU PANEL
    private void setRightMenu(){
        pRight.removeAll();

        bNewPizza = new JButton("New Pizza");
        bNewPizza.setBounds(30,30,240,40);
        bNewPizza.setBackground(new Color(0xBDBAA5));
        bNewPizza.addActionListener(this);

        bEditPizza = new JButton("Edit");
        bEditPizza.setBounds(30,100,240,40);
        bEditPizza.setBackground(new Color(0xBDBAA5));
        bEditPizza.addActionListener(this);

        bDetailsPizza = new JButton("Details");
        bDetailsPizza.setBounds(30,170,240,40);
        bDetailsPizza.setBackground(new Color(0xBDBAA5));
        bDetailsPizza.addActionListener(this);

        bDeletePizza = new JButton("Delete");
        bDeletePizza.setBounds(30,240,240,40);
        bDeletePizza.setBackground(new Color(0xBDBAA5));
        bDeletePizza.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchPizza = new JButton("Search");
        bSearchPizza.setBounds(30,30,140,30);
        bSearchPizza.setBackground(new Color(0xBDBAA5));
        bSearchPizza.addActionListener(this);

        tfSearchPizza = new JTextField();
        tfSearchPizza.setBounds(180,30,140,30);

        cbSearchPizza = new JComboBox();
        cbSearchPizza.setBounds(340,30,100,30);
        cbSearchPizza.addItem("Name");
        cbSearchPizza.addItem("Description");
        cbSearchPizza.addItem("Category");
        cbSearchPizza.addItem("Price");

        listSearchPizza= new ArrayList<>();
        listSearchPizza.add("Pizza.name");
        listSearchPizza.add("description");
        listSearchPizza.add("Category.name");
        listSearchPizza.add("price");

        pLeft.add(bSearchPizza);
        pLeft.add(tfSearchPizza);
        pLeft.add(cbSearchPizza);

        pRight.add(bNewPizza);
        pRight.add(bDetailsPizza);
        pRight.add(bEditPizza);
        pRight.add(bDeletePizza);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
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

        bDetailsIngredient = new JButton("Details");
        bDetailsIngredient.setBounds(30,170,240,40);
        bDetailsIngredient.setBackground(new Color(0xBDBAA5));
        bDetailsIngredient.addActionListener(this);

        bDeleteIngredient = new JButton("Delete");
        bDeleteIngredient.setBounds(30,240,240,40);
        bDeleteIngredient.setBackground(new Color(0xBDBAA5));
        bDeleteIngredient.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchIngredient = new JButton("Search");
        bSearchIngredient.setBounds(30,30,140,30);
        bSearchIngredient.setBackground(new Color(0xBDBAA5));
        bSearchIngredient.addActionListener(this);

        tfSearchIngredient = new JTextField();
        tfSearchIngredient.setBounds(180,30,140,30);

        cbSearchIngredient = new JComboBox();
        cbSearchIngredient.setBounds(340,30,100,30);
        cbSearchIngredient.addItem("Name");
        cbSearchIngredient.addItem("Weight");
        cbSearchIngredient.addItem("Price");
        cbSearchIngredient.addItem("Supplier");

        listSearchIngredient= new ArrayList<>();
        listSearchIngredient.add("Ingredient.name");
        listSearchIngredient.add("mass");
        listSearchIngredient.add("price");
        listSearchIngredient.add("Supplier.name");

        pLeft.add(bSearchIngredient);
        pLeft.add(tfSearchIngredient);
        pLeft.add(cbSearchIngredient);

        pRight.add(bNewIngredient);
        pRight.add(bEditIngredient);
        pRight.add(bDetailsIngredient);
        pRight.add(bDeleteIngredient);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
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

        bDetailsSupplier = new JButton("Details");
        bDetailsSupplier.setBounds(30,170,240,40);
        bDetailsSupplier.setBackground(new Color(0xBDBAA5));
        bDetailsSupplier.addActionListener(this);

        bDeleteSupplier = new JButton("Delete");
        bDeleteSupplier.setBounds(30,240,240,40);
        bDeleteSupplier.setBackground(new Color(0xBDBAA5));
        bDeleteSupplier.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchSupplier = new JButton("Search");
        bSearchSupplier.setBounds(30,30,140,30);
        bSearchSupplier.setBackground(new Color(0xBDBAA5));
        bSearchSupplier.addActionListener(this);

        tfSearchSupplier = new JTextField();
        tfSearchSupplier.setBounds(180,30,140,30);

        cbSearchSupplier = new JComboBox();
        cbSearchSupplier.setBounds(340,30,100,30);
        cbSearchSupplier.addItem("Name");
        cbSearchSupplier.addItem("Locality");
        cbSearchSupplier.addItem("Street");

        listSearchSupplier= new ArrayList<>();
        listSearchSupplier.add("name");
        listSearchSupplier.add("locality");
        listSearchSupplier.add("street");

        pLeft.add(bSearchSupplier);
        pLeft.add(tfSearchSupplier);
        pLeft.add(cbSearchSupplier);

        pRight.add(bNewSupplier);
        pRight.add(bEditSupplier);
        pRight.add(bDetailsSupplier);
        pRight.add(bDeleteSupplier);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
    }
    // ------------- USERS PANEL
    private void setRightUsers(){
        pRight.removeAll();

        bNewUser = new JButton("New User");
        bNewUser.setBounds(30,30,240,40);
        bNewUser.setBackground(new Color(0xBDBAA5));
        bNewUser.addActionListener(this);

        bEditUser = new JButton("Edit");
        bEditUser.setBounds(30,100,240,40);
        bEditUser.setBackground(new Color(0xBDBAA5));
        bEditUser.addActionListener(this);

        bDetailsUser = new JButton("Details");
        bDetailsUser.setBounds(30,170,240,40);
        bDetailsUser.setBackground(new Color(0xBDBAA5));
        bDetailsUser.addActionListener(this);

        bDeleteUser = new JButton("Delete");
        bDeleteUser.setBounds(30,240,240,40);
        bDeleteUser.setBackground(new Color(0xBDBAA5));
        bDeleteUser.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchUser = new JButton("Search");
        bSearchUser.setBounds(30,30,140,30);
        bSearchUser.setBackground(new Color(0xBDBAA5));
        bSearchUser.addActionListener(this);

        tfSearchUser = new JTextField();
        tfSearchUser.setBounds(180,30,140,30);

        cbSearchUser = new JComboBox();
        cbSearchUser.setBounds(340,30,100,30);
        cbSearchUser.addItem("Login");

        listSearchUser= new ArrayList<>();
        listSearchUser.add("Login");

        pLeft.add(bSearchUser);
        pLeft.add(tfSearchUser);
        pLeft.add(cbSearchUser);

        pRight.add(bNewUser);
        pRight.add(bEditUser);
        pRight.add(bDetailsUser);
        pRight.add(bDeleteUser);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
    }
    // ------------- SIZE PANEL
    private void setRightSize(){
        pRight.removeAll();

        bNewSize = new JButton("New Size");
        bNewSize.setBounds(30,30,240,40);
        bNewSize.setBackground(new Color(0xBDBAA5));
        bNewSize.addActionListener(this);

        bEditSize = new JButton("Edit Size");
        bEditSize.setBounds(30,100,240,40);
        bEditSize.setBackground(new Color(0xBDBAA5));
        bEditSize.addActionListener(this);

        bDetailsSize = new JButton("Details");
        bDetailsSize.setBounds(30,170,240,40);
        bDetailsSize.setBackground(new Color(0xBDBAA5));
        bDetailsSize.addActionListener(this);

        bDeleteSize = new JButton("Delete");
        bDeleteSize.setBounds(30,240,240,40);
        bDeleteSize.setBackground(new Color(0xBDBAA5));
        bDeleteSize.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchSize = new JButton("Search");
        bSearchSize.setBounds(30,30,140,30);
        bSearchSize.setBackground(new Color(0xBDBAA5));
        bSearchSize.addActionListener(this);

        tfSearchSize = new JTextField();
        tfSearchSize.setBounds(180,30,140,30);

        cbSearchSize = new JComboBox();
        cbSearchSize.setBounds(340,30,100,30);
        cbSearchSize.addItem("Name");
        cbSearchSize.addItem("Diameter");

        listSearchSize= new ArrayList<>();
        listSearchSize.add("name");
        listSearchSize.add("diameter");

        pLeft.add(bSearchSize);
        pLeft.add(tfSearchSize);
        pLeft.add(cbSearchSize);

        pRight.add(bNewSize);
        pRight.add(bEditSize);
        pRight.add(bDetailsSize);
        pRight.add(bDeleteSize);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
    }
    // ------------- SAUCE PANEL
    private void setRightSauce(){
        pRight.removeAll();

        bNewSauce = new JButton("New Sauce");
        bNewSauce.setBounds(30,30,240,40);
        bNewSauce.setBackground(new Color(0xBDBAA5));
        bNewSauce.addActionListener(this);

        bEditSauce = new JButton("Edit");
        bEditSauce.setBounds(30,100,240,40);
        bEditSauce.setBackground(new Color(0xBDBAA5));
        bEditSauce.addActionListener(this);

        bDetailsSauce = new JButton("Details");
        bDetailsSauce.setBounds(30,170,240,40);
        bDetailsSauce.setBackground(new Color(0xBDBAA5));
        bDetailsSauce.addActionListener(this);

        bDeleteSauce = new JButton("Delete");
        bDeleteSauce.setBounds(30,240,240,40);
        bDeleteSauce.setBackground(new Color(0xBDBAA5));
        bDeleteSauce.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchSauce = new JButton("Search");
        bSearchSauce.setBounds(30,30,140,30);
        bSearchSauce.setBackground(new Color(0xBDBAA5));
        bSearchSauce.addActionListener(this);

        tfSearchSauce = new JTextField();
        tfSearchSauce.setBounds(180,30,140,30);

        cbSearchSauce = new JComboBox();
        cbSearchSauce.setBounds(340,30,100,30);
        cbSearchSauce.addItem("Name");
        cbSearchSauce.addItem("Price");

        listSearchSauce= new ArrayList<>();
        listSearchSauce.add("name");
        listSearchSauce.add("price");

        pLeft.add(bSearchSauce);
        pLeft.add(tfSearchSauce);
        pLeft.add(cbSearchSauce);

        pRight.add(bNewSauce);
        pRight.add(bDetailsSauce);
        pRight.add(bEditSauce);
        pRight.add(bDeleteSauce);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
    }
    // ------------- CATEGORY PANEL
    private void setRightCategory(){
        pRight.removeAll();

        bNewCategory = new JButton("New Category");
        bNewCategory.setBounds(30,30,240,40);
        bNewCategory.setBackground(new Color(0xBDBAA5));
        bNewCategory.addActionListener(this);

        bEditCategory = new JButton("Edit Category");
        bEditCategory.setBounds(30,100,240,40);
        bEditCategory.setBackground(new Color(0xBDBAA5));
        bEditCategory.addActionListener(this);

        bDetailsCategory = new JButton("Details");
        bDetailsCategory.setBounds(30,170,240,40);
        bDetailsCategory.setBackground(new Color(0xBDBAA5));
        bDetailsCategory.addActionListener(this);

        bDeleteCategory = new JButton("Delete");
        bDeleteCategory.setBounds(30,240,240,40);
        bDeleteCategory.setBackground(new Color(0xBDBAA5));
        bDeleteCategory.addActionListener(this);

        bBack = new JButton("Back");
        bBack.setBounds(30,310,240,40);
        bBack.setBackground(new Color(0xBDBAA5));
        bBack.addActionListener(this);

        bSearchCategory = new JButton("Search");
        bSearchCategory.setBounds(30,30,140,30);
        bSearchCategory.setBackground(new Color(0xBDBAA5));
        bSearchCategory.addActionListener(this);

        tfSearchCategory = new JTextField();
        tfSearchCategory.setBounds(180,30,140,30);

        cbSearchCategory = new JComboBox();
        cbSearchCategory.setBounds(340,30,100,30);
        cbSearchCategory.addItem("Name");

        listSearchCategory= new ArrayList<>();
        listSearchCategory.add("name");

        pLeft.add(bSearchCategory);
        pLeft.add(tfSearchCategory);
        pLeft.add(cbSearchCategory);

        pRight.add(bNewCategory);
        pRight.add(bDetailsCategory);
        pRight.add(bEditCategory);
        pRight.add(bDeleteCategory);
        pRight.add(bBack);
        bg.add(pRight);
        validate();
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
        scrollPane.setBounds(30,70,400,360);
        pLeft.add(scrollPane);
        bg.add(pLeft);
        validate();
    }
    // ------------- ORDER CRUD
    private void newOrder() throws SQLException {
        NewOrder frame = new NewOrder(cn);
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
            EditOrder frame = new EditOrder(cn, id);
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
            DetailsOrder frame = new DetailsOrder(cn, id);
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
    private void searchOrder() throws SQLException {
        String searchData = tfSearchOrder.getText();
        String text = listSearchOrder.get(cbSearchOrder.getSelectedIndex());
        setBG();
        showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                "FROM Orderr inner join Customer " +
                "ON Customer_idCustomer=idCustomer " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY dateProduction DESC");
        storeId("SELECT idOrder " +
                "FROM Orderr inner join Customer " +
                "ON Customer_idCustomer=idCustomer " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY dateProduction DESC");
        setRightOrder();
    }
    // ------------- PIZZA CRUD
    private void newPizza() throws SQLException {
        NewPizza frame = new NewPizza(cn);
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
            EditPizza frame = new EditPizza(cn, id);
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
            DetailsPizza frame = new DetailsPizza(cn, id);
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
    private void searchPizza() throws SQLException {
        String searchData = tfSearchPizza.getText();
        String text = listSearchPizza.get(cbSearchPizza.getSelectedIndex());
        setBG();
        showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                "FROM Pizza inner join Category\n" +
                "ON Category_idCategory=idCategory\n" +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Pizza.name");
        storeId("SELECT idPizza " +
                "FROM Pizza inner join Category\n" +
                "ON Category_idCategory=idCategory\n" +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Pizza.name");
        setRightMenu();
    }
    // ------------- SUPPLIER CRUD
    private void newSupplier(){
        NewSupplier frame = new NewSupplier(cn);
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
            EditSupplier frame = new EditSupplier(cn, id);
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
    private void detailsSupplier() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsSupplier frame = new DetailsSupplier(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                        storeId("SELECT idSupplier FROM Supplier ORDER BY name");
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
    private void deleteSupplier() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Supplier?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Supplier WHERE idSupplier=" + id);
                showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                storeId("SELECT idSupplier FROM Supplier ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Supplier", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchSupplier() throws SQLException {
        String searchData = tfSearchSupplier.getText();
        String text = listSearchSupplier.get(cbSearchSupplier.getSelectedIndex());
        setBG();
        showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' " +
                "FROM Supplier inner join Address ON Address_idAddress=idAddress " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        storeId("SELECT idSupplier " +
                "FROM Supplier inner join Address ON Address_idAddress=idAddress " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        setRightSupp();
    }
    // ------------- DRIVER CRUD
    private void newDriver(){
        NewDriver frame = new NewDriver(cn);
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
            EditDriver frame = new EditDriver(cn, id);
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
    private void detailsDriver() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsDriver frame = new DetailsDriver(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                        storeId("SELECT idDriver FROM Driver ORDER BY name");
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
    private void deleteDriver() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Driver?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Driver WHERE idDriver=" + id);
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose driver", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchDriver() throws SQLException {
        String searchData = tfSearchDriver.getText();
        String text = listSearchDriver.get(cbSearchDriver.getSelectedIndex());
        setBG();
        showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver WHERE "+text+" LIKE '%"+searchData+"%' ORDER BY name");
        storeId("SELECT idDriver FROM Driver WHERE "+text+" LIKE '%"+searchData+"%' ORDER BY name");
        setRightDriver();
    }
    // ------------- INGREDIENT CRUD
    private void newIngredient() throws SQLException {
        NewIngredient frame = new NewIngredient(cn);
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
            EditIngredient frame = new EditIngredient(cn, id);
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
    private void detailsIngredient() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsIngredient frame = new DetailsIngredient(cn, id);
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
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Ingredient", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteIngredient() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Ingredient?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
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
    private void searchIngredient() throws SQLException {
        String searchData = tfSearchIngredient.getText();
        String text = listSearchIngredient.get(cbSearchIngredient.getSelectedIndex());
        setBG();
        showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' " +
                "FROM Ingredient inner join Supplier " +
                "ON Supplier_idSupplier=idSupplier\n" +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Ingredient.name");
        storeId("SELECT idIngredient "+
                "FROM Ingredient inner join Supplier " +
                "ON Supplier_idSupplier=idSupplier\n" +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Ingredient.name");
        setRightIng();
    }
    // ------------- USERS CRUD
    private void newUser() throws SQLException {
        NewUser frame = new NewUser(cn);
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
    private void editUser() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            EditUser frame = new EditUser(cn, id);
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
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose User", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsUser() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsUser frame = new DetailsUser(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT Login FROM Users ORDER BY Login");
                        storeId("SELECT idUser FROM Users ORDER BY Login");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose User", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteUser() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this User?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Users WHERE idUser=" + id);
                showTable("SELECT Login FROM Users ORDER BY Login");
                storeId("SELECT idUser FROM Users ORDER BY Login");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose User", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchUser() throws SQLException {
        String searchData = tfSearchUser.getText();
        String text = listSearchUser.get(cbSearchUser.getSelectedIndex());
        setBG();
        showTable("SELECT Login " +
                "FROM Users " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Login");
        storeId("SELECT idUser "+
                "FROM Users " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY Login");
        setRightUsers();
    }
    // ------------- SIZE CRUD
    private void newSize() throws SQLException {
        NewSize frame = new NewSize(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name, diameter FROM Size ORDER BY name");
                    storeId("SELECT idSize FROM Size ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    private void editSize() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            EditSize frame = new EditSize(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name, diameter FROM Size ORDER BY name");
                        storeId("SELECT idSize FROM Size ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Size", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsSize() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsSize frame = new DetailsSize(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name, diameter FROM Size ORDER BY name");
                        storeId("SELECT idSize FROM Size ORDER BY name");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Size", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteSize() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Size?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Size WHERE idSize=" + id);
                showTable("SELECT name, diameter FROM Size ORDER BY name");
                storeId("SELECT idSize FROM Size ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Size", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchSize() throws SQLException {
        String searchData = tfSearchSize.getText();
        String text = listSearchSize.get(cbSearchSize.getSelectedIndex());
        setBG();
        showTable("SELECT name, diameter " +
                "FROM Size " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        storeId("SELECT idSize "+
                "FROM Size " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        setRightSize();
    }
    // ------------- SAUCE CRUD
    private void newSauce() throws SQLException {
        NewSauce frame = new NewSauce(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name, price FROM Sauce ORDER BY name");
                    storeId("SELECT idSauce FROM Sauce ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    private void editSauce() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            EditSauce frame = new EditSauce(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name, price FROM Sauce ORDER BY name");
                        storeId("SELECT idSauce FROM Sauce ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Sauce", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsSauce() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsSauce frame = new DetailsSauce(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name, price FROM Sauce ORDER BY name");
                        storeId("SELECT idSauce FROM Sauce ORDER BY name");
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
    private void deleteSauce() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Sauce?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Sauce WHERE idSauce=" + id);
                showTable("SELECT name, price FROM Sauce ORDER BY name");
                storeId("SELECT idSauce FROM Sauce ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Sauce", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchSauce() throws SQLException {
        String searchData = tfSearchSauce.getText();
        String text = listSearchSauce.get(cbSearchSauce.getSelectedIndex());
        setBG();
        showTable("SELECT name, price " +
                "FROM Sauce " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        storeId("SELECT idSauce "+
                "FROM Sauce " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        setRightSauce();
    }
    // ------------- CATEGORY CRUD
    private void newCategory() throws SQLException {
        NewCategory frame = new NewCategory(cn);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    pLeft.remove(scrollPane);
                    showTable("SELECT name FROM Category ORDER BY name");
                    storeId("SELECT idCategory FROM Category ORDER BY name");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    private void editCategory() throws SQLException {
        try{
            int id = (int) idList.get(table.getSelectedRow());
            EditCategory frame = new EditCategory(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name FROM Category ORDER BY name");
                        storeId("SELECT idCategory FROM Category ORDER BY name");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Category", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void detailsCategory() throws SQLException {
        try {
            int id = (int) idList.get(table.getSelectedRow());
            DetailsCategory frame = new DetailsCategory(cn, id);
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        pLeft.remove(scrollPane);
                        showTable("SELECT name FROM Category ORDER BY name");
                        storeId("SELECT idCategory FROM Category ORDER BY name");
                        validate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        } catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Category", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void deleteCategory() throws SQLException {
        Statement st = cn.createStatement();
        try{
            int id = (int) idList.get(table.getSelectedRow());
            Integer choise = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this Category?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if(choise==0) {
                pLeft.remove(scrollPane);
                st.executeUpdate("DELETE FROM Category WHERE idCategory=" + id);
                showTable("SELECT name FROM Category ORDER BY name");
                storeId("SELECT idCategory FROM Category ORDER BY name");
            }
        }catch(IndexOutOfBoundsException ex){
            JOptionPane.showMessageDialog(null,"You must choose Category", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void searchCategory() throws SQLException {
        String searchData = tfSearchCategory.getText();
        String text = listSearchCategory.get(cbSearchCategory.getSelectedIndex());
        setBG();
        showTable("SELECT name " +
                "FROM Category " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        storeId("SELECT idCategory "+
                "FROM Category " +
                "WHERE "+text+" LIKE '%"+searchData+"%' " +
                "ORDER BY name");
        setRightCategory();
    }
    // ------------- CONNECT WITH SQL
    private void connect() throws SQLException, ClassNotFoundException, IOException {
        downloaddata();
        cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName="+basename+";", user, pw);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
    private void downloaddata() throws IOException {
        String filePath = "src/connectdata";
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            basename = fileReader.readLine();
            user = fileReader.readLine();
            pw = fileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
    }
    // ------------- CHECK LOGIN AND PASSWORD
    private boolean checklogindata() throws SQLException, NoSuchAlgorithmException {
        Statement st = cn.createStatement();
        User user = new User(nameField.getText(), getPassword(passField));
        ResultSet rs = st.executeQuery("SELECT Password FROM Users WHERE Login='"+user.getLogin()+"'");
        if(rs.next()){
            String hash=rs.getString(1);
            String hash1= User.generateHash(user.getPassword());
            if(hash.equals(hash1)){
                return true;
            }else{
                return false;
            }
        }else return false;
    }
    // ------------- PASSWORD TO STRING
    private String getPassword(JPasswordField passField) {
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
            try {
                showTable("SELECT idOrder as 'Nr Order', Customer.name as 'Customer name', dateProduction as 'Date' " +
                        "FROM Orderr inner join Customer " +
                        "ON Customer_idCustomer=idCustomer " +
                        "ORDER BY dateProduction DESC");
                storeId("SELECT idOrder FROM Orderr ORDER BY dateProduction DESC");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightOrder();
            validate();
        }else if(z==bMenu){
            setBG();
            try {
                showTable("SELECT Pizza.name as 'Name', description as 'Description', Category.name as 'Category', price as 'Price'\n" +
                        "FROM Pizza inner join Category\n" +
                        "ON Category_idCategory=idCategory\n" +
                        "ORDER BY Pizza.name");
                storeId("SELECT idPizza FROM Pizza ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightMenu();
        }else if(z==bDrivers){
            setBG();
            try {
                showTable("SELECT name as 'Name',surname as 'Surname', pesel as 'Pesel' FROM Driver ORDER BY name");
                storeId("SELECT idDriver FROM Driver ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightDriver();
        }else if(z==bIngredient){
            setBG();
            try {
                showTable("SELECT Ingredient.name as 'Name',mass as 'Weight', price as 'Price', Supplier.name as 'Supplier' FROM Ingredient inner join Supplier " +
                        "ON Supplier_idSupplier=idSupplier\n" +
                        "ORDER BY Ingredient.name");
                storeId("SELECT idIngredient FROM Ingredient ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightIng();
        }else if(z==bSupplier){
            setBG();
            try {
                showTable("SELECT name as 'Name', (CONCAT(locality,' ',postcode)) as 'Locality', (CONCAT(street,' ',nrHouse)) as 'Street' FROM Supplier inner join Address ON Address_idAddress=idAddress ORDER BY name");
                storeId("SELECT idSupplier FROM Supplier ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightSupp();
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
            try {
                showTable("SELECT Login FROM Users ORDER BY Login");
                storeId("SELECT idUser FROM Users ORDER BY Login");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightUsers();
        }else if(z==bSize){
            setBG();
            try {
                showTable("SELECT name, diameter FROM Size ORDER BY name");
                storeId("SELECT idSize FROM Size ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightSize();
        }else if(z==bSauce){
            setBG();
            try {
                showTable("SELECT name, price FROM Sauce ORDER BY name");
                storeId("SELECT idSauce FROM Sauce ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightSauce();
        }else if(z==bCategory){
            setBG();
            try {
                showTable("SELECT name FROM Category ORDER BY name");
                storeId("SELECT idCategory FROM Category ORDER BY name");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setRightCategory();
        }else if(z==bStatistics){
            try {
                setStatistics();
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
        }else if(z==bDetailsUser){
            try {
                detailsUser();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditUser){
            try {
                editUser();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteUser){
            try {
                deleteUser();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchUser){
            try {
                searchUser();
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
        }else if(z==bSearchOrder){
            try {
                searchOrder();
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
        }else if(z==bSearchPizza){
            try {
                searchPizza();
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
        }else if(z==bDetailsDriver){
            try {
                detailsDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteDriver){
            try {
                deleteDriver();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchDriver){
            try {
                searchDriver();
                validate();
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
        }else if(z==bDetailsSupplier){
            try {
                detailsSupplier();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteSupplier){
            try {
                deleteSupplier();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchSupplier){
            try {
                searchSupplier();
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
        }else if(z==bDetailsIngredient){
            try {
                detailsIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteIngredient){
            try {
                deleteIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchIngredient){
            try {
                searchIngredient();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //SIZE
        else if(z==bNewSize){
            try {
                newSize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditSize){
            try {
                editSize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDetailsSize){
            try {
                detailsSize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteSize){
            try {
                deleteSize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchSize){
            try {
                searchSize();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //SAUCE
        else if(z==bNewSauce){
            try {
                newSauce();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditSauce){
            try {
                editSauce();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDetailsSauce){
            try {
                detailsSauce();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteSauce){
            try {
                deleteSauce();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchSauce){
            try {
                searchSauce();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        //CATEGORY
        else if(z==bNewCategory){
            try {
                newCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bEditCategory){
            try {
                editCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDetailsCategory){
            try {
                detailsCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bDeleteCategory){
            try {
                deleteCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else if(z==bSearchCategory){
            try {
                searchCategory();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
