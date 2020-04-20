package posProjectForTuring.ui.panelsForAdminPage;

import java.sql.Timestamp;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.DateTimeUtils;
import posProjectForTuring.controller.Subscriber;
import posProjectForTuring.model.Transaction;
import posProjectForTuring.model.Voucher;
import posProjectForTuring.model.service.CashierService;
import posProjectForTuring.model.service.ProductService;
import posProjectForTuring.model.service.TransactionService;
import posProjectForTuring.model.service.VoucherService;
import posProjectForTuring.model.service.impl.CashierServiceImpl;
import posProjectForTuring.model.service.impl.ProductServiceImpl;
import posProjectForTuring.model.service.impl.TransactionServiceImpl;
import posProjectForTuring.model.service.impl.VoucherServiceImpl;

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
        this.loadAllReports();
        this.jrbAllReports.setSelected(true);
    }
    
    private void loadAllReports(){
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
    
    private void loadMonthlyReports(){
        List<Transaction> transactions = transactionService.getTransactionByInterval(
                Timestamp.valueOf(DateTimeUtils.getCurrentMonthStart()),
                Timestamp.valueOf(DateTimeUtils.getCurrentMonthEnd())
        );
        DefaultTableModel model = (DefaultTableModel) this.tblReport.getModel();
        if(!transactions.isEmpty()){
            List<Voucher> vouchers = voucherService.getVouchersByTransactionIds(
                transactions.get(0).getId(), 
                transactions.get(transactions.size()-1).getId()
            );

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
        else{
            model.setRowCount(0);
        }
    }
    
    private void loadReportsByInterval(String startInterval, String endInterval){
        List<Transaction> transactions = transactionService.getTransactionByInterval(
                Timestamp.valueOf(startInterval),
                Timestamp.valueOf(endInterval)
        );
        DefaultTableModel model = (DefaultTableModel) this.tblReport.getModel();
        if(!transactions.isEmpty()){
            List<Voucher> vouchers = voucherService.getVouchersByTransactionIds(
                    transactions.get(0).getId(), 
                    transactions.get(transactions.size()-1).getId()
            );
            
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
        else{
            model.setRowCount(0);
        }
    }
    
    private byte getSelectedRadioButton(){
        return Byte.parseByte(this.btngpReportStyle.getSelection().getActionCommand());
    }
    
    private void setSelectedChooserEnabled(){
        switch (getSelectedRadioButton()) {
            case 2:
                this.jStartDateChooser.setEnabled(true);
                this.jEndDateChooser.setEnabled(true);
                this.jStartMonthChooser.setEnabled(false);
                this.jEndMonthChooser.setEnabled(false);
                this.jStartYearChooser.setEnabled(false);
                this.jEndYearChooser.setEnabled(false);
                this.btnComfirmDate.setEnabled(true);
                this.btnConfirmMonth.setEnabled(false);
                this.btnConfirmYear.setEnabled(false);
                break;
            case 3:
                this.jStartMonthChooser.setEnabled(true);
                this.jEndMonthChooser.setEnabled(true);
                this.jStartDateChooser.setEnabled(false);
                this.jEndDateChooser.setEnabled(false);
                this.jStartYearChooser.setEnabled(false);
                this.jEndYearChooser.setEnabled(false);
                this.btnConfirmMonth.setEnabled(true);
                this.btnComfirmDate.setEnabled(false);
                this.btnConfirmYear.setEnabled(false);
                break;
            case 4:
                this.jStartYearChooser.setEnabled(true);
                this.jEndYearChooser.setEnabled(true);
                this.jStartDateChooser.setEnabled(false);
                this.jEndDateChooser.setEnabled(false);
                this.jStartMonthChooser.setEnabled(false);
                this.jEndMonthChooser.setEnabled(false);
                this.btnConfirmYear.setEnabled(true);
                this.btnComfirmDate.setEnabled(false);
                this.btnConfirmMonth.setEnabled(false);
                break;
            default:
                break;
        }
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
        jrbAllReports = new javax.swing.JRadioButton();
        jrbMonthlyReport = new javax.swing.JRadioButton();
        jrbReportByDateInterval = new javax.swing.JRadioButton();
        jStartDateChooser = new com.toedter.calendar.JDateChooser();
        jrbReportByMonthInterval = new javax.swing.JRadioButton();
        jStartMonthChooser = new com.toedter.calendar.JMonthChooser();
        jrbReportByYearInterval = new javax.swing.JRadioButton();
        jStartYearChooser = new com.toedter.calendar.JYearChooser();
        jEndDateChooser = new com.toedter.calendar.JDateChooser();
        jEndMonthChooser = new com.toedter.calendar.JMonthChooser();
        jEndYearChooser = new com.toedter.calendar.JYearChooser();
        btnComfirmDate = new javax.swing.JButton();
        btnConfirmYear = new javax.swing.JButton();
        btnConfirmMonth = new javax.swing.JButton();

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

        btngpReportStyle.add(jrbAllReports);
        jrbAllReports.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbAllReports.setText("All Sale Reports");
        jrbAllReports.setActionCommand("0");
        jrbAllReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbAllReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAllReportsActionPerformed(evt);
            }
        });

        btngpReportStyle.add(jrbMonthlyReport);
        jrbMonthlyReport.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbMonthlyReport.setText("Monthly Sale Report");
        jrbMonthlyReport.setActionCommand("1");
        jrbMonthlyReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbMonthlyReport.setFocusPainted(false);
        jrbMonthlyReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMonthlyReportActionPerformed(evt);
            }
        });

        btngpReportStyle.add(jrbReportByDateInterval);
        jrbReportByDateInterval.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbReportByDateInterval.setText("Sale Report by Date Interval");
        jrbReportByDateInterval.setActionCommand("2");
        jrbReportByDateInterval.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbReportByDateInterval.setFocusPainted(false);
        jrbReportByDateInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbReportByDateIntervalActionPerformed(evt);
            }
        });

        jStartDateChooser.setEnabled(false);
        jStartDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btngpReportStyle.add(jrbReportByMonthInterval);
        jrbReportByMonthInterval.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbReportByMonthInterval.setText("Sale Report by Month Interval");
        jrbReportByMonthInterval.setActionCommand("3");
        jrbReportByMonthInterval.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbReportByMonthInterval.setFocusPainted(false);
        jrbReportByMonthInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbReportByMonthIntervalActionPerformed(evt);
            }
        });

        jStartMonthChooser.setEnabled(false);
        jStartMonthChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btngpReportStyle.add(jrbReportByYearInterval);
        jrbReportByYearInterval.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jrbReportByYearInterval.setText("Sale Report by Year Interval");
        jrbReportByYearInterval.setActionCommand("4");
        jrbReportByYearInterval.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jrbReportByYearInterval.setFocusPainted(false);
        jrbReportByYearInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbReportByYearIntervalActionPerformed(evt);
            }
        });

        jStartYearChooser.setEnabled(false);

        jEndDateChooser.setEnabled(false);
        jEndDateChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jEndMonthChooser.setEnabled(false);
        jEndMonthChooser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jEndYearChooser.setEnabled(false);

        btnComfirmDate.setText("Confirm");
        btnComfirmDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComfirmDate.setEnabled(false);
        btnComfirmDate.setFocusPainted(false);
        btnComfirmDate.setPreferredSize(new java.awt.Dimension(73, 30));
        btnComfirmDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComfirmDateActionPerformed(evt);
            }
        });

        btnConfirmYear.setText("Confirm");
        btnConfirmYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmYear.setEnabled(false);
        btnConfirmYear.setFocusPainted(false);
        btnConfirmYear.setPreferredSize(new java.awt.Dimension(73, 30));
        btnConfirmYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmYearActionPerformed(evt);
            }
        });

        btnConfirmMonth.setText("Confirm");
        btnConfirmMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmMonth.setEnabled(false);
        btnConfirmMonth.setFocusPainted(false);
        btnConfirmMonth.setPreferredSize(new java.awt.Dimension(73, 30));
        btnConfirmMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmMonthActionPerformed(evt);
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
                    .addComponent(jrbAllReports)
                    .addComponent(jrbMonthlyReport)
                    .addComponent(jrbReportByDateInterval)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jrbReportByMonthInterval, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jStartMonthChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jEndMonthChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnConfirmMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jrbReportByYearInterval)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jStartDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(jEndDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(btnComfirmDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jStartYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jEndYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConfirmYear, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jrbAllReports)
                .addGap(18, 18, 18)
                .addComponent(jrbMonthlyReport)
                .addGap(18, 18, 18)
                .addComponent(jrbReportByDateInterval)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jStartDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEndDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnComfirmDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrbReportByMonthInterval)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jStartMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEndMonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jrbReportByYearInterval)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jStartYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEndYearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jrbAllReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAllReportsActionPerformed
        this.loadAllReports();
    }//GEN-LAST:event_jrbAllReportsActionPerformed

    private void jrbMonthlyReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMonthlyReportActionPerformed
        this.loadMonthlyReports();
    }//GEN-LAST:event_jrbMonthlyReportActionPerformed

    private void jrbReportByDateIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbReportByDateIntervalActionPerformed
        this.setSelectedChooserEnabled();
    }//GEN-LAST:event_jrbReportByDateIntervalActionPerformed

    private void jrbReportByMonthIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbReportByMonthIntervalActionPerformed
        this.setSelectedChooserEnabled();
    }//GEN-LAST:event_jrbReportByMonthIntervalActionPerformed

    private void jrbReportByYearIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbReportByYearIntervalActionPerformed
        this.setSelectedChooserEnabled();
    }//GEN-LAST:event_jrbReportByYearIntervalActionPerformed

    private void btnComfirmDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComfirmDateActionPerformed
        this.loadReportsByInterval(
                DateTimeUtils.getStartDateString(this.jStartDateChooser.getDate()),
                DateTimeUtils.getEndDateString(this.jEndDateChooser.getDate())
                );
    }//GEN-LAST:event_btnComfirmDateActionPerformed

    private void btnConfirmMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmMonthActionPerformed
        this.loadReportsByInterval(
                DateTimeUtils.getStartMonthString(this.jStartMonthChooser.getMonth()),
                DateTimeUtils.getEndMonthString(this.jEndMonthChooser.getMonth())
                );
    }//GEN-LAST:event_btnConfirmMonthActionPerformed

    private void btnConfirmYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmYearActionPerformed
        this.loadReportsByInterval(
                DateTimeUtils.getStartYearString(this.jStartYearChooser.getYear()),
                DateTimeUtils.getEndYearString(this.jEndYearChooser.getYear())
                );
    }//GEN-LAST:event_btnConfirmYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnComfirmDate;
    private javax.swing.JButton btnConfirmMonth;
    private javax.swing.JButton btnConfirmYear;
    private javax.swing.ButtonGroup btngpReportStyle;
    private com.toedter.calendar.JDateChooser jEndDateChooser;
    private com.toedter.calendar.JMonthChooser jEndMonthChooser;
    private com.toedter.calendar.JYearChooser jEndYearChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jStartDateChooser;
    private com.toedter.calendar.JMonthChooser jStartMonthChooser;
    private com.toedter.calendar.JYearChooser jStartYearChooser;
    private javax.swing.JRadioButton jrbAllReports;
    private javax.swing.JRadioButton jrbMonthlyReport;
    private javax.swing.JRadioButton jrbReportByDateInterval;
    private javax.swing.JRadioButton jrbReportByMonthInterval;
    private javax.swing.JRadioButton jrbReportByYearInterval;
    private javax.swing.JTable tblReport;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        this.loadAllReports();
    }
}
