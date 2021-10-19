package com.hanghae.miniprojectmeatshop.controller;

import com.hanghae.miniprojectmeatshop.defaultResponse.DefaultResponse;
import com.hanghae.miniprojectmeatshop.defaultResponse.ResponseMessage;
import com.hanghae.miniprojectmeatshop.defaultResponse.StatusCode;
import com.hanghae.miniprojectmeatshop.defaultResponse.SuccessYn;
import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.ItemDetailResponseDto;
import com.hanghae.miniprojectmeatshop.dto.ItemUpdateRequestDto;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.service.ImageService;
import com.hanghae.miniprojectmeatshop.service.ItemService;
import com.hanghae.miniprojectmeatshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;
    private final ImageService imageService;



    @GetMapping("/item/detail/{item_Id}")
    public ResponseEntity<DefaultResponse<ItemDetailResponseDto>> getItemDetail(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("item_Id") Long itemId) {
        Item item = itemService.getItemById(itemId);
        ItemDetailResponseDto data = ItemDetailResponseDtoToString(userDetails, item);
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK, ResponseMessage.GET_ITEM_SUCCESS, data));
    }
    private ItemDetailResponseDto ItemDetailResponseDtoToString(UserDetails userDetails, Item item){
        return item.ItemDetailResponseDtoToString(userDetails);
    }

//param 으로받을때는 @ModelAttribute, @RequestBody 바디로 받는다
    @PostMapping("/item")
    public ResponseEntity<DefaultResponse<Void>> createItem(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemCreateRequestDto requestDto) {
        User user = userService.userFromUserDetails(userDetails);
        itemService.createItem(requestDto,user);
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK, ResponseMessage.CREATE_ITEM,null));
    }


    //---------------------이미지 받는곳---------------------
    @PostMapping("/image")
    public Path upLoadImg(@RequestPart(value = "image")MultipartFile multipartFile){
        Path sumImgUrl = imageService.saveFile(multipartFile);

        return sumImgUrl;
    }

//    @PostMapping("/image/update")
//    public ImgResponseDto upDateImg(@RequestPart(value = "image")MultipartFile multipartFile){
//        Path sumImgUrl = imageService.saveFile(multipartFile);
//        Path detailImgUrl = imageService.saveFile(multipartFile);
//        return new ImgResponseDto(sumImgUrl,detailImgUrl);
//    }

    //-----------------------------------------------------------

    @GetMapping("/item/update/{item_Id}")
    public ResponseEntity<DefaultResponse<Void>> updateItem(@PathVariable("item_Id") Long itemId ,@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemUpdateRequestDto requestDto ) {
        itemService.updateItem(itemId,requestDto,userService.userFromUserDetails(userDetails));
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK,ResponseMessage.UPDATE_ITEM_SUCCESS, null));
    }

    @GetMapping("/item/delete/{item_Id}")
    public ResponseEntity<DefaultResponse<Void>> deleteItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("item_Id") Long itemId) {
        itemService.deleteItem(itemId,userService.userFromUserDetails(userDetails));
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK, ResponseMessage.DELETE_ITEM_SUCCESS, null));
    }

}
