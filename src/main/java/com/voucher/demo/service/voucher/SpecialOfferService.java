package com.voucher.demo.service.voucher;

import com.voucher.demo.entity.offer.SpecialOfferBean;

public interface SpecialOfferService {

	SpecialOfferBean getSpecialOfferById(Integer specialOfferId);

	String validateSpecialOffer(SpecialOfferBean specialOfferBean);

	void updateSpecialOfferEffectiveFlag(SpecialOfferBean specialOfferBean);

}
