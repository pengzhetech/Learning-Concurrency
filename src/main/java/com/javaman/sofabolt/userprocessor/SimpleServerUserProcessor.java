package com.javaman.sofabolt.userprocessor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.alipay.remoting.BizContext;
import com.alipay.remoting.InvokeContext;
import com.alipay.remoting.NamedThreadFactory;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步用户请求处理器
 *
 * @author pengzhe
 * @date 2019-06-20 16:27
 */
@Slf4j
public class SimpleServerUserProcessor extends SyncUserProcessor<RequestBody> {
    /**
     * delay milliseconds
     */
    private long delayMs;

    /**
     * whether delay or not
     */
    private boolean delaySwitch;

    /**
     * executor
     */
    private ThreadPoolExecutor executor;

    /**
     * default is true
     */
    private boolean timeoutDiscard = true;

    private AtomicInteger invokeTimes = new AtomicInteger();

    private AtomicInteger onewayTimes = new AtomicInteger();
    private AtomicInteger syncTimes = new AtomicInteger();
    private AtomicInteger futureTimes = new AtomicInteger();
    private AtomicInteger callbackTimes = new AtomicInteger();

    private String remoteAddr;
    private CountDownLatch latch = new CountDownLatch(1);

    public SimpleServerUserProcessor() {
        this.delaySwitch = false;
        this.delayMs = 0;
        this.executor = new ThreadPoolExecutor(1, 3, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(4), new NamedThreadFactory("Request-process-pool"));
    }

    public SimpleServerUserProcessor(long delay) {
        this();
        if (delay < 0) {
            throw new IllegalArgumentException("delay time illegal!");
        }
        this.delaySwitch = true;
        this.delayMs = delay;
    }

    public SimpleServerUserProcessor(long delay, int core, int max, int keepaliveSeconds,
        int workQueue) {
        this(delay);
        this.executor = new ThreadPoolExecutor(core, max, keepaliveSeconds, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(workQueue), new NamedThreadFactory(
            "Request-process-pool"));
    }

    // ~~~ override methods

    @Override
    public Object handleRequest(BizContext bizCtx, RequestBody request) throws Exception {

     log.info("服务端  Request received:" + request + ", timeout:" + bizCtx.getClientTimeout()
            + ", arriveTimestamp:" + bizCtx.getArriveTimestamp());

        if (bizCtx.isRequestTimeout()) {
            String errMsg = "Stop process in server biz thread, already timeout!";
            processTimes(request);
            throw new Exception(errMsg);
        }

        this.remoteAddr = bizCtx.getRemoteAddress();

        //test biz context get connection
        Assert.assertNotNull(bizCtx.getConnection());
        Assert.assertTrue(bizCtx.getConnection().isFine());

        Long waittime = (Long)bizCtx.getInvokeContext().get(InvokeContext.BOLT_PROCESS_WAIT_TIME);
        Assert.assertNotNull(waittime);

        latch.countDown();
        Assert.assertEquals(RequestBody.class, request.getClass());
        processTimes(request);
        if (!delaySwitch) {
            return RequestBody.DEFAULT_SERVER_RETURN_STR;
        }
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RequestBody.DEFAULT_SERVER_RETURN_STR;
    }

    @Override
    public String interest() {
        return RequestBody.class.getName();
    }

    @Override
    public Executor getExecutor() {
        return executor;
    }

    @Override
    public boolean timeoutDiscard() {
        return this.timeoutDiscard;
    }

    // ~~~ public methods
    public int getInvokeTimes() {
        return this.invokeTimes.get();
    }

    public int getInvokeTimesEachCallType(RequestBody.InvokeType type) {
        return new int[] {this.onewayTimes.get(), this.syncTimes.get(), this.futureTimes.get(),
            this.callbackTimes.get()}[type.ordinal()];
    }

    public String getRemoteAddr() throws InterruptedException {
        latch.await(100, TimeUnit.MILLISECONDS);
        return this.remoteAddr;
    }

    // ~~~ private methods
    private void processTimes(RequestBody req) {
        this.invokeTimes.incrementAndGet();
        if (req.getMsg().equals(RequestBody.DEFAULT_ONEWAY_STR)) {
            this.onewayTimes.incrementAndGet();
        } else if (req.getMsg().equals(RequestBody.DEFAULT_SYNC_STR)) {
            this.syncTimes.incrementAndGet();
        } else if (req.getMsg().equals(RequestBody.DEFAULT_FUTURE_STR)) {
            this.futureTimes.incrementAndGet();
        } else if (req.getMsg().equals(RequestBody.DEFAULT_CALLBACK_STR)) {
            this.callbackTimes.incrementAndGet();
        }
    }

    // ~~~ getters and setters

    /**
     * Getter method for property <tt>timeoutDiscard</tt>.
     *
     * @return property value of timeoutDiscard
     */
    public boolean isTimeoutDiscard() {
        return timeoutDiscard;
    }

    /**
     * Setter method for property <tt>timeoutDiscard<tt>.
     *
     * @param timeoutDiscard value to be assigned to property timeoutDiscard
     */
    public void setTimeoutDiscard(boolean timeoutDiscard) {
        this.timeoutDiscard = timeoutDiscard;
    }
}
