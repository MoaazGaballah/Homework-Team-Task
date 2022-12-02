package com.moaaz_gaballah.models.internal.cable;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@Audited
public class AbstractModel implements Serializable {

    @Column(name = "Create_Date_Time", updatable =false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDatetime;

    @Column(name = "Create_User")
    @CreatedBy
    private String createUser;

    @Column(name = "Update_Date_time")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDatetime;

    @Column(name = "Update_User")
    @LastModifiedBy
    private String updateUser;

    @Column(name = "isDeleted")
    private Boolean isDeleted= false;


    @Column(name = "updateIpAddress")
    private String updateIpAddress;

    @Column(name = "updateCustomer")
    private Integer updateCustomer;

    @Column(name = "updateApplication")
    private String updateApplication;
}
