package com.voucher.demo.enums.message;

public enum MessageEnum {

	// voucher code action
	VOUCHER_CODE_CREATION_SUCCESS(100, "Voucher code created for %s recipients."),
	
	// voucher code validation
	VOUCHER_CODE_NOT_FOUND(500, "Voucher code not found."), 
	VOUCHER_CODE_RECEIPIENT_ID_NOT_MATCHED(501, "Voucher code %s not match with receipient id %s"),
	VOUCHER_CODE_EXPIRED(502, "Voucher code %s expired, expiration date is %s."),
	VOUCHER_CODE_IS_USED(503, "Voucher code %s is used. "),
	
	// special offer validation
	SPECIAL_OFFER_NOT_FOUND(600, "Special offer %s not found."),
	SPECIAL_OFFER_IN_EFFECTIVE_FAILED_TO_CREATE(601, "Special offer already in effective mode, cannot be applied to all recipients."),
	
	// recipient validation
	RECIPIENT_EMAIL_NOT_FOUND(800, "Customer email not found.");

	private int messageCode;
	private String message;
	
	MessageEnum(int messageCode, String message) {
		setMessageCode(messageCode);
		setMessage(message);
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
