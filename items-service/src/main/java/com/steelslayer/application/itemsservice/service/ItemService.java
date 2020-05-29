package com.steelslayer.application.itemsservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.steelslayer.application.itemsservice.entity.Item;
import com.steelslayer.application.itemsservice.repository.ItemRepository;

@Service
public class ItemService {
	@Value("${images.path}")
	private String imagesPath;
	@Autowired
	private ItemRepository itemRepository;
	
	public ResponseEntity<List<Item>> findAll(){
		 List<Item> items = itemRepository.findAll();
		 return ResponseEntity.ok(items);
	}
	
	public ResponseEntity<Item> findById(long id){
		Optional<Item> item = itemRepository.findById(id);
		if(!item.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(item.get());
	}
	
	public ResponseEntity<Object> createItem(MultipartFile image,Item item){
		if(image.isEmpty()) return ResponseEntity.badRequest().build();
		if(item.getId()!=null) return ResponseEntity.badRequest().build();
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());
		imageName = Calendar.getInstance().getTimeInMillis()+imageName.toLowerCase().replace(" ", "-");
		String path = imagesPath+imageName;
		try {
		InputStream is = image.getInputStream();
		Files.copy(is,Paths.get(path),StandardCopyOption.REPLACE_EXISTING);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
		}
		item.setImageUri(imageName);
		Item newItem = itemRepository.save(item);
		return ResponseEntity.ok(newItem);
	}
	
	public ResponseEntity<Resource> getImage(String imageUri) throws IOException {
		Path path = Paths.get(imagesPath+imageUri);
		Resource resource = new UrlResource(path.toUri());
		if(resource.exists() || resource.isReadable()) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(resource);
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<Item> updateItem(long id,Item item){
		Optional<Item> existingItemOptional = itemRepository.findById(id);
		if(!existingItemOptional.isPresent()) return ResponseEntity.notFound().build();
		Item existingItem  = existingItemOptional.get();
		existingItem.setName(item.getName());
		existingItem.setCategory(item.getCategory());
		existingItem.setDescription(item.getDescription());
		existingItem.setImageUri(item.getImageUri());
		existingItem.setPrice(item.getPrice());
		existingItem.setQuantity(item.getQuantity());
		existingItem.setSeller_id(item.getSeller_id());
		Item newItem = itemRepository.save(existingItem);
		return ResponseEntity.ok(newItem);
	}
	
	public ResponseEntity<Item> deleteById(long id){
		Optional<Item> item = itemRepository.findById(id);
		if(!item.isPresent()) return ResponseEntity.notFound().build();
		itemRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
