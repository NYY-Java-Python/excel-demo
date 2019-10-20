package com.gjing.excel.demo.config;

import cn.gjing.tools.excel.ExcelStyle;
import org.apache.poi.ss.usermodel.*;

/**
 * 自己定义的样式
 * @author Gjing
 **/
public class MyExcelStyle implements ExcelStyle {

    @Override
    public CellStyle setTitleStyle(CellStyle cellStyle) {
        cellStyle.setFillForegroundColor(IndexedColors.PINK.index);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setWrapText(true);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }
}
