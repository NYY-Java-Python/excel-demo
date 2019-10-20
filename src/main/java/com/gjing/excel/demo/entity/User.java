package com.gjing.excel.demo.entity;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelEnumConvert;
import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.Type;
import cn.gjing.tools.excel.valid.ExplicitValid;
import cn.gjing.tools.excel.valid.NumericValid;
import com.gjing.excel.demo.config.MyExcelStyle;
import com.gjing.excel.demo.enums.GenderEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

import static cn.gjing.tools.excel.valid.OperatorType.GREATER_OR_EQUAL;
import static cn.gjing.tools.excel.valid.ValidType.TEXT_LENGTH;

/**
 * @author Gjing
 **/
@Data
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Excel(value = "用户列表",style = MyExcelStyle.class,type = Type.XLSX)
public class User extends BaseModel {

    @Column(name = "user_name", columnDefinition = "varchar(30)")
    @ExcelField("用户名")
    @NumericValid(validationType = TEXT_LENGTH, expr1 = "3", operatorType = GREATER_OR_EQUAL)
    private String userName;

    @ExcelField("性别")
    @ExplicitValid(combobox = {"男,女"})
    @Column(name = "user_gender", columnDefinition = "tinyint(2)")
    @ExcelEnumConvert(convert = GenderEnum.MyExcelEnumConvert.class)
    @Convert(converter = GenderEnum.GenderEnumDbConvert.class)
    private GenderEnum genderEnum;
}
