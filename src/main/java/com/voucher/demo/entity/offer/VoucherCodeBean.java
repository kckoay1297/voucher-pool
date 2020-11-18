package com.voucher.demo.entity.offer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VOUCHER_CODE")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
@Proxy(lazy = false)
public class VoucherCodeBean implements Serializable {
	
	private static final long serialVersionUID = 2414980039316996097L;

	@Id
	@Column(name = "VOUCHER_CODE_ID", nullable = false)
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	        name = "UUID",
	        strategy = "org.hibernate.id.UUIDGenerator"
	)
	@ColumnDefault("random_uuid()")
	private String voucherCodeId;
	
	@Column(name = "RECIPIENT_ID", nullable = false)
	private Integer recipientId;
	
	@Column(name = "SPECIAL_OFFER_ID", nullable = false)
	private Integer specialOfferId;
	
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
	
	@Column(name = "USED_FREQUENCY")
	private Integer usedFrequency;
	
	@Column(name = "USAGE_DATE")
	private Date usageDate;
	
}
