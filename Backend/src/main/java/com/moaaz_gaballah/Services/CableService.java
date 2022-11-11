package com.moaaz_gaballah.Services;

import com.moaaz_gaballah.Models.Cable;
import com.moaaz_gaballah.Models.CableDTO;
import com.moaaz_gaballah.Models.CableData;
import com.moaaz_gaballah.Models.CableMapper;
import com.moaaz_gaballah.Repositories.CableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CableService {

    private final CableRepository cableRepository;

    public Page<CableData> getAllCables(String query, Pageable pageable){
        return this.cableRepository.searchCables(query, pageable);
    }

    public CableDTO getCable(Integer id){
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

    public void deleteCable(Integer id){
        this.cableRepository.deleteById(id);
    }
}
