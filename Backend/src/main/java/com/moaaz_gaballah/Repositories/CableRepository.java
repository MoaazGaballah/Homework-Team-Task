package com.moaaz_gaballah.Repositories;

import com.moaaz_gaballah.Models.Cable;
import com.moaaz_gaballah.Models.CableData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CableRepository extends JpaRepository<Cable, Integer> {

    @Query(value = "SELECT cable.id as id,\n" +
            "       cable.activated_date as activatedDate,\n" +
            "       cable.cable_name cableName,\n" +
            "       cable.cable_type cableType,\n" +
            "       cable.length length,\n" +
            "       cable.price price,\n" +
            "       cable.thickness thickness,\n" +
            "       cable.status status,\n" +
            "       cable.image image\n" +
            "FROM cables cable\n" +
            "WHERE ((CAST(:query AS text) IS NULL OR CAST(:query AS text) = '')\n" +
            "    OR lower(cable.cable_name) like concat('%', lower(CAST(:query AS text)), '%')\n" +
            "    OR LOWER(cable.cable_type) LIKE CONCAT('%', LOWER(CAST(:query AS text)), '%')\n" +
            "    OR CAST(cable.length AS text) LIKE CONCAT('%', LOWER(CAST(:query AS text)), '%')\n" +
            "    OR CAST(cable.price AS text) LIKE CONCAT('%', LOWER(CAST(:query AS text)), '%')\n" +
            "    OR CAST(cable.thickness AS text) LIKE CONCAT('%', LOWER(CAST(:query AS text)), '%'))", nativeQuery = true)
    Page<CableData> searchCables(String query, Pageable pageable);

}
