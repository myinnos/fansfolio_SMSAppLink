package in.myinnos.textmeapp_fansfolio;

import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
    private final in.myinnos.textmeapp_fansfolio.CallJavaResultInterface mCallJavaResultInterface;

    public JavaScriptInterface(CallJavaResultInterface callJavaResult) {
        this.mCallJavaResultInterface = callJavaResult;
    }

    @JavascriptInterface
    public void returnResultToJava(String value, int callIndex) {
        this.mCallJavaResultInterface.jsCallFinished(value, Integer.valueOf(callIndex));
    }
}
