package com.voucher.demo.service.recipient;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.voucher.demo.repository.recipient.RecipientRepository;
import com.voucher.demo.entity.recipient.RecipientBean;

@Service
public class RecipientServiceImpl implements RecipientService {

	@Autowired
	private RecipientRepository recipientRepository;
	
	@Transactional
	@Override
	public List<RecipientBean> findAllReceipentList(){
		return recipientRepository.findAll();
	}
	
	@Transactional
	@Override
	public RecipientBean getRecipientByEmail(String email) {
		List<RecipientBean> recipientList = recipientRepository.findRecipientByEmail(email);
		if(!CollectionUtils.isEmpty(recipientList)) {
			return recipientList.get(0);
		}
		
		return null;
	}
	
}
