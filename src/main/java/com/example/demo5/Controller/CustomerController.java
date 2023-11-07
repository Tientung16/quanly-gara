package com.example.demo5.Controller;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public ResponseEntity<ResponseObject> createCustomer(
            @Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException {
        log.debug("REST request to save customer : {}", customerDTO);
        CustomerDTO result = customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query customer successfully",result)
        );
    }

    @PostMapping("/customer-getAll")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable,String name) throws URISyntaxException{
        log.debug("REST request to get a page of Customer");
        Page<CustomerDTO> page = customerService.findAll(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable String id){
        Optional<CustomerDTO> customerDTO = customerService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("okDetail","Query customer successfully",customerDTO));
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @GetMapping("/customer-by-phone/{phone}")
    public ResponseEntity<CustomerDTO> findByPhone(@PathVariable String phone){
        Optional<CustomerDTO> customerDTO = customerService.findByPhone(phone);
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("okDetail","Query customer successfully",customerDTO));
        return ResponseUtil.wrapOrNotFound(customerDTO);
    }

    @GetMapping("/customer-page-number")
    public int findPageNumberCustomer(){
        List<CustomerDTO> customerDTO = customerService.findPageNumberCustomer();
        return customerDTO.size();
    }
//    @GetMapping("/customer/{id}")
//    public ResponseEntity<ResponseObject> findById(@PathVariable String id){
//        Optional<CustomerDTO> customerDTO = CustomerService.findById(id);
//        if (customerDTO.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok","Query customer successfully",customerDTO.get()));
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("error","Customer not found",null));
//        }
//    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<ResponseObject> updateCustomer(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody CustomerDTO customerDTO) throws URISyntaxException{
        log.debug("Request to update customer : {}", customerDTO);
        CustomerDTO result = customerService.updateCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query customer successfully",result)
        );
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable String id) {
        log.debug("Request to delete customer : {}", id);
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query customer successfully",""));
    }

    @PostMapping("/List-customer")
    public List<CustomerDTO> getAllCustomerToSelect()
            throws URISyntaxException {
        return customerService.getAll();
    }
}
