package com.example.controller;


import com.example.dto.UserDTO;
import com.example.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/member")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveProcess(@RequestBody UserDTO userDTO){
        System.out.println(userDTO.getName());
        boolean isSaved = loginService.save(userDTO);
        if (isSaved) {
            System.out.println("Save Success");
            return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("회원가입 실패: 이미 존재하는 사용자", HttpStatus.CONFLICT);
        }
    }



    @PostMapping("/loginProc")
    public ResponseEntity<?> loginProcess(@RequestBody UserDTO userDTO, HttpSession session){
        Optional<UserDTO> loginResult = loginService.sign_in(userDTO);
        if (loginResult.isPresent()) {
            session.setAttribute("loginName", loginResult.get().getName());
            System.out.println("login Success");
            return new ResponseEntity<>(loginResult.get(), HttpStatus.OK);
        } else {
            System.out.println("login error");
            return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        System.out.println("로그아웃 성공");
        session.invalidate();
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }


    @GetMapping("/list")// 리스트
    public String findAll(Model model) {
        System.out.println("hi");
        List<UserDTO> memberDTOList = loginService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }
}
