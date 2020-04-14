package posProjectForTuring.ui.panelsForAdminPage;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.Publisher;
import posProjectForTuring.controller.Subscriber;
import posProjectForTuring.model.Voucher;
import posProjectForTuring.model.service.CashierService;
import posProjectForTuring.model.service.ProductService;
import posProjectForTuring.model.service.TransactionService;
import posProjectForTuring.model.service.VoucherService;
import posProjectForTuring.model.service.impl.CashierServiceImpl;
import posProjectForTuring.model.service.impl.ProductServiceImpl;
import posProjectForTuring.model.service.impl.TransactionServiceImpl;
import posProjectForTuring.model.service.impl.VoucherServiceImpl;
import posProjectForTuring.ui.CashierSale;

/**
 *
 * @author hp
 */
public class SaleReports extends javax.swing.JPanel implements Subscriber {
    Voucher voucher = new Voucher();
    VoucherService voucherService = new VoucherServiceImpl();
    ProductService productService = new ProductServiceImpl();
    TransactionService transactionService = new TransactionServiceImpl();
    CashierService cashierService = new CashierServiceImpl();
    
    /**
     * Creates new form SaleReport
     */
    public SaleReports() {
        initComponents();
        this.startUp();
    }
    
    private void startUp(){
        this.loadReportTable();
    }
    
    private void loadReportTable(){
        List<Voucher> vouchers = voucherService.getAllVoucher();
        DefaultTableModel model = (DefaultTableModel) this.tblReport.getModel();
        model.setRowCount(0);
        
        for(Voucher voucher: vouchers){
            Object row[] = new Object[7];
            
            row[0] = voucher.getTransactionId();
            row[1] = voucher.getId();
            row[2] = productService.getProductById(
                    voucher.getProductId()
            ).getName();
            row[3] = voucher.getQuantity();
            row[4] = voucher.getPrice();
            row[5] = cashierService.getCashierById(
                    transactionService.getTransactionById(
                            voucher.getTransactionId()
                    ).getCashierId()
            ).getName();
            row[6] = transactionService.getTransactionById(
                    voucher.getTransactionId()
            ).getDate();
            
            model.addRow(row);
        }
    }
    
    private void loadReportTableByMonth(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngpReportStyle = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReport = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        tblReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Product ID", "Product Name", "Quantity", "Price", "Cashier Name", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReport);
        if (tblReport.getColumnModel().getColumnCount() > 0) {
            tblReport.getColumnModel().getColumn(0).setResizable(false);
            tblReport.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblReport.getColumnModel().getColumn(1).setResizable(false);
            tblReport.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblReport.getColumnModel().getColumn(2).setResizable(false);
            tblReport.getColumnModel().getColumn(2).setPreferredWidth(350);
            tblReport.getColumnModel().getColumn(3).setResizable(false);
            tblReport.getColumnModel().getColumn(4).setResizable(false);
            tblReport.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblReport.getColumnModel().getColumn(5).setResizable(false);
            tblReport.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblReport.getColumnModel().getColumn(6).setResizable(false);
        }

        btngpReportStyle.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton1.setText("All Sale Reports");
        jRadioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btngpReportStyle.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton2.setText("Monthly Sale Report");
        jRadioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btngpReportStyle.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jRadioButton3.setText("Sale Report by Date Interval");
        jRadioButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngpReportStyle;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        this.loadReportTable();
    }
}
