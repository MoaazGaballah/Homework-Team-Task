package com.moaaz_gaballah.repositories;

import com.moaaz_gaballah.models.internal.cable.AbstractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface AbstractRepository <T, O> extends JpaRepository<T, O>{

//    @Query(value = "select *\n" +
//            "from cables\n" +
//            "where id >= :start\n" +
//            "  and id < :end", nativeQuery = true)
//    List<AbstractModel> getLimitedRecords(Long start, Long end);
}
