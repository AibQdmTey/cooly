/*
 * TestExcelComponet.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.poi.component;

import cn.aib.core.util.FileOpsUtil;
import cn.aib.poi.excel.table.RowSingleTable;
import cn.aib.poi.util.ExcelTransUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/4.
 */
@Component
public class ExcelTestComponent {
    private static final String IN_FILE_EXCEL =
//            "test.xlsx";
            "富润印染2017年度财务报表.xlsx";

    public void test() throws Exception {
        final File file = FileOpsUtil.retrieveFile(IN_FILE_EXCEL);
        final Map<String, RowSingleTable> stringRowSingleTableMap =
                ExcelTransUtil.transSimpleFile(file);
    }
}
