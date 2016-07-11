package in.myinnos.textmeapp_fansfolio;

import android.webkit.WebView;

public interface JsEvaluatorInterface {
    void callFunction(String var1, JsCallback var2, String var3, Object... var4);

    void evaluate(String var1);

    void evaluate(String var1, JsCallback var2);

    void destroy();

    WebView getWebView();
}
