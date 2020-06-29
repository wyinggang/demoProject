package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.read.metadata.holder.ReadHolder;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.alibaba.excel.read.metadata.property.ExcelReadHeadProperty;
import lombok.extern.slf4j.Slf4j;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.groups.Default;
import java.util.*;

@Slf4j
public class ModelExcelListener<E> extends AnalysisEventListener<E> {

    private List<E> dataList = new ArrayList<E>();
    private List<ExcelErrorDTO> errorList = new ArrayList<ExcelErrorDTO>();

    @Override
    public void invoke(E object, AnalysisContext context) {
        Map<String, ExcelCellBo> propertyNameMap = this.getPropertyNameMap(true, context);
        if (this.valid(object,propertyNameMap)){
            dataList.add(object);
            System.out.println(object);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }


    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
            //将错误信息放入错误列表
            ExcelErrorDTO excelErrorDTO = new ExcelErrorDTO();
            excelErrorDTO.setHeadName(excelDataConvertException.getExcelContentProperty().getHead().getFieldName());
            excelErrorDTO.setColumnIndex(excelDataConvertException.getColumnIndex());
            excelErrorDTO.setRowIndex(excelDataConvertException.getRowIndex());
            excelErrorDTO.setErrMsg("第"+excelDataConvertException.getRowIndex()+"行,第"+excelDataConvertException.getColumnIndex()
                    +"列类型转换异常:"+excelDataConvertException.getMessage());
            excelErrorDTO.setValue(excelDataConvertException.getCellData().toString());
            errorList.add(excelErrorDTO);
        }
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", headMap.toString());
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }

    public List<ExcelErrorDTO> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ExcelErrorDTO> errorList) {
        this.errorList = errorList;
    }


    //验证valid,将错误信息放入errorList
    public boolean valid(E object,  Map<String, ExcelCellBo> propertyNameMap){
        boolean validateResult = true;
        Set<ConstraintViolation<E>> validateSet = Validation.buildDefaultValidatorFactory().getValidator().validate(object, Default.class);
        if (validateSet != null && !validateSet.isEmpty()) {
            validateResult = false;
            ExcelErrorDTO errorDTO;
            for (ConstraintViolation<E> constraint : validateSet) {
                Path propertyPath = constraint.getPropertyPath();
                String propertyName = propertyPath.toString();
                ExcelCellBo bo = propertyNameMap.get(propertyName);
                errorDTO = new ExcelErrorDTO();
                errorDTO.setHeadName(bo.getHeadName());
                Object invalidValue = constraint.getInvalidValue();
                if (invalidValue != null) {
                    errorDTO.setValue(invalidValue.toString());
                }else {
                    errorDTO.setValue(null);
                }
                errorDTO.setColumnIndex(bo.getColumnIndex()+1);
                errorDTO.setRowIndex(bo.getRowIndex()+1);
                errorDTO.setErrMsg("第"+errorDTO.getRowIndex()+"行,第"+errorDTO.getColumnIndex()+"列,"+constraint.getMessage());
                errorList.add(errorDTO);
            }
        }
        return validateResult;
    }


    //获取字段名与其对应行列
    Map<String, ExcelCellBo> getPropertyNameMap(boolean isSingleHeader, AnalysisContext analysisContext){
        Map<String, ExcelCellBo> propertyNameMap = new HashMap<>(16);
        ReadRowHolder readRowHolder = analysisContext.readRowHolder();
        Integer rowIndex = readRowHolder.getRowIndex();
        ReadHolder readHolder = analysisContext.currentReadHolder();
        ExcelReadHeadProperty excelReadHeadProperty = readHolder.excelReadHeadProperty();
        Collection<ExcelContentProperty> values;
        if (isSingleHeader){
            Map<Integer, ExcelContentProperty> contentPropertyMap = excelReadHeadProperty.getContentPropertyMap();
            values = contentPropertyMap.values();
        }else {
            Map<String, ExcelContentProperty> fieldNameContentPropertyMap = excelReadHeadProperty.getFieldNameContentPropertyMap();
            values = fieldNameContentPropertyMap.values();
        }
        ExcelCellBo bo;
        for (ExcelContentProperty contentProperty : values) {
            bo = new ExcelCellBo();
            bo.setRowIndex(rowIndex);
            bo.setColumnIndex(contentProperty.getHead().getColumnIndex());
            bo.setFieldName(contentProperty.getHead().getFieldName());
            bo.setHeadName(String.join(",",contentProperty.getHead().getHeadNameList()));
            bo.setField(contentProperty.getField());
            propertyNameMap.put(contentProperty.getHead().getFieldName(),bo);
        }
        return propertyNameMap;
    }

}