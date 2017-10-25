package com.mandy.testGuava.base;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

/**
 * com.google.common.base.Stopwatch
 * 监控程序运行的开始，结束
 * Platform中使用
 * ServiceLoader<PatternCompiler> loader = ServiceLoader.load(PatternCompiler.class);
 * 1.6+ ServiceLoader从清单文件中加载出来一个接口的具体实现
 * 在1.9中模块化编程使用 详见MyServant和MyService的测试
 */
public class TestStopWatch {

    private final static Logger logger= LoggerFactory.getLogger(TestStopWatch.class);

    public static void main(String[] args) {
        try {
            process("123");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void process(String orderNo) throws InterruptedException {
        logger.info("Start the process of the order is [{}]",orderNo);
        System.out.printf("Start the process of the order is [%s]\n",orderNo);
        Stopwatch stopwatch=Stopwatch.createStarted();
        long startTime=System.nanoTime();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo [%s] is process successful and cost [%s] ns\n",orderNo,(System.nanoTime()-startTime));
        //logger.info("The orderNo [{}] is process successful and cost [{}] ns",orderNo,stopwatch.stop());
        logger.info("The orderNo [{}] is process successful and cost [{}] ns",orderNo,stopwatch.stop().elapsed(TimeUnit.NANOSECONDS));
    }

}
