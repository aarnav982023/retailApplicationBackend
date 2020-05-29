package com.steelslayer.application.itemsservice.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.steelslayer.application.itemsservice.entity.Item;
import com.steelslayer.application.itemsservice.service.ItemService;

@RestController
public class ItemController {
	@Autowired
	private ItemService itemService;
	@GetMapping("/")
	public ResponseEntity<List<Item>> getAllItems() {
		return itemService.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Item> getItem(@PathVariable long id){
		return itemService.findById(id);
	}
	@GetMapping(path="/image/{imageUri}",produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> getItemImage(@PathVariable String imageUri){
		try {
			return itemService.getImage(imageUri);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	@PostMapping("/")
	public ResponseEntity<Object> createItem(MultipartFile image,@Valid Item item){
		return itemService.createItem(image,item);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item item){
		return itemService.updateItem(id,item);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Item> deleteItem(@PathVariable long id){
		return itemService.deleteById(id);
	}
}
