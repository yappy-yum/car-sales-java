package Components;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Details.AboutUs;
import Details.TaC;
import Details.Verify;
import Details.checkProfile;
import Inventory.stockInventory;
import LoginSystem.isLogin;
import LoginSystem.schedule;
import LoginSystem.storage;
import LoginSystem.Argon2.Argon;
import LoginSystem.LoginPage.Customer.Customer;
import LoginSystem.LoginPage.Job.Job;
import SecondPage.UI;
import SecondPage.password;
import SecondPage.EmployeePage.unverifiedDB.VerifyCust;
import SecondPage.EmployeePage.unverifiedDB.VerifyEmployee;
import SecondPage.EmployeePage.verifiedDB.CustomerDB;
import SecondPage.EmployeePage.verifiedDB.InventoryDB;
import SecondPage.EmployeePage.verifiedDB.ManagerDB;
import SecondPage.EmployeePage.verifiedDB.SalesmanDB;
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
    public password password;
    public Verify Verify;
    public VerifyCust VerifyCust;
    public VerifyEmployee VerifyEmployee;

    public compAnimStorage compAnimStorage;
    public videoAnimStorage videoAnimStorage;

    public initializer() {
        new Window(this);
    }

}
