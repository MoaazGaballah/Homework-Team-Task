package com.moaaz_gaballah.controllers;

import com.moaaz_gaballah.models.internal.cable.CableDTO;
import com.moaaz_gaballah.models.internal.cable.CableData;
import com.moaaz_gaballah.services.CableService;
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

    /**
     *
     * @param query
     * @param pageable
     * @return page of CableData, that is all cables filtered with query parameter
     */
//    @GetMapping("/get-all")
//    public Page<CableData> getAllCables(
//            @RequestParam(value = "query", required = false) String query,
//            Pageable pageable){
//        log.info("<=================== Fetching all cables form database!! ===================>");
//        return this.cableService.getAllCables(query, pageable);
//    }

    /**
     *
     * @param cableId
     * @return a ResponseEntity of CableDTO which includes the cable details with provided id
     */
    @GetMapping("/get-cable")
    public ResponseEntity<CableDTO> getCableById(@RequestParam("cableId") Long cableId){
        log.info("<=================== Fetching cable with id: "+ cableId +" form database!! ===================>");
        return new ResponseEntity<>(
                this.cableService.getCable(cableId),
                HttpStatus.OK
        );
    }

    /**
     *
     * @param cableDTO
     * @return a ResponseEntity which includes an 'OK' status for Http reques
     */
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

    /**
     *
     * @param cableId
     * @param pageable
     * @return page of CableData that is all cables without deleted cable
     */
//    @DeleteMapping()
//    public Page<CableData> deleteCable(@RequestParam("cableId") Long cableId,
//                                       Pageable pageable){
//        log.info("<=================== Deleting cable with id: " + cableId + " form database!! ===================>");
//        this.cableService.deleteCable(cableId);
//        log.info("<=================== Fetching all cables form database!! ===================>");
//        return this.cableService.getAllCables(null, pageable);
//    }
}
