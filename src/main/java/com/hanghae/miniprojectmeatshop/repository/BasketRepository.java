package com.hanghae.miniprojectmeatshop.repository;

import com.hanghae.miniprojectmeatshop.model.Basket;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BasketRepository extends JpaRepository<Basket,Long> {

    List<Basket> findAllByUser(User user);

    Basket findByUserAndItem(User user, Item item);


}
