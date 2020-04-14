/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.ui.panelsForAdminPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import posProjectForTuring.ui.AdminLoginDialog;
import posProjectForTuring.ui.AdminPage;
import posProjectForTuring.ui.MainLogin;
import static posProjectForTuring.ui.MainLogin.mainLogin;

/**
 *
 * @author hp
 */
public class SideMenuPanel extends javax.swing.JPanel {
    
    public SideMenuPanel() {
        initComponents();
        addHoverEffect();
        this.startUp();
    }
    
    private void startUp(){
        this.btnAdminProfile.setText(AdminLoginDialog.adminName);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProductData = new javax.swing.JButton();
        btnProductCrud = new javax.swing.JButton();
        btnSaleReports = new javax.swing.JButton();
        btnCashierCrud = new javax.swing.JButton();
        btnCashierData = new javax.swing.JButton();
        btnAdminProfile = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnAdminAccountCreation = new javax.swing.JButton();

        setBackground(java.awt.Color.decode("#263238"));

        btnProductData.setBackground(java.awt.Color.decode("#263238"));
        btnProductData.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnProductData.setForeground(new java.awt.Color(240, 240, 240));
        btnProductData.setText("Product Data");
        btnProductData.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProductData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductData.setFocusPainted(false);
        btnProductData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductDataActionPerformed(evt);
            }
        });

        btnProductCrud.setBackground(java.awt.Color.decode("#263238"));
        btnProductCrud.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnProductCrud.setForeground(new java.awt.Color(240, 240, 240));
        btnProductCrud.setText("Product CRUD");
        btnProductCrud.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnProductCrud.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProductCrud.setFocusPainted(false);
        btnProductCrud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductCrudActionPerformed(evt);
            }
        });

        btnSaleReports.setBackground(java.awt.Color.decode("#263238"));
        btnSaleReports.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnSaleReports.setForeground(new java.awt.Color(240, 240, 240));
        btnSaleReports.setText("Sale Reports");
        btnSaleReports.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnSaleReports.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSaleReports.setFocusPainted(false);
        btnSaleReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleReportsActionPerformed(evt);
            }
        });

        btnCashierCrud.setBackground(java.awt.Color.decode("#263238"));
        btnCashierCrud.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnCashierCrud.setForeground(new java.awt.Color(240, 240, 240));
        btnCashierCrud.setText("Cashier CRUD");
        btnCashierCrud.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnCashierCrud.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCashierCrud.setFocusPainted(false);
        btnCashierCrud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashierCrudActionPerformed(evt);
            }
        });

        btnCashierData.setBackground(java.awt.Color.decode("#263238"));
        btnCashierData.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnCashierData.setForeground(new java.awt.Color(240, 240, 240));
        btnCashierData.setText("Cashier Data");
        btnCashierData.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnCashierData.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCashierData.setFocusPainted(false);
        btnCashierData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCashierDataActionPerformed(evt);
            }
        });

        btnAdminProfile.setBackground(java.awt.Color.decode("#263238"));
        btnAdminProfile.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        btnAdminProfile.setForeground(new java.awt.Color(240, 240, 240));
        btnAdminProfile.setText("Admin");
        btnAdminProfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnAdminProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminProfile.setFocusPainted(false);
        btnAdminProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminProfileActionPerformed(evt);
            }
        });

        btnLogOut.setBackground(new java.awt.Color(51, 153, 255));
        btnLogOut.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.setFocusPainted(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnAdminAccountCreation.setBackground(java.awt.Color.decode("#263238"));
        btnAdminAccountCreation.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnAdminAccountCreation.setForeground(new java.awt.Color(240, 240, 240));
        btnAdminAccountCreation.setText("Admin Account Registeration");
        btnAdminAccountCreation.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnAdminAccountCreation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdminAccountCreation.setFocusPainted(false);
        btnAdminAccountCreation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminAccountCreationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnProductData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnProductCrud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(btnSaleReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(btnCashierCrud, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCashierData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnAdminProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnLogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(btnAdminAccountCreation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnAdminProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnAdminAccountCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCashierData, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnCashierCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnProductData, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnProductCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSaleReports, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void modifyFocusedButtonDesign(AbstractButton focusedButton){
        focusedButton.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
        focusedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 10, Color.white));
        
        for(Component component : this.getComponents()){
            if(component instanceof AbstractButton && 
                    component!= focusedButton &&
                    component!= this.btnAdminProfile &&
                    component!= this.btnLogOut){
                component.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
                ((AbstractButton)component).setBorder(BorderFactory.createEmptyBorder());
            }
        }        
    }
    
    private void hoverEffect(AbstractButton hoveredButton){
        hoveredButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hoveredButton.setBackground(Color.decode("#455A64"));
                hoveredButton.setForeground(Color.black);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredButton.setBackground(java.awt.Color.decode("#263238"));
                hoveredButton.setForeground(Color.white);
            }
        });
    }
    
    private void addHoverEffect(){
        for(Component component : this.getComponents()){
            if(component instanceof AbstractButton && component != this.btnLogOut){
                hoverEffect((AbstractButton) component);
            }
        }
    }
    
    private void setSelectedPanelVisible(javax.swing.JPanel selectedPanel){
        List<javax.swing.JPanel> panels = new ArrayList<>();
        panels.add(AdminPage.adminAccountRegisteration);
        panels.add(AdminPage.productData);
        panels.add(AdminPage.productCrud);
        panels.add(AdminPage.cashierData);
        panels.add(AdminPage.cashierCrud);
        panels.add(AdminPage.saleReports);
        
        panels.remove(selectedPanel);
        
        selectedPanel.setVisible(true);
        for(javax.swing.JPanel panel: panels){
            panel.setVisible(false);
        }
    }
    
    private void btnProductDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductDataActionPerformed
        this.modifyFocusedButtonDesign(this.btnProductData);
        this.setSelectedPanelVisible(AdminPage.productData);
    }//GEN-LAST:event_btnProductDataActionPerformed

    private void btnProductCrudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductCrudActionPerformed
        this.modifyFocusedButtonDesign(this.btnProductCrud);
        this.setSelectedPanelVisible(AdminPage.productCrud);
    }//GEN-LAST:event_btnProductCrudActionPerformed

    private void btnSaleReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleReportsActionPerformed
        this.modifyFocusedButtonDesign(this.btnSaleReports);
        this.setSelectedPanelVisible(AdminPage.saleReports);
    }//GEN-LAST:event_btnSaleReportsActionPerformed

    private void btnCashierCrudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashierCrudActionPerformed
        this.modifyFocusedButtonDesign(this.btnCashierCrud);
        this.setSelectedPanelVisible(AdminPage.cashierCrud);
    }//GEN-LAST:event_btnCashierCrudActionPerformed

    private void btnCashierDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCashierDataActionPerformed
        this.modifyFocusedButtonDesign(this.btnCashierData);
        this.setSelectedPanelVisible(AdminPage.cashierData);
    }//GEN-LAST:event_btnCashierDataActionPerformed

    private void btnAdminProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdminProfileActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        AdminPage.disposeAdminPage();
        MainLogin.mainLogin = new MainLogin();
        mainLogin.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnAdminAccountCreationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminAccountCreationActionPerformed
        this.modifyFocusedButtonDesign(this.btnAdminAccountCreation);
        this.setSelectedPanelVisible(AdminPage.adminAccountRegisteration);
    }//GEN-LAST:event_btnAdminAccountCreationActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdminAccountCreation;
    private javax.swing.JButton btnAdminProfile;
    private javax.swing.JButton btnCashierCrud;
    private javax.swing.JButton btnCashierData;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnProductCrud;
    private javax.swing.JButton btnProductData;
    private javax.swing.JButton btnSaleReports;
    // End of variables declaration//GEN-END:variables
}
