package com.voucher.demo.entity.offer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SPECIAL_OFFER")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
@Proxy(lazy = false)
public class SpecialOfferBean implements Serializable {

	private static final long serialVersionUID = -6555076307445578068L;

	@Id
	@Column(name = "SPECIAL_OFFER_ID", nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer specialOfferId;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "FIXED_PERCENTAGE_DISCOUNT", nullable = false)
	private BigDecimal fixedPercentageDiscount;
	
	@Column(name = "EFFECTIVE_FLAG", nullable = false)
	private String effectiveFlag;
}
