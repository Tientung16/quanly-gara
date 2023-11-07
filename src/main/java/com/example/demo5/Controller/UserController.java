package com.example.demo5.Controller;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.UserDTO;
import com.example.demo5.Service.AccessoryService;
import com.example.demo5.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public Boolean updateAccessory(
            @Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("Request to update accessory : {}", userDTO);
        Boolean result = userService.check(userDTO);
        return result;
    }
}
