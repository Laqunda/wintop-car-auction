/**
 * Copyright (c)  2016 All Rights Reserved.
 */
package com.wintop.ms.carauction.util.utils;

/**             
 * @date: Aug 1, 2016
 * @Author: 付陈林
 * @Email: fuchenlin@live.com
 * @FileName: IdWorker.java
 * @Version: 1.0
 * @About: id生成器
 */
public class IdWorker {
    private final long        workerId;
    private final static long twepoch            = 1288834974657L;
    private long              sequence           = 0L;
    private final static long workerIdBits       = 4L;
    public final static long  maxWorkerId        = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits       = 10L;

    private final static long workerIdShift      = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long  sequenceMask       = -1L ^ -1L << sequenceBits;

    private long              lastTimestamp      = -1L;

    private static IdWorker idWorker = null;

    public IdWorker(final long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String
                    .format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public static IdWorker getInstance(){
        if(idWorker==null){
            idWorker = new IdWorker(10);
        }
        return idWorker;
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
                | (this.workerId << this.workerIdShift) | (this.sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
