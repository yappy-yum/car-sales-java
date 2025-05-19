package Components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Details.AboutUs;
import Details.TaC;
import Details.checkProfile;
import Inventory.stockInventory;
import LoginSystem.isLogin;
import LoginSystem.schedule;
import LoginSystem.storage;
import LoginSystem.Argon2.Argon;
import LoginSystem.LoginPage.Customer.Customer;
import LoginSystem.LoginPage.Job.Job;
import SecondPage.UI;
import SecondPage.EmployeePage.CustomerDB;
import SecondPage.EmployeePage.InventoryDB;
import SecondPage.EmployeePage.ManagerDB;
import SecondPage.EmployeePage.SalesmanDB;
import StoreAnimation.compAnimStorage;
import StoreAnimation.videoAnimStorage;
import frontPage.FrontPage;
import frontPage.isDarkTheme;

public class initializer {
    
    public JFrame frame = new JFrame();
    public isLogin isLogin = new isLogin();
    public isDarkTheme isDarkTheme = new isDarkTheme();
    public Argon Argon = new Argon();
    public storage storage = new storage();
    public schedule schedule = new schedule(storage::_incAge);
    public stockInventory stockInventory = new stockInventory();
    
    public JScrollPane scrollPane;
    public Components component;
    public SwitchThemeComp switchThemeComp;
    public storeVid storeVid;
    public FrontPage frontPage;
    public Customer Customer;
    public AboutUs AboutUs;
    public checkProfile checkProfile;
    public CustomerDB CustomerDB;
    public SalesmanDB SalesmanDB;
    public ManagerDB ManagerDB;
    public InventoryDB InventoryDB;
    public TaC TaC;
    public Job Job;
    public UI UI;

    public compAnimStorage compAnimStorage;
    public videoAnimStorage videoAnimStorage;

    public initializer() {
        new Window(this);
    }

}
