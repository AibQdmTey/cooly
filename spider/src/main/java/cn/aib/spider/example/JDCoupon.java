/*
 * JDCoupon.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.spider.example;

import cn.aib.core.util.JsonTranUtil;
import cn.aib.spider.fix.HttpClientDownloaderTLS12;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 *
 * @author shiqi
 * Created by shiqi on 2019/6/16.
 */
public class JDCoupon implements PageProcessor {

    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        page.putField("test", "test");
        System.out.println("[JDCoupon-process]:start");
        final List<String> all = page.getHtml()
//                .xpath("//div")
//                .links().regex("https://item\\.jd\\.com/[0-9]+\\.html")
                .all();
        page.putField("res", all);
        System.out.println("[JDCoupon-process]:"
                + JsonTranUtil.toStr(all));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JDCoupon())
                .setDownloader(new HttpClientDownloaderTLS12())
                .addUrl("https://search.jd.com/Search?coupon_batch=236461966&coupon_id=66292007665")
                .addPipeline(new JsonFilePipeline("D:\\临时\\20190616\\"))
                .thread(5).run();
    }
}
