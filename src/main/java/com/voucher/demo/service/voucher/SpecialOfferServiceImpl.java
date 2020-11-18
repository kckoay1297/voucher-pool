package com.voucher.demo.service.voucher;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voucher.demo.entity.offer.SpecialOfferBean;
import com.voucher.demo.enums.message.FlagEnum;
import com.voucher.demo.enums.message.MessageEnum;
import com.voucher.demo.repository.offer.SpecialOfferRepository;

@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

	@Autowired
	private SpecialOfferRepository specialOfferRepository;
	
	@Transactional
	@Override
	public SpecialOfferBean getSpecialOfferById(Integer specialOfferId) {
		Optional<SpecialOfferBean> specialOfferOptional =  specialOfferRepository.findById(specialOfferId);
		if(specialOfferOptional.isPresent()) {
			return specialOfferOptional.get();
		}
		
		return null;
	}
	
	@Transactional
	@Override
	public void updateSpecialOfferEffectiveFlag(SpecialOfferBean specialOfferBean) {
		specialOfferBean.setEffectiveFlag(FlagEnum.SINGLE_YES_FLAG.getFlag());
		specialOfferRepository.save(specialOfferBean);
	}
	
	@Transactional
	@Override
	public String validateSpecialOffer(SpecialOfferBean specialOfferBean) {
		String validationResuleMessage = "";
		if(specialOfferBean == null) {
			return MessageEnum.VOUCHER_CODE_NOT_FOUND.getMessage();
		}
		
		if(FlagEnum.SINGLE_YES_FLAG.getFlag().equals(specialOfferBean.getEffectiveFlag())) {
			validationResuleMessage += MessageEnum.SPECIAL_OFFER_IN_EFFECTIVE_FAILED_TO_CREATE.getMessage();
		}
		
		return validationResuleMessage;
	}
}
