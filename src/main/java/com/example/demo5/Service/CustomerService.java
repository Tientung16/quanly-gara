package com.example.demo5.Service;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Entity.Customer;
import com.example.demo5.Mapper.CustomerMapper;
import com.example.demo5.Repository.CustomerRepository;
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
public class CustomerService {
    private final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    private ReceiveCarService receiveCarService;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
//        ModelMapper modelMapper = new ModelMapper();
//        Customer customer = customerMapper.toEntity(customerDTO);
//        customer = customerRepository.save(customer);
//        return customerMapper.toDto(customer);
        log.debug("Request to get all customer");
        ModelMapper modelMapper = new ModelMapper();
        Customer customerEntity = modelMapper.map(customerDTO, Customer.class);
        customerEntity.setId(UUIDUtil.generateId());
        customerEntity = customerRepository.save(customerEntity);
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

//    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable,String name) {
        log.debug("Request to get all customer");
        ModelMapper modelMapper = new ModelMapper();
        Page<Customer> result = null;
        if(name == ""){
            result = customerRepository.findAll( pageable);
        }else{
            result = customerRepository.findCustomerByName(pageable,name);
        }
        Page<CustomerDTO> dtoPage = result.map(entity -> modelMapper.map(entity, CustomerDTO.class));
        return dtoPage;
    }

//    @Transactional(readOnly = true)
    public List<CustomerDTO> findPageNumberCustomer() {
        log.debug("Request to get all customer");
        ModelMapper modelMapper = new ModelMapper();
        List<Customer> result = customerRepository.findAll();
        List<CustomerDTO> dtoList = result.stream().map(entity -> modelMapper.map(entity, CustomerDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

//    public Optional<CustomerDTO> findById(String id){
//        log.debug("Request to get customer : {}", id);
//        Optional<Customer> customer = customerRepository.findById(id);
//        ModelMapper modelMapper = new ModelMapper();
////        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
//        Optional<CustomerDTO> customerDTO = customer.map(c -> modelMapper.map(c, CustomerDTO.class));
//        return customerDTO;
//    }
    public Optional<CustomerDTO> findById(String id){
        log.debug("Request to get customer : {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        Optional<CustomerDTO> customerDTO = customer.map(c -> modelMapper.map(c, CustomerDTO.class));
        return customerDTO;
    }

    public Optional<CustomerDTO> findByPhone(String phone){
        log.debug("Request to get customer : {}", phone);
        Optional<Customer> customer = customerRepository.findByPhoneNumber(phone);
        ModelMapper modelMapper = new ModelMapper();
        Optional<CustomerDTO> customerDTO = customer.map(c -> modelMapper.map(c, CustomerDTO.class));
        return customerDTO;
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        log.debug("Request to save customer : {}", customerDTO);
        ModelMapper modelMapper = new ModelMapper();
        Customer customerEntity = modelMapper.map(customerDTO, Customer.class);
        customerEntity = customerRepository.save(customerEntity);
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public void delete(String id){
        log.debug("Request to delete customer : {}", id);
        customerRepository.deleteById(id);
    }

//    @Transactional(readOnly = true)
    public List<CustomerDTO> getAll() {
        log.debug("Request to get all customer");
        ModelMapper modelMapper = new ModelMapper();
        List<Customer> result = null;
        result = customerRepository.findAll();
        List<CustomerDTO> dtoList = result.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
