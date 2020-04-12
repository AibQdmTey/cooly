/*
 * LevelPageProcessor.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.spider;

import lombok.Data;
import lombok.experimental.Accessors;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author shiqi
 * Created by shiqi on 2020/4/12.
 */
@Data
@Accessors(chain = true)
public abstract class LevelPageProcessor implements PageProcessor {
    private static final Site site = Site.me().setRetryTimes(3)
            .setSleepTime(1000).setTimeOut(10000);
    protected static final String KEY_OF_LEVEL = "_level";

    private String host;
    private Map<Integer, Consumer<Page>> workMap;

    public LevelPageProcessor(final String host) {
        this.host = host;
        this.workMap = buildWorkMap();
    }

    public abstract Map<Integer, Consumer<Page>> buildWorkMap();

    @Override
    public void process(final Page page) {
        final Integer level = retrieveLevel(page.getRequest());
        if (!Objects.isNull(level) && !Objects.isNull(workMap.containsKey(level))) {
            workMap.get(level).accept(page);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    protected Request buildRequest(final String uri, final int level) {
        return new Request()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.92 Safari/537.36")
                .setMethod(HttpConstant.Method.GET)
                .setUrl(host + uri)
                .putExtra(KEY_OF_LEVEL, level);
    }

    protected Integer retrieveLevel(final Request request) {
        return (Integer) request.getExtra(KEY_OF_LEVEL);
    }
}
