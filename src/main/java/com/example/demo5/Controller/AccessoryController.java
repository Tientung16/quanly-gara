package com.example.demo5.Controller;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.AutoMakerDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Service.AccessoryService;
import com.example.demo5.Service.AutoMakerService;
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
public class AccessoryController {
    private final Logger log = LoggerFactory.getLogger(AccessoryController.class);
    private final AccessoryService accessoryService;

    public AccessoryController (AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/accessory")
    public ResponseEntity<ResponseObject> createAccessory(
            @Valid @RequestBody AccessoryDTO accessoryDTO) throws URISyntaxException {
        log.debug("REST request to save accessory : {}", accessoryDTO);
        AccessoryDTO result = accessoryService.saveAccessory(accessoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query accessory successfully",result)
        );
    }
    @PostMapping("/accessory-getAll")
    public ResponseEntity<List<AccessoryDTO>> getAllAccessory(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String name) throws URISyntaxException{
        log.debug("REST request to get a page of accessory");
        Page<AccessoryDTO> page = accessoryService.findAll(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/accessory/{id}")
    public ResponseEntity<AccessoryDTO> findById(@PathVariable String id){
        Optional<AccessoryDTO> accessoryDTO = accessoryService.findById(id);
        return ResponseUtil.wrapOrNotFound(accessoryDTO);
    }

    @PutMapping("/accessory/{id}")
    public ResponseEntity<ResponseObject> updateAccessory(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody AccessoryDTO accessoryDTO) throws URISyntaxException{
        log.debug("Request to update accessory : {}", accessoryDTO);
        AccessoryDTO result = accessoryService.updateAccessory(accessoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query accessory successfully",result)
        );
    }

    @DeleteMapping("/accessory/{id}")
    public ResponseEntity<ResponseObject> deleteAccessory(@PathVariable String id) {
        log.debug("Request to delete accessory : {}", id);
        accessoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query accessory successfully",""));
    }

    @PostMapping("/List-accessory")
    public List<AccessoryDTO> getAllAccessoryToSelect()
            throws URISyntaxException {
        return accessoryService.getAll();
    }

    @GetMapping("/accessory-page-number")
    public int findPageNumberAccessory(){
        List<AccessoryDTO> accessoryDTOS = accessoryService.findPageNumberAccessory();
        return accessoryDTOS.size();
    }
}
