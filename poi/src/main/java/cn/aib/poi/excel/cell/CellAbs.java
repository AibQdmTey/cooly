/*
 * UnitAbs.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.cell;

import cn.aib.poi.excel.Cell;
import cn.aib.poi.excel.CellUnit;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
@Data
@NoArgsConstructor
public abstract class CellAbs implements Cell {
    // 行信息
    private int rowStart;
    private int rowEnd;
    // 列信息
    private int columnStart;
    private int columnEnd;
    //
    private CellUnit cellUnit;

    public CellAbs(final int rowStart, final int rowEnd, final int columnStart, final int columnEnd,
            final CellUnit cellUnit) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
        this.cellUnit = cellUnit;
    }

    @Override
    public boolean isMatchIndex(final int rowIndex, final int columnIndex) {
        return (rowStart <= rowIndex && rowIndex < rowEnd) &&
                (columnStart <= columnIndex && columnIndex < columnEnd);
    }
}
