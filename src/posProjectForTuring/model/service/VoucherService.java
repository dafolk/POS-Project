package posProjectForTuring.model.service;

import java.util.List;
import posProjectForTuring.model.Voucher;

public interface VoucherService {
    public void insertVoucher(Voucher voucher);
    public List<Voucher> getAllVoucher();
}
