package com.example.demo5.Service;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.AutoMakerDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Entity.Accessory;
import com.example.demo5.Entity.AutoMaker;
import com.example.demo5.Entity.Supplier;
import com.example.demo5.Repository.AutoMakerRepository;
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
@Transactional
public class AutoMakerService {
    private final Logger log = LoggerFactory.getLogger(AutoMakerService.class);

    private AutoMakerRepository autoMakerRepository;

    public AutoMakerService(AutoMakerRepository autoMakerRepository){
        this.autoMakerRepository = autoMakerRepository;
    }

    public AutoMakerDTO saveAutoMaker(AutoMakerDTO autoMakerDTO){
        log.debug("Request to get all autoMaker");
        ModelMapper modelMapper = new ModelMapper();
        AutoMaker autoMaker = modelMapper.map(autoMakerDTO, AutoMaker.class);
        autoMaker.setId(UUIDUtil.generateId());
        autoMaker = autoMakerRepository.save(autoMaker);
        return modelMapper.map(autoMaker, AutoMakerDTO.class);
    }

//    @Transactional(readOnly = true)
    public Page<AutoMakerDTO> findAll(Pageable pageable, String name) {
        log.debug("Request to get all autoMaker");
        ModelMapper modelMapper = new ModelMapper();
        Page<AutoMaker> result = null;
        if(name == ""){
            result = autoMakerRepository.findAll( pageable);
        }else{
            result = autoMakerRepository.findAutoMakerByName(pageable,name);
        }
        Page<AutoMakerDTO> dtoPage = result.map(entity -> modelMapper.map(entity, AutoMakerDTO.class));
        return dtoPage;
    }

    public Optional<AutoMakerDTO> findById(String id){
        log.debug("Request to get autoMaker : {}", id);
        Optional<AutoMaker> autoMaker = autoMakerRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<AutoMakerDTO> autoMakerDTO = autoMaker.map(c -> modelMapper.map(c, AutoMakerDTO.class));
        return autoMakerDTO;
    }

    public AutoMakerDTO updateAutoMaker(AutoMakerDTO autoMakerDTO){
        log.debug("Request to save autoMaker : {}", autoMakerDTO);
        ModelMapper modelMapper = new ModelMapper();
        AutoMaker autoMaker = modelMapper.map(autoMakerDTO, AutoMaker.class);
        autoMaker = autoMakerRepository.save(autoMaker);
        return modelMapper.map(autoMaker, AutoMakerDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete autoMaker : {}", id);
        autoMakerRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
    public List<AutoMakerDTO> findPageNumberAutomaker() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<AutoMaker> result = autoMakerRepository.findAll();
        List<AutoMakerDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, AutoMakerDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public List<AutoMakerDTO> getAll() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<AutoMaker> result = null;
        result = autoMakerRepository.findAll();
        List<AutoMakerDTO> dtoList = result.stream()
                .map(supplier -> modelMapper.map(supplier, AutoMakerDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

}
