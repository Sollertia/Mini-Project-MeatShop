package com.hanghae.miniprojectmeatshop.testdata;

import com.hanghae.miniprojectmeatshop.dto.ItemCreateRequestDto;
import com.hanghae.miniprojectmeatshop.dto.UserRequestDto;
import com.hanghae.miniprojectmeatshop.model.Item;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.repository.ItemRepository;
import com.hanghae.miniprojectmeatshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestData implements ApplicationRunner {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        String porkDetailImg = "https://firebasestorage.googleapis.com/v0/b/jyg-custom-seoul-app/o/frontend%2Fdescriptions%2Fweb%2Fporkbelly-clean2.png?alt=media";
        String beefDetailImg = "https://firebasestorage.googleapis.com/v0/b/jyg-custom-seoul-app/o/frontend%2Fdescriptions%2Fweb%2Fbeeftender-monep2.png?alt=media";
        String chickenDetailImg = "https://firebasestorage.googleapis.com/v0/b/jyg-custom-seoul-app/o/frontend%2Fdescriptions%2Fweb%2Fchickef-cut2.png?alt=media";


        List<ItemCreateRequestDto> dto = new ArrayList<>();

        // 테스트 유저 생성
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setPassword(passwordEncoder.encode("password"));
        requestDto.setUsername("username");
        User testUser = new User(requestDto);
        testUser = userRepository.save(testUser);

        requestDto.setPassword(passwordEncoder.encode("password2"));
        requestDto.setUsername("username2");
        User testUser2 = new User(requestDto);
        testUser2 = userRepository.save(testUser2);


        // 이미지 파일 URL 및 텍스트 읽어오는 용도
        FileReader rw;
        BufferedReader br;
        FileReader rw2;
        BufferedReader br2;
        try {
            //파일 읽기
            rw = new FileReader("./src/main/resources/static/address.txt");
            br = new BufferedReader(rw);
            rw2 = new FileReader("./src/main/resources/static/title.txt");
            br2 = new BufferedReader(rw2);
            String readLine;
            String readLine2;
            int i = 0;

            while( ( readLine =  br.readLine()) != null ){
                readLine2 = br2.readLine();
                if(i<12){ //돼지
                    dto.add(ItemCreateRequestDto.builder().sumImgUrl(readLine)
                            .category("pork").defaultprice(100000).title(readLine2).detailImgUrl(porkDetailImg).build());
                }else if(i<24) { //소
                    dto.add(ItemCreateRequestDto.builder().sumImgUrl(readLine)
                            .category("beef").defaultprice(100000).title(readLine2).detailImgUrl(beefDetailImg).build());
                }else{ //닭
                    dto.add(ItemCreateRequestDto.builder().sumImgUrl(readLine)
                            .category("chicken").defaultprice(100000).title(readLine2).detailImgUrl(chickenDetailImg).build());
                }
                i++;
            }
        }catch (IOException e){

        }

        // item 생성
        for (ItemCreateRequestDto d : dto){
            Item item = new Item(d,testUser);
            itemRepository.save(item);
        }


    }

}