package com.hanghae.miniprojectmeatshop.service;

import com.hanghae.miniprojectmeatshop.dto.UserRequestDto;
import com.hanghae.miniprojectmeatshop.exception.UnauthenticatedException;
import com.hanghae.miniprojectmeatshop.model.User;
import com.hanghae.miniprojectmeatshop.repository.UserRepository;
import com.hanghae.miniprojectmeatshop.security.JwtTokenProvider;
import com.hanghae.miniprojectmeatshop.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserRequestDto requestDto) {
        User user =  new User(requestDto);
        Boolean bcheck = checkValidation(user);

        if (bcheck)
        {
            user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            userRepository.save(user);
        }
        else
        {
            return;
        }
    }

    private Boolean checkValidation(User user) {
        //필요에 따라서 생성할지말지.. 협의후 진행
        return true;
    }

    public List<Map<String, String>> login(UserRequestDto requestDto) {
        User user = userRepository.findByUserName(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 username 입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        Map<String,String>username =new HashMap<>();
        Map<String,String>token = new HashMap<>();
        List<Map<String,String>> tu = new ArrayList<>();
        token.put("token",jwtTokenProvider.createToken(user.getUserName(), user.getId()));
        username.put("username",user.getUserName());
        tu.add(username);
        tu.add(token);
        return tu;
    }
    public User userFromUserDetails(UserDetails userDetails) {
        if ( userDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) userDetails).getUser();
        } else {
            throw new UnauthenticatedException("로그인이 필요합니다.");
        }
    }

}
