/*
 * JsonTranUtil.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/5/3.
 */
public class JsonTranUtil {
    public static ObjectMapper OM = new ObjectMapper();

    @SneakyThrows
    public static String toStr(final Object o) {
        return OM.writeValueAsString(o);
    }
}
