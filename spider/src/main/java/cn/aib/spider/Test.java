/*
 * Test.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.spider;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * @author shiqi
 * Created by shiqi on 2020/4/8.
 */
public class Test {
    public static void main(String[] args) {
        run(ImmutableList.of("2de4", "f880", "c585", "e135"), 0, 0,
                Lists.newArrayList("2de4", "f880", "c585", "e135"));
    }

    public static void run(final List<String> data, final int step, final int stage,
            final List<String> res) {
        if (step == 4) {
            res.stream().forEach(r -> System.out.print(r));
            System.out.println("");
            return;
        }
        //
        for (int i = 0; i < 4; i++) {
            if ((stage & (1 << i)) == 0) {
                res.remove(i);
                res.add(i, data.get(step));
                run(data, step + 1, (stage | (1 << i)), res);
            }
        }
    }
}
