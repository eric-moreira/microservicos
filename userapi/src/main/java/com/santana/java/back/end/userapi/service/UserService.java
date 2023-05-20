package com.santana.java.back.end.userapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.santana.java.back.end.userapi.dto.UserDTO;
import com.santana.java.back.end.userapi.model.User;
import com.santana.java.back.end.userapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> usuarios = userRepository.findAll();
        
        return usuarios.stream()
        .map(UserDTO::convert)
        .collect(Collectors.toList());

    }

    public UserDTO findById(long userId){
        return userRepository.findById(userId)
        .map(UserDTO::convert)
        .orElseThrow(RuntimeException::new);
    }

    public UserDTO save(UserDTO userDTO){

        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));

        return UserDTO.convert(user);
    }

    public UserDTO delete(long userId){

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()){
            userRepository.delete(optionalUser.get());
            return UserDTO.convert(optionalUser.get());
        }
        return null;

    }

    public UserDTO findByCpf(String cpf){

        User user = userRepository.findByCpf(cpf);

        if (user != null){

            return UserDTO.convert(user);

        }

        return null;
    }

    public List<UserDTO> queryByName(String name){

        List<User> usuarios = userRepository.queryByNomeLike(name);
        
        return
                usuarios.stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());

    }
}
