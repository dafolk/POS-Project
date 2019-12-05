package posProjectForTuring.ui.panelsForAdminPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import posProjectForTuring.controller.CurrentDateTime;
import posProjectForTuring.model.Category;
import posProjectForTuring.model.Product;
import posProjectForTuring.model.Supplier;
import posProjectForTuring.model.service.CategoryService;
import posProjectForTuring.model.service.ProductService;
import posProjectForTuring.model.service.SupplierService;
import posProjectForTuring.model.service.impl.CategoryServiceImpl;
import posProjectForTuring.model.service.impl.ProductServiceImpl;
import posProjectForTuring.model.service.impl.SupplierServiceImpl; 

public class ProductCrud extends javax.swing.JPanel {    
    Product product = new Product();
    Category category = new Category();
    Supplier supplier = new Supplier();
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    SupplierService supplierService = new SupplierServiceImpl();
    
    public ProductCrud() {
        initComponents();
        startUp();
        
    }
    
    private void startUp() {
        this.loader();
        this.productTableActionListener();
        this.categoryTableActionListener();
        this.supplierTableActionListener();
        
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnNewProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlNewProduct);
        
        this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnNewCategoryPanel);
        this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlNewCategory);
        
        this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnNewSupplierPanel);
        this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlNewSupplier);
        
    }
    
    private void productTableActionListener(){
        
        this.tblProductData.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(!tblProductData.getSelectionModel().isSelectionEmpty()){
                product.setId((int)tblProductData.getValueAt(tblProductData.getSelectedRow(), 0));
                
                Product selectedProduct = productService.getProductById(product.getId());
                
                txtProductName.setText(selectedProduct.getName());
                txtUnitPrice.setText(""+selectedProduct.getUnitPrice());
                txtSellingPrice.setText(""+selectedProduct.getSellingPrice());
                cmbCategory.setSelectedItem(categoryService.getCategoryName(selectedProduct.getCategory()));
                cmbSupplier.setSelectedItem(supplierService.getSupplierName(selectedProduct.getSupplier()));
                
                lblProductName.setText(selectedProduct.getName());
                lblUnitPrice.setText(""+selectedProduct.getUnitPrice());
                lblSellingPrice.setText(""+selectedProduct.getSellingPrice());
                lblCategory.setText(categoryService.getCategoryName(selectedProduct.getCategory()));
                lblSupplier.setText(supplierService.getSupplierName(selectedProduct.getSupplier()));
                
                productName.setText(selectedProduct.getName());
                instock.setText(""+selectedProduct.getStock());
            }
        });
        
    }
    private void categoryTableActionListener(){
        this.tblCategory.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(!tblCategory.getSelectionModel().isSelectionEmpty()){
                category.setId((int)tblCategory.getValueAt(tblCategory.getSelectedRow(), 0));
                txtCategoryName.setText(categoryService.getCategoryName(category.getId()));
                
                lblCategoryName.setText(categoryService.getCategoryName(category.getId()));
            }
        });
    }
    private void supplierTableActionListener(){
        this.tblSupplier.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if(!tblSupplier.getSelectionModel().isSelectionEmpty()){
                supplier.setId((int)tblSupplier.getValueAt(tblSupplier.getSelectedRow(), 0));
                
                Supplier selectedSupplier = supplierService.getSupplierById(supplier.getId());
                
                txtSupplierName.setText(selectedSupplier.getName());
                txtAddress.setText(selectedSupplier.getAddress());
                txtContactNo.setText(selectedSupplier.getContactNo());
                
                lblSupplierName.setText(selectedSupplier.getName());
                lblAddress.setText(selectedSupplier.getAddress());
                lblContactNo.setText(selectedSupplier.getContactNo());
            }
        });
    }
    
    private void loader(){
        this.loadProducts();
        this.loadCategories();
        this.loadSuppliers();
        this.cmbLoader();
    }
    
    private void cmbLoader(){
        List<Category> categories = this.categoryService.getAllCategories();
        this.cmbNewCategory.removeAllItems();
        for(Category category: categories){
            this.cmbNewCategory.addItem(category.getName());
            this.cmbCategory.addItem(category.getName());
        }
        
        List<Supplier> suppliers = this.supplierService.getAllSuppliers();
        this.cmbNewSupplier.removeAllItems();
        for(Supplier supplier: suppliers){
            this.cmbNewSupplier.addItem(supplier.getName());
            this.cmbSupplier.addItem(supplier.getName());
        }
    }
    
    private void loadProducts(){
        List<Product> products = this.productService.getAllProducts();
        DefaultTableModel model = (DefaultTableModel) this.tblProductData.getModel();
        model.setRowCount(0);
        
        for(Product product: products){
            Object row[] = new Object[8];
            row[0] = product.getId();
            row[1] = product.getName();
            row[2] = product.getUnitPrice()+" MMK";
            row[3] = product.getSellingPrice()+" MMK";
            row[4] = this.categoryService.getCategoryName(product.getCategory());
            row[5] = this.supplierService.getSupplierName(product.getSupplier());
            row[6] = product.getStock();
            row[7] = product.getLastUpdated();
            
            model.addRow(row);
        }
    }
    
    private void loadCategories(){
        List<Category> categories = this.categoryService.getAllCategories();
        DefaultTableModel model = (DefaultTableModel) this.tblCategory.getModel();
        model.setRowCount(0);
        
        for(Category category: categories){
            Object row[] = new Object[2];
            row[0] = category.getId();
            row[1] = category.getName();
            
            model.addRow(row);
        }
    }
    
    private void loadSuppliers(){
        List<Supplier> suppliers = this.supplierService.getAllSuppliers();
        DefaultTableModel model = (DefaultTableModel) this.tblSupplier.getModel();
        model.setRowCount(0);
        
        for(Supplier supplier: suppliers){
            Object row[] = new Object[4];
            row[0] = supplier.getId();
            row[1] = supplier.getName();
            row[2] = supplier.getAddress();
            row[3] = supplier.getContactNo();
            
            model.addRow(row);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        adminAccountRegistration1 = new posProjectForTuring.ui.AdminAccountRegistration();
        jtbProductData = new javax.swing.JTabbedPane();
        pnlProductDetails = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductData = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        productCrudPane = new javax.swing.JLayeredPane();
        btnNewProductPanel = new javax.swing.JButton();
        btnEditProductPanel = new javax.swing.JButton();
        btnDeleteProductPanel = new javax.swing.JButton();
        btnRestockProductPanel = new javax.swing.JButton();
        productCrudPanelPane = new javax.swing.JLayeredPane();
        pnlNewProduct = new javax.swing.JPanel();
        txtNewProductName = new javax.swing.JTextField();
        txtNewUnitPrice = new javax.swing.JTextField();
        txtNewSellingPrice = new javax.swing.JTextField();
        cmbNewCategory = new javax.swing.JComboBox<>();
        cmbNewSupplier = new javax.swing.JComboBox<>();
        txtNewStock = new javax.swing.JTextField();
        btnAddNewProduct = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlEditProduct = new javax.swing.JPanel();
        txtProductName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        txtSellingPrice = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox<>();
        cmbSupplier = new javax.swing.JComboBox<>();
        btnUpdateProduct = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pnlDeleteProduct = new javax.swing.JPanel();
        lblSellingPrice = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblProductName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblUnitPrice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCategory = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSupplier = new javax.swing.JLabel();
        btnRemoveProduct = new javax.swing.JButton();
        pnlRestockProduct = new javax.swing.JPanel();
        productName = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        instock = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnPlusOne = new javax.swing.JButton();
        btnPlusTen = new javax.swing.JButton();
        btnPlusHundred = new javax.swing.JButton();
        btnRestock = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        pnlCategory = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        categoryCrudPane = new javax.swing.JLayeredPane();
        btnNewCategoryPanel = new javax.swing.JButton();
        btnEditCategoryPanel = new javax.swing.JButton();
        btnDeleteCategoryPanel = new javax.swing.JButton();
        categoryCrudPanelPane = new javax.swing.JLayeredPane();
        pnlNewCategory = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtNewCategoryName = new javax.swing.JTextField();
        btnAddNewCategory = new javax.swing.JButton();
        pnlEditCategory = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtCategoryName = new javax.swing.JTextField();
        btnUpdateCategory = new javax.swing.JButton();
        pnlDeleteCategory = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lblCategoryName = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        btnRemoveCategory = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        pnlSupplierDetails = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        supplierCrudPane = new javax.swing.JLayeredPane();
        btnNewSupplierPanel = new javax.swing.JButton();
        btnEditSupplierPanel = new javax.swing.JButton();
        btnDeleteSupplierPanel = new javax.swing.JButton();
        supplierCrudPanelPane = new javax.swing.JLayeredPane();
        pnlNewSupplier = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtNewSupplierName = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNewContactNo = new javax.swing.JTextField();
        txtNewAddress = new javax.swing.JTextField();
        btnAddNewSupplier = new javax.swing.JButton();
        pnlEditSupplier = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtContactNo = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnUpdateSuppiler = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        txtSupplierName = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        pnlDeleteSupplier = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lblSupplierName = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lblContactNo = new javax.swing.JLabel();
        btnRemoveSupplier = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();

        setBackground(java.awt.Color.decode("#263238"));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, new java.awt.Color(255, 255, 255)));

        jtbProductData.setBackground(java.awt.Color.decode("#263238"));
        jtbProductData.setForeground(new java.awt.Color(240, 240, 240));

        pnlProductDetails.setBackground(java.awt.Color.decode("#263238"));

        tblProductData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Unit Price", "Selling Price", "Category", "Supplier", "Stock", "Last Updated"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProductData);

        btnNewProductPanel.setBackground(java.awt.Color.decode("#263238"));
        btnNewProductPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnNewProductPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnNewProductPanel.setText("New Product");
        btnNewProductPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewProductPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewProductPanel.setFocusPainted(false);
        btnNewProductPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewProductPanelActionPerformed(evt);
            }
        });

        btnEditProductPanel.setBackground(java.awt.Color.decode("#263238"));
        btnEditProductPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnEditProductPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnEditProductPanel.setText("Edit Product");
        btnEditProductPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditProductPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditProductPanel.setFocusPainted(false);
        btnEditProductPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductPanelActionPerformed(evt);
            }
        });

        btnDeleteProductPanel.setBackground(java.awt.Color.decode("#263238"));
        btnDeleteProductPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnDeleteProductPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnDeleteProductPanel.setText("Delete Product");
        btnDeleteProductPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteProductPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteProductPanel.setFocusPainted(false);
        btnDeleteProductPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductPanelActionPerformed(evt);
            }
        });

        btnRestockProductPanel.setBackground(java.awt.Color.decode("#263238"));
        btnRestockProductPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnRestockProductPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnRestockProductPanel.setText("Restock Product");
        btnRestockProductPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnRestockProductPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRestockProductPanel.setFocusPainted(false);
        btnRestockProductPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestockProductPanelActionPerformed(evt);
            }
        });

        txtNewProductName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        txtNewUnitPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        txtNewSellingPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        cmbNewCategory.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        cmbNewSupplier.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        txtNewStock.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        btnAddNewProduct.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddNewProduct.setText("Add New Product");
        btnAddNewProduct.setFocusPainted(false);
        btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewProductActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Product Name");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel8.setText("Unit Price");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel9.setText("Selling Price");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel10.setText("Category");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel11.setText("Supplier");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel12.setText("Stock");

        javax.swing.GroupLayout pnlNewProductLayout = new javax.swing.GroupLayout(pnlNewProduct);
        pnlNewProduct.setLayout(pnlNewProductLayout);
        pnlNewProductLayout.setHorizontalGroup(
            pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewProductLayout.createSequentialGroup()
                .addContainerGap(790, Short.MAX_VALUE)
                .addComponent(btnAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(645, 645, 645))
            .addGroup(pnlNewProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(131, 131, 131)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNewSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(126, 126, 126)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbNewCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(120, 120, 120)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbNewSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewProductLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(txtNewStock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNewProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );
        pnlNewProductLayout.setVerticalGroup(
            pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewProductLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlNewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNewCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNewSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNewStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnAddNewProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        txtProductName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        txtUnitPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        txtSellingPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        cmbCategory.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        cmbSupplier.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        btnUpdateProduct.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnUpdateProduct.setText("Update");
        btnUpdateProduct.setFocusPainted(false);
        btnUpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel13.setText("Product Name");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel14.setText("Unit Price");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel15.setText("Selling Price");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel16.setText("Category");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel17.setText("Supplier");

        javax.swing.GroupLayout pnlEditProductLayout = new javax.swing.GroupLayout(pnlEditProduct);
        pnlEditProduct.setLayout(pnlEditProductLayout);
        pnlEditProductLayout.setHorizontalGroup(
            pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditProductLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(646, 646, 646))
            .addGroup(pnlEditProductLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(131, 131, 131)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(126, 126, 126)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(120, 120, 120)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(124, 124, 124))
        );
        pnlEditProductLayout.setVerticalGroup(
            pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditProductLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(btnUpdateProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        lblSellingPrice.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSellingPrice.setText(" ");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Selling Price:");

        lblProductName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblProductName.setText(" ");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Product name:");

        lblUnitPrice.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblUnitPrice.setText(" ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Unit Price:");

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        jLabel1.setText("Remove Product");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Category:");

        lblCategory.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCategory.setText(" ");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Supplier:");

        lblSupplier.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSupplier.setText(" ");

        btnRemoveProduct.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRemoveProduct.setText("Remove");
        btnRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteProductLayout = new javax.swing.GroupLayout(pnlDeleteProduct);
        pnlDeleteProduct.setLayout(pnlDeleteProductLayout);
        pnlDeleteProductLayout.setHorizontalGroup(
            pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteProductLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDeleteProductLayout.createSequentialGroup()
                        .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSupplier)
                            .addComponent(lblCategory)
                            .addComponent(lblSellingPrice)
                            .addComponent(lblUnitPrice)
                            .addComponent(lblProductName)))
                    .addComponent(btnRemoveProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1180, Short.MAX_VALUE))
        );
        pnlDeleteProductLayout.setVerticalGroup(
            pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblProductName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblUnitPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblSellingPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCategory))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDeleteProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnRemoveProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        productName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        productName.setText(" ");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel18.setText("Restock Product");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("Selected Product:");

        txtStock.setEditable(false);
        txtStock.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Stock to be added:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Instock Count:");

        instock.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        instock.setText(" ");

        btnPlusOne.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPlusOne.setText("+1");
        btnPlusOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusOneActionPerformed(evt);
            }
        });

        btnPlusTen.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPlusTen.setText("+10");
        btnPlusTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusTenActionPerformed(evt);
            }
        });

        btnPlusHundred.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPlusHundred.setText("+100");
        btnPlusHundred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusHundredActionPerformed(evt);
            }
        });

        btnRestock.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRestock.setText("Restock");
        btnRestock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestockActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRestockProductLayout = new javax.swing.GroupLayout(pnlRestockProduct);
        pnlRestockProduct.setLayout(pnlRestockProductLayout);
        pnlRestockProductLayout.setHorizontalGroup(
            pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestockProductLayout.createSequentialGroup()
                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRestockProductLayout.createSequentialGroup()
                        .addGap(628, 628, 628)
                        .addComponent(jLabel18))
                    .addGroup(pnlRestockProductLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRestockProductLayout.createSequentialGroup()
                                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(productName)
                                    .addGroup(pnlRestockProductLayout.createSequentialGroup()
                                        .addComponent(instock)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlRestockProductLayout.createSequentialGroup()
                                                .addGap(127, 127, 127)
                                                .addComponent(btnPlusOne)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnPlusTen)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnPlusHundred))
                                            .addGroup(pnlRestockProductLayout.createSequentialGroup()
                                                .addComponent(jLabel20)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnClear)))
                                        .addGap(92, 92, 92))))))
                    .addGroup(pnlRestockProductLayout.createSequentialGroup()
                        .addGap(669, 669, 669)
                        .addComponent(btnRestock)))
                .addContainerGap(211, Short.MAX_VALUE))
        );
        pnlRestockProductLayout.setVerticalGroup(
            pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRestockProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productName)
                    .addComponent(jLabel19))
                .addGap(18, 18, 18)
                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(instock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlusOne)
                    .addComponent(btnPlusTen)
                    .addComponent(btnPlusHundred))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnRestock)
                .addGap(65, 65, 65))
        );

        productCrudPanelPane.setLayer(pnlNewProduct, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPanelPane.setLayer(pnlEditProduct, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPanelPane.setLayer(pnlDeleteProduct, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPanelPane.setLayer(pnlRestockProduct, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout productCrudPanelPaneLayout = new javax.swing.GroupLayout(productCrudPanelPane);
        productCrudPanelPane.setLayout(productCrudPanelPaneLayout);
        productCrudPanelPaneLayout.setHorizontalGroup(
            productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditProduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRestockProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        productCrudPanelPaneLayout.setVerticalGroup(
            productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(productCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRestockProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        productCrudPane.setLayer(btnNewProductPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPane.setLayer(btnEditProductPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPane.setLayer(btnDeleteProductPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPane.setLayer(btnRestockProductPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        productCrudPane.setLayer(productCrudPanelPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout productCrudPaneLayout = new javax.swing.GroupLayout(productCrudPane);
        productCrudPane.setLayout(productCrudPaneLayout);
        productCrudPaneLayout.setHorizontalGroup(
            productCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productCrudPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNewProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRestockProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(919, Short.MAX_VALUE))
            .addComponent(productCrudPanelPane)
        );
        productCrudPaneLayout.setVerticalGroup(
            productCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productCrudPaneLayout.createSequentialGroup()
                .addGroup(productCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestockProductPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productCrudPanelPane))
        );

        javax.swing.GroupLayout pnlProductDetailsLayout = new javax.swing.GroupLayout(pnlProductDetails);
        pnlProductDetails.setLayout(pnlProductDetailsLayout);
        pnlProductDetailsLayout.setHorizontalGroup(
            pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1585, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(productCrudPane))
                .addContainerGap())
        );
        pnlProductDetailsLayout.setVerticalGroup(
            pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productCrudPane)
                .addContainerGap())
        );

        jtbProductData.addTab("Product Details", pnlProductDetails);

        pnlCategory.setBackground(java.awt.Color.decode("#263238"));

        tblCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCategory);

        btnNewCategoryPanel.setBackground(java.awt.Color.decode("#263238"));
        btnNewCategoryPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnNewCategoryPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnNewCategoryPanel.setText("New Category");
        btnNewCategoryPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewCategoryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewCategoryPanel.setFocusPainted(false);
        btnNewCategoryPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCategoryPanelActionPerformed(evt);
            }
        });

        btnEditCategoryPanel.setBackground(java.awt.Color.decode("#263238"));
        btnEditCategoryPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnEditCategoryPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnEditCategoryPanel.setText("Edit Category");
        btnEditCategoryPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditCategoryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditCategoryPanel.setFocusPainted(false);
        btnEditCategoryPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditCategoryPanelActionPerformed(evt);
            }
        });

        btnDeleteCategoryPanel.setBackground(java.awt.Color.decode("#263238"));
        btnDeleteCategoryPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnDeleteCategoryPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnDeleteCategoryPanel.setText("Delete Category");
        btnDeleteCategoryPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteCategoryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteCategoryPanel.setFocusPainted(false);
        btnDeleteCategoryPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCategoryPanelActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel22.setText("Category Name:");

        txtNewCategoryName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btnAddNewCategory.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnAddNewCategory.setText("Add New Category");
        btnAddNewCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewCategoryLayout = new javax.swing.GroupLayout(pnlNewCategory);
        pnlNewCategory.setLayout(pnlNewCategoryLayout);
        pnlNewCategoryLayout.setHorizontalGroup(
            pnlNewCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewCategoryLayout.createSequentialGroup()
                .addGap(498, 498, 498)
                .addComponent(jLabel22)
                .addGap(50, 50, 50)
                .addGroup(pnlNewCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddNewCategory)
                    .addComponent(txtNewCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(607, Short.MAX_VALUE))
        );
        pnlNewCategoryLayout.setVerticalGroup(
            pnlNewCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewCategoryLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(pnlNewCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNewCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnAddNewCategory)
                .addGap(66, 66, 66))
        );

        jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel24.setText("Category Name:");

        txtCategoryName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        btnUpdateCategory.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnUpdateCategory.setText("Update");
        btnUpdateCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlEditCategoryLayout = new javax.swing.GroupLayout(pnlEditCategory);
        pnlEditCategory.setLayout(pnlEditCategoryLayout);
        pnlEditCategoryLayout.setHorizontalGroup(
            pnlEditCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCategoryLayout.createSequentialGroup()
                .addGap(539, 539, 539)
                .addComponent(jLabel24)
                .addGap(50, 50, 50)
                .addGroup(pnlEditCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(566, Short.MAX_VALUE))
        );
        pnlEditCategoryLayout.setVerticalGroup(
            pnlEditCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditCategoryLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(pnlEditCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(btnUpdateCategory)
                .addGap(73, 73, 73))
        );

        jLabel23.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel23.setText("Category Name:");

        lblCategoryName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblCategoryName.setText(" ");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel26.setText("Remove Category");

        btnRemoveCategory.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRemoveCategory.setText("Remove");
        btnRemoveCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveCategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteCategoryLayout = new javax.swing.GroupLayout(pnlDeleteCategory);
        pnlDeleteCategory.setLayout(pnlDeleteCategoryLayout);
        pnlDeleteCategoryLayout.setHorizontalGroup(
            pnlDeleteCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDeleteCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDeleteCategoryLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(47, 47, 47)
                        .addComponent(lblCategoryName))
                    .addComponent(btnRemoveCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1382, Short.MAX_VALUE))
        );
        pnlDeleteCategoryLayout.setVerticalGroup(
            pnlDeleteCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(72, 72, 72)
                .addGroup(pnlDeleteCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lblCategoryName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(btnRemoveCategory)
                .addContainerGap())
        );

        categoryCrudPanelPane.setLayer(pnlNewCategory, javax.swing.JLayeredPane.DEFAULT_LAYER);
        categoryCrudPanelPane.setLayer(pnlEditCategory, javax.swing.JLayeredPane.DEFAULT_LAYER);
        categoryCrudPanelPane.setLayer(pnlDeleteCategory, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout categoryCrudPanelPaneLayout = new javax.swing.GroupLayout(categoryCrudPanelPane);
        categoryCrudPanelPane.setLayout(categoryCrudPanelPaneLayout);
        categoryCrudPanelPaneLayout.setHorizontalGroup(
            categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        categoryCrudPanelPaneLayout.setVerticalGroup(
            categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryCrudPanelPaneLayout.createSequentialGroup()
                .addComponent(pnlNewCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryCrudPanelPaneLayout.createSequentialGroup()
                    .addComponent(pnlEditCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(categoryCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(categoryCrudPanelPaneLayout.createSequentialGroup()
                    .addComponent(pnlDeleteCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        categoryCrudPane.setLayer(btnNewCategoryPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        categoryCrudPane.setLayer(btnEditCategoryPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        categoryCrudPane.setLayer(btnDeleteCategoryPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        categoryCrudPane.setLayer(categoryCrudPanelPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout categoryCrudPaneLayout = new javax.swing.GroupLayout(categoryCrudPane);
        categoryCrudPane.setLayout(categoryCrudPaneLayout);
        categoryCrudPaneLayout.setHorizontalGroup(
            categoryCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryCrudPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryCrudPanelPane)
                .addContainerGap())
            .addGroup(categoryCrudPaneLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnNewCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        categoryCrudPaneLayout.setVerticalGroup(
            categoryCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryCrudPaneLayout.createSequentialGroup()
                .addGroup(categoryCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteCategoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryCrudPanelPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCategoryLayout = new javax.swing.GroupLayout(pnlCategory);
        pnlCategory.setLayout(pnlCategoryLayout);
        pnlCategoryLayout.setHorizontalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categoryCrudPane)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        pnlCategoryLayout.setVerticalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryCrudPane, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtbProductData.addTab("Category", pnlCategory);

        pnlSupplierDetails.setBackground(java.awt.Color.decode("#263238"));

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Id", "Supplier Name", "Address", "Contact Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblSupplier);

        btnNewSupplierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnNewSupplierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnNewSupplierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnNewSupplierPanel.setText("New Supplier");
        btnNewSupplierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnNewSupplierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewSupplierPanel.setFocusPainted(false);
        btnNewSupplierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewSupplierPanelActionPerformed(evt);
            }
        });

        btnEditSupplierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnEditSupplierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnEditSupplierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnEditSupplierPanel.setText("Edit Supplier");
        btnEditSupplierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEditSupplierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditSupplierPanel.setFocusPainted(false);
        btnEditSupplierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditSupplierPanelActionPerformed(evt);
            }
        });

        btnDeleteSupplierPanel.setBackground(java.awt.Color.decode("#263238"));
        btnDeleteSupplierPanel.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        btnDeleteSupplierPanel.setForeground(new java.awt.Color(240, 240, 240));
        btnDeleteSupplierPanel.setText("Delete Supplier");
        btnDeleteSupplierPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteSupplierPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteSupplierPanel.setFocusPainted(false);
        btnDeleteSupplierPanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSupplierPanelActionPerformed(evt);
            }
        });

        jLabel25.setText("Supplier Name");

        jLabel27.setText("Address");

        jLabel28.setText("Contact Number");

        btnAddNewSupplier.setText("Add New Supplier");
        btnAddNewSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlNewSupplierLayout = new javax.swing.GroupLayout(pnlNewSupplier);
        pnlNewSupplier.setLayout(pnlNewSupplierLayout);
        pnlNewSupplierLayout.setHorizontalGroup(
            pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(txtNewSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(153, 153, 153)
                        .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27)
                            .addComponent(txtNewAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(160, 160, 160)
                        .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNewContactNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                        .addGap(735, 735, 735)
                        .addComponent(btnAddNewSupplier)))
                .addContainerGap(239, Short.MAX_VALUE))
        );
        pnlNewSupplierLayout.setVerticalGroup(
            pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNewSupplierLayout.createSequentialGroup()
                        .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNewSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNewSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNewAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(btnAddNewSupplier)
                .addGap(51, 51, 51))
        );

        jLabel29.setText("Contact Number");

        btnUpdateSuppiler.setText("Update");
        btnUpdateSuppiler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSuppilerActionPerformed(evt);
            }
        });

        jLabel30.setText("Supplier Name");

        jLabel31.setText("Address");

        javax.swing.GroupLayout pnlEditSupplierLayout = new javax.swing.GroupLayout(pnlEditSupplier);
        pnlEditSupplier.setLayout(pnlEditSupplierLayout);
        pnlEditSupplierLayout.setHorizontalGroup(
            pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditSupplierLayout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel30)
                    .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153)
                .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(160, 160, 160)
                .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtContactNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(215, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditSupplierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUpdateSuppiler)
                .addGap(735, 735, 735))
        );
        pnlEditSupplierLayout.setVerticalGroup(
            pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditSupplierLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditSupplierLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEditSupplierLayout.createSequentialGroup()
                        .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEditSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSupplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(btnUpdateSuppiler)
                .addGap(51, 51, 51))
        );

        jLabel32.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel32.setText("Remove Supplier");

        jLabel33.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel33.setText("Supplier Name:");

        lblSupplierName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblSupplierName.setText(" ");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel34.setText("Address:");

        lblAddress.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblAddress.setText(" ");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel35.setText("Contact Number:");

        lblContactNo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblContactNo.setText(" ");

        btnRemoveSupplier.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnRemoveSupplier.setText("Remove");
        btnRemoveSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDeleteSupplierLayout = new javax.swing.GroupLayout(pnlDeleteSupplier);
        pnlDeleteSupplier.setLayout(pnlDeleteSupplierLayout);
        pnlDeleteSupplierLayout.setHorizontalGroup(
            pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDeleteSupplierLayout.createSequentialGroup()
                        .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(51, 51, 51)
                        .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblContactNo)
                            .addComponent(lblAddress)
                            .addComponent(lblSupplierName)))
                    .addComponent(btnRemoveSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1382, Short.MAX_VALUE))
        );
        pnlDeleteSupplierLayout.setVerticalGroup(
            pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDeleteSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addGap(51, 51, 51)
                .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblSupplierName))
                .addGap(18, 18, 18)
                .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(lblAddress))
                .addGap(18, 18, 18)
                .addGroup(pnlDeleteSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(lblContactNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnRemoveSupplier)
                .addContainerGap())
        );

        supplierCrudPanelPane.setLayer(pnlNewSupplier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        supplierCrudPanelPane.setLayer(pnlEditSupplier, javax.swing.JLayeredPane.DEFAULT_LAYER);
        supplierCrudPanelPane.setLayer(pnlDeleteSupplier, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout supplierCrudPanelPaneLayout = new javax.swing.GroupLayout(supplierCrudPanelPane);
        supplierCrudPanelPane.setLayout(supplierCrudPanelPaneLayout);
        supplierCrudPanelPaneLayout.setHorizontalGroup(
            supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNewSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlEditSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlDeleteSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supplierCrudPanelPaneLayout.setVerticalGroup(
            supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierCrudPanelPaneLayout.createSequentialGroup()
                .addComponent(pnlNewSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplierCrudPanelPaneLayout.createSequentialGroup()
                    .addComponent(pnlEditSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(supplierCrudPanelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(supplierCrudPanelPaneLayout.createSequentialGroup()
                    .addComponent(pnlDeleteSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        supplierCrudPane.setLayer(btnNewSupplierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        supplierCrudPane.setLayer(btnEditSupplierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        supplierCrudPane.setLayer(btnDeleteSupplierPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        supplierCrudPane.setLayer(supplierCrudPanelPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout supplierCrudPaneLayout = new javax.swing.GroupLayout(supplierCrudPane);
        supplierCrudPane.setLayout(supplierCrudPaneLayout);
        supplierCrudPaneLayout.setHorizontalGroup(
            supplierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierCrudPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplierCrudPanelPane)
                .addContainerGap())
            .addGroup(supplierCrudPaneLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnNewSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDeleteSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supplierCrudPaneLayout.setVerticalGroup(
            supplierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierCrudPaneLayout.createSequentialGroup()
                .addGroup(supplierCrudPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNewSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteSupplierPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplierCrudPanelPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSupplierDetailsLayout = new javax.swing.GroupLayout(pnlSupplierDetails);
        pnlSupplierDetails.setLayout(pnlSupplierDetailsLayout);
        pnlSupplierDetailsLayout.setHorizontalGroup(
            pnlSupplierDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1585, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(supplierCrudPane)
            .addComponent(jSeparator5)
        );
        pnlSupplierDetailsLayout.setVerticalGroup(
            pnlSupplierDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supplierCrudPane, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
        );

        jtbProductData.addTab("Supplier Details", pnlSupplierDetails);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbProductData)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtbProductData)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnNewProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewProductPanelActionPerformed
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnNewProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlNewProduct);
        this.loadProducts();
        this.removeCrudText(this.pnlEditProduct);
        this.txtStock.setText("");
        
    }//GEN-LAST:event_btnNewProductPanelActionPerformed

    private void btnEditProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductPanelActionPerformed
        if(this.isProductSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a product to continue!", "No Product Selected!", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.productCrudPane, this.btnEditProductPanel);
            this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlEditProduct);
            this.txtStock.setText("");
        }
        
    }//GEN-LAST:event_btnEditProductPanelActionPerformed

    private void btnDeleteProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductPanelActionPerformed
        if(this.isProductSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a product to continue!", "No Product Selected!", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.productCrudPane, this.btnDeleteProductPanel);
            this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlDeleteProduct);
            this.txtStock.setText("");
        }
    }//GEN-LAST:event_btnDeleteProductPanelActionPerformed

    private void btnRestockProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockProductPanelActionPerformed
        if(this.isProductSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a product to continue!", "No Product Selected!", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.productCrudPane, this.btnRestockProductPanel);
            this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlRestockProduct);
        }
    }//GEN-LAST:event_btnRestockProductPanelActionPerformed

    private void btnRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductActionPerformed
        byte choice;
        choice = (byte) JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this product?",
                                                                        "Click YES to remove the product", YES_NO_OPTION, QUESTION_MESSAGE);
        if(choice==0){
            this.productService.deleteProduct(product.getId());
            JOptionPane.showMessageDialog(null, "Selected Product is successfully removed!", "Product Removed!", INFORMATION_MESSAGE);
            
            this.removeCrudText(this.pnlDeleteProduct);
            this.removeCrudText(this.pnlEditProduct);
            this.removeCrudText(this.pnlRestockProduct);
            this.modifyFocusedButtonDesign(this.productCrudPane, this.btnNewProductPanel);
            this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlNewProduct);
            this.loadCategories();
        }
    }//GEN-LAST:event_btnRemoveProductActionPerformed

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        this.editProduct();
        
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        this.addnewProduct();
        
    }//GEN-LAST:event_btnAddNewProductActionPerformed

    private void btnPlusOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusOneActionPerformed
        this.addStock(1);
    }//GEN-LAST:event_btnPlusOneActionPerformed

    private void btnPlusTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusTenActionPerformed
        this.addStock(10);
    }//GEN-LAST:event_btnPlusTenActionPerformed

    private void btnPlusHundredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusHundredActionPerformed
        this.addStock(100);
    }//GEN-LAST:event_btnPlusHundredActionPerformed

    private void btnRestockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockActionPerformed
        if(!txtStock.getText().isEmpty()){
            Product selectedProduct = this.productService.getProductById(product.getId());
            
            int updatedStock = selectedProduct.getStock()+parseInt(this.txtStock.getText());
            selectedProduct.setStock(updatedStock);
            
            this.productService.restockProduct(selectedProduct);
            JOptionPane.showMessageDialog(null, "Product is successfully restocked!", "Product Restocked!", INFORMATION_MESSAGE);
            this.loadProducts();
        }
        else{
            JOptionPane.showMessageDialog(null, "Stock amount is required!", "Product cannot be restocked!", WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRestockActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.txtStock.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnNewCategoryPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCategoryPanelActionPerformed
        this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnNewCategoryPanel);
        this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlNewCategory);
        this.loadCategories();
    }//GEN-LAST:event_btnNewCategoryPanelActionPerformed

    private void btnEditCategoryPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditCategoryPanelActionPerformed
        if(tblCategory.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a category to continue.", "No Category Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnEditCategoryPanel);
            this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlEditCategory);
        }
    }//GEN-LAST:event_btnEditCategoryPanelActionPerformed

    private void btnDeleteCategoryPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCategoryPanelActionPerformed
        if(tblCategory.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a category to continue.", "No Category Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnDeleteCategoryPanel);
            this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlDeleteCategory);
        }
    }//GEN-LAST:event_btnDeleteCategoryPanelActionPerformed

    private void btnNewSupplierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewSupplierPanelActionPerformed
        this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnNewSupplierPanel);
        this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlNewSupplier);
        this.loadSuppliers();
    }//GEN-LAST:event_btnNewSupplierPanelActionPerformed

    private void btnEditSupplierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditSupplierPanelActionPerformed
        if(tblSupplier.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a supplier to continue.", "No Supplier Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnEditSupplierPanel);
            this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlEditSupplier);
        }
    }//GEN-LAST:event_btnEditSupplierPanelActionPerformed

    private void btnDeleteSupplierPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSupplierPanelActionPerformed
        if(tblSupplier.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Select a supplier to continue.", "No Supplier Selected", WARNING_MESSAGE);
        }
        else{
            this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnDeleteSupplierPanel);
            this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlDeleteSupplier);
        }
    }//GEN-LAST:event_btnDeleteSupplierPanelActionPerformed

    private void btnAddNewCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewCategoryActionPerformed
        if(txtNewCategoryName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "New category name is required to be added.", "New Category cannot be added!", WARNING_MESSAGE);
        }
        else{
            this.category.setName(this.txtNewCategoryName.getText());
            this.categoryService.insertCategory(category);
            JOptionPane.showMessageDialog(null, "New Category is Successfully Addded!", "New Category Added!", INFORMATION_MESSAGE);
            this.txtNewCategoryName.setText("");
            this.loadCategories();
        }
    }//GEN-LAST:event_btnAddNewCategoryActionPerformed

    private void btnUpdateCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCategoryActionPerformed
        this.category.setName(this.txtCategoryName.getText());

        this.categoryService.updateCategory(category);
        JOptionPane.showMessageDialog(null, "Selected Category is updated", "Category Edited", INFORMATION_MESSAGE);
        
        this.txtCategoryName.setText("");
        this.lblCategoryName.setText("");
        this.loadCategories();
        this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnNewCategoryPanel);
        this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlNewCategory);
    }//GEN-LAST:event_btnUpdateCategoryActionPerformed

    private void btnRemoveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCategoryActionPerformed
        byte choice;
        choice = (byte) JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this category?",
                                                                    "Click YES to remove the category", YES_NO_OPTION, QUESTION_MESSAGE);
        if(choice==0){
            this.categoryService.deleteCategory(this.category.getId());
            JOptionPane.showMessageDialog(null, "Selected category is successfully removed!", "Category Removed!", INFORMATION_MESSAGE);
            
            this.lblCategoryName.setText("");
            this.txtCategoryName.setText("");
            this.loadCategories();
            this.modifyFocusedButtonDesign(this.categoryCrudPane, this.btnNewCategoryPanel);
            this.setSelectedCrudPanelVisible(this.categoryCrudPanelPane, this.pnlNewCategory);
        }
    }//GEN-LAST:event_btnRemoveCategoryActionPerformed

    private void btnAddNewSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewSupplierActionPerformed
        if(txtNewSupplierName.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "New Suppier name is required to be added.", "New Supplier cannot be added!", WARNING_MESSAGE);
        }
        else{
            this.supplier.setName(this.txtNewSupplierName.getText());
            this.supplier.setAddress(this.txtNewAddress.getText());
            this.supplier.setContactNo(this.txtNewContactNo.getText());
            this.supplierService.insertSupplier(supplier);
            
            JOptionPane.showMessageDialog(null, "New Supplier is Successfully Addded!", "New Supplier Added!", INFORMATION_MESSAGE);
            
            this.removeCrudText(this.pnlNewSupplier);
            this.loadSuppliers();
        }
    }//GEN-LAST:event_btnAddNewSupplierActionPerformed

    private void btnUpdateSuppilerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSuppilerActionPerformed
        this.supplier.setName(this.txtSupplierName.getText());
        this.supplier.setAddress(this.txtAddress.getText());
        this.supplier.setContactNo(this.txtContactNo.getText());
        
        this.supplierService.updateSupplier(supplier);
        JOptionPane.showMessageDialog(null, "Selected Supplier is updated", "Supplier Edited", INFORMATION_MESSAGE);
        
        this.loadSuppliers();
        this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnNewSupplierPanel);
        this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlNewSupplier);
    }//GEN-LAST:event_btnUpdateSuppilerActionPerformed

    private void btnRemoveSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveSupplierActionPerformed
        byte choice;
        choice = (byte) JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this supplier?",
                                                                    "Click YES to remove the supplier", YES_NO_OPTION, QUESTION_MESSAGE);
        if(choice==0){
            this.supplierService.deleteSupplier(supplier.getId());
            JOptionPane.showMessageDialog(null, "Selected supplier is successfully removed!", "Supplier Removed!", INFORMATION_MESSAGE);
            
            this.loadSuppliers();
            this.modifyFocusedButtonDesign(this.supplierCrudPane, this.btnNewSupplierPanel);
            this.setSelectedCrudPanelVisible(this.supplierCrudPanelPane, this.pnlNewSupplier);
        }
    }//GEN-LAST:event_btnRemoveSupplierActionPerformed
    
    private boolean isProductSelectionEmpty(){
        return this.tblProductData.getSelectionModel().isSelectionEmpty();
    }
    
    private void addnewProduct(){        
        this.product.setName(this.txtNewProductName.getText());
        this.product.setUnitPrice(parseInt(this.txtNewUnitPrice.getText()));
        this.product.setSellingPrice(parseInt(this.txtNewSellingPrice.getText()));
        this.product.setCategory(this.categoryService.getCategoryId(this.cmbNewCategory.getSelectedItem().toString()));
        this.product.setSupplier(this.supplierService.getSupplierId(this.cmbNewSupplier.getSelectedItem().toString()));
        this.product.setStock(parseInt(this.txtNewStock.getText()));
        this.product.setLastUpdated(CurrentDateTime.get());
        
        this.removeCrudText(this.pnlNewProduct);
        this.productService.insertProduct(product);
        JOptionPane.showMessageDialog(null, "New Product is Successfully Addded!", "New Product Added!", INFORMATION_MESSAGE);
        this.loadProducts();
    }
    
    private void editProduct() {
        this.product.setName(this.txtProductName.getText());
        this.product.setUnitPrice(parseInt(this.txtUnitPrice.getText()));
        this.product.setSellingPrice(parseInt(this.txtSellingPrice.getText()));
        this.product.setCategory(this.categoryService.getCategoryId(this.cmbCategory.getSelectedItem().toString()));
        this.product.setSupplier(this.supplierService.getSupplierId(this.cmbSupplier.getSelectedItem().toString()));
        this.product.setLastUpdated(CurrentDateTime.get());
        
        this.productService.updateProduct(this.product);
        this.removeCrudText(this.pnlEditProduct);
        this.removeCrudText(this.pnlDeleteProduct);
        this.removeCrudText(this.pnlRestockProduct);
        JOptionPane.showMessageDialog(null, "Selected Product is Successfully Updated!", "Product Updated!", INFORMATION_MESSAGE);
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnNewProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlNewProduct);
        this.loadProducts();
    }
    
    private void removeCrudText(javax.swing.JPanel jPanel) {
        for(Component component : jPanel.getComponents()){
            if(component instanceof javax.swing.JTextField){
                    ((javax.swing.JTextField) component).setText("");
            }
            else if(component instanceof javax.swing.JComboBox){
                ((javax.swing.JComboBox) component).setSelectedIndex(0);
            }
        }
    }
    
    public void modifyFocusedButtonDesign(javax.swing.JLayeredPane jLayeredPane, AbstractButton focusedButton){
        focusedButton.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
        focusedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.white));
        
        for(Component component : jLayeredPane.getComponents()){
            if(component instanceof AbstractButton && component!= focusedButton){
                component.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
                ((AbstractButton)component).setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
    
    public void setSelectedCrudPanelVisible(javax.swing.JLayeredPane jLayeredPane, javax.swing.JPanel selectedPanel){
        selectedPanel.setVisible(true);
        for(Component component : jLayeredPane.getComponents()){
            if(component instanceof javax.swing.JPanel && component!= selectedPanel){
                component.setVisible(false);
            }
        }
    }
        
    private void addStock(int count){
        int stock;
        if(this.txtStock.getText().isEmpty()){
            stock = 0;
        }
        else{
            stock = parseInt(this.txtStock.getText());
        }
        
        stock += count; 
        
        this.txtStock.setText(""+stock);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private posProjectForTuring.ui.AdminAccountRegistration adminAccountRegistration1;
    private javax.swing.JButton btnAddNewCategory;
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnAddNewSupplier;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteCategoryPanel;
    private javax.swing.JButton btnDeleteProductPanel;
    private javax.swing.JButton btnDeleteSupplierPanel;
    private javax.swing.JButton btnEditCategoryPanel;
    private javax.swing.JButton btnEditProductPanel;
    private javax.swing.JButton btnEditSupplierPanel;
    private javax.swing.JButton btnNewCategoryPanel;
    private javax.swing.JButton btnNewProductPanel;
    private javax.swing.JButton btnNewSupplierPanel;
    private javax.swing.JButton btnPlusHundred;
    private javax.swing.JButton btnPlusOne;
    private javax.swing.JButton btnPlusTen;
    private javax.swing.JButton btnRemoveCategory;
    private javax.swing.JButton btnRemoveProduct;
    private javax.swing.JButton btnRemoveSupplier;
    private javax.swing.JButton btnRestock;
    private javax.swing.JButton btnRestockProductPanel;
    private javax.swing.JButton btnUpdateCategory;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JButton btnUpdateSuppiler;
    private javax.swing.JLayeredPane categoryCrudPane;
    private javax.swing.JLayeredPane categoryCrudPanelPane;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbNewCategory;
    private javax.swing.JComboBox<String> cmbNewSupplier;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JLabel instock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jtbProductData;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblCategoryName;
    private javax.swing.JLabel lblContactNo;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblSellingPrice;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblSupplierName;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JPanel pnlDeleteCategory;
    private javax.swing.JPanel pnlDeleteProduct;
    private javax.swing.JPanel pnlDeleteSupplier;
    private javax.swing.JPanel pnlEditCategory;
    private javax.swing.JPanel pnlEditProduct;
    private javax.swing.JPanel pnlEditSupplier;
    private javax.swing.JPanel pnlNewCategory;
    private javax.swing.JPanel pnlNewProduct;
    private javax.swing.JPanel pnlNewSupplier;
    private javax.swing.JPanel pnlProductDetails;
    private javax.swing.JPanel pnlRestockProduct;
    private javax.swing.JPanel pnlSupplierDetails;
    private javax.swing.JLayeredPane productCrudPane;
    private javax.swing.JLayeredPane productCrudPanelPane;
    private javax.swing.JLabel productName;
    private javax.swing.JLayeredPane supplierCrudPane;
    private javax.swing.JLayeredPane supplierCrudPanelPane;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblProductData;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtNewAddress;
    private javax.swing.JTextField txtNewCategoryName;
    private javax.swing.JTextField txtNewContactNo;
    private javax.swing.JTextField txtNewProductName;
    private javax.swing.JTextField txtNewSellingPrice;
    private javax.swing.JTextField txtNewStock;
    private javax.swing.JTextField txtNewSupplierName;
    private javax.swing.JTextField txtNewUnitPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtSupplierName;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
