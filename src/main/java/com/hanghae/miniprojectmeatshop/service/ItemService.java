package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemUpdateRequestDto;
import com.hanghae.miniprojectmeatshop.exception.ItemNotFoundException;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ImageService imageService;


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


}
