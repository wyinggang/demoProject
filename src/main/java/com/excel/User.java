package com.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@TableName("user")
public class User {
    // 主键id
    @ExcelIgnore // 生成报表时忽略，不生成次字段
    @TableId
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    @ExcelProperty(value = "姓名", index = 0) // 定义表头名称和位置,0代表第一列
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    @Min(value = 11,message = "最小年龄为11")
    private Integer age;

    @ColumnWidth(20) // 定义列宽
    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "出生日期", index = 2)
    private String birthday;

    @Min(value = 3,message = "最小为3")
    private Integer numberOfGirlFriend;

}
