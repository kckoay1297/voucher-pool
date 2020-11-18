package com.voucher.demo.dto.redeem;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class RedeemVoucherDto implements Serializable {
	
	private static final long serialVersionUID = -7250473286614845905L;

	private String voucherCodeId;
	private String email;
}
