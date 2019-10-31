package com.gjing.excel.demo.entity;

import cn.gjing.tools.excel.Excel;
import cn.gjing.tools.excel.ExcelEnumConvert;
import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.Type;
import cn.gjing.tools.excel.valid.ExplicitValid;
import com.gjing.excel.demo.config.MyExcelStyle;
import com.gjing.excel.demo.enums.GenderEnum;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@Excel(value = "用户列表", style = MyExcelStyle.class, type = Type.XLS)
public class User extends BaseModel {

    @Column(name = "user_name", columnDefinition = "varchar(30)")
    @ExcelField("用户名")
    private String userName;

    @ExcelField("性别")
    @ExplicitValid(combobox = {"男,女"})
    @Column(name = "user_gender", columnDefinition = "tinyint(2)")
    @ExcelEnumConvert(convert = GenderEnum.MyExcelEnumConvert.class)
    @Convert(converter = GenderEnum.GenderEnumDbConvert.class)
    private GenderEnum genderEnum;
}
