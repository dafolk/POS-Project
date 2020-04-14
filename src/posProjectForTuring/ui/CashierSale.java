package posProjectForTuring.ui;

import java.awt.event.KeyEvent;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.CurrentDateTime;
import posProjectForTuring.controller.Publisher;
import posProjectForTuring.controller.Subscriber;
import posProjectForTuring.model.Category;
import posProjectForTuring.model.Product;
import posProjectForTuring.model.Transaction;
import posProjectForTuring.model.Voucher;
import posProjectForTuring.model.service.CategoryService;
import posProjectForTuring.model.service.ProductService;
import posProjectForTuring.model.service.TransactionService;
import posProjectForTuring.model.service.VoucherService;
import posProjectForTuring.model.service.impl.CategoryServiceImpl;
import posProjectForTuring.model.service.impl.ProductServiceImpl;
import posProjectForTuring.model.service.impl.TransactionServiceImpl;
import posProjectForTuring.model.service.impl.VoucherServiceImpl;
import static posProjectForTuring.ui.MainLogin.mainLogin;

public class CashierSale extends javax.swing.JFrame implements Publisher {
    Product product = new Product();
    Transaction transaction = new Transaction();
    Voucher voucher = new Voucher();
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    TransactionService transactionService = new TransactionServiceImpl();
    VoucherService voucherService = new VoucherServiceImpl();
    
    private int totalPrice = 0, price;
    
    public CashierSale() {
        initComponents();
        this.startUp();
    }
    
    private void startUp(){
        this.loadInventoryTable(productService.getAllProducts());
        this.cmbLoader();
        this.productTableActionListener();
        this.cartTableActionListener();
        this.btngp();
        
        setProductEmpty();
        
        if(this.tblCart.getSelectionModel().isSelectionEmpty()){
            this.btnRemoveFromCart.setEnabled(false);
        }
        else{
            this.btnRemoveFromCart.setEnabled(true);
        }
        
        if(tblInventory.getSelectionModel().isSelectionEmpty()){
            this.btnAddToCart.setEnabled(false);
        }
        else{
            this.btnAddToCart.setEnabled(true);
        }
        
        this.btnCashierName.setText(CashierLoginDialog.cashierName);
        this.lblOutOfStock.setVisible(false);
        this.lblExceededStock.setVisible(false);
        
    }

    private void setProductEmpty() {
        this.jrbChoose.setEnabled(false);
        this.jrbCustom.setEnabled(false);
        this.cmbSelectStock.setEnabled(false);
        this.txtSelectStock.setEnabled(false);
        
        this.cmbSelectStock.setSelectedIndex(0);
        this.txtSelectStock.setText("");
        
        this.tblInventory.getSelectionModel().clearSelection();
        
        this.lblSelectedProductName.setText("Select a product!");
        this.lblSelectedProductPrice.setText("Select a product!");
        this.lblInstock.setText("Select a product!");
    }
    
