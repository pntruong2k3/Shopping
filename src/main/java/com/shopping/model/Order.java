package com.shopping.model;

import java.util.Date;

import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private Long id;
	  
	  	@Column(name = "order_date", columnDefinition = "datetime")
	    private Date orderDate;
		@Column(name = "delivery_date", columnDefinition = "datetime")

	    private Date deliveryDate;
	    private double totalPrice;
	    private double shippingFee;
	    private String orderStatus;
	    private String notes;
	    private boolean isAccept;
	    private String paymentMethod;
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "user", referencedColumnName = "id")
	    private User user;

	    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	    private List<OrderDetail> orderDetailList;
	    
	public Order() {
	}

	


	public Order(Long id, Date orderDate, Date deliveryDate, double totalPrice, double shippingFee, String orderStatus,
			String notes, boolean isAccept, String paymentMethod, User user, List<OrderDetail> orderDetailList) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.totalPrice = totalPrice;
		this.shippingFee = shippingFee;
		this.orderStatus = orderStatus;
		this.notes = notes;
		this.isAccept = isAccept;
		this.paymentMethod = paymentMethod;
		this.user = user;
		this.orderDetailList = orderDetailList;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}


