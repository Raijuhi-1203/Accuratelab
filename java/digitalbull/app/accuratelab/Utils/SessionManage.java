package digitalbull.app.accuratelab.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import digitalbull.app.accuratelab.Models.CustomerModel;


public class SessionManage {

    public static void SetCustomerSessions(Context context, CustomerModel customers) {
        Gson gson = new Gson();
        String json = gson.toJson(customers);
        setSharedPreferenceString(context, "login", json);
    }

    public static CustomerModel getCurrentUser(Context context) {
        CustomerModel customers=null;
        Gson gson = new Gson();
        String json = getSharedPreferenceString(context, "login", null);
        if (json != null)
            customers = gson.fromJson(json, CustomerModel.class);
        return customers;
    }

    public static boolean ClearSession(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        return true;
    }

    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public static void setSharedPreferenceBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        return settings.getString(key, defValue);
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static int getSharedPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        return settings.getInt(key, defValue);
    }

    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference isn't found.
     * @return value - String containing value of the shared preference if found.
     */
    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_PREFS, 0);
        return settings.getBoolean(key, defValue);
    }
}
