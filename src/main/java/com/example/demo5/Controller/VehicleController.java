package com.example.demo5.Controller;

import com.example.demo5.DTO.VehicleDTO;
import com.example.demo5.Service.VehicleService;
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
public class VehicleController {
    private final Logger log = LoggerFactory.getLogger(VehicleController.class);

    private final VehicleService vehicleService;

    public VehicleController (VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle")
    public ResponseEntity<ResponseObject> createVehicle(
            @Valid @RequestBody VehicleDTO vehicleDTO) throws URISyntaxException {
        log.debug("REST request to save vehicle : {}", vehicleDTO);
        VehicleDTO result = vehicleService.saveVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query vehicle successfully",result)
        );
    }

    @PostMapping("/vehicle-getAll")
    public ResponseEntity<List<VehicleDTO>> getAllVehicle(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String licensePlates) throws URISyntaxException{
        log.debug("REST request to get a page of vehicle");
        Page<VehicleDTO> page = vehicleService.findAll(pageable, licensePlates);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/vehicle/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable String id){
        Optional<VehicleDTO> vehicleDTO = vehicleService.findById(id);
        return ResponseUtil.wrapOrNotFound(vehicleDTO);
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<ResponseObject> updateVehicle(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody VehicleDTO vehicleDTO) throws URISyntaxException{
        log.debug("Request to update vehicle : {}", vehicleDTO);
        VehicleDTO result = vehicleService.updateVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query vehicle successfully",result)
        );
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<ResponseObject> deleteVehicle(@PathVariable String id) {
        log.debug("Request to delete vehicle : {}", id);
        vehicleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query vehicle successfully",""));
    }
}
