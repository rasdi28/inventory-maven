package com.sdd.inventory.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Mproduct {
	
	private Integer mproductpk;
	private String productname;
	private Integer productstock;
	private BigDecimal productprice;
	
	
	@Id
	@SequenceGenerator(name= "MPRODUCT_SEQ", sequenceName="MPRODUCT_SEQ", allocationSize=1, initialValue=100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MPRODUCT_SEQ")
	public Integer getMproductpk() {
		return mproductpk;
	}
	public String getProductname() {
		return productname;
	}
	public Integer getProductstock() {
		return productstock;
	}
	public BigDecimal getProductprice() {
		return productprice;
	}
	public void setMproductpk(Integer mproductpk) {
		this.mproductpk = mproductpk;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public void setProductstock(Integer productstock) {
		this.productstock = productstock;
	}
	public void setProductprice(BigDecimal productprice) {
		this.productprice = productprice;
	}
	
	
}
