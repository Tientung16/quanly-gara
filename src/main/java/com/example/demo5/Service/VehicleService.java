package com.example.demo5.Service;

import com.example.demo5.DTO.VehicleDTO;
import com.example.demo5.Entity.Vehicle;
import com.example.demo5.Repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class VehicleService {
    private final Logger log = LoggerFactory.getLogger(VehicleService.class);

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO){
        log.debug("Request to get all vehicle");
        ModelMapper modelMapper = new ModelMapper();
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle.setId(UUIDUtil.generateId());
        vehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

//    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAll(Pageable pageable, String licensePlates) {
        log.debug("Request to get all vehicle");
        ModelMapper modelMapper = new ModelMapper();
        Page<Vehicle> result = null;
        if(licensePlates == ""){
            result = vehicleRepository.findAll( pageable);
        }else{
            result = vehicleRepository.findVehicleByLicensePlates(pageable,licensePlates);
        }
        Page<VehicleDTO> dtoPage = result.map(entity -> modelMapper.map(entity, VehicleDTO.class));
        return dtoPage;
    }

    public Optional<VehicleDTO> findById(String id){
        log.debug("Request to get vehicle : {}", id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<VehicleDTO> vehicleDTO = vehicle.map(c -> modelMapper.map(c, VehicleDTO.class));
        return vehicleDTO;
    }

    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO){
        log.debug("Request to save vehicle : {}", vehicleDTO);
        ModelMapper modelMapper = new ModelMapper();
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete vehicle : {}", id);
        vehicleRepository.deleteById(id);
    }
}
