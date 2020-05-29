package com.steelslayer.application.ordersservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.steelslayer.application.ordersservice.entity.OrderItem;
import com.steelslayer.application.ordersservice.service.OrderService;


@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	@GetMapping("/")
	public ResponseEntity<List<OrderItem>> getAllItems() {
		return orderService.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> getItem(@PathVariable long id){
		return orderService.findById(id);
	}
	@PostMapping("/")
	public ResponseEntity<OrderItem> createOrder(@Valid @RequestBody OrderItem item){
		return orderService.createOrder(item);
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderItem> updateOrder(@PathVariable long id, @RequestBody OrderItem item){
		return orderService.updateOrder(id,item);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<OrderItem> deleteItem(@PathVariable long id){
		return orderService.deleteById(id);
	}
}
