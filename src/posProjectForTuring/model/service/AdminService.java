package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Admin;

public interface AdminService {
    public void insertAdmin(Admin admin);
    public List<Admin> getAllAdmin();
}
