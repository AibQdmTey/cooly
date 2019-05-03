/*
 * EmptyCell.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.cell;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
public class EmptyCell extends CellAbs {
    public EmptyCell(final int rowStart, final int columnStart) {
        super(rowStart, rowStart + 1, columnStart, columnStart + 1, null);
    }
}
