package com.steelslayer.application.ordersservice.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.steelslayer.application.ordersservice.entity.OrderItem;
import com.steelslayer.application.ordersservice.repository.OrderRepository;


@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public ResponseEntity<OrderItem> findById(long id){
		Optional<OrderItem> order = orderRepository.findById(id);
		if(!order.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(order.get());
	}
	
	public ResponseEntity<List<OrderItem>> findAll(){
		List<OrderItem> orders = orderRepository.findAll();
		return ResponseEntity.ok(orders);
	}
	
	public ResponseEntity<OrderItem> createOrder(OrderItem order){
		if(order.getId()==null) {
			OrderItem newUser = orderRepository.save(order);
			return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	public ResponseEntity<OrderItem> updateOrder(long id,OrderItem order){
		Optional<OrderItem> existingOrderOptional = orderRepository.findById(id);
		if(!existingOrderOptional.isPresent()) return ResponseEntity.notFound().build();
		OrderItem existingOrder  = existingOrderOptional.get();
		OrderItem newOrder = orderRepository.save(existingOrder);
		newOrder.setItem_id(order.getItem_id());
		newOrder.setSeller_id(order.getSeller_id());
		newOrder.setBuyer_id(order.getBuyer_id());
		newOrder.setPlaced(order.isPlaced());
		newOrder.setCompleted(order.isCompleted());
		return ResponseEntity.ok(newOrder);
	}
	
	public ResponseEntity<OrderItem> deleteById(long id) {
		Optional<OrderItem> order = orderRepository.findById(id);
		if(!order.isPresent()) return ResponseEntity.notFound().build();
		orderRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
