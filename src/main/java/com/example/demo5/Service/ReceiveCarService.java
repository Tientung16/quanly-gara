package com.example.demo5.Service;

import com.example.demo5.DTO.AutoMakerDTO;
import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.ReceiveCarDTO;
import com.example.demo5.Entity.AutoMaker;
import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.ReceiveCar;
import com.example.demo5.Repository.CustomerRepository;
import com.example.demo5.Repository.ReceiveCarRepository;
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
public class ReceiveCarService {

    private final Logger log = LoggerFactory.getLogger(ReceiveCarService.class);

    private ReceiveCarRepository receiveCarRepository;

    private CustomerRepository customerRepository;

    public ReceiveCarService(ReceiveCarRepository receiveCarRepository,CustomerRepository customerRepository){
        this.receiveCarRepository = receiveCarRepository;
        this.customerRepository = customerRepository;
    }

    public ReceiveCarDTO saveReceiveCar(ReceiveCarDTO receiveCarDTO){
        log.debug("Request to get all receiveCar");
        ModelMapper modelMapper = new ModelMapper();
        ReceiveCar receiveCar = modelMapper.map(receiveCarDTO, ReceiveCar.class);
        receiveCar.setId(UUIDUtil.generateId());

        String idCustomer = UUIDUtil.generateId();
        CustomerDTO customerDTO = receiveCarDTO.getCustomer();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setId(idCustomer);
        receiveCar.setIdCustomer(idCustomer);
        receiveCar.setActive(false);
        receiveCar.setIsCreated(false);
        receiveCar = receiveCarRepository.save(receiveCar);
        customerRepository.save((customer));
        return modelMapper.map(receiveCar, ReceiveCarDTO.class);
    }

//    @Transactional(readOnly = true)
    public Page<ReceiveCarDTO> findAll(Pageable pageable, String licensePlates) {
        log.debug("Request to get all receiveCar");
        ModelMapper modelMapper = new ModelMapper();
        Page<ReceiveCar> result = null;
        if(licensePlates == ""){
            result = receiveCarRepository.findAllByActive(false, pageable);
        }else{
            result = receiveCarRepository.findReceiveCarByLicensePlates(pageable,licensePlates,false);
        }
        Page<ReceiveCarDTO> dtoPage = result.map(entity -> modelMapper.map(entity, ReceiveCarDTO.class));
        return dtoPage;
    }

//    @Transactional(readOnly = true)
    public List<ReceiveCarDTO> findPageNumberReceiveCar() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<ReceiveCar> result = receiveCarRepository.findAllByActiveToGetPage(false);
        List<ReceiveCarDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, ReceiveCarDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

//    @Transactional(readOnly = true)
    public List<ReceiveCarDTO> findPageNumberRepairCar() {
        log.debug("Request to get all accessory");
        ModelMapper modelMapper = new ModelMapper();
        List<ReceiveCar> result = receiveCarRepository.findAllByActiveToGetPage(true);
        List<ReceiveCarDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, ReceiveCarDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

//    @Transactional(readOnly = true)
    public Page<ReceiveCarDTO> findAllByActive(Pageable pageable, String licensePlates) {
        log.debug("Request to get all receiveCar");
        ModelMapper modelMapper = new ModelMapper();
        Page<ReceiveCar> result = null;
        if(licensePlates == ""){
        result = receiveCarRepository.findAllByActive(true, pageable);}
        else{
                result = receiveCarRepository.findReceiveCarByLicensePlates(pageable,licensePlates,true);
        }
        Page<ReceiveCarDTO> dtoPage = result.map(entity -> modelMapper.map(entity, ReceiveCarDTO.class));
        return dtoPage;
    }


    public Optional<ReceiveCarDTO> findById(String id){
        log.debug("Request to get receiveCar : {}", id);
        Optional<ReceiveCar> receiveCar = receiveCarRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<ReceiveCarDTO> receiveCarDTO = receiveCar.map(c -> modelMapper.map(c, ReceiveCarDTO.class));
        return receiveCarDTO;
    }

    public ReceiveCarDTO updateReceiveCar(ReceiveCarDTO receiveCarDTO){
        log.debug("Request to save receiveCar : {}", receiveCarDTO);
        ModelMapper modelMapper = new ModelMapper();
        ReceiveCar receiveCar = modelMapper.map(receiveCarDTO, ReceiveCar.class);
        receiveCar.setIdCustomer(receiveCarDTO.getCustomer().getId());
        receiveCar.setActive(false);
        receiveCar.setIsCreated(false);
        receiveCar = receiveCarRepository.save(receiveCar);
        CustomerDTO customerDTO = receiveCarDTO.getCustomer();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save((customer));
        return modelMapper.map(receiveCar, ReceiveCarDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete receiveCar : {}", id);
        receiveCarRepository.deleteById(id);
    }

    public void active(String id){
        log.debug("Request to delete receiveCar : {}", id);
        receiveCarRepository.updateActiveToTrue(id);
    }

//    @Transactional(readOnly = true)
    public List<ReceiveCarDTO> getAll() {
        log.debug("Request to get all receiveCar");
        ModelMapper modelMapper = new ModelMapper();
        List<ReceiveCar> result = null;
        result = receiveCarRepository.findAll();
        List<ReceiveCarDTO> dtoList = result.stream()
                .map(receiveCar -> modelMapper.map(receiveCar, ReceiveCarDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

//    public String updateIdCustomer(String idCustomer){
//        log.debug("Request to save idCustomer : {}",idCustomer);
//        ReceiveCar receiveCar = receiveCarRepository.find;
//        receiveCar.setIdCustomer(idCustomer);
//        receiveCarRepository.save(receiveCar);
//        return idCustomer;
//    }

}
