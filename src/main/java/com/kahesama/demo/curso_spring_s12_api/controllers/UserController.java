package com.kahesama.demo.curso_spring_s12_api.controllers;

import com.kahesama.demo.curso_spring_s12_api.model.mappers.rest.UserRestMapper;
import com.kahesama.demo.curso_spring_s12_api.model.request.UserCreateRequest;
import com.kahesama.demo.curso_spring_s12_api.model.response.UserResponse;
import com.kahesama.demo.curso_spring_s12_api.services.interfaces.UserServiceInt;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserServiceInt userService;
    private final UserRestMapper userMapper;

    @GetMapping("/v1/api")
    public List<UserResponse> findAll() {
        return userMapper.toUserResponseList(userService.findAll());
    }

    @GetMapping("/v1/api/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userMapper.toUserResponse(userService.findById(id));
    }

    @PostMapping("/v1/api")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    userMapper.toUserResponse(
                        userService.save(
                                userMapper.toUser(userRequest)
                        )
                    )
                );
    }

    @PostMapping("/v1/api/register")
    public ResponseEntity<UserResponse> registerNewUser(@Valid @RequestBody UserCreateRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    userMapper.toUserResponse(
                        userService.save(
                                userMapper.toUser(userRequest)
                        )
                    )
                );
    }
    /*
    @PutMapping("/v1/api/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserCreateRequest userRequest) {
        return userMapper.toUserResponse(
                userService.update(id, userMapper.toUser(userRequest)
            )
        );
    }
    */
    @DeleteMapping("/v1/api/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
