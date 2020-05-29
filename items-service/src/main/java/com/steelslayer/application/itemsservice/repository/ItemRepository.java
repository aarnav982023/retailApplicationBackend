package com.steelslayer.application.itemsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.steelslayer.application.itemsservice.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
