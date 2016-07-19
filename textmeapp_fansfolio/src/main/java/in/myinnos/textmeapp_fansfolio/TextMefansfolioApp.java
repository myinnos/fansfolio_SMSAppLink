package in.myinnos.textmeapp_fansfolio;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class TextMefansfolioApp {

    public static void make(final Activity activity, final String mobileNumber, final String returnMessage) {

        final Evaluator jsEvaluator = new Evaluator(activity);

        if (CheckNetwork.isNetworkAvailable(activity.getApplicationContext())) {

            if (mobileNumber.trim().length() > 0) {

                jsEvaluator.callFunction("(function(b,r,a,n,c,h,_,s,d,k){if(!b[n]||!b[n]._q){for(;s<_.length;)c(h,_[s++]);d=r.createElement(a);d.async=1;d.src=\"https://cdn.branch.io/branch-latest.min.js\";k=r.getElementsByTagName(a)[0];k.parentNode.insertBefore(d,k);b[n]=h}})(window,document,\"script\",\"branch\",function(b,r){b[r]=function(){b._q.push([r,arguments])}},{_q:[],_v:1},\"addListener applyCode banner closeBanner creditHistory credits data deepview deepviewCta first getCode init link logout redeem referrals removeListener sendSMS setIdentity track validateCode\".split(\" \"), 0);" +
                                "branch.init('" + BuildConfig.APP_KEY + "');" +
                                "            function sendSMS(phonenumber) {" +
                                "                var phone = phonenumber;" +
                                "                var linkData = {tags: [],channel: 'Website',feature: 'TextMeTheApp',data: {'foo': 'bar'}};" +
                                "                var options = {};" +
                                "                var callback = function(err, result) {if (err) {return err;}else {return 'test';} };" +
                                "                branch.sendSMS(phone, linkData, options, callback);" +
                                "                phonenumber = \"\";" +
                                "            }",
                        new JsCallback() {

                            @Override
                            public void onResult(final String result) {

                                sendMessage(activity.getApplicationContext(), returnMessage);
                            }
                        }, "sendSMS", mobileNumber);
            } else {
                Toast toast = Toast.makeText(activity.getApplicationContext(), "Mobile Number cannot be empty!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
                toast.show();
            }
        }
    }

    public static void sendMessage(final Context context, final String message) {

        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        toast.show();

    }

}
