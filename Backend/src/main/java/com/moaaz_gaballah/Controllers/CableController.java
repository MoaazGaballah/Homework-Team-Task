package com.moaaz_gaballah.Controllers;

import com.moaaz_gaballah.Models.CableDTO;
import com.moaaz_gaballah.Models.CableData;
import com.moaaz_gaballah.Services.CableService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("cable")
@RequiredArgsConstructor
@Log4j2
@Api("This controller is doing CRUD process for cables")
@CrossOrigin
public class CableController {

    private final CableService cableService;

    @GetMapping("/get-all")
    public Page<CableData> getAllCables(
            @RequestParam(value = "query", required = false) String query,
            Pageable pageable){
        log.info("<=================== Fetching all cables form database!! ===================>");
        return this.cableService.getAllCables(query, pageable);
    }

    @GetMapping("/get-cable")
    public ResponseEntity<CableDTO> getCableById(@RequestParam("cableId") Integer cableId){
        log.info("<=================== Fetching cable with id: "+ cableId +" form database!! ===================>");
        return new ResponseEntity<>(
                this.cableService.getCable(cableId),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity saveCable(@RequestBody CableDTO cableDTO){
        log.info("<=================== Saving new cable to database!! ===================>");
        if (cableDTO != null)
            return new ResponseEntity<>(
                    this.cableService.saveOrUpdate(cableDTO),
                    HttpStatus.OK
            );
        else return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public Page<CableData> deleteCable(@RequestParam("cableId") Integer cableId,
                                       Pageable pageable){
        log.info("<=================== Deleting cable with id: " + cableId + " form database!! ===================>");
        this.cableService.deleteCable(cableId);
        log.info("<=================== Fetching all cables form database!! ===================>");
        return this.cableService.getAllCables(null, pageable);
    }
}
