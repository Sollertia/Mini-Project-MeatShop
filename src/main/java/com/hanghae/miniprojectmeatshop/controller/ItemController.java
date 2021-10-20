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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public ResponseEntity<DefaultResponse<Void>> createItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ItemCreateRequestDto requestDto) {
        User user = userService.userFromUserDetails(userDetails);
        itemService.createItem(requestDto,user);
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK, ResponseMessage.CREATE_ITEM,null));
    }


    //---------------------이미지 받는곳---------------------
    @PostMapping("/image")
    public String upLoadImg(@RequestPart(value = "image")MultipartFile multipartFile){
        String sumImgUrl = imageService.saveFile(multipartFile);
        return sumImgUrl;
    }

    @PutMapping("/item/update/{item_Id}")
    public ResponseEntity<DefaultResponse<Void>> updateItem(@PathVariable("item_Id") Long itemId ,@AuthenticationPrincipal UserDetails userDetails, @RequestBody ItemUpdateRequestDto requestDto ) {
        itemService.updateItem(itemId,requestDto,userService.userFromUserDetails(userDetails));
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK,ResponseMessage.UPDATE_ITEM_SUCCESS, null));
    }

    @GetMapping("/item/delete/{item_Id}")
    public ResponseEntity<DefaultResponse<Void>> deleteItem(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("item_Id") Long itemId) {
        itemService.deleteItem(itemId,userService.userFromUserDetails(userDetails));
        return ResponseEntity.ok(DefaultResponse.res(SuccessYn.OK, StatusCode.OK, ResponseMessage.DELETE_ITEM_SUCCESS, null));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/display/{file}")
    public ResponseEntity<org.springframework.core.io.Resource> display(
            @PathVariable String file
    ) {
        String path = "/home/ubuntu/images/"+file; // 이경로는 우분투랑 윈도우랑 다르니까 주의해야댐 우분투 : / 윈도우 \\ 인것같음.
        String folder = "";
        org.springframework.core.io.Resource resource = new FileSystemResource(path);
        if (!resource.exists())
            return new ResponseEntity<org.springframework.core.io.Resource>(HttpStatus.NOT_FOUND);
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            filePath = Paths.get(path);
            header.add("Content-Type", Files.probeContentType(filePath));
        } catch (IOException e) {
            return null;
        }
        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
