package com.gjing.excel.demo.entity;

import cn.gjing.tools.excel.ExcelField;
import cn.gjing.tools.excel.valid.DateValid;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Gjing
 **/
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ExcelField("id")
    private Long id;

    @Column(name = "create_time", columnDefinition = "datetime")
    @CreatedDate
    @ExcelField(value = "创建时间",pattern = "yyyy-MM-dd")
    @DateValid(boxLastRow = 10,expr1 = "2019-10-11",expr2 = "2019-10-13")
    private Date createTime;
}
