package com.example.demo5.Service;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.UserDTO;
import com.example.demo5.Entity.Accessory;
import com.example.demo5.Entity.User;
import com.example.demo5.Repository.AccessoryRepository;
import com.example.demo5.Repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {
    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Boolean check(UserDTO userDTO){
        log.debug("Request to save accessory : {}", userDTO);
        ModelMapper modelMapper = new ModelMapper();
//        User user = modelMapper.map(userDTO, User.class);
        Optional<User> user = userRepository.check(userDTO.getEmail(),userDTO.getPassword());
        if(user.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
