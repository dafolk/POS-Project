package posProjectForTuring.ui;

import posProjectForTuring.ui.panelsForAdminPage.ControlPanel;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JPanel;
import posProjectForTuring.ui.panelsForAdminPage.ProductCrud;
import posProjectForTuring.ui.panelsForAdminPage.ProductData;

public class AdminPage extends JFrame {
    public static ProductData productData = new ProductData();
    public static ProductCrud productCrud = new ProductCrud();
    public AdminPage() {
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setBounds(0, 0, 400, 1015);
        
        this.addPanel(this.productData);
        this.addPanel(this.productCrud);
        
        this.setLayout(null);
        this.add(controlPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setVisible(false);
    }
    public void addPanel(JPanel panel){
        panel.setBounds(400, 0, 1520, 1015);
        panel.setVisible(false);
        this.add(panel);
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
        new AdminPage().setVisible(true);
    }
}
