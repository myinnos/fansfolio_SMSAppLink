package in.myinnos.textmeapp_fansfolio;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.widget.Toast;

public class CheckNetwork {

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        toastNoNetwork(context);
        return false;
    }

    public static void toastNoNetwork(Context context) {
        Toast toast = Toast.makeText(context, "please check your internet connection", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        toast.show();
    }
}
