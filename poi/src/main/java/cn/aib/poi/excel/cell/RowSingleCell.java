/*
 * RowSingleUnit.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.cell;

import cn.aib.poi.excel.unit.StringCellUnit;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单行cell
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
@Data
@NoArgsConstructor
public class RowSingleCell extends CellAbs {
    // string 值
    public RowSingleCell(final int rowStart, final int columnStart, final int columnEnd,
            final String value) {
        super(rowStart, rowStart + 1, columnStart, columnEnd, new StringCellUnit(value));
    }
}
