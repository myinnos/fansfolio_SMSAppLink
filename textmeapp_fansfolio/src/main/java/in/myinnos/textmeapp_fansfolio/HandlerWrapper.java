package in.myinnos.textmeapp_fansfolio;

import android.os.Handler;

public class HandlerWrapper implements HandlerWrapperInterface {
    private final Handler mHandler = new Handler();

    public HandlerWrapper() {
    }

    public void post(Runnable r) {
        this.mHandler.post(r);
    }
}
