/*
 * CellValueTransUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author shiqi
 * Created by shiqi on 2018/5/21.
 */
public class CellValueTransUtil {

    /**
     * 获取单元格内容的string
     * @param cell 单元格
     * @return
     */
    public static String retrieveValueString(final Cell cell) {
        final CellType cellTypeEnum = cell.getCellTypeEnum();
        switch (cellTypeEnum) {
        case STRING:
            return cell.getStringCellValue();
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        case NUMERIC:
            return String.valueOf(cell.getNumericCellValue());
        case FORMULA:
            return cell.getCellFormula();
        default:
            throw new IllegalArgumentException("[CellValueTransUtil-retrieveValueString]:" +
                    "不支持当前类型 - " + cellTypeEnum);
        }
    }


}
