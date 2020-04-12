/*
 * WXPage.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package cn.aib.spider.example;

import cn.aib.core.util.FileOpsUtil;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 间客有声小说
 *   起始页面：https://mp.weixin.qq.com/s/8ll0a0tphPbn67idAqMJ9A
 *   单章节详情页：https://mp.weixin.qq.com/s?__biz=MzIyMjg4NDA1OA==&mid=2247486002&idx=1&sn=8edf241ce997046699088d72efea9365&chksm=e827f387df507a91c7ff6dc08e2f1b0fe5375f98a07f4f297c3921f7554ff00e44e9676c0ade&scene=21#wechat_redirect
 *   音频页面：https://res.wx.qq.com/voice/getvoice?mediaid=MzIyMjg4NDA1OF8yMjQ3NDg1OTk0
 * @author shiqi
 * Created by shiqi on 2020/4/4.
 */
public class WXPageProcessor implements PageProcessor {

    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        // 第一层：https://mp.weixin.qq.com/s/8ll0a0tphPbn67idAqMJ9A
        // 获取单个章节链接
        final Selectable selectable = page.getHtml()
                .xpath("//div[@id='js_content']/section/p/a/@href | //div[@id='js_content']/p/a/@href");
        final List<String> all = selectable.all();
        System.out.println("[WXPageProcessor-process]:"
                + "一层数量：" + all.size());
        if (!all.isEmpty()) {
            all.stream().forEach(r -> page.addTargetRequest(r));
        }
        // 第二层：获取 MP3
        final Selectable selectable2 = page.getHtml()
                .xpath("//mpvoice[@class='audio_iframe']/@voice_encode_fileid");
//                .xpath("//div[@id='js_content']/section/p/mpvoice/@voice_encode_fileid");
//                .xpath("//div[@id='js_content']/section/section/p/mpvoice/@voice_encode_fileid");
        final List<String> all2 = selectable2.all();
        final Selectable selectable3 = page.getHtml()
                .xpath("//mpvoice[@class='audio_iframe']/@name");
//                .xpath("//div[@id='js_content']/section/p/mpvoice/@name");
//                .xpath("//div[@id='js_content']/section/section/p/mpvoice/@name");
        final List<String> all3 = selectable3.all();
        System.out.println("[WXPageProcessor-process]:"
                + "二层数量：" + all2.size());
        if (!all2.isEmpty()) {
            for (int i = 0; i < all2.size(); i++) {
                downloadFile(all3.get(i),
                        "https://res.wx.qq.com/voice/getvoice?mediaid=" + all2.get(i));
            }
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // 爬取数据
//        Spider.create(new WXPageProcessor())
////                .addUrl("https://mp.weixin.qq.com/s/ZSymfyV9WuHgx492sotB-w")
////                .addUrl("https://mp.weixin.qq.com/s/_1rRtmoB-n0H-LoOg0yw4Q")
//                .addUrl("https://mp.weixin.qq.com/s/Dw-S9pRgPbcKsVyiLxzigA")
////                .addUrl("https://mp.weixin.qq.com/s/8ll0a0tphPbn67idAqMJ9A")
//                .thread(50)
//                .run();
        // 检验数量
        final File file = new File("D:\\project\\out\\第四卷");
        final List<String> fileNameList = Lists.newArrayList(file.list());
        for (int i = 100; i < 394; i++) {
            final int c = i;
            if (!fileNameList.stream().anyMatch(r -> r.startsWith("第四卷" + c))) {
                System.out.println("[WXPageProcessor-main]:"
                        + "缺少：" + i);
            }
        }
        // 文件重命名
//        Arrays.stream(file.listFiles()).forEach(currentFile -> {
//            final String name = currentFile.getName();
//            if (name.startsWith("四")) {
////                System.out.println("[WXPageProcessor-main]:"
////                        + name.substring(3));
//                currentFile.renameTo(new File("D:\\project\\out\\第四卷\\第四卷" + name.substring(1)));
//            }
//        });
    }

    private void downloadFile(final String name, final String url) {
        System.out.println("[WXPageProcessor-run]:开始执行："
                + name + ":" + url);
        final HttpGet httpGet = new HttpGet(url);
        try {
            final HttpResponse response = httpclient.execute(httpGet);
            final InputStream input = response.getEntity().getContent();
            FileOpsUtil.writeToFile("D:\\project\\out\\第一卷", name + ".mp3", input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}