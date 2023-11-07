package com.example.demo5.Service;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Entity.Accessory;
import com.example.demo5.Entity.Supplier;
import com.example.demo5.Repository.AccessoryRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccessoryService {
    private final Logger log = LoggerFactory.getLogger(AccessoryService.class);

    private AccessoryRepository accessoryRepository;

    public AccessoryService(AccessoryRepository accessoryRepository){
        this.accessoryRepository = accessoryRepository;
    }

    public AccessoryDTO saveAccessory(AccessoryDTO accessoryDTO){
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        Accessory accessory = modelMapper.map(accessoryDTO, Accessory.class);
        accessory.setId(UUIDUtil.generateId());
        accessory = accessoryRepository.save(accessory);
        return modelMapper.map(accessory, AccessoryDTO.class);
    }
//    @Transactional(readOnly = true)
    public Page<AccessoryDTO> findAll(Pageable pageable, String name) {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        Page<Accessory> result = null;
        if(name == ""){
            result = accessoryRepository.findAll( pageable);
        }else{
            result = accessoryRepository.findAccessoryByName(pageable,name);
        }
        Page<AccessoryDTO> dtoPage = result.map(entity -> modelMapper.map(entity, AccessoryDTO.class));
        return dtoPage;
    }

    public Optional<AccessoryDTO> findById(String id){
        log.debug("Request to get accessory : {}", id);
        Optional<Accessory> accessory = accessoryRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<AccessoryDTO> accessoryDTO = accessory.map(c -> modelMapper.map(c, AccessoryDTO.class));
        return accessoryDTO;
    }

    public AccessoryDTO updateAccessory(AccessoryDTO accessoryDTO){
        log.debug("Request to save accessory : {}", accessoryDTO);
        ModelMapper modelMapper = new ModelMapper();
        Accessory accessory = modelMapper.map(accessoryDTO, Accessory.class);
        accessory = accessoryRepository.save(accessory);
        return modelMapper.map(accessory, AccessoryDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete accessory : {}", id);
        accessoryRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
    public List<AccessoryDTO> getAll() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<Accessory> result = null;
        result = accessoryRepository.findAll();
        List<AccessoryDTO> dtoList = result.stream()
                .map(supplier -> modelMapper.map(supplier, AccessoryDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }


//    @Transactional(readOnly = true)
    public List<AccessoryDTO> findPageNumberAccessory() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<Accessory> result = accessoryRepository.findAll();
        List<AccessoryDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, AccessoryDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
