package com.steelslayer.application.itemsservice.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.steelslayer.application.itemsservice.utility.Category;

@Entity
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Name is required")
	private String name;
	@Enumerated(EnumType.STRING)
	private Category category;
	@NotNull(message = "Price is required")
	private Double price;
	@NotNull(message = " Quantity required")
	private int quantity;
	private String imageUri;
	@NotNull(message = "Seller_id is required")
	private Long seller_id;
	@NotBlank(message = "Description is required")
	private String description;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public Long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(Long seller_id) {
		this.seller_id = seller_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	protected Item() {}
	
	public Item(@NotBlank(message = "Name is required") String name, Category category,
		@NotNull(message = "Price is required") Double price, @NotNull(message = " Quantity required") int quantity,
		String imageUri, @NotNull(message = "Seller_id is required") Long seller_id,
		@NotBlank(message = "Description is required") String description) {
	super();
	this.name = name;
	this.category = category;
	this.price = price;
	this.quantity = quantity;
	this.imageUri = imageUri;
	this.seller_id = seller_id;
	this.description = description;
}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", quantity="
				+ quantity + ", imageUri=" + imageUri + ", seller_id=" + seller_id + ", description=" + description
				+ "]";
	}
}
