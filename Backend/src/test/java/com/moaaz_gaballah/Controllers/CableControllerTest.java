package com.moaaz_gaballah.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moaaz_gaballah.Models.Cable;
import com.moaaz_gaballah.Models.CableDTO;
import com.moaaz_gaballah.Services.CableService;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

@ContextConfiguration(classes = {CableController.class})
@ExtendWith(SpringExtension.class)
class CableControllerTest {
    @Autowired
    private CableController cableController;

    @MockBean
    private CableService cableService;

    /**
     * Method under test: {@link CableController#deleteCable(Integer)}
     */
    @Test
    void testDeleteCable() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cable");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("cableId", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cableController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

//    /**
//     * Method under test: {@link CableController#getAllCables()}
//     */
//    @Test
//    void testGetAllCables() throws Exception {
//        when(cableService.getAllCables()).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cable/get-all");
//        MockMvcBuilders.standaloneSetup(cableController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    /**
//     * Method under test: {@link CableController#getAllCables()}
//     */
//    @Test
//    void testGetAllCables2() throws Exception {
//        when(cableService.getAllCables()).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cable/get-all");
//        getResult.characterEncoding("Encoding");
//        MockMvcBuilders.standaloneSetup(cableController)
//                .build()
//                .perform(getResult)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }

    /**
     * Method under test: {@link CableController#getCableById(Integer)}
     */
    @Test
    void testGetCableById() throws Exception {
        when(cableService.getCable((Integer) any())).thenReturn(new CableDTO());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cable/get-cable");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("cableId", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cableController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Method under test: {@link CableController#saveCable(CableDTO)}
     */
    @Test
    void testSaveCable() throws Exception {
//        // TODO: Complete this test.
//        //   Reason: R008 Failed to instantiate class under test.
//        //   Diffblue Cover was unable to construct an instance of CableController.
//        //   Ensure there is a package-visible constructor or factory method that does not
//        //   throw for the class under test.
//        //   If such a method is already present but Diffblue Cover does not find it, it can
//        //   be specified using custom rules for inputs:
//        //   https://docs.diffblue.com/knowledge-base/cli/custom-inputs/
//        //   This can happen because the factory method takes arguments, throws, returns null
//        //   or returns a subtype.
//        //   See https://diff.blue/R008 for further troubleshooting of this issue.
//
//        // Arrange
//        // TODO: Populate arranged inputs
//        Object[] uriVariables = new Object[]{};
//        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/cable/save", uriVariables)
//                .contentType(MediaType.APPLICATION_JSON);
//        CableDTO value = new CableDTO();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(value));
//        Object[] controllers = new Object[]{cableController};
//        MockMvc buildResult = MockMvcBuilders.standaloneSetup(controllers).build();
//
//        // Act
//        ResultActions actualPerformResult = buildResult.perform(requestBuilder);
//
//        // Assert
//        // TODO: Add assertions on result

//        when(this.cableService.getAllCables()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
//
//        Cable cable = new Cable();
//        cable.setId(123);
//        cable.setCableName("Cable Name");
//        cable.setCableType("Cable Type");
//        cable.setLength(1.23f);
//        cable.setPrice(1.23);
//        cable.setThickness(1.23f);
//        cable.setActivatedDate(new Date());
//
//        String content = (new ObjectMapper()).writeValueAsString(cable);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/save/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cableController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(404));
    }
}

