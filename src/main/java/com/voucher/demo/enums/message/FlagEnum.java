package com.voucher.demo.enums.message;

public enum FlagEnum {
	SINGLE_YES_FLAG("Y"),
	SINGLE_NO_FLAG("N");
	
	private String flag;
	
	FlagEnum(String flag){
		setFlag(flag);
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
