package com.moaaz_gaballah.models.internal.cable;

import java.util.Date;

public interface CableData {

    Long getId();
    String getCableName();
    String getCableType();
    Float getThickness();
    Float getLength();
    Double getPrice();
    CableStatus getStatus();
    Date getActivatedDate();
    byte [] getImage();

    default String getCableStatusValue(){
        if (this.getStatus() != null)return this.getStatus().getValue();
        return null;
    }
}
