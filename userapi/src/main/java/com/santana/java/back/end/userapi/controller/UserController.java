package com.santana.java.back.end.userapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;

import com.santana.java.back.end.userapi.dto.UserDTO;
import com.santana.java.back.end.userapi.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getUsers(){
        List<UserDTO> usuarios = userService.getAll();
        return usuarios;
    }

    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    UserDTO newUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @GetMapping("/user/cpf/{cpf}")
    UserDTO findByCpf(@PathVariable String cpf){
        return userService.findByCpf(cpf);
    }

    @DeleteMapping("/user/{id}")
    UserDTO delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome){
        return userService.queryByName(nome);
    }
    
}