package com.goodhealth.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.goodhealth.controller.ValidGroup1;

@Entity
@Table(name="orders")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id")
	private int orderId;

	@Column(name="member_id")
	private int memberId;

	
	@Column(name="order_address")
	@NotBlank(message="联系地址不能为空",groups={ValidGroup1.class})
	private String orderAddress;

	@Column(name="order_award")
	private int orderAward;
	
	@Column(name="order_contacts")
	@NotBlank(message="联系人不能为空",groups={ValidGroup1.class})
	private String orderContacts;

	@Column(name="order_count")
	private BigDecimal orderCount;

	@Column(name="order_date")
	private String orderDate;

	@Column(name="order_detail")
	private String orderDetail;
	
	
	@Column(name="order_additional")
	private String orderAdditional;


	@Column(name="order_status")
	private int orderStatus;

	@Column(name="order_tell")
	@NotBlank(message="联系电话不能为空" ,groups={ValidGroup1.class})
	@Pattern(regexp="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message="请输入正确的电话号码",groups={ValidGroup1.class})
	private String orderTell;

	public Orders() {
	}

	/**
	 * @return the orderAdditional
	 */
	public String getOrderAdditional() {
		return orderAdditional;
	}
	
	/**
	 * @param orderAdditional the orderAdditional to set
	 */
	public void setOrderAdditional(String orderAdditional) {
		this.orderAdditional = orderAdditional;
	}
	
	
	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMemberId() {
		return this.memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getOrderAddress() {
		return this.orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public int getOrderAward() {
		return this.orderAward;
	}

	public void setOrderAward(int orderAward) {
		this.orderAward = orderAward;
	}

	public String getOrderContacts() {
		return this.orderContacts;
	}

	public void setOrderContacts(String orderContacts) {
		this.orderContacts = orderContacts;
	}

	public BigDecimal getOrderCount() {
		return this.orderCount;
	}

	public void setOrderCount(BigDecimal orderCount) {
		this.orderCount = orderCount;
	}

	public String getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDetail() {
		return this.orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public int getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTell() {
		return this.orderTell;
	}

	public void setOrderTell(String orderTell) {
		this.orderTell = orderTell;
	}

}