package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemUpdateRequestDto;
import com.hanghae.miniprojectmeatshop.exception.ItemNotFoundException;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import com.hanghae.miniprojectmeatshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageService imageService;
    private final UserRepository userRepository; //LSJ 테스트용 지우면 지워주세욤..


    public void createItem(ItemCreateRequestDto requestDto, User user) {
        Item item = Item.of(requestDto, user);
        itemRepository.save(item);
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(
                () -> new ItemNotFoundException("상품을 찾을 수 없습니다")
        );
    }

    public void updateItem(Long itemId, ItemUpdateRequestDto requestDto, User user) {
        Item item = getItemById(itemId);
        if (item.isWritedBy(user)) {
            item.update(requestDto);
            itemRepository.save(item);
        } else {
            throw new AccessDeniedException("권한이 없습니다.");
        }
    }

    public void deleteItem(Long itemId, User user) {
        Item item = getItemById(itemId);
        if (item.isWritedBy(user)) {
            itemRepository.delete(item);
        } else {
            throw new AccessDeniedException("권한이 없습니다");
        }
    }


    //Test용 혹시 사용할수도있을거같아서 남겨놔요..
    @Transactional
    public void mytest() {
        User user = userRepository.findByUserName("tmdwns1235").orElse(null);
        Item item1 = new Item();
        item1.setCategory("1");
        item1.setDefaultprice(3000);
        item1.setTitle("제목1");
        item1.setUser(user);
        Item item2 = new Item();
        item2.setCategory("1");
        item2.setDefaultprice(3000);
        item2.setTitle("제목1");
        item2.setUser(user);
        Item item3 = new Item();
        item3.setCategory("1");
        item3.setDefaultprice(3000);
        item3.setTitle("제목1");
        item3.setUser(user);
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
    }
}
