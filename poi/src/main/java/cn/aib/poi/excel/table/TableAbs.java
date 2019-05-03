/*
 * TableAbs.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.table;

import cn.aib.poi.excel.Cell;
import cn.aib.poi.excel.Table;
import cn.aib.poi.excel.cell.EmptyCell;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
@Data
@NoArgsConstructor
public abstract class TableAbs implements Table {
    private String name;
    private List<List<Cell>> cellTable;

    public TableAbs(final String name) {
        this.name = name;
        this.cellTable = Lists.newArrayList();
    }

    public Cell retrieveCell(final int rowIndex, final int columnIndex) {
        return cellTable.stream().flatMap(r -> r.stream())
                .filter(r -> r.isMatchIndex(rowIndex, columnIndex))
                .findFirst()
                .orElseGet(() -> new EmptyCell(rowIndex, columnIndex));
    }
}
