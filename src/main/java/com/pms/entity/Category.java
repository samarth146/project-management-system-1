package com.pms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Builder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cmId;
	private String cmName;
	private String cmDisplayName;
	private String cmAuditFields;
	
	
//	/cascade = CascadeType.ALL,
	 @OneToMany(mappedBy = "category")
	 @JsonIgnore
     public List<Product> products;
	
	public Long getCmId() {
		return cmId;
	}
	public void setCmId(Long cmId) {
		this.cmId = cmId;
	}
	public String getCmName() {
		return cmName;
	}
	public void setCmName(String cmName) {
		this.cmName = cmName;
	}
	public String getCmDisplayName() {
		return cmDisplayName;
	}
	public void setCmDisplayName(String cmDisplayName) {
		this.cmDisplayName = cmDisplayName;
	}
	public String getCmAuditFields() {
		return cmAuditFields;
	}
	public void setCmAuditFields(String cmAuditFields) {
		this.cmAuditFields = cmAuditFields;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}


}
