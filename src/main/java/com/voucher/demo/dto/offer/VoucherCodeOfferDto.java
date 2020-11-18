package com.voucher.demo.dto.offer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class VoucherCodeOfferDto implements Serializable {

	private static final long serialVersionUID = 7706907571244913730L;
	
	private String voucherCodeId;
	private String specialOfferName;
	private BigDecimal fixedPercentageDiscount;
	private Date expirationDate;
	private Integer usedFrequencies;
	private Date usageDate;
	
	public VoucherCodeOfferDto(String voucherCodeId, String specialOfferName, BigDecimal fixedPercentageDiscount, Date expirationDate, 
			Integer usedFrequencies, Date usageDate) {
		setVoucherCodeId(voucherCodeId);
		setSpecialOfferName(specialOfferName);
		setFixedPercentageDiscount(fixedPercentageDiscount);
		setExpirationDate(expirationDate);
		setUsedFrequencies(usedFrequencies);
		setUsageDate(usageDate);
	}

}
