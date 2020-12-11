package org.geekbang.time.commonmistakes.callback;

import java.util.concurrent.CompletableFuture;

/**
 * @author Legal
 * @date 2020/10/31
 * n 个任务并行执行
 */
public class CompletableFutureTest3 {
    public static void main(String[] args) {

        //俩个CompletableFuture 执行异步查询
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code");
        });
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });
        //用 any 合并为一个新的completableFuture
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFrom163, cfQueryFromSina);

        //俩个 completableFuture 执行异步查询
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        //用any 合并为一个新的completableFuture
        CompletableFuture<Object> cfFetch = CompletableFuture.anyOf(cfFetchFrom163, cfFetchFromSina);

        //最终结果
        cfFetch.thenAccept((result) -> {
            System.out.println("price:" + result);
        });

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池就会立刻关闭
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    static String queryCode(String name, String url) {

        System.out.println("query code from " + url + "...");

        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "601857";
    }

    static Double fetchPrice(String code, String url) {

        System.out.println("query price from " + url + "....");

        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 5 + Math.random() * 20;

    }
}
