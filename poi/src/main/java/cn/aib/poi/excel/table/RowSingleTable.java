/*
 * RowSingleTable.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.table;

import cn.aib.poi.excel.Cell;
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
public class RowSingleTable extends TableAbs {
    public RowSingleTable(final String name) {
        super(name);
    }

    public RowSingleTable addCellList(final List<Cell> cellList) {
        getCellTable().add(cellList);

        return this;
    }

    public RowSingleTable addCell(final int rNum, final Cell cell) {
        final List<List<Cell>> cellTable = getCellTable();
        // 补充空行
        for (int i = cellTable.size(); i < rNum; i++) {
            cellTable.add(Lists.newArrayList());
        }
        // 增加
        cellTable.get(rNum).add(cell);

        return this;
    }
}
