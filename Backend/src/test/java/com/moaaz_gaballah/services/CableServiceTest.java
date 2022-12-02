package com.moaaz_gaballah.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moaaz_gaballah.models.external.Cable;
import com.moaaz_gaballah.models.internal.cable.CableDTO;
import com.moaaz_gaballah.models.internal.cable.CableData;
import com.moaaz_gaballah.models.internal.cable.CableStatus;
import com.moaaz_gaballah.repositories.CableRepository;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CableService.class})
@ExtendWith(SpringExtension.class)
class CableServiceTest {
    @MockBean
    private CableRepository cableRepository;

    @Autowired
    private CableService cableService;

    /**
     * Method under test: {@link CableService#getAllCables(String, Pageable)}
     */
    @Test
    void testGetAllCables() {
        PageImpl<CableData> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cableRepository.searchCables((String) any(), (Pageable) any())).thenReturn(pageImpl);
        Page<CableData> actualAllCables = cableService.getAllCables("Query", null);
        assertSame(pageImpl, actualAllCables);
        assertTrue(actualAllCables.toList().isEmpty());
        verify(cableRepository).searchCables((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link CableService#getCable(Long)}
     */
    @Test
    void testGetCable() throws UnsupportedEncodingException {
//        Cable cable = new Cable();
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
//        cable.setActivatedDate(fromResult);
//        cable.setCableName("Cable Name");
//        cable.setCableType("Cable Type");
//        cable.setId(1L);
//        cable.setImage("AAAAAAAA".getBytes("UTF-8"));
//        cable.setLength(10.0f);
//        cable.setPrice(10.0d);
//        cable.setStatus(CableStatus.NEW);
//        cable.setThickness(10.0f);
//        Optional<Cable> ofResult = Optional.of(cable);
//        when(cableRepository.findById((Long) any())).thenReturn(ofResult);
//        CableDTO actualCable = cableService.getCable(1L);
//        assertSame(fromResult, actualCable.getActivatedDate());
//        assertEquals(10.0f, actualCable.getThickness().floatValue());
//        assertEquals(CableStatus.NEW, actualCable.getStatus());
//        assertEquals(10.0d, actualCable.getPrice().doubleValue());
//        assertEquals(10.0f, actualCable.getLength().floatValue());
//        assertEquals(8, actualCable.getImage().length);
//        assertEquals(1, actualCable.getId().intValue());
//        assertEquals("Cable Type", actualCable.getCableType());
//        assertEquals("Cable Name", actualCable.getCableName());
//        verify(cableRepository).findById((Long) any());
    }
    /**
     * Method under test: {@link CableService#getCable(Long)}
     */
    @Test
    void testGetCable2() throws UnsupportedEncodingException {
//        Cable cable = new Cable();
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        cable.setActivatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
//        cable.setCableName("Cable Name");
//        cable.setCableType("Cable Type");
//        cable.setId(1L);
//        cable.setImage("AAAAAAAA".getBytes("UTF-8"));
//        cable.setLength(10.0f);
//        cable.setPrice(10.0d);
//        cable.setStatus(CableStatus.NEW);
//        cable.setThickness(10.0f);
//        Optional<Cable> ofResult = Optional.of(cable);
//        when(cableRepository.findById((Long) any())).thenReturn(ofResult);
//        assertNull(cableService.getCable(null));
    }

    /**
     * Method under test: {@link CableService#saveOrUpdate(CableDTO)}
     */
    @Test
    void testSaveOrUpdate() throws UnsupportedEncodingException {
//        Cable cable = new Cable();
//        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
//        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
//        cable.setActivatedDate(fromResult);
//        cable.setCableName("Cable Name");
//        cable.setCableType("Cable Type");
//        cable.setId(1L);
//        cable.setImage("AAAAAAAA".getBytes("UTF-8"));
//        cable.setLength(10.0f);
//        cable.setPrice(10.0d);
//        cable.setStatus(CableStatus.NEW);
//        cable.setThickness(10.0f);
//        when(cableRepository.save((Cable) any())).thenReturn(cable);
//
//        CableDTO cableDTO = new CableDTO();
//        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
//        cableDTO.setActivatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
//        cableDTO.setCableName("Cable Name");
//        cableDTO.setCableType("Cable Type");
//        cableDTO.setId(1);
//        cableDTO.setImage("AAAAAAAA".getBytes("UTF-8"));
//        cableDTO.setLength(10.0f);
//        cableDTO.setPrice(10.0d);
//        cableDTO.setStatus(CableStatus.NEW);
//        cableDTO.setStatusMap(new HashMap<>());
//        cableDTO.setThickness(10.0f);
//        CableDTO actualSaveOrUpdateResult = cableService.saveOrUpdate(cableDTO);
//        assertSame(fromResult, actualSaveOrUpdateResult.getActivatedDate());
//        assertEquals(10.0f, actualSaveOrUpdateResult.getThickness().floatValue());
//        assertEquals(CableStatus.NEW, actualSaveOrUpdateResult.getStatus());
//        assertEquals(10.0d, actualSaveOrUpdateResult.getPrice().doubleValue());
//        assertEquals(10.0f, actualSaveOrUpdateResult.getLength().floatValue());
//        assertEquals(8, actualSaveOrUpdateResult.getImage().length);
//        assertEquals(1, actualSaveOrUpdateResult.getId().intValue());
//        assertEquals("Cable Type", actualSaveOrUpdateResult.getCableType());
//        assertEquals("Cable Name", actualSaveOrUpdateResult.getCableName());
//        verify(cableRepository).save((Cable) any());
    }

    /**
     * Method under test: {@link CableService#deleteCable(Long)}
     */
    @Test
    void testDeleteCable() {
        doNothing().when(cableRepository).deleteById((Long) any());
        cableService.deleteCable(1L);
        verify(cableRepository).deleteById((Long) any());
    }
}

