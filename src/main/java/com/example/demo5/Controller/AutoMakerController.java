package com.example.demo5.Controller;

import com.example.demo5.DTO.AccessoryDTO;
import com.example.demo5.DTO.AutoMakerDTO;
import com.example.demo5.DTO.SupplierDTO;
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
public class AutoMakerController {
    private final Logger log = LoggerFactory.getLogger(AutoMakerController.class);

    private final AutoMakerService autoMakerService;

    public AutoMakerController (AutoMakerService autoMakerService) {
        this.autoMakerService = autoMakerService;
    }

    @PostMapping("/automaker")
    public ResponseEntity<ResponseObject> createAutomaker(
            @Valid @RequestBody AutoMakerDTO autoMakerDTO) throws URISyntaxException {
        log.debug("REST request to save automaker : {}", autoMakerDTO);
        AutoMakerDTO result = autoMakerService.saveAutoMaker(autoMakerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okCreate","Query automaker successfully",result)
        );
    }
    @PostMapping("/automaker-getAll")
    public ResponseEntity<List<AutoMakerDTO>> getAllAutomaker(
            @org.springdoc.api.annotations.ParameterObject Pageable pageable, String name) throws URISyntaxException{
        log.debug("REST request to get a page of Automaker");
        Page<AutoMakerDTO> page = autoMakerService.findAll(pageable, name);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/automaker/{id}")
    public ResponseEntity<AutoMakerDTO> findById(@PathVariable String id){
        Optional<AutoMakerDTO> autoMakerDTO = autoMakerService.findById(id);
        return ResponseUtil.wrapOrNotFound(autoMakerDTO);
    }

    @PutMapping("/automaker/{id}")
    public ResponseEntity<ResponseObject> updateAutomaker(
            @PathVariable(value = "id", required = false) final String id,
            @Valid @RequestBody AutoMakerDTO autoMakerDTO) throws URISyntaxException{
        log.debug("Request to update automaker : {}", autoMakerDTO);
        AutoMakerDTO result = autoMakerService.updateAutoMaker(autoMakerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("okUpdate","Query automaker successfully",result)
        );
    }

    @DeleteMapping("/automaker/{id}")
    public ResponseEntity<ResponseObject> deleteAutomaker(@PathVariable String id) {
        log.debug("Request to delete automaker : {}", id);
        autoMakerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("okDelete","Query automaker successfully",""));
    }

    @GetMapping("/automaker-page-number")
    public int findPageNumberAutomaker(){
        List<AutoMakerDTO> autoMakerDTOS = autoMakerService.findPageNumberAutomaker();
        return autoMakerDTOS.size();
    }

    @PostMapping("/List-automaker")
    public List<AutoMakerDTO> getAllAutoMakerToSelect()
            throws URISyntaxException {
        return autoMakerService.getAll();
    }
}
