package com.moaaz_gaballah.controllers;

import com.moaaz_gaballah.models.internal.data.export.ExportDataModel;
import com.moaaz_gaballah.services.DataServise;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("data")
@RequiredArgsConstructor
@Log4j2
@Api("This controller is doing CRUD process for cables")
@CrossOrigin
public class DataController {

    private final DataServise dataServise;


    @PostMapping("/export")
    public ResponseEntity exportCSV(HttpServletResponse httpServletResponse,
                                    @RequestBody ExportDataModel exportDataModel) throws ClassNotFoundException, IllegalAccessException {
        return this.dataServise.export(exportDataModel);
    }

    @GetMapping("/model-classes")
    public ResponseEntity<Set<Class>> getAllModelClasses(){
        log.info("<=================== Reading all classes in the same package ===================>");
        Reflections reflections = new Reflections("com.moaaz_gaballah.models.external", new SubTypesScanner(false));

        Set<Class> allClasses =
                reflections.getSubTypesOf(Object.class)
                        .stream()
                        .collect(Collectors.toSet());

        return new ResponseEntity<>(
                allClasses,
                HttpStatus.OK
        );
    }
}
