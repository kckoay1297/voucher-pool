package com.voucher.demo.repository.offer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import com.voucher.demo.entity.offer.SpecialOfferBean;

@Repository
@Transactional
public interface SpecialOfferRepository extends JpaRepository<SpecialOfferBean, Integer> {


}
