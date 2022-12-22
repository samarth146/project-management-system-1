package com.pms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Table(name="PRODUCT",indexes = @Index(columnList = "pName,pDisplayName"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {

	 private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false,updatable = false)
	private Long pId;
	
	@Size(min = 3, max = 50, message = "product Name must be between 3 and 50 characters")
	@Column(name = "pName",nullable = false,unique=true)
	private String pName;
	
	@Column(name = "pDisplayName",nullable = false,unique=true)
	private String pDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
//  @JoinColumn(name="cmId", updatable=false)
	private Category category;
	
	@Column(name = "pPrice",nullable = false)
	private Double pPrice;
	
	@Column(name = "pAuditFields",nullable = false)
	private String pAuditFields;
	
	@Column(nullable = false)
	private Date createdDate;

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDisplayName() {
		return pDisplayName;
	}

	public void setpDisplayName(String pDisplayName) {
		this.pDisplayName = pDisplayName;
	}

	

	public String getpAuditFields() {
		return pAuditFields;
	}

	public void setpAuditFields(String pAuditFields) {
		this.pAuditFields = pAuditFields;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getpPrice() {
		return pPrice;
	}

	public void setpPrice(Double pPrice) {
		this.pPrice = pPrice;
	}

	public Product(String pName, String pDisplayName, Category i, Double pPrice, String pAuditFields, Date date) {
		super();
		this.category = category;
		this.createdDate = date;
		this.pAuditFields = pAuditFields;
		this.pPrice = pPrice;
		this.pDisplayName = pDisplayName;
		this.pName = pName;
		
	}

	

}
