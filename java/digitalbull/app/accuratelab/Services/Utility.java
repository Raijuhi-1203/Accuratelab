package digitalbull.app.accuratelab.Services;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.format.Time;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Utility
{
   public static String Staffid;
   public static String caaid;
   static boolean valid=false;

    public static boolean OpenDialog(Context context,String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        valid=true;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    { valid=false;
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle("Application Confirmation");
        alert.show();
        return  valid;
    }

    public static void ShowMEssage(Context context,String msg)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Alert !");
        alert.setMessage(msg);
        alert.setPositiveButton("OK",null);
        alert.show();
    }

    public static String GetBase64(Bitmap photo)
    {
        String imgstr=null;
        if(photo != null)
        {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           // photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            photo.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            imgstr= Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
        return imgstr;
    }

    public static String MYValueRecorder(String cls) {
        String fileName;
        Time t= new Time();
        t.setToNow();
        int hashCode= t.hashCode();
        fileName= cls+"_Home_Work_" +hashCode +".pdf";
        return  fileName;
    }

    public static String ImageName(String cls) {
        String fileName;
        Time t= new Time();
        t.setToNow();
        int hashCode= t.hashCode();
        fileName= cls+"_Home_Work_" +hashCode +".jpg";
        return  fileName;
    }
}
