package digitalbull.app.accuratelab.Services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Utils.ZProgressHUD;

public class CallJson
{
   Activity activity;
   public CallJson(Activity activity){
       this.activity=activity;
   }

   public void SendRequest(final String jurl, final ArrayList<NetParam> params, final JsonCallbacks ulistner, final String args, final String waitmsg)
   {
         class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
            private JsonCallbacks listner;
             ZProgressHUD progressHUD;
            private  ProgressDialog pgd;
            //when the task started displaying a progressbar
            protected PerformNetworkRequest(JsonCallbacks listner){
                this.listner = listner;
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try{
                    if(waitmsg.length()!=0){
                        progressHUD = new ZProgressHUD(activity);
                        progressHUD.setMessage(waitmsg);
                        progressHUD.show();
//                        pgd=new ProgressDialog(activity);
//                        pgd.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                        pgd.setIndeterminate(false);
//                        pgd.setMessage(waitmsg);
//                        pgd.setCancelable(false);
//                        pgd.show();
                    }
                }catch (Exception ex){}
            }
            //this method will give the response from the request
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressHUD.dismiss();
//                   if(pgd!=null){pgd.dismiss();}
                   try {
                       if(s.contains("Error")){
                           JSONObject js=new JSONObject(s);
                           String msg= js.getString("data");
                           listner.onPostError(msg);
                       }else{
                           listner.onPostSuceess(s,args);
                       }
                       // JsonCallbacks
                   } catch (JSONException e) {
                       listner.onPostError(e.getMessage());
                   }
            }
            //the network operation will be performed in background
            @Override
            protected String doInBackground(Void... voids)
            {
               if(UserUtil.isInternetAvailable(activity)){
                return NetService.invokeJSONWS(jurl, params);
               }else{ return "Network Error!";}
            }
        }
       PerformNetworkRequest prqst = new PerformNetworkRequest(ulistner);
       prqst.execute();
   }
}
