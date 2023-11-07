package com.example.demo5.Service;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Entity.Customer;
import com.example.demo5.Entity.Supplier;
import com.example.demo5.Repository.SupplierRepository;
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
public class SupplierService {
    private final Logger log = LoggerFactory.getLogger(SupplierService.class);

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public SupplierDTO saveSupplier(SupplierDTO supplierDTO){
        log.debug("Request to get all supplier");
        ModelMapper modelMapper = new ModelMapper();
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        supplier.setId(UUIDUtil.generateId());
        supplier = supplierRepository.save(supplier);
        return modelMapper.map(supplier, SupplierDTO.class);
    }

//    @Transactional(readOnly = true)
    public Page<SupplierDTO> findAll(Pageable pageable, String name) {
        log.debug("Request to get all supplier");
        ModelMapper modelMapper = new ModelMapper();
        Page<Supplier> result = null;
        if(name == ""){
            result = supplierRepository.findAll( pageable);
        }else{
            result = supplierRepository.findSupplierByName(pageable,name);
        }
        Page<SupplierDTO> dtoPage = result.map(entity -> modelMapper.map(entity, SupplierDTO.class));
        return dtoPage;
    }

    public Optional<SupplierDTO> findById(String id){
        log.debug("Request to get supplier : {}", id);
        Optional<Supplier> supplier = supplierRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<SupplierDTO> supplierDTO = supplier.map(c -> modelMapper.map(c, SupplierDTO.class));
        return supplierDTO;
    }

    public SupplierDTO updateSupplier(SupplierDTO supplierDTO){
        log.debug("Request to save supplier : {}", supplierDTO);
        ModelMapper modelMapper = new ModelMapper();
        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        supplier = supplierRepository.save(supplier);
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete supplier : {}", id);
        supplierRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
    public List<SupplierDTO> getAll() {
        log.debug("Request to get all supplier");
        ModelMapper modelMapper = new ModelMapper();
        List<Supplier> result = null;
        result = supplierRepository.findAll();
        List<SupplierDTO> dtoList = result.stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

//    @Transactional(readOnly = true)
    public List<SupplierDTO> findPageNumberSupplier() {
        log.debug("Request to get all customer");
        ModelMapper modelMapper = new ModelMapper();
        List<Supplier> result = supplierRepository.findAll();
        List<SupplierDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, SupplierDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
