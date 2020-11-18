package com.voucher.demo.repository.offer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.voucher.demo.dto.offer.VoucherCodeOfferDto;
import com.voucher.demo.entity.offer.VoucherCodeBean;

@Repository
@Transactional
public interface VoucherCodeRepository extends JpaRepository<VoucherCodeBean, String>{

	@Query("SELECT " +
	           "new com.voucher.demo.dto.offer.VoucherCodeOfferDto(v.voucherCodeId, o.name, o.fixedPercentageDiscount, v.expirationDate, v.usedFrequency, v.usageDate) " +
	           "FROM " +
	           "VoucherCodeBean v , SpecialOfferBean o, RecipientBean r " +
	           "WHERE r.email = ?1 and r.recipientId = v.recipientId " +
	           "and v.specialOfferId = o.specialOfferId ")
	List<VoucherCodeOfferDto> findVoucherCodeOfferByEmail(String email);
}
