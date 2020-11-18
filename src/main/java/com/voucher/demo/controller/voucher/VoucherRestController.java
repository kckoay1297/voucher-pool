package com.voucher.demo.controller.voucher;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.voucher.demo.dto.offer.VoucherCodeOfferDto;
import com.voucher.demo.dto.redeem.RedeemVoucherDto;
import com.voucher.demo.entity.offer.SpecialOfferBean;
import com.voucher.demo.entity.offer.VoucherCodeBean;
import com.voucher.demo.entity.recipient.RecipientBean;
import com.voucher.demo.enums.message.MessageEnum;
import com.voucher.demo.service.recipient.RecipientService;
import com.voucher.demo.service.voucher.SpecialOfferService;
import com.voucher.demo.service.voucher.VoucherCodeService;

@RequestMapping("/voucher/rest-api")
@RestController
public class VoucherRestController {

	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private VoucherCodeService voucherCodeService;
	
	@Autowired
	private RecipientService recipientService;
	
	@PostMapping("/create-voucher-code-on-each-recipient")
	public ResponseEntity<String> createVoucherCodeOnEachRecipient(@RequestParam(required=true) Integer specialOfferId,
			@RequestParam(required=true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date expirationDate){
		try {
			String resultMessage = "";
			SpecialOfferBean specialOfferBean = specialOfferService.getSpecialOfferById(specialOfferId);
			
			String validationOfferMessage = specialOfferService.validateSpecialOffer(specialOfferBean);
			if(StringUtils.isNullOrEmpty(validationOfferMessage)) {
				List<RecipientBean> allRecipientList = recipientService.findAllReceipentList();
				for(RecipientBean recipient: allRecipientList) {
					VoucherCodeBean voucherCodeForRecipient = constructVoucherCodeBean(recipient.getRecipientId(), specialOfferBean.getSpecialOfferId(), expirationDate);
					voucherCodeService.createVoucherCode(voucherCodeForRecipient);
				}
				
				specialOfferService.updateSpecialOfferEffectiveFlag(specialOfferBean);
				resultMessage = String.format(MessageEnum.VOUCHER_CODE_CREATION_SUCCESS.getMessage(), Optional.ofNullable(allRecipientList.size()).orElse(0));
			}else {
				resultMessage = validationOfferMessage;
			}
			
			return new ResponseEntity<>(resultMessage , HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	private VoucherCodeBean constructVoucherCodeBean(Integer recipientId, Integer specialOfferId, Date expirationDate) {
		VoucherCodeBean voucherCodeBean = new VoucherCodeBean();
		voucherCodeBean.setRecipientId(recipientId);
		voucherCodeBean.setSpecialOfferId(specialOfferId);
		voucherCodeBean.setExpirationDate(expirationDate);
		voucherCodeBean.setUsedFrequency(0);
		return voucherCodeBean;
	}
	
	@PutMapping("/redeem-voucher")
	public ResponseEntity<Object> redeemVoucher(@RequestBody(required=true) RedeemVoucherDto redeemVoucherRequest) {
		try {
			RecipientBean recipient = recipientService.getRecipientByEmail(redeemVoucherRequest.getEmail());
			VoucherCodeBean voucherCodeRedeem = voucherCodeService.getVoucherCodeById(redeemVoucherRequest.getVoucherCodeId());
			
			String voucherValidationMessage = voucherCodeService.validateRedeemVoucherCode(voucherCodeRedeem, recipient);
			if(!StringUtils.isNullOrEmpty(voucherValidationMessage)) {
				return new ResponseEntity<>(voucherValidationMessage , HttpStatus.OK);
			}
			
			SpecialOfferBean specialOffer = specialOfferService.getSpecialOfferById(voucherCodeRedeem.getSpecialOfferId());
			if(specialOffer == null) {
				return new ResponseEntity<>(String.format(MessageEnum.SPECIAL_OFFER_NOT_FOUND.getMessage(), voucherCodeRedeem.getSpecialOfferId()) , HttpStatus.OK);
			}else {
				voucherCodeService.updateVoucherCodeRedeem(voucherCodeRedeem);
				return new ResponseEntity<>(specialOffer.getFixedPercentageDiscount() , HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
	}
	
	@GetMapping("/get-voucher-by-email")
	public ResponseEntity<List<VoucherCodeOfferDto>> getVoucherByEmail(@RequestParam(required=true) String email) {
		try {
			List<VoucherCodeOfferDto> voucherCodeOfferList = voucherCodeService.findVoucherCodeOfferByEmail(email);
			return new ResponseEntity<>(voucherCodeOfferList , HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
