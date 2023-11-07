package com.example.demo5.Controller;

import com.example.demo5.DTO.CustomerDTO;
import com.example.demo5.DTO.SupplierDTO;
import com.example.demo5.Service.SupplierService;
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
public class SupplierController {

    private final Logger log = LoggerFactory.getLogger(SupplierController.class);

    private final SupplierService supplierService;

    public SupplierController (SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/supplier")
    public ResponseEntity<ResponseObject> createSupplier(
            @Valid @RequestBody SupplierDTO supplierDTO) throws URISyntaxException {
        log.debug("REST request to save supplier : {}", supplierDTO);
        SupplierDTO result = supplierService.saveSupplier(supplierDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query supplier successfully",result)
        );
    }

    @PostMapping("/supplier-getAll")
    public ResponseEntity<List<SupplierDTO>> getAllSupplier(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String name) throws URISyntaxException{
        log.debug("REST request to get a page of supplier");
        Page<SupplierDTO> page = supplierService.findAll(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<SupplierDTO> findById(@PathVariable String id){
        Optional<SupplierDTO> supplierDTO = supplierService.findById(id);
        return ResponseUtil.wrapOrNotFound(supplierDTO);
    }

    @PutMapping("/supplier/{id}")
    public ResponseEntity<ResponseObject> updateSupplier(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody SupplierDTO supplierDTO) throws URISyntaxException{
        log.debug("Request to update supplier : {}", supplierDTO);
        SupplierDTO result = supplierService.updateSupplier(supplierDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query supplier successfully",result)
        );
    }

    @DeleteMapping("/supplier/{id}")
    public ResponseEntity<ResponseObject> deleteSupplier(@PathVariable String id) {
        log.debug("Request to delete supplier : {}", id);
        supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query supplier successfully",""));
    }

    @PostMapping("/List-supplier")
    public List<SupplierDTO> getAllSupplierToSelect()
            throws URISyntaxException {
        return supplierService.getAll();
    }

    @GetMapping("/supplier-page-number")
    public int findPageNumberSupplier(){
        List<SupplierDTO> supplierDTOS = supplierService.findPageNumberSupplier();
        return supplierDTOS.size();
    }
}
