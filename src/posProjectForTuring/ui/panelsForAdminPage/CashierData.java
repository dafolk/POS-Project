package posProjectForTuring.ui.panelsForAdminPage;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.Subscriber;
import posProjectForTuring.model.Cashier;
import posProjectForTuring.model.service.CashierService;
import posProjectForTuring.model.service.impl.CashierServiceImpl;

public class CashierData extends javax.swing.JPanel implements Subscriber {
    Cashier cashier = new Cashier();
    CashierService cashierService = new CashierServiceImpl();
    
    public CashierData() {
        initComponents();
        this.loadCashier();
    }
    
    private void loadCashier(){
        List<Cashier> cashiers = cashierService.getAllCashiers();
        DefaultTableModel model = (DefaultTableModel) this.tblCashierData.getModel();
        model.setRowCount(0);
        
        for(Cashier cashier: cashiers){
            Object[]row = new Object[5];
            
            row[0] = cashier.getId();
            row[1] = cashier.getName();
            row[2] = cashier.getUsername();
            row[3] = cashier.getPhoneNo();
            row[4] = cashier.getAddress();
            
            model.addRow(row);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashierData = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setBackground(java.awt.Color.decode("#263238"));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 255, 255)));

        tblCashierData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cashier ID", "Cashier Name", "Username", "Phone Number", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCashierData);
        if (tblCashierData.getColumnModel().getColumnCount() > 0) {
            tblCashierData.getColumnModel().getColumn(0).setResizable(false);
            tblCashierData.getColumnModel().getColumn(1).setResizable(false);
            tblCashierData.getColumnModel().getColumn(2).setResizable(false);
            tblCashierData.getColumnModel().getColumn(3).setResizable(false);
            tblCashierData.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton1.setText("Refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1466, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.loadCashier();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCashierData;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        this.loadCashier();
    }
}
