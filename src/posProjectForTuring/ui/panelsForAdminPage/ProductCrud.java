/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posProjectForTuring.ui.panelsForAdminPage;

import static java.lang.Integer.parseInt;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

/**
 *
 * @author hp
 */
public class ProductCrud extends javax.swing.JPanel {    
    ProductService productService = new ProductServiceImpl();
    CategoryService categoryService = new CategoryServiceImpl();
    SupplierService supplierService = new SupplierServiceImpl();
    
    public ProductCrud() {
        initComponents();
        this.loader();
        editProduct();
    }
    
    private void addnewProduct(){
        Product product = new Product();
        product.setName(this.txtProductName.getText());
        product.setUnitPrice(parseInt(this.txtUnitPrice.getText()));
        product.setSellingPrice(parseInt(this.txtSellingPrice.getText()));
        product.setCategroy(this.cmbCategory.getSelectedIndex()+1);
        product.setSupplier(this.cmbSupplier.getSelectedIndex()+1);
        product.setStock(parseInt(this.txtStock.getText()));
        product.setLastUpdated(CurrentDateTime.get());
        
        this.productService.insertProduct(product);
    }
    
    private void editProduct(){
        this.tblProductData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                txtProductName.setText(tblProductData.getValueAt(tblProductData.getSelectedRow(), 1).toString());
                txtUnitPrice.setText(tblProductData.getValueAt(tblProductData.getSelectedRow(), 2).toString());
                txtSellingPrice.setText(tblProductData.getValueAt(tblProductData.getSelectedRow(), 3).toString());
                cmbCategory.setSelectedItem(tblProductData.getValueAt(tblProductData.getSelectedRow(), 4).toString());
                cmbSupplier.setSelectedItem(tblProductData.getValueAt(tblProductData.getSelectedRow(), 5).toString());
                txtStock.setText(tblProductData.getValueAt(tblProductData.getSelectedRow(), 6).toString());
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
        this.cmbCategory.removeAllItems();
        for(Category category: categories){
            this.cmbCategory.addItem(category.getName());
        }
        
        List<Supplier> suppliers = this.supplierService.getAllSuppliers();
        this.cmbSupplier.removeAllItems();
        for(Supplier supplier: suppliers){
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
            row[4] = productService.getCategoryName(product.getCategroy());
            row[5] = productService.getSupplierName(product.getSupplier());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbProductData = new javax.swing.JTabbedPane();
        pnlProductDetails = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductData = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtProductName = new javax.swing.JTextField();
        txtUnitPrice = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSellingPrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        cmbSupplier = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAddNewProduct = new javax.swing.JButton();
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

        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Product Name");

        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Unit Price");

        txtSellingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSellingPriceActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Selling Price");

        cmbCategory.setMaximumRowCount(100);
        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        cmbSupplier.setMaximumRowCount(100);

        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Category");

        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("Supplier");

        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("Stock");

        btnAddNewProduct.setText("Add New Product");
        btnAddNewProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProductDetailsLayout = new javax.swing.GroupLayout(pnlProductDetails);
        pnlProductDetails.setLayout(pnlProductDetailsLayout);
        pnlProductDetailsLayout.setHorizontalGroup(
            pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jSeparator1)))
                    .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                        .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(320, 320, 320)
                                        .addComponent(jLabel2)
                                        .addGap(146, 146, 146)
                                        .addComponent(jLabel3))
                                    .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(100, 100, 100)
                                        .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(100, 100, 100)
                                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(100, 100, 100)
                                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(100, 100, 100)
                                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                                .addGap(677, 677, 677)
                                .addComponent(btnAddNewProduct)))
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlProductDetailsLayout.setVerticalGroup(
            pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlProductDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addComponent(btnAddNewProduct)
                .addContainerGap(140, Short.MAX_VALUE))
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

    private void txtSellingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSellingPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSellingPriceActionPerformed

    private void btnAddNewProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewProductActionPerformed
        this.addnewProduct();
        this.loader();
    }//GEN-LAST:event_btnAddNewProductActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNewProduct;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jtbProductData;
    private javax.swing.JPanel pnlCategory;
    private javax.swing.JPanel pnlProductDetails;
    private javax.swing.JPanel pnlSupplierDetails;
    private javax.swing.JTable tblCategory;
    private javax.swing.JTable tblProductData;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtSellingPrice;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
