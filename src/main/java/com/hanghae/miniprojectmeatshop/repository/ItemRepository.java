package com.hanghae.miniprojectmeatshop.repository;

import com.hanghae.miniprojectmeatshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByIdAndAndCategory(Long id, String category);

}
