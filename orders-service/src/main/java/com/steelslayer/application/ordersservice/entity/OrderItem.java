package com.steelslayer.application.ordersservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class OrderItem {
	@Id
	@GeneratedValue
	private Long id;
	private boolean completed;
	private boolean placed;
	@NotNull
	private Long seller_id;
	@NotNull
	private Long buyer_id;
	@NotNull
	private Long item_id;
	public Long getId() {
		return id;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public boolean isPlaced() {
		return placed;
	}
	public void setPlaced(boolean placed) {
		this.placed = placed;
	}
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	public Long getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Long buyer_id) {
		this.buyer_id = buyer_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public OrderItem(Long id, boolean completed, boolean placed, @NotNull Long seller_id, @NotNull Long buyer_id,
			@NotNull Long item_id) {
		super();
		this.id = id;
		this.completed = completed;
		this.placed = placed;
		this.seller_id = seller_id;
		this.buyer_id = buyer_id;
		this.item_id = item_id;
	}
	protected OrderItem() {}
}
