package com.voucher.demo.service.voucher;

import java.util.List;

import com.voucher.demo.dto.offer.VoucherCodeOfferDto;
import com.voucher.demo.entity.offer.VoucherCodeBean;
import com.voucher.demo.entity.recipient.RecipientBean;

public interface VoucherCodeService {

	VoucherCodeBean createVoucherCode(VoucherCodeBean voucherCodeBean);

	VoucherCodeBean getVoucherCodeById(String voucherCodeId);

	List<VoucherCodeOfferDto> findVoucherCodeOfferByEmail(String email);

	void updateVoucherCodeRedeem(VoucherCodeBean voucherCodeBean);

	String validateRedeemVoucherCode(VoucherCodeBean voucherCodeBean, RecipientBean recipient);

}
