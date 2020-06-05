package viewandcontroller;

import models.Address;
import models.Customer;
import models.Order;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewOrder extends JFrame implements ActionListener, KeyListener {
    private Container cp; //Main Panel
    private Connection cn;
    private JPanel pOrder;
    private JPanel pCustomer;
    private JPanel pPizza;
    private JScrollPane scrollPane;
    private JTextField tfName;
    private JTextField tfSurname;
    private JTextField tfTel;
    private JTextField tfStreet;
    private JTextField tfnr;
    private JTextField tfCity;
    private JTextField tfPostcode;
    private JTextField tfPriced;
    private JComboBox cbDriver;
    private JComboBox cbSauce;
    private JButton bAdd;
    private JButton bNext;
    private ArrayList<Integer> idPizza = new ArrayList();
    private ArrayList<Integer> idDriver = new ArrayList();
    private ArrayList<Integer> idSauce = new ArrayList();
    private ArrayList<Integer> idList = new ArrayList();
    private ArrayList<JComboBox> listPizza = new ArrayList();
    private ArrayList<JTextField> listAmount = new ArrayList();
    private Integer i = 0;
    Customer customer = new Customer();
    Address adres = new Address();
    Order order = new Order();

    public NewOrder(Connection cn) throws SQLException {

        setSize(880,550);
        setTitle("New Order");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(0xBDBAA5));
        this.cn=cn;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        pOrder=new JPanel();
        pOrder.setBounds(25,25,400,425);
        pOrder.setLayout(null);

        pCustomer=new JPanel();
        pCustomer.setBounds(450,25,400,425);
        pCustomer.setLayout(null);

        bAdd = new JButton("Add");
        bAdd.setBounds(390,470,100,30);
        bAdd.addActionListener(this);

        cp.add(pOrder);
        cp.add(pCustomer);
        cp.add(bAdd);

        buildOrder();
        buildCustomer();
    }
    private void buildOrder() throws SQLException {
        Statement st2 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st3 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs2 = st2.executeQuery("SELECT idSauce, name " +
                "FROM Sauce");
        ResultSet rs3 = st3.executeQuery("SELECT idDriver, name " +
                "FROM Driver");
        JLabel lOrder = new JLabel("Order");
        JLabel lPizza = new JLabel("Pizza:");
        JLabel lSauce = new JLabel("Sauce:");
        JLabel lDriver = new JLabel("Driver:");
        JLabel lpriced = new JLabel("Price Delivery:");
        lOrder.setBounds(175,20,100,30);
        lOrder.setForeground(Color.BLACK);
        lPizza.setBounds(20,70,100,30);
        lPizza.setForeground(Color.BLACK);
        lSauce.setBounds(20,220,100,30);
        lSauce.setForeground(Color.BLACK);
        lDriver.setBounds(20,270,100,30);
        lDriver.setForeground(Color.BLACK);
        lpriced.setBounds(20,320,100,30);
        lpriced.setForeground(Color.BLACK);

        pPizza = new JPanel();
        pPizza.setBackground(Color.GRAY);
        pPizza.setPreferredSize(new Dimension(180,300));
        scrollPane = new JScrollPane(pPizza);
        scrollPane.setBounds(150,70,205,130);
//        pPizza.setAutoscrolls(true);
        pPizza.setLayout(null);
        bNext = new JButton("+");
        bNext.setBounds(0,30,50,30);
        bNext.addActionListener(this);
        pPizza.add(bNext);

        addPizza();

        cbSauce = new JComboBox();
        cbSauce.setBounds(150,220,200,30);
        cbDriver = new JComboBox();
        cbDriver.setBounds(150,270,200,30);
        tfPriced = new JTextField();
        tfPriced.setBounds(150,320,200,30);
        tfPriced.addKeyListener(this);

        if(rs2.next()) {
            do{
                idSauce.add(rs2.getInt(1));
                cbSauce.addItem(rs2.getString(2));
            }while (rs2.next());
        }
        if(rs3.next()) {
            do{
                idDriver.add(rs3.getInt(1));
                cbDriver.addItem(rs3.getString(2));
            }while (rs3.next());
        }


        pOrder.add(lOrder);
        pOrder.add(lPizza);
        pOrder.add(lSauce);
        pOrder.add(lDriver);
        pOrder.add(lpriced);
        pOrder.add(cbSauce);
        pOrder.add(cbDriver);
        pOrder.add(tfPriced);
        pOrder.add(scrollPane);

    }
    private void buildCustomer(){
        JLabel lcustomer = new JLabel("Customer");
        JLabel lname = new JLabel("Name:");
        JLabel lsurname = new JLabel("Surname:");
        JLabel ltel = new JLabel("Telephone:");
        JLabel lstreet = new JLabel("Street:");
        JLabel lnr = new JLabel("Nr:");
        JLabel lcity = new JLabel("City:");
        JLabel lpostcode = new JLabel("Postcode:");
        lcustomer.setBounds(175,20,100,30);
        lcustomer.setForeground(Color.BLACK);
        lname.setBounds(20,70,100,30);
        lname.setForeground(Color.BLACK);
        lsurname.setBounds(20,120,100,30);
        lsurname.setForeground(Color.BLACK);
        ltel.setBounds(20,170,100,30);
        ltel.setForeground(Color.BLACK);
        lstreet.setBounds(20,220,100,30);
        lstreet.setForeground(Color.BLACK);
        lnr.setBounds(20,270,100,30);
        lnr.setForeground(Color.BLACK);
        lcity.setBounds(20,320,100,30);
        lcity.setForeground(Color.BLACK);
        lpostcode.setBounds(20,370,100,30);
        lpostcode.setForeground(Color.BLACK);

        tfName = new JTextField();
        tfName.setBounds(150,70,200,30);
        tfName.addKeyListener(this);
        tfSurname = new JTextField();
        tfSurname.setBounds(150,120,200,30);
        tfSurname.addKeyListener(this);
        tfTel = new JTextField();
        tfTel.setBounds(150,170,200,30);
        tfTel.addKeyListener(this);
        tfStreet = new JTextField();
        tfStreet.setBounds(150,220,200,30);
        tfStreet.addKeyListener(this);
        tfnr = new JTextField();
        tfnr.setBounds(150,270,200,30);
        tfnr.addKeyListener(this);
        tfCity = new JTextField();
        tfCity.setBounds(150,320,200,30);
        tfCity.addKeyListener(this);
        tfPostcode = new JTextField();
        tfPostcode.setBounds(150,370,200,30);
        tfPostcode.addKeyListener(this);

        pCustomer.add(lcustomer);
        pCustomer.add(lname);
        pCustomer.add(lsurname);
        pCustomer.add(ltel);
        pCustomer.add(lstreet);
        pCustomer.add(lnr);
        pCustomer.add(lcity);
        pCustomer.add(lpostcode);
        pCustomer.add(tfName);
        pCustomer.add(tfSurname);
        pCustomer.add(tfTel);
        pCustomer.add(tfStreet);
        pCustomer.add(tfnr);
        pCustomer.add(tfCity);
        pCustomer.add(tfPostcode);
    }
    private void addOrder() throws SQLException {
        Statement st = cn.createStatement();
        order.setIdDriver(idDriver.get((cbDriver.getSelectedIndex())));
        order.setIdSauce(idSauce.get((cbSauce.getSelectedIndex())));
        Date nowDate = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        order.setData(sdf1.format(nowDate));
        order.setPrice(tfPriced.getText());
        storeid("SELECT idCustomer FROM Customer ORDER BY idCustomer");
        int id=0;
        for(int c : idList){
            if(c>id) id=c;
        }
        order.setIdCustomer(id);
        st.executeUpdate("INSERT INTO Orderr(Customer_idCustomer,Driver_idDriver,Sauce_idSauce,dateProduction,price_delivery) VALUES" +
                "("+order.getIdCustomer()+
                ", "+order.getIdDriver()+
                ","+order.getIdSauce()+
                ",'"+order.getData()+
                "',"+order.getPrice()+")");
        storeid("SELECT idOrder FROM Orderr ORDER BY idOrder");
        id=0;
        for(int c : idList){
            if(c>id) id=c;
        }
        int j=0;
        for(JComboBox x: listPizza){
            Integer pizza = idPizza.get((x.getSelectedIndex()));
            String amount = (listAmount.get(j)).getText();
            j++;
            st.executeUpdate("INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount) VALUES" +
                    "("+id+
                    ","+pizza+
                    ","+amount+")");
        }
        dispose();
    }
    private void addCustomer() throws SQLException {
        Statement st = cn.createStatement();
        customer.setName(tfName.getText());
        customer.setSurname(tfSurname.getText());
        customer.setTel(tfTel.getText());
        adres.setLocality(tfCity.getText());
        adres.setPostcode(tfPostcode.getText());
        adres.setStreet(tfStreet.getText());
        String[] number = tfnr.getText().split("/");
        adres.setNrHouse(number[0]);
        String nrflat;
        if(number.length==2) {
            nrflat = number[1];
        }else{
            nrflat=null;
        }
        adres.setNrFlat(nrflat);

        st.executeUpdate("INSERT INTO Address(street,nrHouse,nrFlat,locality,postcode) VALUES" +
                "('"+adres.getStreet()+
                "', "+adres.getNrHouse()+
                ","+adres.getNrFlat()+
                ",'"+adres.getLocality()+"'" +
                ",'"+adres.getPostcode()+"')");
        storeid("SELECT idAddress FROM Address ORDER BY idAddress");
        int id=0;
        for(int c : idList){
            if(c>id) id=c;
        }
        customer.setIdAdres(id);
        st.executeUpdate("INSERT INTO Customer(Address_idAddress,name,surname,tel) VALUES" +
                "("+customer.getIdAdres()+
                ", '"+customer.getName()+
                "','"+customer.getSurname()+
                "','"+customer.getTel()+"')");
        dispose();
    }
    private void addPizza() throws SQLException {
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT idPizza, name " +
                "FROM Pizza");

        JComboBox cbPizza = new JComboBox();
        JTextField tfAmount = new JTextField("0");
        idPizza.add(null);
        cbPizza.addItem(null);
        if(rs1.next()) {
            do{
                idPizza.add(rs1.getInt(1));
                cbPizza.addItem(rs1.getString(2));
            }while (rs1.next());
        }
        cbPizza.setBounds(0,i,140,30);
        tfAmount.setBounds(140,i,50,30);
        bNext.setBounds(0,i+30,50,30);
        i += 30;
        pPizza.add(cbPizza);
        pPizza.add(tfAmount);
        listPizza.add(cbPizza);
        listAmount.add(tfAmount);
        validate();
    }
    public void storeid(String query) throws SQLException {
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
        if(z==bAdd){
            try {
                boolean correct = customer.checkName() && customer.checkSurname() && customer.checkTel() && order.checkPrice() && adres.checkLocality() && adres.checkPostcode() && adres.checkStreet() && adres.checkNrHouse() && adres.checkNrFlat();
                if(correct){
                    addCustomer();
                    addOrder();
                }
                else JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (NullPointerException ex){
                JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if(z==bNext){
            try {
                addPizza();
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
            customer.setName(tfName.getText());
            if(customer.checkName()) tfName.setBackground(Color.GREEN);
            else tfName.setBackground(Color.RED);
        }else if(z==tfSurname){
            customer.setSurname(tfSurname.getText());
            if(customer.checkSurname()) tfSurname.setBackground(Color.GREEN);
            else tfSurname.setBackground(Color.RED);
        }else if(z==tfTel){
            customer.setTel(tfTel.getText());
            if(customer.checkTel()) tfTel.setBackground(Color.GREEN);
            else tfTel.setBackground(Color.RED);
        }else if(z==tfCity){
            adres.setLocality(tfCity.getText());
            if(adres.checkLocality()) tfCity.setBackground(Color.GREEN);
            else tfCity.setBackground(Color.RED);
        }else if(z==tfPostcode){
            adres.setPostcode(tfPostcode.getText());
            if(adres.checkPostcode()) tfPostcode.setBackground(Color.GREEN);
            else tfPostcode.setBackground(Color.RED);
        }else if(z==tfStreet){
            adres.setStreet(tfStreet.getText());
            if(adres.checkStreet()) tfStreet.setBackground(Color.GREEN);
            else tfStreet.setBackground(Color.RED);
        }else if(z==tfnr){
            String[] number = tfnr.getText().split("/");
            adres.setNrHouse(number[0]);
            try{
                adres.setNrFlat(number[1]);
                if(adres.checkNrFlat() && adres.checkNrHouse()) tfnr.setBackground(Color.GREEN);
                else tfnr.setBackground(Color.RED);
            }catch (ArrayIndexOutOfBoundsException ex){
                if(adres.checkNrHouse()) tfnr.setBackground(Color.GREEN);
                else tfnr.setBackground(Color.RED);
            }
        }else if(z==tfPriced){
            order.setPrice(tfPriced.getText());
            if(order.checkPrice()) tfPriced.setBackground(Color.GREEN);
            else tfPriced.setBackground(Color.RED);
        }
    }
}
