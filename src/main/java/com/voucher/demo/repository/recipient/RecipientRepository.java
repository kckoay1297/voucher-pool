package com.voucher.demo.repository.recipient;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voucher.demo.entity.recipient.RecipientBean;

@Repository
@Transactional
public interface RecipientRepository  extends JpaRepository<RecipientBean, Integer>{

	List<RecipientBean> findRecipientByEmail(String email);
}
