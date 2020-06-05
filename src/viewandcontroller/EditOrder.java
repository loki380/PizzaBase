package viewandcontroller;

import models.Address;
import models.Customer;
import models.Order;
import models.DateLabelFormatter;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditOrder extends JFrame implements ActionListener, KeyListener {
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
    private JButton bEdit;
    private JButton bNext;
    private ArrayList<ArrayList<Integer>> listOfidPizza = new ArrayList();
    private ArrayList<Integer> idDriver = new ArrayList();
    private ArrayList<Integer> idSauce = new ArrayList();
    private ArrayList<Integer> idList = new ArrayList();
    private ArrayList<JComboBox> listPizza = new ArrayList();
    private ArrayList<JTextField> listAmount = new ArrayList();
    private Integer i = 0;
    private Integer id;
    private JDatePickerImpl datePicker;
    Customer customer = new Customer();
    Address adres = new Address();
    Order order = new Order();


    public EditOrder(Connection cn, Integer id) throws SQLException {

        setSize(880,550);
        setTitle("Edit Order");
        setLocationRelativeTo(null);
        setResizable(false);

        cp = this.getContentPane();
        cp.setLayout(null);
        cp.setBackground(new Color(0xBDBAA5));
        this.cn=cn;
        this.id=id;
        buildPanel();
    }
    private void buildPanel() throws SQLException {
        pOrder=new JPanel();
        pOrder.setBounds(25,25,400,425);
        pOrder.setLayout(null);

        pCustomer=new JPanel();
        pCustomer.setBounds(450,25,400,425);
        pCustomer.setLayout(null);

        bEdit = new JButton("Edit");
        bEdit.setBounds(390,470,100,30);
        bEdit.addActionListener(this);

        cp.add(pOrder);
        cp.add(pCustomer);
        cp.add(bEdit);

        buildOrder();
        buildCustomer();
    }
    private void buildOrder() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st2 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st3 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        Statement st4 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT idSauce, name " +
                "FROM Sauce WHERE idSauce=(SELECT Sauce_idSauce FROM Orderr WHERE idOrder="+id+")");
        ResultSet rs1 = st1.executeQuery("SELECT idDriver, name " +
                "FROM Driver WHERE idDriver=(SELECT Driver_idDriver FROM Orderr WHERE idOrder="+id+")");
        ResultSet rs2 = st2.executeQuery("SELECT idSauce, name " +
                "FROM Sauce ");
        ResultSet rs3 = st3.executeQuery("SELECT idDriver, name " +
                "FROM Driver");
        ResultSet rs4 = st4.executeQuery("SELECT price_delivery, year(dateProduction), month(dateProduction), day(dateProduction) " +
                "FROM Orderr WHERE idOrder="+id);
        JLabel lOrder = new JLabel("Order");
        JLabel lPizza = new JLabel("Pizza:");
        JLabel lSauce = new JLabel("Sauce:");
        JLabel lDriver = new JLabel("Driver:");
        JLabel lpriced = new JLabel("Price Delivery:");
        JLabel ldata = new JLabel("Production Date: ");
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
        ldata.setBounds(20,370,100,30);
        ldata.setForeground(Color.BLACK);

        pPizza = new JPanel();
        pPizza.setBackground(Color.GRAY);
        pPizza.setPreferredSize(new Dimension(180,300));
        scrollPane = new JScrollPane(pPizza);
        scrollPane.setBounds(150,70,205,130);
        pPizza.setLayout(null);

        bNext = new JButton("+");
        bNext.setBounds(0,30,50,30);
        bNext.addActionListener(this);
        pPizza.add(bNext);

        cbSauce = new JComboBox();
        cbSauce.setBounds(150,220,200,30);
        cbDriver = new JComboBox();
        cbDriver.setBounds(150,270,200,30);
        tfPriced = new JTextField();
        tfPriced.setBounds(150,320,200,30);

        if(rs.next()) {
            idSauce.add(rs.getInt(1));
            cbSauce.addItem(rs.getString(2));
        }
        if(rs1.next()) {
            idDriver.add(rs1.getInt(1));
            cbDriver.addItem(rs1.getString(2));
        }
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
        UtilDateModel model = new UtilDateModel();
        if(rs4.next()) {
            tfPriced.setText(rs4.getString(1));
            System.out.println(rs4.getInt(3));
            model.setDate(rs4.getInt(2), rs4.getInt(3)-1, rs4.getInt(4));
        }
        setPizza();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(150,370,200,30);

        pOrder.add(lOrder);
        pOrder.add(datePicker);
        pOrder.add(lPizza);
        pOrder.add(lSauce);
        pOrder.add(lDriver);
        pOrder.add(lpriced);
        pOrder.add(ldata);
        pOrder.add(cbSauce);
        pOrder.add(cbDriver);
        pOrder.add(tfPriced);
        pOrder.add(scrollPane);

    }
    private void buildCustomer() throws SQLException {
        Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery("SELECT Customer.name, Customer.surname,Customer.tel,Address.street,Address.nrHouse,Address.nrFlat,Address.locality,Address.postcode " +
                "FROM Orderr inner join Customer " +
                "ON Customer_idCustomer=idCustomer inner join Address " +
                "ON Address_idAddress=idAddress " +
                "WHERE idOrder="+id);

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
        if(rs.next()) {
            customer.setName(rs.getString(1));
            customer.setSurname(rs.getString(2));
            customer.setTel(rs.getString(3));
            adres.setStreet(rs.getString(4));
            adres.setNrHouse(rs.getString(5));
            adres.setNrFlat(rs.getString(6));
            adres.setLocality(rs.getString(7));
            adres.setPostcode(rs.getString(8));
        }
        tfName.setText(customer.getName());
        tfSurname.setText(customer.getSurname());
        tfTel.setText(customer.getTel());
        tfStreet.setText(adres.getStreet());
        tfnr.setText(adres.getNrHouse()+"/"+adres.getNrFlat());
        tfCity.setText(adres.getLocality());
        tfPostcode.setText(adres.getPostcode());

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
    private void updateOrder() throws SQLException {
        Statement st = cn.createStatement();
        order.setIdDriver(idDriver.get((cbDriver.getSelectedIndex())));
        order.setIdSauce(idSauce.get((cbSauce.getSelectedIndex())));
        Date selectedDate = (Date) datePicker.getModel().getValue();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        order.setData(sdf1.format(selectedDate));
        order.setPrice(tfPriced.getText());
        st.executeUpdate("UPDATE Orderr SET " +
                "Driver_idDriver="+order.getIdDriver()+
                ",Sauce_idSauce="+order.getIdSauce()+
                ",dateProduction='"+order.getData()+
                "',price_delivery="+order.getPrice()+" WHERE idOrder="+id);
        st.executeUpdate("DELETE FROM Order_has_Pizza WHERE Orderr_idOrder="+id);
        int j=0;
        for(JComboBox x: listPizza){
            Integer pizza = listOfidPizza.get(j).get(x.getSelectedIndex());
            String amount = (listAmount.get(j)).getText();
            j++;

            st.executeUpdate("INSERT INTO Order_has_Pizza(Orderr_idOrder,Pizza_idPizza,amount) VALUES" +
                    "("+id+
                    ","+pizza+
                    ","+amount+")");
        }
        dispose();
    }
    private void updateCustomer() throws SQLException {
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

        st.executeUpdate("UPDATE Address SET " +
                "street='"+adres.getStreet()+
                "', nrHouse="+adres.getNrHouse()+
                ", nrFlat="+adres.getNrFlat()+
                ", locality='"+adres.getLocality()+"'" +
                ", postcode='"+adres.getPostcode()+"' " +
                "WHERE idAddress=(SELECT Address_idAddress FROM Customer WHERE idCustomer=(SELECT Customer_idCustomer FROM Orderr WHERE idOrder="+id+"))");
        storeid("SELECT idAddress FROM Address ORDER BY idAddress");
        st.executeUpdate("UPDATE Customer SET " +
                "name='"+customer.getName()+
                "', surname='"+customer.getSurname()+
                "', tel='"+customer.getTel()+"' " +
                "WHERE idCustomer=(SELECT Customer_idCustomer FROM Orderr WHERE idOrder="+id+")");
        dispose();
    }
    private void addPizza() throws SQLException {
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT idPizza, name " +
                "FROM Pizza");

        JComboBox cbPizza = new JComboBox();
        JTextField tfAmount = new JTextField("0");
        ArrayList<Integer> idPizza = new ArrayList();

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
        listOfidPizza.add(idPizza);
        listPizza.add(cbPizza);
        listAmount.add(tfAmount);
        validate();
    }
    private void setPizza() throws SQLException {
        Statement st1 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs1 = st1.executeQuery("SELECT Pizza.name, Pizza_idPizza, amount " +
                "FROM Order_has_Pizza inner join Pizza " +
                "ON Pizza_idPizza=idPizza "+
                "WHERE Orderr_idOrder="+id);
        if(rs1.next()) {
            do{
                JComboBox cbPizza = new JComboBox();
                JTextField tfAmount = new JTextField();
                ArrayList<Integer> idPizza = new ArrayList();

                cbPizza.addItem(rs1.getString(1));
                idPizza.add(rs1.getInt(2));
                tfAmount.setText(rs1.getString(3));
                Statement st2 = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs3 = st2.executeQuery("SELECT idPizza, name " +
                        "FROM Pizza WHERE idPizza!="+rs1.getInt(2));
                if(rs3.next()) {
                    do{
                        idPizza.add(rs3.getInt(1));
                        cbPizza.addItem(rs3.getString(2));
                    }while (rs3.next());
                }
                cbPizza.setBounds(0,i,140,30);
                tfAmount.setBounds(140,i,50,30);
                bNext.setBounds(0,i+30,50,30);
                i += 30;

                pPizza.add(cbPizza);
                pPizza.add(tfAmount);
                listOfidPizza.add(idPizza);
                listPizza.add(cbPizza);
                listAmount.add(tfAmount);
                validate();
            }while (rs1.next());
        }
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
        if(z==bEdit){
            try {
            boolean correct = customer.checkName() && customer.checkSurname() && customer.checkTel() && order.checkPrice() && adres.checkLocality() && adres.checkPostcode() && adres.checkStreet() && adres.checkNrHouse() && adres.checkNrFlat() && order.checkData();
            if(correct){
                updateCustomer();
                updateOrder();
            }
            else JOptionPane.showMessageDialog(null,"Oops, you enter wrong values", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (NullPointerException | ParseException ex){
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
