package com.excel;


import lombok.Data;
import java.lang.reflect.Field;

@Data
public class ExcelCellBo {
    private Field field;
    private String fieldName;
    private String headName;
    private Integer columnIndex;
    private Integer rowIndex;
}
