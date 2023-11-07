package com.example.demo5.Service;

import com.example.demo5.DTO.RepairCarDTO;
import com.example.demo5.DTO.RepairCarDetailDTO;
import com.example.demo5.Entity.RepairCar;
import com.example.demo5.Entity.RepairCarDetail;
import com.example.demo5.Repository.ReceiveCarRepository;
import com.example.demo5.Repository.RepairCarDetailRepository;
import com.example.demo5.Repository.RepairCarRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RepairCarDetailService {
    private final Logger log = LoggerFactory.getLogger(RepairCarDetailService.class);

    private RepairCarDetailRepository repairCarDetailRepository;

    public RepairCarDetailService(RepairCarDetailRepository repairCarDetailRepository){
        this.repairCarDetailRepository = repairCarDetailRepository;
    }

//    @Transactional(readOnly = true)
    public List<RepairCarDetailDTO> findAllByRepairId(String idRepair) {
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        List<RepairCarDetail> result = repairCarDetailRepository.findRepairCarDetailsByRepairId(idRepair);
        List<RepairCarDetailDTO> dtoList = result.stream()
                .map(entity -> modelMapper.map(entity, RepairCarDetailDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }


    public List<RepairCarDetailDTO> findAllByIdCustomer(String idCustomer) {
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        List<RepairCarDetail> result = repairCarDetailRepository.findRepairCarDetailsByIdCutomer(idCustomer);
        List<RepairCarDetailDTO> dtoList = result.stream()
                .map(entity -> modelMapper.map(entity, RepairCarDetailDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public List<RepairCarDetailDTO> findAll() {
        log.debug("Request to get all repairCar");
        ModelMapper modelMapper = new ModelMapper();
        List<RepairCarDetail> result = repairCarDetailRepository.findAll();
        List<RepairCarDetailDTO> dtoList = result.stream()
                .map(entity -> modelMapper.map(entity, RepairCarDetailDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }



}
