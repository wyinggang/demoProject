package com.excel;

import lombok.Data;

@Data
public class ExcelErrorDTO {
    private String headName;
    private String value;
    private String errMsg;
    private Integer columnIndex;
    private Integer rowIndex;
}
