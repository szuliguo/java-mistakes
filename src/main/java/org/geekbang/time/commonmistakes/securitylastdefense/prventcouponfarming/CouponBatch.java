package org.geekbang.time.commonmistakes.securitylastdefense.prventcouponfarming;

import lombok.Data;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class CouponBatch {
    private long id;
    private AtomicInteger totalCount;
    /**
     * 需要原子性进行增减
     * 原子性的
     */
    private AtomicInteger remainCount;
    private BigDecimal amount;
    private String reason;
}
