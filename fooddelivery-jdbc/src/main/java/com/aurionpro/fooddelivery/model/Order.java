package com.aurionpro.fooddelivery.model;

import java.time.LocalDateTime;
import java.util.List;

import com.aurionpro.fooddelivery.enums.PaymentMode;

import struqt.util.UniqueIdGenerator;

public class Order {
	private long id;
	private List<OrderItem> items;
	private double totalAmount; // w/o discount
	private double discout;
	private double finalAmt; // w discount
	private PaymentMode paymentMode;
	private String deliveryAdd;
	private LocalDateTime timestamp;
	private DeliveryPartner deliveryPartner;
	transient UniqueIdGenerator generator = new UniqueIdGenerator(1L);

	public Order(List<OrderItem> items, PaymentMode paymentMode, String deliveryAdd) {
		this.id = generator.next();
		this.items = items;
		this.paymentMode = paymentMode;
		this.timestamp = LocalDateTime.now();
		this.deliveryAdd = deliveryAdd;
		calculateAmounts();
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getDiscout() {
		return discout;
	}

	public void setDiscout(double discout) {
		this.discout = discout;
	}

	public double getFinalAmt() {
		return finalAmt;
	}

	public void setFinalAmt(double finalAmt) {
		this.finalAmt = finalAmt;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void calculateAmounts() {
		double total = 0.0;
		for (OrderItem item : items) {
			total += item.getItem().getPrice() * item.getQuantity();
		}
		this.totalAmount = total;

		if (total > 500) {
			this.discout = total * 0.10; // 10% discount
		} else {
			this.discout = 0;
		}

		this.finalAmt = total - this.discout;
	}

	public String getDeliveryAddress() {
		return this.deliveryAdd;
	}

	public double getFinalAmount() {
		return this.finalAmt;
	}

	public DeliveryPartner getDeliveryPartner() {
		return this.deliveryPartner;
	}

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

}
