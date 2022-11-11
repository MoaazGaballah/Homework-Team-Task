package com.moaaz_gaballah.Services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moaaz_gaballah.Models.Cable;
import com.moaaz_gaballah.Models.CableDTO;
import com.moaaz_gaballah.Models.CableMapper;
import com.moaaz_gaballah.Repositories.CableRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CableService.class})
@ExtendWith(SpringExtension.class)
class CableServiceTest {
    @MockBean
    private CableRepository cableRepository;

    @Autowired
    private CableService cableService;

//    /**
//     * Method under test: {@link CableService#getAllCables()}
//     */
//    @Test
//    void testGetAllCables() {
//        when(cableRepository.findAll()).thenReturn(new ArrayList<>());
//        assertTrue(cableService.getAllCables().isEmpty());
//        verify(cableRepository).findAll();
//    }

    /**
     * Method under test: {@link CableService#getCable(Integer)}
     */
    @Test
    void testGetCable() {
        // TODO: Complete this test.
        //   Reason: R011 Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access 'sun.misc'.
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        Integer id = null;

        // Act
        CableDTO actualCable = this.cableService.getCable(id);

        // Assert
        // TODO: Add assertions on result
        assertTrue(actualCable == null);

        when(cableRepository.findById((Integer) any())).thenReturn(Optional.empty());


        Cable cable = new Cable();
        cable.setId(123);
        cable.setCableName("Cable Name");
        cable.setCableType("Cable Type");
        cable.setLength(1.23f);
        cable.setPrice(1.23);
        cable.setThickness(1.23f);
        cable.setActivatedDate(new Date());

        cableService.saveOrUpdate(CableMapper.INSTANCE.entityToDTO(cable));

        CableDTO secondActualCable = this.cableService.getCable(123);

        assertTrue(secondActualCable == null);
    }

//    /**
//     * Method under test: {@link CableService#saveOrUpdate(CableDTO)}
//     */
//    @Test
//    void testSaveOrUpdate() {
////        // TODO: Complete this test.
////        //   Reason: R011 Sandboxing policy violation.
////        //   Diffblue Cover ran code in your project that tried
////        //     to access 'sun.misc'.
////        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
////        //   your code from damaging your system environment.
////        //   See https://diff.blue/R011 to resolve this issue.
////
////        // Arrange
////        // TODO: Populate arranged inputs
////        CableDTO cableDTO = null;
////
////        // Act
////        CableDTO actualSaveOrUpdateResult = this.cableService.saveOrUpdate(cableDTO);
////
////        // Assert
////        // TODO: Add assertions on result
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
//        // Mapepr implementation is not shown in Mock
//        when(this.cableService.saveOrUpdate((CableDTO) any())).thenReturn(new CableDTO());
//
////        verify(this.cableRepository.save((Cable) any()));
//        assertTrue(this.cableService.getAllCables().isEmpty());
//    }

    /**
     * Method under test: {@link CableService#deleteCable(Integer)}
     */
    @Test
    void testDeleteCable() {
        doNothing().when(cableRepository).deleteById((Integer) any());
        cableService.deleteCable(1);
        verify(cableRepository).deleteById((Integer) any());
    }
}

