package com.moaaz_gaballah.controllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moaaz_gaballah.models.internal.cable.CableDTO;
import com.moaaz_gaballah.models.internal.cable.CableData;
import com.moaaz_gaballah.models.internal.cable.CableStatus;
import com.moaaz_gaballah.repositories.CableRepository;
import com.moaaz_gaballah.services.CableService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CableController.class})
@ExtendWith(SpringExtension.class)
class CableControllerTest {
    @Autowired
    private CableController cableController;

    @MockBean
    private CableService cableService;

    /**
     * Method under test: {@link CableController#getAllCables(String, Pageable)}
     */
    @Test
    void testGetAllCables() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrangement/act section of your test took more than
        //   20 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        CableRepository cableRepository = mock(CableRepository.class);
        PageImpl<CableData> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cableRepository.searchCables((String) any(), (Pageable) any())).thenReturn(pageImpl);
        Page<CableData> actualAllCables = (new CableController(new CableService(cableRepository))).getAllCables("foo",
                null);
        assertSame(pageImpl, actualAllCables);
        assertTrue(actualAllCables.toList().isEmpty());
        verify(cableRepository).searchCables((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link CableController#getAllCables(String, Pageable)}
     */
    @Test
    void testGetAllCables2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrangement/act section of your test took more than
        //   20 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        CableService cableService = mock(CableService.class);
        PageImpl<CableData> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cableService.getAllCables((String) any(), (Pageable) any())).thenReturn(pageImpl);
        Page<CableData> actualAllCables = (new CableController(cableService)).getAllCables("foo", null);
        assertSame(pageImpl, actualAllCables);
        assertTrue(actualAllCables.toList().isEmpty());
        verify(cableService).getAllCables((String) any(), (Pageable) any());
    }

    /**
     * Method under test: {@link CableController#deleteCable(Long, Pageable)}
     */
    @Test
    void testDeleteCable() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrangement/act section of your test took more than
        //   20 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        CableRepository cableRepository = mock(CableRepository.class);
        PageImpl<CableData> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cableRepository.searchCables((String) any(), (Pageable) any())).thenReturn(pageImpl);
        doNothing().when(cableRepository).deleteById((Long) any());
        Page<CableData> actualDeleteCableResult = (new CableController(new CableService(cableRepository))).deleteCable(1L,
                null);
        assertSame(pageImpl, actualDeleteCableResult);
        assertTrue(actualDeleteCableResult.toList().isEmpty());
        verify(cableRepository).searchCables((String) any(), (Pageable) any());
        verify(cableRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CableController#deleteCable(Long, Pageable)}
     */
    @Test
    void testDeleteCable2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R010 Timeout.
        //   Creating the arrange/act section of your test took more than
        //   20 seconds. This often happens because Diffblue Cover ran code in your
        //   project which requests user input (System.in), blocks on a lock, or runs into
        //   an infinite or very long loop.
        //   See https://diff.blue/R010 to resolve this issue.

        CableService cableService = mock(CableService.class);
        PageImpl<CableData> pageImpl = new PageImpl<>(new ArrayList<>());
        when(cableService.getAllCables((String) any(), (Pageable) any())).thenReturn(pageImpl);
        doNothing().when(cableService).deleteCable((Long) any());
        Page<CableData> actualDeleteCableResult = (new CableController(cableService)).deleteCable(1L, null);
        assertSame(pageImpl, actualDeleteCableResult);
        assertTrue(actualDeleteCableResult.toList().isEmpty());
        verify(cableService).getAllCables((String) any(), (Pageable) any());
        verify(cableService).deleteCable((Long) any());
    }

    /**
     * Method under test: {@link CableController#getCableById(Long)}
     */
    @Test
    void testGetCableById() throws Exception {
        CableDTO cableDTO = new CableDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        cableDTO.setActivatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        cableDTO.setCableName("Cable Name");
        cableDTO.setCableType("Cable Type");
        cableDTO.setId(1);
        cableDTO.setImage("AAAAAAAA".getBytes("UTF-8"));
        cableDTO.setLength(10.0f);
        cableDTO.setPrice(10.0d);
        cableDTO.setStatus(CableStatus.NEW);
        cableDTO.setStatusMap(new HashMap<>());
        cableDTO.setThickness(10.0f);
        when(cableService.getCable((Long) any())).thenReturn(cableDTO);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cable/get-cable");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("cableId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(cableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"cableName\":\"Cable Name\",\"cableType\":\"Cable Type\",\"thickness\":10.0,\"length\":10.0,\"price\":10.0"
                                        + ",\"status\":null,\"activatedDate\":0,\"image\":\"QUFBQUFBQUE=\",\"statusMap\":null}"));
    }

    /**
     * Method under test: {@link CableController#saveCable(CableDTO)}
     */
    @Test
    void testSaveCable() throws Exception {
        CableDTO cableDTO = new CableDTO();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        cableDTO.setActivatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        cableDTO.setCableName("Cable Name");
        cableDTO.setCableType("Cable Type");
        cableDTO.setId(1);
        cableDTO.setImage("AAAAAAAA".getBytes("UTF-8"));
        cableDTO.setLength(10.0f);
        cableDTO.setPrice(10.0d);
        cableDTO.setStatus(CableStatus.NEW);
        cableDTO.setStatusMap(new HashMap<>());
        cableDTO.setThickness(10.0f);
        when(cableService.saveOrUpdate((CableDTO) any())).thenReturn(cableDTO);

        CableDTO cableDTO1 = new CableDTO();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        cableDTO1.setActivatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        cableDTO1.setCableName("Cable Name");
        cableDTO1.setCableType("Cable Type");
        cableDTO1.setId(1);
        cableDTO1.setImage("AAAAAAAA".getBytes("UTF-8"));
        cableDTO1.setLength(10.0f);
        cableDTO1.setPrice(10.0d);
        cableDTO1.setStatus(CableStatus.NEW);
        cableDTO1.setStatusMap(new HashMap<>());
        cableDTO1.setThickness(10.0f);
        String content = (new ObjectMapper()).writeValueAsString(cableDTO1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cable/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cableController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"cableName\":\"Cable Name\",\"cableType\":\"Cable Type\",\"thickness\":10.0,\"length\":10.0,\"price\":10.0"
                                        + ",\"status\":null,\"activatedDate\":0,\"image\":\"QUFBQUFBQUE=\",\"statusMap\":null}"));
    }
}

