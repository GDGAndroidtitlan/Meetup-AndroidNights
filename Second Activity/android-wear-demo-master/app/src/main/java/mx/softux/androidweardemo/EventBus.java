package mx.softux.androidweardemo;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by juan on 7/7/15.
 */
public class EventBus extends Bus {
    private static EventBus instance;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private EventBus() {

    }

    public static EventBus getInstance() {
        if(instance == null) {
            instance = new EventBus();
        }

        return instance;
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    EventBus.super.post(event);
                }
            });
        }
    }
}
