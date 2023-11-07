package com.example.demo5.Controller;

import com.example.demo5.DTO.ReceiveCarDTO;
import com.example.demo5.DTO.RepairCarDTO;
import com.example.demo5.DTO.RepairCarDetailDTO;
import com.example.demo5.Service.ReceiveCarService;
import com.example.demo5.Service.RepairCarDetailService;
import com.example.demo5.Service.RepairCarService;
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
public class RepairCarController {
    private final Logger log = LoggerFactory.getLogger(RepairCarController.class);

    private final RepairCarService repairCarService;

    private final RepairCarDetailService repairCarDetailService;

    public RepairCarController (RepairCarService repairCarService,RepairCarDetailService repairCarDetailService) {
        this.repairCarService = repairCarService;
        this.repairCarDetailService = repairCarDetailService;
    }

    @PostMapping("/repair-car")
    public ResponseEntity<ResponseObject> createReceiveCar(
            @Valid @RequestBody RepairCarDTO repairCarDTO) throws URISyntaxException {
        log.debug("REST request to save repairCar : {}", repairCarDTO);
        RepairCarDTO result = repairCarService.saveRepairCar(repairCarDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query receiveCar successfully",result)
        );
    }

    @GetMapping("/repair-car-getAll")
    public ResponseEntity<List<RepairCarDTO>> getAllReceiveCar(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable) throws URISyntaxException{
        log.debug("REST request to get a page of repairCar");
        List<RepairCarDTO> list = repairCarService.findAll();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(list);
    }



    @GetMapping("/repair-car/{id}")
    public ResponseEntity<RepairCarDTO> findById(@PathVariable String id){
        Optional<RepairCarDTO> repairCarDTO = repairCarService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("okDetail","Query customer successfully",customerDTO));
        return ResponseUtil.wrapOrNotFound(repairCarDTO);
    }

    @PutMapping("/repair-car/{id}")
    public ResponseEntity<ResponseObject> updateReceiveCar(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody RepairCarDTO repairCarDTO) throws URISyntaxException{
        log.debug("Request to update repairCar : {}", repairCarDTO);
        RepairCarDTO result = repairCarService.updateRepairCarDTO(id,repairCarDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query receiveCar successfully",result)
        );
    }

    @DeleteMapping("/repair-car/{id}")
    public ResponseEntity<ResponseObject> deleteReceiveCar(@PathVariable String id) {
        log.debug("Request to delete repairCar : {}", id);
        repairCarService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query receiveCar successfully",""));
    }

    @PostMapping("/repair-car-detail-getAll/{id}")
    public ResponseEntity<List<RepairCarDetailDTO>> getAllReceiveCarDetail(
            @org.springdoc.api.annotations.ParameterObject @PathVariable String id) throws URISyntaxException{
        log.debug("REST request to get a page of repairCar");
        List<RepairCarDetailDTO> list = repairCarDetailService.findAllByRepairId(id);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(list);
    }

//    @PutMapping("/repair-car-active/{id}")
//    public ResponseEntity<ResponseObject> activeReceiveCar(@PathVariable String id) {
//        log.debug("Request to delete customer : {}", id);
//        repairCarService.active(id);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ResponseObject("okSend","Query receiveCar successfully",""));
//    }
}
