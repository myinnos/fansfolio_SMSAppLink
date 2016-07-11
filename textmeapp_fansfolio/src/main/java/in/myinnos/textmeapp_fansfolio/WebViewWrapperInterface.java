package in.myinnos.textmeapp_fansfolio;

import android.webkit.WebView;

public interface WebViewWrapperInterface {
    void loadJavaScript(String var1);

    void destroy();

    WebView getWebView();
}
