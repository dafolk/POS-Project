package posProjectForTuring.model.service.impl;

import java.util.List;
import posProjectForTuring.model.Voucher;
import posProjectForTuring.model.dao.VoucherDao;
import posProjectForTuring.model.service.VoucherService;

public class VoucherServiceImpl implements VoucherService {
    VoucherDao voucherDao = new VoucherDao();
    
    @Override
    public void insertVoucher(Voucher voucher) {
        this.voucherDao.insertVoucher(voucher);
    }

    @Override
    public List<Voucher> getAllVoucher() {
        return this.voucherDao.getAllVouchers();
    }
    
}
