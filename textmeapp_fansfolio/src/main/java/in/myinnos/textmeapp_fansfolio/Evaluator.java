package in.myinnos.textmeapp_fansfolio;

import android.content.Context;
import android.webkit.WebView;

import java.util.ArrayList;

public class Evaluator implements CallJavaResultInterface, JsEvaluatorInterface {
    public static final String JS_NAMESPACE = "evgeniiJsEvaluator";
    protected WebViewWrapperInterface mWebViewWrapper;
    private final Context mContext;
    private final ArrayList<JsCallback> mResultCallbacks = new ArrayList();
    private HandlerWrapperInterface mHandler;

    public static String escapeCarriageReturn(String str) {
        return str.replace("\r", "\\r");
    }

    public static String escapeClosingScript(String str) {
        return str.replace("</", "<\\/");
    }

    public static String escapeNewLines(String str) {
        return str.replace("\n", "\\n");
    }

    public static String escapeSingleQuotes(String str) {
        return str.replace("\'", "\\\'");
    }

    public static String escapeSlash(String str) {
        return str.replace("\\", "\\\\");
    }

    public static String getJsForEval(String jsCode, int callbackIndex) {
        jsCode = escapeSlash(jsCode);
        jsCode = escapeSingleQuotes(jsCode);
        jsCode = escapeClosingScript(jsCode);
        jsCode = escapeNewLines(jsCode);
        jsCode = escapeCarriageReturn(jsCode);
        return String.format("%s.returnResultToJava(eval(\'%s\'), %s);", new Object[]{"evgeniiJsEvaluator", jsCode, Integer.valueOf(callbackIndex)});
    }

    public Evaluator(Context context) {
        this.mContext = context;
        this.mHandler = new HandlerWrapper();
    }

    public void callFunction(String jsCode, JsCallback resultCallback, String name, Object... args) {
        jsCode = jsCode + "; " + JsFunctionCallFormatter.toString(name, args);
        this.evaluate(jsCode, resultCallback);
    }

    public void evaluate(String jsCode) {
        this.evaluate(jsCode, (JsCallback)null);
    }

    public void evaluate(String jsCode, JsCallback resultCallback) {
        int callbackIndex = this.mResultCallbacks.size();
        if(resultCallback == null) {
            callbackIndex = -1;
        }

        String js = getJsForEval(jsCode, callbackIndex);
        if(resultCallback != null) {
            this.mResultCallbacks.add(resultCallback);
        }

        this.getWebViewWrapper().loadJavaScript(js);
    }

    public void destroy() {
        this.getWebViewWrapper().destroy();
    }

    public WebView getWebView() {
        return this.getWebViewWrapper().getWebView();
    }

    public ArrayList<JsCallback> getResultCallbacks() {
        return this.mResultCallbacks;
    }

    public WebViewWrapperInterface getWebViewWrapper() {
        if(this.mWebViewWrapper == null) {
            this.mWebViewWrapper = new WebViewWrapper(this.mContext, this);
        }

        return this.mWebViewWrapper;
    }

    public void jsCallFinished(final String value, Integer callIndex) {
        if(callIndex.intValue() != -1) {
            final JsCallback callback = (JsCallback)this.mResultCallbacks.get(callIndex.intValue());
            this.mHandler.post(new Runnable() {
                public void run() {
                    callback.onResult(value);
                }
            });
        }
    }

    public void setHandler(HandlerWrapperInterface handlerWrapperInterface) {
        this.mHandler = handlerWrapperInterface;
    }

    public void setWebViewWrapper(WebViewWrapperInterface webViewWrapper) {
        this.mWebViewWrapper = webViewWrapper;
    }
}
