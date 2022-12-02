package com.moaaz_gaballah.models.internal.cable;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class CableDTO {
    private Integer id;
    private String cableName;
    private String cableType;
    private Float thickness;
    private Float length;
    private Double price;
    private CableStatus status;
    private Date activatedDate;
    private byte[] image;

    public void setStatusMap(Map<String, String> statusMap) {
        if (statusMap == null)
            this.status = null;
        else
            this.status = status.fromValue(statusMap.get("value"));
    }

    public Map<String, String> getStatusMap(){
        if (this.status == null) return null;
        Map<String, String> map = new HashMap<>();
        map.put("name", this.status.name());
        map.put("value", this.status.getValue());
        return map;
    }
}
