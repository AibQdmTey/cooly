/*
 * ExcelTransUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.util;

import cn.aib.poi.excel.cell.RowSingleCell;
import cn.aib.poi.excel.table.RowSingleTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
public class ExcelTransUtil {
    /**
     * 转化 简单excel文件
     */
    @SneakyThrows
    public static Map<String, RowSingleTable> transSimpleFile(final File file) {
        final XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream(file));
        final Map<String, RowSingleTable> res = Maps.newHashMap();
        final Iterator<Sheet> iterator = xssfWorkbook.iterator();
        while (iterator.hasNext()) {
            final Sheet sheet = iterator.next();
            final RowSingleTable rowSingleTable = transSimpleTableFromSheet(sheet);
            res.put(rowSingleTable.getName(), rowSingleTable);
        }

        return res;
    }

    /**
     * 转化 简单表
     */
    public static RowSingleTable transSimpleTableFromSheet(final Sheet sheet) {
        System.out.println("[CellValueTransUtil-transSimpleTableFromSheet]:"
                + "解析sheet - " + sheet.getSheetName());
        final RowSingleTable table = new RowSingleTable(sheet.getSheetName());
        final Iterator<Row> rowIterator = sheet.iterator();
        // 每一 row
        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            table.addCellList(transSimpleCellListFromRow(row));
        }

        return table;
    }

    /**
     * 转化 简单行元素
     *   RowSingleCell
     *   StringCellUnit
     */
    public static List<cn.aib.poi.excel.Cell> transSimpleCellListFromRow(final Row row) {
        final List<cn.aib.poi.excel.Cell> res = Lists.newArrayList();
        //
        final List<Cell> cellList = IteratorUtils.toList(row.iterator());
        for (int i = 0; i < cellList.size(); i++) {
            final Cell cell = cellList.get(i);
            final String value = CellValueTransUtil.retrieveValueString(cell);
            final int rowStart = cell.getRowIndex();
            final int columnStart = cell.getColumnIndex();
            int columnEnd = columnStart;
            while (++i < cellList.size() && cellList.get(i).getCellTypeEnum() == CellType.BLANK) {
                columnEnd++;
            }
            i--;
            res.add(new RowSingleCell(rowStart, columnStart, columnEnd + 1, value));
        }

        return res;
    }
}
