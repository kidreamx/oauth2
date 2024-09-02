package login.oauth2.controller;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import login.global.oauth2.CustomOAuth2User;
import login.oauth2.dto.JwtResponseDto;
import login.oauth2.dto.UserSignUpDto;
import login.oauth2.entity.User;
import login.oauth2.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception {
        userService.signUp(userSignUpDto);
        return "회원가입 성공";
    }

    @GetMapping("/jwt-test")
    public ResponseEntity<String> jwtTest() throws Exception {
        
        return ResponseEntity.ok("OK");

    }

}