    private void productTableActionListener(){
        this.tblInventory.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(!tblInventory.getSelectionModel().isSelectionEmpty()){
                
                this.checkProductOutOfStock();
                
                product.setId((int)tblInventory.getValueAt(tblInventory.getSelectedRow(), 0));
                
                product = productService.getProductById(product.getId());
                
                this.lblSelectedProductName.setText(this.product.getName());
                this.lblSelectedProductPrice.setText(this.product.getSellingPrice()+" MMK");
                this.lblInstock.setText(""+this.product.getStock());
                
                this.jrbChoose.setEnabled(true);
                this.jrbCustom.setEnabled(true);
                this.jrbChoose.setSelected(true);
                this.cmbSelectStock.setEnabled(true);
                this.cmbSelectStock.setSelectedIndex(0);
                this.txtSelectStock.setEnabled(false);
                
            }
            else{
                this.btnAddToCart.setEnabled(false);
            }
        });
    }
    
    private void checkProductOutOfStock(){
        if(this.product.getStock()<1){
            this.lblOutOfStock.setVisible(true);
            this.pnlChooseAmount.setVisible(false);
            this.btnAddToCart.setEnabled(false);
        }
        else{
            this.lblOutOfStock.setVisible(false);
            this.pnlChooseAmount.setVisible(true);
            this.btnAddToCart.setEnabled(true);
        }
        
    }
    
    private void cartTableActionListener(){
        this.tblCart.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(tblCart.getSelectionModel().isSelectionEmpty()){
                this.btnRemoveFromCart.setEnabled(false);
            }

            else{
                this.btnRemoveFromCart.setEnabled(true);
                price = ((int)(tblCart.getValueAt(tblCart.getSelectedRow(), 3)));
                
            }
        });
    }
    
    private void btngp(){
        this.btngpChooseAmount.add(this.jrbChoose);
        this.btngpChooseAmount.add(this.jrbCustom);
    }
    
    private void cmbLoader(){
        List<Category> categories = this.categoryService.getAllCategories();
        for(Category category: categories){
            this.cmbCategory.addItem(category.getName());
        }
    }
    
    private void loadProductByCategory(){
        List<Product> products = productService.getProductByCategory(
                this.categoryService.getCategoryId(
                        this.cmbCategory.getSelectedItem().toString()
                )
        );
        
        loadInventoryTable(products);
    }
    
    private void loadSearchProducts(){
        List<Product> products = productService.findProductByName(this.txtSearchProduct.getText());   
        
        loadInventoryTable(products);
    }

    private void loadInventoryTable(List<Product> products) {
        DefaultTableModel model = (DefaultTableModel) this.tblInventory.getModel();
        model.setRowCount(0);
        
        for(Product product: products){
            Object row[] = new Object[5];
            row[0] = product.getId();
            row[1] = product.getName();
            row[2] = product.getSellingPrice()+" MMK";
            row[3] = this.categoryService.getCategoryName(product.getCategory());
            row[4] = product.getStock();
            
            model.addRow(row);
        }
    }
    
    private void addToCart(){
        if(!tblInventory.getSelectionModel().isSelectionEmpty()){
            int quantity;
            if(jrbChoose.isSelected()){
                quantity = parseInt(this.cmbSelectStock.getSelectedItem().toString());
            }
            else{
                quantity = parseInt(this.txtSelectStock.getText());
            }
            
            DefaultTableModel model = (DefaultTableModel) this.tblCart.getModel();

            Object row[] = new Object[4];
            row[0] = product.getId();
            row[1] = product.getName();
            row[2] = quantity;
            row[3] = (this.product.getSellingPrice())* quantity;

            model.addRow(row);
            
            totalPrice += (this.product.getSellingPrice())* quantity;
            this.lblTotalPrice.setText(totalPrice+" MMK");
        }
        
        this.setProductEmpty();
        
        this.txtPayAmount.setEnabled(true);
        this.txtPayAmount.setText("");
        
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngpChooseAmount = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventory = new javax.swing.JTable();
        cmbCategory = new javax.swing.JComboBox<>();
        txtSearchProduct = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblSelectedProductName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSelectedProductPrice = new javax.swing.JLabel();
        pnlChooseAmount = new javax.swing.JPanel();
        jrbChoose = new javax.swing.JRadioButton();
        jrbCustom = new javax.swing.JRadioButton();
        txtSelectStock = new javax.swing.JTextField();
        cmbSelectStock = new javax.swing.JComboBox<>();
        lblExceededStock = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblInstock = new javax.swing.JLabel();
        btnAddToCart = new javax.swing.JButton();
        lblOutOfStock = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnRemoveFromCart = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogOut = new javax.swing.JButton();
        btnCashierName = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtPayAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        btnPaymentDone = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        tblInventory.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblInventory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Price", "Category", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventory.setRowHeight(25);
        jScrollPane1.setViewportView(tblInventory);
        if (tblInventory.getColumnModel().getColumnCount() > 0) {
            tblInventory.getColumnModel().getColumn(0).setResizable(false);
            tblInventory.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblInventory.getColumnModel().getColumn(1).setResizable(false);
            tblInventory.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblInventory.getColumnModel().getColumn(2).setResizable(false);
            tblInventory.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblInventory.getColumnModel().getColumn(3).setResizable(false);
            tblInventory.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblInventory.getColumnModel().getColumn(4).setResizable(false);
            tblInventory.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        cmbCategory.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbCategory.setMaximumRowCount(100);
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });

        txtSearchProduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSearchProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchProductKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSearch.setText("Go");
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Search By Product Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Choose Product By Category");

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Product Name:");

        lblSelectedProductName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSelectedProductName.setText("Select a product!");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Price:");

        lblSelectedProductPrice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblSelectedProductPrice.setText("Select a product!");

        pnlChooseAmount.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Choose amount", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 18), new java.awt.Color(1, 1, 1))); // NOI18N

        jrbChoose.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jrbChoose.setText("Choose");
        jrbChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbChooseActionPerformed(evt);
            }
        });

        jrbCustom.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jrbCustom.setText("Custom");
        jrbCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbCustomActionPerformed(evt);
            }
        });

        txtSelectStock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtSelectStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSelectStockKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSelectStockKeyReleased(evt);
            }
        });

        cmbSelectStock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbSelectStock.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "5", "10", "15", "20", "25", "30", "35", "40", "60" }));
        cmbSelectStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelectStockActionPerformed(evt);
            }
        });

        lblExceededStock.setFont(new java.awt.Font("Arial", 1, 17)); // NOI18N
        lblExceededStock.setForeground(new java.awt.Color(153, 0, 0));
        lblExceededStock.setText("Your selected stock exceeds the existing stock!");

        javax.swing.GroupLayout pnlChooseAmountLayout = new javax.swing.GroupLayout(pnlChooseAmount);
        pnlChooseAmount.setLayout(pnlChooseAmountLayout);
        pnlChooseAmountLayout.setHorizontalGroup(
            pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChooseAmountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlChooseAmountLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jrbChoose)
                            .addComponent(cmbSelectStock, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlChooseAmountLayout.createSequentialGroup()
                                .addComponent(jrbCustom)
                                .addGap(99, 99, 99))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChooseAmountLayout.createSequentialGroup()
                                .addComponent(txtSelectStock, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlChooseAmountLayout.createSequentialGroup()
                        .addComponent(lblExceededStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlChooseAmountLayout.setVerticalGroup(
            pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChooseAmountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbChoose)
                    .addComponent(jrbCustom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlChooseAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSelectStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSelectStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblExceededStock)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Instock Amount:");

        lblInstock.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblInstock.setText("Select a product!");

        btnAddToCart.setText("Add to cart");
        btnAddToCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddToCart.setFocusPainted(false);
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        lblOutOfStock.setFont(new java.awt.Font("Arial", 1, 31)); // NOI18N
        lblOutOfStock.setForeground(new java.awt.Color(153, 0, 0));
        lblOutOfStock.setText("This Product is out of stock!");
        lblOutOfStock.setMaximumSize(new java.awt.Dimension(417, 37));
        lblOutOfStock.setMinimumSize(new java.awt.Dimension(417, 37));
        lblOutOfStock.setPreferredSize(new java.awt.Dimension(417, 37));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChooseAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddToCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInstock)
                            .addComponent(lblSelectedProductPrice)
                            .addComponent(lblSelectedProductName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblOutOfStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblSelectedProductName))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblSelectedProductPrice))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblInstock))
                .addGap(102, 102, 102)
                .addComponent(pnlChooseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblOutOfStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        tblCart.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Product Name", "Quantity", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setRowHeight(30);
        jScrollPane2.setViewportView(tblCart);
        if (tblCart.getColumnModel().getColumnCount() > 0) {
            tblCart.getColumnModel().getColumn(0).setResizable(false);
            tblCart.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCart.getColumnModel().getColumn(1).setResizable(false);
            tblCart.getColumnModel().getColumn(1).setPreferredWidth(405);
            tblCart.getColumnModel().getColumn(2).setResizable(false);
            tblCart.getColumnModel().getColumn(3).setResizable(false);
            tblCart.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        btnRemoveFromCart.setText("Remove");
        btnRemoveFromCart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRemoveFromCart.setFocusPainted(false);
        btnRemoveFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFromCartActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel6.setText("Your Cart");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Total:");

        lblTotalPrice.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTotalPrice.setText("0 MMK");

        btnLogOut.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnLogOut.setText("Log Out");
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.setFocusPainted(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        btnCashierName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCashierName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCashierName.setFocusPainted(false);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Pay:");

        txtPayAmount.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPayAmount.setEnabled(false);
        txtPayAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPayAmountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPayAmountKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Change:");

        lblChange.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblChange.setText("0 MMK");

        btnPaymentDone.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnPaymentDone.setText("Payment Done");
        btnPaymentDone.setEnabled(false);
        btnPaymentDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSearch))
                                    .addComponent(jLabel1)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(267, 267, 267)
                                                    .addComponent(jLabel6)
                                                    .addGap(285, 285, 285))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                            .addComponent(jLabel9)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lblChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(btnPaymentDone, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(360, 360, 360)
                                        .addComponent(jLabel8)
                                        .addGap(30, 30, 30)
                                        .addComponent(txtPayAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30)
                                .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCashierName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblTotalPrice)
                                .addComponent(jLabel7))
                            .addComponent(btnRemoveFromCart, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPayAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lblChange))
                        .addGap(36, 36, 36)
                        .addComponent(btnPaymentDone, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        if(this.cmbCategory.getSelectedIndex() == 0){
            this.loadInventoryTable(productService.getAllProducts());
        }
        else{
            this.loadProductByCategory();
        }
    }//GEN-LAST:event_cmbCategoryActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        this.loadSearchProducts();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchProductKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchProductKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.loadSearchProducts();
        }
    }//GEN-LAST:event_txtSearchProductKeyPressed

    private void jrbChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbChooseActionPerformed
        this.cmbSelectStock.setEnabled(true);
        this.txtSelectStock.setEnabled(false);
    }//GEN-LAST:event_jrbChooseActionPerformed

    private void jrbCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbCustomActionPerformed
        this.cmbSelectStock.setEnabled(false);
        this.txtSelectStock.setEnabled(true);
    }//GEN-LAST:event_jrbCustomActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        this.addToCart();
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnRemoveFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFromCartActionPerformed
        if(!tblCart.getSelectionModel().isSelectionEmpty()){
            DefaultTableModel model = (DefaultTableModel) this.tblCart.getModel();
            model.removeRow(this.tblCart.getSelectedRow());
            totalPrice -= price;
            this.lblTotalPrice.setText(totalPrice+" MMK");
            
        }
        
        if(tblCart.getModel().getRowCount()>0){
            this.txtPayAmount.setEnabled(true);
            this.txtPayAmount.setText("");
        }
        else{
            this.txtPayAmount.setEnabled(false);
            this.txtPayAmount.setText("");
        }
    }//GEN-LAST:event_btnRemoveFromCartActionPerformed
    int counter=0;
    private void txtPayAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayAmountKeyPressed
        if (
                Character.isDigit(evt.getKeyChar()) || 
                evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || 
                evt.getKeyCode() == KeyEvent.VK_ENTER
        ) {
            txtPayAmount.setEditable(true);
        }
            
        else if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            txtPayAmount.setEditable(true);
        }
            
        else if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            txtPayAmount.setEditable(true);
        }
        
        else{
            txtPayAmount.setEditable(false);
        }
        
    }//GEN-LAST:event_txtPayAmountKeyPressed

    private void cmbSelectStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelectStockActionPerformed
        int selectedStock = parseInt(this.cmbSelectStock.getSelectedItem().toString());
        if(selectedStock <= product.getStock()){
            this.lblExceededStock.setVisible(false);
            this.btnAddToCart.setEnabled(true);
        }
        else{
            this.lblExceededStock.setVisible(true);
            this.btnAddToCart.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSelectStockActionPerformed

    private void txtSelectStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSelectStockKeyPressed
        if (
                    Character.isDigit(evt.getKeyChar()) || 
                    evt.getKeyCode() == KeyEvent.VK_BACK_SPACE || 
                    evt.getKeyCode() == KeyEvent.VK_ENTER
            ) {
            txtSelectStock.setEditable(true);
        }
         
        else{
            txtSelectStock.setEditable(false);
        }
    }//GEN-LAST:event_txtSelectStockKeyPressed

    private void txtSelectStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSelectStockKeyReleased
        if(!txtSelectStock.getText().isEmpty()){
            int selectedStock = parseInt(this.txtSelectStock.getText());
            if(selectedStock <= product.getStock()){
                this.lblExceededStock.setVisible(false);
                this.btnAddToCart.setEnabled(true);
                
                if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                    this.addToCart();
                }
                
            }
            else{
                this.lblExceededStock.setVisible(true);
                this.btnAddToCart.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtSelectStockKeyReleased

    private int changeAmount = 0;
    private void txtPayAmountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPayAmountKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(parseInt(this.txtPayAmount.getText()) >= this.totalPrice){
                this.changeAmount = parseInt(this.txtPayAmount.getText())- this.totalPrice;
                this.lblChange.setText(changeAmount + " MMK");
                this.txtPayAmount.setEnabled(false);
                this.btnPaymentDone.setEnabled(true);
            }
            else{
                this.txtPayAmount.setEnabled(true);
                this.lblChange.setText("Paid amount is not enough!");
            }
        }
    }//GEN-LAST:event_txtPayAmountKeyReleased

    private void btnPaymentDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentDoneActionPerformed
        insertTransaction();
        insertVoucher();
        reduceStock();
        paymentDone();
        AdminPage.changeUpdateToSubscriber();
    }//GEN-LAST:event_btnPaymentDoneActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        this.dispose();
        MainLogin.mainLogin = new MainLogin();
        mainLogin.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void insertTransaction() {
        this.transaction.setCashierId(CashierLoginDialog.cashierId);
        this.transaction.setDate(CurrentDateTime.get());
        this.transaction.setTotal(this.totalPrice);
        this.transaction.setPayAmount(parseInt(this.txtPayAmount.getText()));
        this.transaction.setChangeAmount(this.changeAmount);
        
        this.transactionService.insertTransaction(transaction);
    }
    
    private void insertVoucher() {
        DefaultTableModel model = (DefaultTableModel) this.tblCart.getModel();
        int transactionId = this.transactionService.getLastTransationId();
        
        for(int i=0; i<model.getRowCount(); i++){
            this.voucher.setTransactionId(transactionId);
            this.voucher.setProductId((int) model.getValueAt(i, 0));
            this.voucher.setQuantity((int) model.getValueAt(i, 2));
            this.voucher.setPrice((int) model.getValueAt(i, 3));
            
            this.voucherService.insertVoucher(voucher);
        }
    }
    
    private void reduceStock(){
        DefaultTableModel model = (DefaultTableModel) this.tblCart.getModel();
        Product purchasedProduct = new Product();
        
        for(int i=0; i<model.getRowCount(); i++){
            purchasedProduct.setId((int) model.getValueAt(i, 0));
            purchasedProduct.setStockPurchased((int) model.getValueAt(i, 2));
            
            this.productService.reduceStock(purchasedProduct);
        }
    }
    
    private void paymentDone(){
        DefaultTableModel model = (DefaultTableModel) this.tblCart.getModel();
        model.setRowCount(0);
        
        this.loadInventoryTable(productService.getAllProducts());
        this.txtPayAmount.setText("");
        this.lblChange.setText("0 MMK");
        this.btnPaymentDone.setEnabled(false);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CashierSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashierSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashierSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashierSale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CashierSale().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnCashierName;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnPaymentDone;
    private javax.swing.JButton btnRemoveFromCart;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup btngpChooseAmount;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSelectStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton jrbChoose;
    private javax.swing.JRadioButton jrbCustom;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblExceededStock;
    private javax.swing.JLabel lblInstock;
    private javax.swing.JLabel lblOutOfStock;
    private javax.swing.JLabel lblSelectedProductName;
    private javax.swing.JLabel lblSelectedProductPrice;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JPanel pnlChooseAmount;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblInventory;
    private javax.swing.JTextField txtPayAmount;
    private javax.swing.JTextField txtSearchProduct;
    private javax.swing.JTextField txtSelectStock;
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
