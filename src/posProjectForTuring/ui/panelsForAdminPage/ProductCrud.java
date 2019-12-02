package posProjectForTuring.ui.panelsForAdminPage;

import java.awt.Color;
import java.awt.Component;
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
        this.tableActionListener();
    }

    private void startUp() {
        this.loader();
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnNewProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlNewProduct);
    }
    
    private int productId;
    private void tableActionListener(){
        
        this.tblProductData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(tblProductData.getSelectionModel().isSelectionEmpty()){
                    productId = productId;
                }
                else{
                    productId = (int)tblProductData.getValueAt(tblProductData.getSelectedRow(), 0);

                    product.setId(productId);
                    Product selectedProduct = productService.getProductById(productId);
                    txtProductName.setText(selectedProduct.getName());
                    txtUnitPrice.setText(""+selectedProduct.getUnitPrice());
                    txtSellingPrice.setText(""+selectedProduct.getSellingPrice());
                    cmbCategory.setSelectedItem(categoryService.getCategoryName(selectedProduct.getCategory()));
                    cmbSupplier.setSelectedItem(supplierService.getSupplierName(selectedProduct.getSupplier()));

                    setSelectedProductDetails(productId);                
                }
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
        pnlCategory = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCategory = new javax.swing.JTable();
        pnlSupplierDetails = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();

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
                .addContainerGap(666, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlRestockProductLayout = new javax.swing.GroupLayout(pnlRestockProduct);
        pnlRestockProduct.setLayout(pnlRestockProductLayout);
        pnlRestockProductLayout.setHorizontalGroup(
            pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1461, Short.MAX_VALUE)
        );
        pnlRestockProductLayout.setVerticalGroup(
            pnlRestockProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
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
                .addContainerGap(795, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1461, Short.MAX_VALUE)
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

        javax.swing.GroupLayout pnlCategoryLayout = new javax.swing.GroupLayout(pnlCategory);
        pnlCategory.setLayout(pnlCategoryLayout);
        pnlCategoryLayout.setHorizontalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1461, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCategoryLayout.setVerticalGroup(
            pnlCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCategoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
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

        javax.swing.GroupLayout pnlSupplierDetailsLayout = new javax.swing.GroupLayout(pnlSupplierDetails);
        pnlSupplierDetails.setLayout(pnlSupplierDetailsLayout);
        pnlSupplierDetailsLayout.setHorizontalGroup(
            pnlSupplierDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1461, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSupplierDetailsLayout.setVerticalGroup(
            pnlSupplierDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
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
        this.removeCrudText(this.pnlDeleteProduct);
        
    }//GEN-LAST:event_btnNewProductPanelActionPerformed

    private void btnEditProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductPanelActionPerformed
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnEditProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlEditProduct);
        
    }//GEN-LAST:event_btnEditProductPanelActionPerformed

    private void btnDeleteProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductPanelActionPerformed
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnDeleteProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlDeleteProduct);
        
    }//GEN-LAST:event_btnDeleteProductPanelActionPerformed

    private void btnRestockProductPanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestockProductPanelActionPerformed
        this.modifyFocusedButtonDesign(this.productCrudPane, this.btnRestockProductPanel);
        this.setSelectedCrudPanelVisible(this.productCrudPanelPane, this.pnlRestockProduct);
        this.loadProducts();
        this.removeCrudText(this.pnlEditProduct);
        this.removeCrudText(this.pnlDeleteProduct);
        
    }//GEN-LAST:event_btnRestockProductPanelActionPerformed

    private void btnRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveProductActionPerformed
        byte choice;
        if(this.tblProductData.getSelectionModel().isSelectionEmpty()){
            JOptionPane.showMessageDialog(null, "Please select a product first to remove", "No product selected!", WARNING_MESSAGE);
        }
        else{
            choice = (byte) JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this product?",
                                                                        "Click YES to remove the product", YES_NO_OPTION, QUESTION_MESSAGE);
            if(choice==0){
                this.productService.deleteProduct(productId);
                JOptionPane.showMessageDialog(null, "Selected Product is successfully removed!", "Product Removed!", INFORMATION_MESSAGE);
            }
        }
        this.removeCrudText(this.pnlDeleteProduct);
        this.removeCrudText(this.pnlEditProduct);
        this.loader();
        
    }//GEN-LAST:event_btnRemoveProductActionPerformed

    private void btnUpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductActionPerformed
        this.editProduct();
        
    }//GEN-LAST:event_btnUpdateProductActionPerformed

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        this.addnewProduct();
        
    }//GEN-LAST:event_btnAddNewProductActionPerformed
    
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
        JOptionPane.showMessageDialog(null, "New Product Successfully Addded!", "Product Added!", INFORMATION_MESSAGE);
        this.loadProducts();
    }
    
    private void editProduct() {
        this.product.setName(this.txtProductName.getText());
        this.product.setUnitPrice(parseInt(this.txtUnitPrice.getText()));
        this.product.setSellingPrice(parseInt(this.txtSellingPrice.getText()));
        this.product.setCategory(this.categoryService.getCategoryId(this.cmbCategory.getSelectedItem().toString()));
        this.product.setSupplier(this.supplierService.getSupplierId(this.cmbSupplier.getSelectedItem().toString()));
        this.product.setLastUpdated(CurrentDateTime.get());
        
        this.removeCrudText(this.pnlEditProduct);
        this.removeCrudText(this.pnlDeleteProduct);
        this.productService.updateProduct(this.product);
        JOptionPane.showMessageDialog(null, "Product Successfully Updated!", "Product Updated!", INFORMATION_MESSAGE);
        this.loadProducts();
    }
    
    private void removeCrudText(javax.swing.JPanel jPanel) {
        if(jPanel== this.pnlDeleteProduct){
            this.lblProductName.setText("");
            this.lblUnitPrice.setText("");
            this.lblSellingPrice.setText("");
            this.lblCategory.setText("");
            this.lblSupplier.setText("");
        }
        else{
            for(Component component : jPanel.getComponents()){
                if(component instanceof javax.swing.JTextField){
                    ((javax.swing.JTextField) component).setText("");
                }
                else if(component instanceof javax.swing.JComboBox){
                    ((javax.swing.JComboBox) component).setSelectedIndex(0);
                }
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
    
    private void setSelectedProductDetails(int id){
        Product selectedProduct = this.productService.getProductById(id);
        this.lblProductName.setText(selectedProduct.getName());
        this.lblUnitPrice.setText(""+selectedProduct.getUnitPrice());
        this.lblSellingPrice.setText(""+selectedProduct.getSellingPrice());
        this.lblCategory.setText(this.categoryService.getCategoryName(selectedProduct.getCategory()));
        this.lblSupplier.setText(this.supplierService.getSupplierName(selectedProduct.getSupplier()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private posProjectForTuring.ui.AdminAccountRegistration adminAccountRegistration1;
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JButton btnDeleteProductPanel;
    private javax.swing.JButton btnEditProductPanel;
    private javax.swing.JButton btnNewProductPanel;
    private javax.swing.JButton btnRemoveProduct;
    private javax.swing.JButton btnRestockProductPanel;
    private javax.swing.JButton btnUpdateProduct;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbNewCategory;
    private javax.swing.JComboBox<String> cmbNewSupplier;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JTabbedPane jtbProductData;
    private javax.swing.JLabel lblCategory;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblSellingPrice;
    private javax.swing.JLabel lblSupplier;
    private javax.swing.JLabel lblUnitPrice;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JPanel pnlDeleteProduct;
    private javax.swing.JPanel pnlEditProduct;
    private javax.swing.JPanel pnlNewProduct;
    private javax.swing.JPanel pnlProductDetails;
    private javax.swing.JPanel pnlRestockProduct;
    private javax.swing.JPanel pnlSupplierDetails;
    private javax.swing.JLayeredPane productCrudPane;
    private javax.swing.JLayeredPane productCrudPanelPane;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblProductData;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtNewProductName;
    private javax.swing.JTextField txtNewSellingPrice;
    private javax.swing.JTextField txtNewStock;
    private javax.swing.JTextField txtNewUnitPrice;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
