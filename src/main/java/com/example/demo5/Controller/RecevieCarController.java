package com.example.demo5.Controller;

import com.example.demo5.DTO.AutoMakerDTO;
import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.ReceiveCarDTO;
import com.example.demo5.Service.CustomerService;
import com.example.demo5.Service.ReceiveCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class RecevieCarController {

    private final Logger log = LoggerFactory.getLogger(RecevieCarController.class);

    private final ReceiveCarService receiveCarService;

    public RecevieCarController (ReceiveCarService receiveCarService) {
        this.receiveCarService = receiveCarService;
    }

    @PostMapping("/receive-car")
    public ResponseEntity<ResponseObject> createReceiveCar(
            @Valid @RequestBody ReceiveCarDTO receiveCarDTO) throws URISyntaxException {
        log.debug("REST request to save receiveCar : {}", receiveCarDTO);
        ReceiveCarDTO result = receiveCarService.saveReceiveCar(receiveCarDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query receiveCar successfully",result)
        );
    }

    @PostMapping("/receive-car-getAll")
    public ResponseEntity<List<ReceiveCarDTO>> getAllReceiveCar(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String name) throws URISyntaxException{
        log.debug("REST request to get a page of receiveCar");
        Page<ReceiveCarDTO> page = receiveCarService.findAll(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/receive-car-active-getAll")
    public ResponseEntity<List<ReceiveCarDTO>> getAllReceiveCarActive(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String name) throws URISyntaxException{
        log.debug("REST request to get a page of receiveCar");
        Page<ReceiveCarDTO> page = receiveCarService.findAllByActive(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/receive-page-number")
    public int findPageNumberReceiveCar(){
        List<ReceiveCarDTO> receiveCarDTOS = receiveCarService.findPageNumberReceiveCar();
        return receiveCarDTOS.size();
    }

    @GetMapping("/receive-car-active-number")
    public int findPageNumberRepairCar(){
        List<ReceiveCarDTO> receiveCarDTOS = receiveCarService.findPageNumberRepairCar();
        return receiveCarDTOS.size();
    }

    @GetMapping("/receive-car/{id}")
    public ResponseEntity<ReceiveCarDTO> findById(@PathVariable String id){
        Optional<ReceiveCarDTO> receiveCarDTO = receiveCarService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("okDetail","Query customer successfully",customerDTO));
        return ResponseUtil.wrapOrNotFound(receiveCarDTO);
    }

    @PutMapping("/receive-car/{id}")
    public ResponseEntity<ResponseObject> updateReceiveCar(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody ReceiveCarDTO receiveCarDTO) throws URISyntaxException{
        log.debug("Request to update receiveCar : {}", receiveCarDTO);
        ReceiveCarDTO result = receiveCarService.updateReceiveCar(receiveCarDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query receiveCar successfully",result)
        );
    }

    @DeleteMapping("/receive-car/{id}")
    public ResponseEntity<ResponseObject> deleteReceiveCar(@PathVariable String id) {
        log.debug("Request to delete customer : {}", id);
        receiveCarService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query receiveCar successfully",""));
    }

    @PutMapping("/receive-car-active/{id}")
    public ResponseEntity<ResponseObject> activeReceiveCar(@PathVariable String id) {
        log.debug("Request to delete customer : {}", id);
        receiveCarService.active(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okSend","Query receiveCar successfully",""));
    }

//    @PostMapping("/List-customer")
//    public List<CustomerDTO> getAllCustomerToSelect()
//            throws URISyntaxException {
//        return customerService.getAll();
//    }

}
