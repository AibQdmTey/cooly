/*
 * Table.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
public interface Table {
    Cell retrieveCell(final int rowIndex, final int columnIndex);
}
