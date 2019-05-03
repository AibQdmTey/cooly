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
    private int rStart;
    private int rEnd;
    // 列信息
    private int cStart;
    private int cEnd;
    //
    private CellUnit cellUnit;

    public CellAbs(final int rStart, final int rEnd, final int cStart, final int cEnd,
            final CellUnit cellUnit) {
        this.rStart = rStart;
        this.rEnd = rEnd;
        this.cStart = cStart;
        this.cEnd = cEnd;
        this.cellUnit = cellUnit;
    }
}
