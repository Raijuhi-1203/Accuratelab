package digitalbull.app.accuratelab.Services;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import digitalbull.app.accuratelab.BuildConfig;

public class UserUtil {

     public static JSONArray GetArray(String json) throws JSONException {
         json = json.replace("["," ");
         json = json.replace("]"," ");
         JSONObject jobj = new JSONObject(json);
         JSONArray jarr = jobj.getJSONArray("sr");
         return jarr;
    }

//    public static JSONObject readToJSON(String sampleXml) {
//        String data=sampleXml;
//        Object object=null;
//        JSONArray arrayObj=null;
//        JSONParser jsonParser=new JSONParser();
//        object=jsonParser.parse(data);
//        arrayObj=(JSONArray) object;
//        System.out.println("Json object :: "+arrayObj);
//    }

    public static String IsNull(String val, String defval) {
        if (val.equals("null")) {
            return defval;
        } else {
            return val;
        }
    }

    public static String getRandomString(final int sizeOfRandomString)
    {
        String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public static JSONObject ConvertStringToJsonObject(String json) throws JSONException {
        json = json.replace("["," ");
        json = json.replace("]"," ");
        JSONObject mobj = new JSONObject(json);
        return  mobj;
        // mobj= "data:"+json;
//        if (mobj.getString("Id").length() > 0) {
//
//            JSONObject objs = mobj.getJSONObject("Id");
//            if (objs != null) {
//                return objs;
//            } else {
//                return mobj;
//            }
//        } else {
//            return mobj;
//        }
    }

    public static void HideME(View wv) {
        wv.setVisibility(View.GONE);
    }

    public static void SetText(View wv, String text) {
        ((TextView) wv).setText(text);
    }

    public static void ShowMsg(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    public static void ShowMsgQuick(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showDialog(Context context, String title, String message,
                                  DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Ok", onClickListener);
        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }

    public static void ODialog(Context context, String title, String message,
                               DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Ok", onClickListener);
        dialog.show();
    }

    public static boolean isInternetAvailable(Context ctx) {
        ConnectivityManager conMgr;
        conMgr = (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }

    public static void ShareApp(final Activity context) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Accurate Lab");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    public static void CallDialog(final Context context, final String No) {
        if (No.length() > 0) {
            showDialog(context, "Are you sure want to call this number!", "Dial : " + No, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + No.toString().trim()));
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    context.startActivity(callIntent);
                }
            });
        } else {
            ShowMsg("Sorry no mobile no exist!", context);
        }
    }

    public static String GetMonth(int month) {
        String monthString;
        switch (month) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid month";
                break;
        }
        return monthString;
    }
}
