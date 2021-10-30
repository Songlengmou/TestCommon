package anningtex.manger.http;

import io.reactivex.Flowable;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @Author Song
 */
public class RxBus {
    private final FlowableProcessor<Object> _bus;

    private RxBus() {
        // toSerialized method made bus thread safe
        _bus = PublishProcessor.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.BUS;
    }

    public void send(Object obj) throws OnErrorNotImplementedException {
        _bus.onNext(obj);
    }

    /**
     * 根据传递的 eventType 类型返回特定事件类型的被观察者
     */
    public <T> Flowable<T> toFlowable(Class<T> tClass) throws OnErrorNotImplementedException {
        return _bus.ofType(tClass);
    }

    public Flowable<Object> toFlowable() {
        return _bus;
    }

    public boolean hasSubscribers() {
        return _bus.hasSubscribers();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }
}