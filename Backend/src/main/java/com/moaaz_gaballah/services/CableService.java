package com.moaaz_gaballah.services;

import com.moaaz_gaballah.models.external.Cable;
import com.moaaz_gaballah.models.internal.cable.CableDTO;
import com.moaaz_gaballah.models.internal.cable.CableData;
import com.moaaz_gaballah.models.internal.cable.CableMapper;
import com.moaaz_gaballah.repositories.CableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CableService {

    private final CableRepository cableRepository;

//    public Page<CableData> getAllCables(String query, Pageable pageable){
//        return this.cableRepository.searchCables(query, pageable);
//    }

    public CableDTO getCable(Long id){
        if (id != null)
            return CableMapper.INSTANCE.entityToDTO(
                this.cableRepository.findById(id).orElse(null));
        else return null;
    }

    public CableDTO saveOrUpdate(CableDTO cableDTO){
        Cable cable = CableMapper.INSTANCE.DTOtoEntity(cableDTO);
        return CableMapper.INSTANCE.entityToDTO(
                this.cableRepository.save(cable)
        );
    }

    public void deleteCable(Long id){
        this.cableRepository.deleteById(id);
    }
}
