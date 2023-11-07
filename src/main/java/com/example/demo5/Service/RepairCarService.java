package com.example.demo5.Service;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.ReceiveCarDTO;
import com.example.demo5.DTO.RepairCarDTO;
import com.example.demo5.DTO.RepairCarDetailDTO;
import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.ReceiveCar;
import com.example.demo5.Entity.RepairCar;
import com.example.demo5.Entity.RepairCarDetail;
import com.example.demo5.Repository.CustomerRepository;
import com.example.demo5.Repository.ReceiveCarRepository;
import com.example.demo5.Repository.RepairCarDetailRepository;
import com.example.demo5.Repository.RepairCarRepository;
import org.apache.commons.lang3.StringUtils;
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
public class RepairCarService {
    private final Logger log = LoggerFactory.getLogger(RepairCarService.class);

    private ReceiveCarRepository receiveCarRepository;

    private RepairCarRepository repairCarRepository;

    private CustomerRepository customerRepository;

    private RepairCarDetailRepository repairCarDetailRepository;


    public RepairCarService(ReceiveCarRepository receiveCarRepository,RepairCarRepository repairCarRepository,CustomerRepository customerRepository,RepairCarDetailRepository repairCarDetailRepository){
        this.receiveCarRepository = receiveCarRepository;
        this.repairCarRepository = repairCarRepository;
        this.customerRepository = customerRepository;
        this.repairCarDetailRepository = repairCarDetailRepository;
    }

    public RepairCarDTO saveRepairCar(RepairCarDTO repairCarDTO){
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        RepairCar repairCar = modelMapper.map(repairCarDTO, RepairCar.class);
        String a = UUIDUtil.generateId();
//        repairCar.setActive(false);
        repairCar.setId(a);
        CustomerDTO customerDTO = repairCarDTO.getCustomer();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setId(repairCarDTO.getCustomer().getId());
        customerRepository.save((customer));

        Optional<ReceiveCar> receiveCar = receiveCarRepository.findById(repairCarDTO.getIdReceive());
        receiveCar.get().setIsCreated(true);
        List<RepairCarDetailDTO> repairCarDetailDTOS = repairCarDTO.getRepairCarDetail();
        for(int i=0;i<repairCarDetailDTOS.size();i++){
            RepairCarDetailDTO repairCarDetailDTO = repairCarDetailDTOS.get(i);
            RepairCarDetail repairCarDetail = modelMapper.map(repairCarDetailDTO, RepairCarDetail.class);
            repairCarDetail.setId(UUIDUtil.generateId());
            repairCarDetail.setIdReceive(repairCarDTO.getIdReceive());
            repairCarDetail.setIdCustomer(repairCarDTO.getCustomer().getId());
            repairCarDetail.setIdRepair(a);
            repairCarDetailRepository.save(repairCarDetail);
        }
        repairCar.setIdCustomer(repairCarDTO.getCustomer().getId());
        repairCar = repairCarRepository.save(repairCar);
        return modelMapper.map(repairCar, RepairCarDTO.class);
    }

//    @Transactional(readOnly = true)
    public List<RepairCarDTO> findAll() {
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        List<RepairCar> result = null;
        result = repairCarRepository.findAll();
//        Page<RepairCarDTO> dtoPage = result.map(entity -> modelMapper.map(entity, RepairCarDTO.class));
        List<RepairCarDTO> dtoList = result.stream()
                .map(entity -> modelMapper.map(entity, RepairCarDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public Optional<RepairCarDTO> findById(String id){
        log.debug("Request to get repairCar : {}", id);
        Optional<RepairCar> repairCar = repairCarRepository.findByIdReceive(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<RepairCarDTO> repairCarDTO = repairCar.map(c -> modelMapper.map(c, RepairCarDTO.class));
        return repairCarDTO;
    }


    public RepairCarDTO updateRepairCarDTO(String repairCarId,RepairCarDTO repairCarDTO){
        log.debug("Request to save repairCar : {}", repairCarDTO);
//        ModelMapper modelMapper = new ModelMapper();
//        RepairCar repairCar = modelMapper.map(repairCarDTO, RepairCar.class);
//
//        CustomerDTO customerDTO = repairCarDTO.getCustomer();
//        Customer customer = modelMapper.map(customerDTO, Customer.class);
//        customerRepository.save((customer));
//
//        List<RepairCarDetailDTO> repairCarDetailDTOS = repairCarDTO.getRepairCarDetail();
//        for(int i=0;i<repairCarDetailDTOS.size();i++){
//            RepairCarDetailDTO repairCarDetailDTO = repairCarDetailDTOS.get(i);
//            RepairCarDetail repairCarDetail = modelMapper.map(repairCarDetailDTO, RepairCarDetail.class);
//            repairCarDetailRepository.save(repairCarDetail);
//        }
//
//        repairCar = repairCarRepository.save(repairCar);
//        return modelMapper.map(repairCar, RepairCarDTO.class);
        ModelMapper modelMapper = new ModelMapper();

        Optional<RepairCar> optionalRepairCar = repairCarRepository.findById(repairCarId);
        if (optionalRepairCar.isEmpty()) {
            // Throw an exception or handle the case where the repair car is not found
        }

        RepairCar repairCar = optionalRepairCar.get();
        repairCar = modelMapper.map(repairCarDTO, RepairCar.class);

        CustomerDTO customerDTO = repairCarDTO.getCustomer();
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setId(repairCarDTO.getCustomer().getId());
        customerRepository.save(customer);

        Optional<ReceiveCar> receiveCar = receiveCarRepository.findById(repairCarDTO.getIdReceive());
        receiveCar.ifPresent(car -> car.setIsCreated(true));
        receiveCarRepository.save(receiveCar.orElseThrow());

        List<RepairCarDetailDTO> repairCarDetailDTOS = repairCarDTO.getRepairCarDetail();
        for (RepairCarDetailDTO repairCarDetailDTO : repairCarDetailDTOS) {
            RepairCarDetail repairCarDetail = modelMapper.map(repairCarDetailDTO, RepairCarDetail.class);
//            repairCarDetail.setId(UUIDUtil.generateId());
            if(!StringUtils.isEmpty(repairCarDetailDTO.getId())){
                repairCarDetail.setIdReceive(repairCarDTO.getIdReceive());
                repairCarDetail.setIdRepair(repairCarId);
                repairCarDetail.setIdCustomer(repairCarDTO.getCustomer().getId());
                repairCarDetailRepository.save(repairCarDetail);
            }else {
                repairCarDetail.setId(UUIDUtil.generateId());
                repairCarDetail.setIdReceive(repairCarDTO.getIdReceive());
                repairCarDetail.setIdRepair(repairCarId);
                repairCarDetail.setIdCustomer(repairCarDTO.getCustomer().getId());
                repairCarDetailRepository.save(repairCarDetail);
            }

        }

        repairCar.setIdCustomer(repairCarDTO.getCustomer().getId());
        repairCar = repairCarRepository.save(repairCar);
        return modelMapper.map(repairCar, RepairCarDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete repairCar : {}", id);
        repairCarRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
    public List<RepairCarDTO> getAll() {
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        List<RepairCar> result = null;
        result = repairCarRepository.findAll();
        List<RepairCarDTO> dtoList = result.stream()
                .map(customer -> modelMapper.map(customer, RepairCarDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }


//    public void active(String id){
//        log.debug("Request to delete receiveCar : {}", id);
//        repairCarRepository.updateActiveToTrue(id);
//    }
}
