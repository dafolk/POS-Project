package posProjectForTuring.ui;

import posProjectForTuring.ui.panelsForAdminPage.SideMenuPanel;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import posProjectForTuring.controller.Publisher;
import posProjectForTuring.ui.panelsForAdminPage.AdminAccountRegistration;
import posProjectForTuring.ui.panelsForAdminPage.CashierCrud;
import posProjectForTuring.ui.panelsForAdminPage.CashierData;
import posProjectForTuring.ui.panelsForAdminPage.ProductCrud;
import posProjectForTuring.ui.panelsForAdminPage.ProductData;
import posProjectForTuring.ui.panelsForAdminPage.SaleReports;

public class AdminPage extends JFrame {
    public static AdminAccountRegistration adminAccountRegisteration = new AdminAccountRegistration();
    public static ProductData productData = new ProductData();
    public static ProductCrud productCrud = new ProductCrud();
    public static CashierData cashierData = new CashierData();
    public static CashierCrud cashierCrud = new CashierCrud();
    public static SaleReports saleReports = new SaleReports();
    
    public static Publisher cashierSale = new CashierSale();
    
    public AdminPage() {
        SideMenuPanel sideMenuPanel = new SideMenuPanel();
        sideMenuPanel.setBounds(0, 0, 400, 1027);
        
        this.setTitle("Admin Panel");
        this.addPanel(AdminPage.adminAccountRegisteration);
        this.addPanel(AdminPage.productData);
        this.addPanel(AdminPage.productCrud);
        this.addPanel(AdminPage.cashierData);
        this.addPanel(AdminPage.cashierCrud);
        this.addPanel(AdminPage.saleReports);
        
        AdminPage.cashierSale.addSubscriber(AdminPage.saleReports);
        AdminPage.productCrud.addSubscriber(AdminPage.productData);
        AdminPage.cashierCrud.addSubscriber(AdminPage.cashierData);
        
        this.setLayout(null); 
        this.add(sideMenuPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(false);
    }
    
    private void addPanel(JPanel panel){
        panel.setBounds(400, 0, 1520, 1027);
        panel.setVisible(false);
        this.add(panel);
    }
    
    public static void changeUpdateToSubscriber(){
        AdminPage.cashierSale.change();
        AdminPage.productCrud.change();
        AdminPage.cashierCrud.change();
    }
    
    static AdminPage adminPage;
    public static void disposeAdminPage(){
        adminPage.dispose();
    }
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        adminPage = new AdminPage();
        adminPage.setVisible(true);
    }
}
