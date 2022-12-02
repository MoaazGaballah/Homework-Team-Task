package com.moaaz_gaballah.models.external;

import com.moaaz_gaballah.models.internal.cable.AbstractModel;
import com.moaaz_gaballah.models.internal.cable.CableStatus;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cables")
@ApiModel("Cable model documentation")
public class Cable extends AbstractModel{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Integer id;

    @Column(name = "cable_name")
    private String cableName;


    @Column(name = "cable_type")
    private String cableType;

    @Column(name = "thickness")
    private Float thickness;

    @Column(name = "length")
    private Float length;

    @Column(name = "price")
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CableStatus status;

    @Column(name = "activated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedDate;

    @Lob
    @Column(name="image")
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;
}
