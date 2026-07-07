package digitalbull.app.accuratelab.Services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import digitalbull.app.accuratelab.R;

public class SharedPref {

    private Context ctx;
    private SharedPreferences default_prefence;

    public SharedPref(Context context) {
        this.ctx = context;
        default_prefence = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private String str(int string_id) {
        return ctx.getString(string_id);
    }

    public void setbatch(String name) {
        default_prefence.edit().putString(str(R.string.batch), name).apply();
    }

    public String getbatch() {
        return default_prefence.getString(str(R.string.batch), str(R.string.batch));
    }

}
