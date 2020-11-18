package com.voucher.demo.service.recipient;

import java.util.List;

import com.voucher.demo.entity.recipient.RecipientBean;

public interface RecipientService {

	List<RecipientBean> findAllReceipentList();

	RecipientBean getRecipientByEmail(String email);

}
