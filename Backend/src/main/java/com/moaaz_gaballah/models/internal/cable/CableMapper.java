package com.moaaz_gaballah.models.internal.cable;

import com.moaaz_gaballah.models.external.Cable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CableMapper {

    CableMapper INSTANCE = Mappers.getMapper(CableMapper.class);

    CableDTO entityToDTO(Cable cable);

    Cable DTOtoEntity(CableDTO cableDTO);
}
