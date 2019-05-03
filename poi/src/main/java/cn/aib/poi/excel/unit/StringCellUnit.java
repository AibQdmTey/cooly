/*
 * StringCellUnit.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.excel.unit;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
@Data
@NoArgsConstructor
public class StringCellUnit extends CellUnitAbs {
    private String value;

    public StringCellUnit(final String value) {
        this.value = value;
    }
}
