package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Supplier;

public interface SupplierService {
    public void insertSupplier(Supplier supplier);
    public void updateSupplier(Supplier supplier);
    public List<Supplier> getAllSuppliers();
    public int getSupplierId(String supplierName);
    public Supplier getSupplierById(int supplierId);
    public String getSupplierName(int supplierId);
    public void deleteSupplier(int supplierId);
}
