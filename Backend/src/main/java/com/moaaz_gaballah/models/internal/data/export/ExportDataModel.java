package com.moaaz_gaballah.models.internal.data.export;

import com.moaaz_gaballah.models.internal.data.Delimiter;
import com.moaaz_gaballah.models.internal.data.DateTimeFormat;
import com.moaaz_gaballah.models.internal.data.IdRange;
import com.moaaz_gaballah.models.internal.data.impor.FileType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExportDataModel {

    private String javaClass;
    private IdRange idRange;
    private FileType fileType;
    private Boolean databaseFieldNamesToSecondRow;
    private Delimiter delimiter;
    private DateTimeFormat dateTimeFormat;

}
