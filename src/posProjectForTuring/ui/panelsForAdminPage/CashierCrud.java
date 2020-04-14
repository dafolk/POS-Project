package posProjectForTuring.ui.panelsForAdminPage;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.PasswordUtil;
import posProjectForTuring.controller.Publisher;
import posProjectForTuring.controller.Subscriber;
import posProjectForTuring.model.Cashier;
import posProjectForTuring.model.service.CashierService;
import posProjectForTuring.model.service.impl.CashierServiceImpl;
import posProjectForTuring.ui.AdminPage;

public class CashierCrud extends javax.swing.JPanel implements Publisher {
    Cashier cashier = new Cashier();
    CashierService cashierService = new CashierServiceImpl();

    public CashierCrud() {
        initComponents();
        this.startUp();
        
    }
    
    private void startUp(){
        this.loadCashier();
        this.cashierTableActionListener();
        
        this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnNewCashierPanel);
        this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlNewCashier);
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
    
    private void cashierTableActionListener(){
        this.tblCashierData.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(!tblCashierData.getSelectionModel().isSelectionEmpty()){
                cashier.setId((int)tblCashierData.getValueAt(tblCashierData.getSelectedRow(), 0));
                
                Cashier selectedCashier = this.cashierService.getCashierById(cashier.getId());
                txtCashierName.setText(selectedCashier.getName());
                txtUsername.setText(selectedCashier.getUsername());
                txtPhoneNo.setText(selectedCashier.getPhoneNo());
                txtAddress.setText(selectedCashier.getAddress());
                
                lblCashierName.setText(selectedCashier.getName());
                lblUsername.setText(selectedCashier.getUsername());
                lblPhoneNo.setText(selectedCashier.getPhoneNo());
                lblAddress.setText(selectedCashier.getAddress());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashierData = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        cashierCrudPane = new javax.swing.JLayeredPane();
        btnNewCashierPanel = new javax.swing.JButton();
        btnEditCashierPanel = new javax.swing.JButton();
        btnDeleteCashierPanel = new javax.swing.JButton();
        cashierCrudPanelPane = new javax.swing.JLayeredPane();
        pnlNewCashier = new javax.swing.JPanel();
        txtNewCashierName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNewPhoneNo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNewAddress = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNewUsername = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNewConfirmPassword = new javax.swing.JTextField();
        btnCreateNewCashier = new javax.swing.JButton();
        pnlEditCashier = new javax.swing.JPanel();
        txtCashierName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPhoneNo = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnUpdateCashier = new javax.swing.JButton();
        pnlDeleteCashier = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCashierName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPhoneNo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        btnRemoveCashier = new javax.swing.JButton();

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

        btnNewCashierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnNewCashierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnNewCashierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnNewCashierPanel.setText("New Cashier");
        btnNewCashierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewCashierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewCashierPanel.setFocusPainted(false);
        btnNewCashierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCashierPanelActionPerformed(evt);
            }
        });

        btnEditCashierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnEditCashierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnEditCashierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnEditCashierPanel.setText("Edit Cashier");
        btnEditCashierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditCashierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditCashierPanel.setFocusPainted(false);
        btnEditCashierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCashierPanelActionPerformed(evt);
            }
        });

        btnDeleteCashierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnDeleteCashierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnDeleteCashierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnDeleteCashierPanel.setText("Delete Cashier");
        btnDeleteCashierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteCashierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCashierPanel.setFocusPainted(false);
        btnDeleteCashierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCashierPanelActionPerformed(evt);
            }
        });

        jLabel10.setText("Cashier Name");

        jLabel11.setText("Phone Number");

        jLabel12.setText("Address");

        jLabel13.setText("Username");

        jLabel14.setText("Password");

        jLabel15.setText("Confirm Password");

        btnCreateNewCashier.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnCreateNewCashier.setText("Create New Cashier");
        btnCreateNewCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateNewCashierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewCashierLayout = new javax.swing.GroupLayout(pnlNewCashier);
        pnlNewCashier.setLayout(pnlNewCashierLayout);
        pnlNewCashierLayout.setHorizontalGroup(
            pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewCashierLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(txtNewCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewPhoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(217, 217, 217)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateNewCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap(538, Short.MAX_VALUE))
        );
        pnlNewCashierLayout.setVerticalGroup(
            pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewCashierLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewCashierLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewPhoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNewCashierLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlNewCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewCashierLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNewCashierLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnCreateNewCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setText("Cashier Name");

        jLabel2.setText("Username");

        jLabel3.setText("Phone Number");

        jLabel4.setText("Address");

        btnUpdateCashier.setText("Update");
        btnUpdateCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCashierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditCashierLayout = new javax.swing.GroupLayout(pnlEditCashier);
        pnlEditCashier.setLayout(pnlEditCashierLayout);
        pnlEditCashierLayout.setHorizontalGroup(
            pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCashierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(81, 81, 81)
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtPhoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditCashierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateCashier)
                .addGap(698, 698, 698))
        );
        pnlEditCashierLayout.setVerticalGroup(
            pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCashierLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(btnUpdateCashier)
                .addGap(64, 64, 64))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel5.setText("Remove Cashier");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Cashier Name:");

        lblCashierName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCashierName.setText(" ");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Username:");

        lblUsername.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblUsername.setText(" ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Phone Number:");

        lblPhoneNo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblPhoneNo.setText(" ");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Address:");

        lblAddress.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblAddress.setText(" ");

        btnRemoveCashier.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRemoveCashier.setText("Remove");
        btnRemoveCashier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCashierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteCashierLayout = new javax.swing.GroupLayout(pnlDeleteCashier);
        pnlDeleteCashier.setLayout(pnlDeleteCashierLayout);
        pnlDeleteCashierLayout.setHorizontalGroup(
            pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteCashierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDeleteCashierLayout.createSequentialGroup()
                        .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(64, 64, 64)
                        .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddress)
                            .addComponent(lblPhoneNo)
                            .addComponent(lblUsername)
                            .addComponent(lblCashierName)))
                    .addComponent(btnRemoveCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1270, Short.MAX_VALUE))
        );
        pnlDeleteCashierLayout.setVerticalGroup(
            pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteCashierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(41, 41, 41)
                .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCashierName))
                .addGap(18, 18, 18)
                .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblPhoneNo))
                .addGap(18, 18, 18)
                .addGroup(pnlDeleteCashierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(btnRemoveCashier)
                .addContainerGap())
        );

        cashierCrudPanelPane.setLayer(pnlNewCashier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cashierCrudPanelPane.setLayer(pnlEditCashier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cashierCrudPanelPane.setLayer(pnlDeleteCashier, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout cashierCrudPanelPaneLayout = new javax.swing.GroupLayout(cashierCrudPanelPane);
        cashierCrudPanelPane.setLayout(cashierCrudPanelPaneLayout);
        cashierCrudPanelPaneLayout.setHorizontalGroup(
            cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cashierCrudPanelPaneLayout.setVerticalGroup(
            cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(cashierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteCashier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cashierCrudPane.setLayer(btnNewCashierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cashierCrudPane.setLayer(btnEditCashierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cashierCrudPane.setLayer(btnDeleteCashierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        cashierCrudPane.setLayer(cashierCrudPanelPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout cashierCrudPaneLayout = new javax.swing.GroupLayout(cashierCrudPane);
        cashierCrudPane.setLayout(cashierCrudPaneLayout);
        cashierCrudPaneLayout.setHorizontalGroup(
            cashierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierCrudPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(cashierCrudPanelPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        cashierCrudPaneLayout.setVerticalGroup(
            cashierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierCrudPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cashierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteCashierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashierCrudPanelPane))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1)
                    .addComponent(cashierCrudPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cashierCrudPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCashierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCashierPanelActionPerformed
        this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnNewCashierPanel);
        this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlNewCashier);
        this.loadCashier();
        
            AdminPage.changeUpdateToSubscriber();
    }//GEN-LAST:event_btnNewCashierPanelActionPerformed

    private void btnEditCashierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCashierPanelActionPerformed
        if(tblCashierData.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a Cashier to continue.", "No Cashier Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnEditCashierPanel);
            this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlEditCashier);
            
            
            AdminPage.changeUpdateToSubscriber();
        }
    }//GEN-LAST:event_btnEditCashierPanelActionPerformed

    private void btnDeleteCashierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCashierPanelActionPerformed
        if(tblCashierData.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a Cashier to continue.", "No Cashier Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnDeleteCashierPanel);
            this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlDeleteCashier);
            
            AdminPage.changeUpdateToSubscriber();
        }
    }//GEN-LAST:event_btnDeleteCashierPanelActionPerformed

    private void btnUpdateCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCashierActionPerformed
        this.cashier.setName(this.txtCashierName.getText());
        this.cashier.setUsername(this.txtUsername.getText());
        this.cashier.setPhoneNo(this.txtPhoneNo.getText());
        this.cashier.setAddress(this.txtAddress.getText());
        
        this.cashierService.updateCashier(cashier);
        JOptionPane.showMessageDialog(null, "Cashier data editing is updated.", "Cashier Data Edited!", INFORMATION_MESSAGE);
        
        this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnNewCashierPanel);
        this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlNewCashier);
        this.loadCashier();
        
        AdminPage.changeUpdateToSubscriber();
    }//GEN-LAST:event_btnUpdateCashierActionPerformed

    private void btnRemoveCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCashierActionPerformed
        byte choice;
        choice = (byte) JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this cashier?",
                                                                    "Click YES to remove the cashier", YES_NO_OPTION, QUESTION_MESSAGE);
        if(choice==0){
            this.cashierService.deleteCashier(cashier.getId());
            JOptionPane.showMessageDialog(null, "Selected cashier is successfully removed!", "Cashier Removed!", INFORMATION_MESSAGE);
            
            this.loadCashier();
            this.modifyFocusedButtonDesign(this.cashierCrudPane, this.btnNewCashierPanel);
            this.setSelectedCrudPanelVisible(this.cashierCrudPanelPane, this.pnlNewCashier);
            
            AdminPage.changeUpdateToSubscriber();
        }
    }//GEN-LAST:event_btnRemoveCashierActionPerformed

    private void btnCreateNewCashierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateNewCashierActionPerformed
        
        
        if(isPerfect() && isPasswordConfirm()){
            this.cashier.setName(this.txtNewCashierName.getText());
            this.cashier.setUsername(this.txtNewUsername.getText());
            this.cashier.setPassword(PasswordUtil.hashPassword(this.txtNewPassword.getText()));
            this.cashier.setPhoneNo(this.txtNewPhoneNo.getText());
            this.cashier.setAddress(this.txtNewAddress.getText());
            
            cashierService.insertCashier(cashier);

            JOptionPane.showMessageDialog(null, "Account successfully created!", "Sign Up Done!", INFORMATION_MESSAGE);
            
            this.txtNewCashierName.setText("");
            this.txtNewUsername.setText("");
            this.txtNewPassword.setText("");
            this.txtNewConfirmPassword.setText("");
            this.txtNewPhoneNo.setText("");
            this.txtNewAddress.setText("");
            this.loadCashier();
            
            AdminPage.changeUpdateToSubscriber();
        }
        else{
            if(!isPerfect()){
                JOptionPane.showMessageDialog(null, "All fields must be filled!", "Fields cannot be empty!", WARNING_MESSAGE);
            }
            
            else{
                JOptionPane.showMessageDialog(null, "Passwords must be matched!", "Passwords Do Not Match!", WARNING_MESSAGE);
                this.txtNewPassword.setText("");
                this.txtNewConfirmPassword.setText("");
            }
        }
    }//GEN-LAST:event_btnCreateNewCashierActionPerformed

    private boolean isPerfect(){
        if(this.txtNewCashierName.getText().isEmpty()){
            return false;
        }
        else if(this.txtNewUsername.getText().isEmpty()){
            return false;
        }
        else if(this.txtNewPassword.getText().isEmpty()){
            return false;
        }
        else if(this.txtNewPassword.getText().isEmpty()){
            return false;
        }
        else if(this.txtNewPhoneNo.getText().isEmpty()){
            return false;
        }
        else if(this.txtNewAddress.getText().isEmpty()){
            return false;
        }
        else{
           return true; 
        }
    }
    
    private boolean isPasswordConfirm(){
        if(this.txtNewPassword.getText().equals(this.txtNewConfirmPassword.getText())){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void modifyFocusedButtonDesign(javax.swing.JLayeredPane jLayeredPane, AbstractButton focusedButton){
        focusedButton.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
        focusedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.white));
        
        for(Component component : jLayeredPane.getComponents()){
            if(component instanceof AbstractButton && component!= focusedButton){
                component.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
                ((AbstractButton)component).setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
    
    private void setSelectedCrudPanelVisible(javax.swing.JLayeredPane jLayeredPane, javax.swing.JPanel selectedPanel){
        selectedPanel.setVisible(true);
        for(Component component : jLayeredPane.getComponents()){
            if(component instanceof javax.swing.JPanel && component!= selectedPanel){
                component.setVisible(false);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateNewCashier;
    private javax.swing.JButton btnDeleteCashierPanel;
    private javax.swing.JButton btnEditCashierPanel;
    private javax.swing.JButton btnNewCashierPanel;
    private javax.swing.JButton btnRemoveCashier;
    private javax.swing.JButton btnUpdateCashier;
    private javax.swing.JLayeredPane cashierCrudPane;
    private javax.swing.JLayeredPane cashierCrudPanelPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCashierName;
    private javax.swing.JLabel lblPhoneNo;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlDeleteCashier;
    private javax.swing.JPanel pnlEditCashier;
    private javax.swing.JPanel pnlNewCashier;
    private javax.swing.JTable tblCashierData;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCashierName;
    private javax.swing.JTextField txtNewAddress;
    private javax.swing.JTextField txtNewCashierName;
    private javax.swing.JTextField txtNewConfirmPassword;
    private javax.swing.JTextField txtNewPassword;
    private javax.swing.JTextField txtNewPhoneNo;
    private javax.swing.JTextField txtNewUsername;
    private javax.swing.JTextField txtPhoneNo;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    List<Subscriber> subscribers = new ArrayList<>();
    
    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void change() {
        for(Subscriber subscriber: subscribers){
            subscriber.update();
        }
    }
}
