package com.moaaz_gaballah.models.internal.cable;

import java.util.Objects;

public enum CableStatus implements EnumInterface{

    NEW("Sıfır"),
    USED("2. el"),
    FINISHED("Tükendi");

    private String value;

    CableStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CableStatus fromValue(String v) {
        for (CableStatus o : CableStatus.values()) {
            if (Objects.equals(o.value, v)) {
                return o;
            }
        }
        return null;
    }
}
