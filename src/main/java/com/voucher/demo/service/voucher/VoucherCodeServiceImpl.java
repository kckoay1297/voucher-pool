package com.voucher.demo.service.voucher;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voucher.demo.dto.offer.VoucherCodeOfferDto;
import com.voucher.demo.entity.offer.VoucherCodeBean;
import com.voucher.demo.entity.recipient.RecipientBean;
import com.voucher.demo.enums.message.MessageEnum;
import com.voucher.demo.repository.offer.VoucherCodeRepository;

@Service
public class VoucherCodeServiceImpl implements VoucherCodeService {

	@Autowired
	private VoucherCodeRepository voucherCodeRepository;
	
	@Transactional
	@Override
	public VoucherCodeBean createVoucherCode(VoucherCodeBean voucherCodeBean) {
		return voucherCodeRepository.save(voucherCodeBean);
	}
	
	@Transactional
	@Override
	public VoucherCodeBean getVoucherCodeById(String voucherCodeId) {
		Optional<VoucherCodeBean> voucherCodeOptional = voucherCodeRepository.findById(voucherCodeId);
		if(voucherCodeOptional.isPresent()) {
			return voucherCodeOptional.get();
		}
		
		return null;
	}
	
	@Transactional
	@Override
	public List<VoucherCodeOfferDto> findVoucherCodeOfferByEmail(String email){
		return voucherCodeRepository.findVoucherCodeOfferByEmail(email);
	}
	
	@Transactional
	@Override
	public void updateVoucherCodeRedeem(VoucherCodeBean voucherCodeBean) {
		int usageTime = voucherCodeBean.getUsedFrequency();
		voucherCodeBean.setUsedFrequency(usageTime+1);
		voucherCodeBean.setUsageDate(new Date());
		voucherCodeRepository.save(voucherCodeBean);
	}
	
	@Override
	public String validateRedeemVoucherCode(VoucherCodeBean voucherCodeBean, RecipientBean recipient) {
		String validationErrorMessage = "";
		
		if(voucherCodeBean == null) {
			return MessageEnum.VOUCHER_CODE_NOT_FOUND.getMessage();
		}
		
		if(recipient == null) {
			return MessageEnum.RECIPIENT_EMAIL_NOT_FOUND.getMessage();
		}
		
		if(recipient.getRecipientId() != voucherCodeBean.getRecipientId()) {
			validationErrorMessage += String.format(MessageEnum.VOUCHER_CODE_RECEIPIENT_ID_NOT_MATCHED.getMessage(), voucherCodeBean.getVoucherCodeId(), recipient.getRecipientId());
		}
		
		if(voucherCodeBean.getExpirationDate().before(new Date())) {
			validationErrorMessage += String.format(MessageEnum.VOUCHER_CODE_EXPIRED.getMessage(), voucherCodeBean.getVoucherCodeId(), voucherCodeBean.getExpirationDate());
		}
		
		if(voucherCodeBean.getUsedFrequency() > 0) {
			validationErrorMessage += String.format(MessageEnum.VOUCHER_CODE_IS_USED.getMessage(), voucherCodeBean.getVoucherCodeId());
		}
		
		return validationErrorMessage;
	}
	
}
