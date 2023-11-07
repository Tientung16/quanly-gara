package com.example.demo5.Controller;

import com.example.demo5.DTO.RepairCarDTO;
import com.example.demo5.DTO.RepairCarDetailDTO;
import com.example.demo5.Service.RepairCarDetailService;
import com.example.demo5.Service.RepairCarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class RepairCarDetailController {

    private final Logger log = LoggerFactory.getLogger(RepairCarDetailController.class);

//    private final RepairCarService repairCarService;

    private final RepairCarDetailService repairCarDetailService;

    public RepairCarDetailController (RepairCarDetailService repairCarDetailService) {
//        this.repairCarService = repairCarService;
        this.repairCarDetailService = repairCarDetailService;
    }


    @GetMapping("/repair-car-detail-by-idcustomer/{idCustomer}")
    public ResponseEntity<List<RepairCarDetailDTO>> getAllReceiveCar(
            @org.springdoc.api.annotations.ParameterObject @PathVariable String idCustomer) throws URISyntaxException {
        log.debug("REST request to get a page of repairCar");
        List<RepairCarDetailDTO> list = repairCarDetailService.findAllByIdCustomer(idCustomer);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(list);
    }

    @GetMapping("/repair-car-detail-All")
    public ResponseEntity<List<RepairCarDetailDTO>> getAllReceiveCar() throws URISyntaxException {
        log.debug("REST request to get a page of repairCar");
        List<RepairCarDetailDTO> list = repairCarDetailService.findAll();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(list);
    }
}
