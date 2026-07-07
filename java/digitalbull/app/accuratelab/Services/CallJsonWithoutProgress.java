package digitalbull.app.accuratelab.Services;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CallJsonWithoutProgress {
    Activity activity;
    public CallJsonWithoutProgress(Activity activity){
        this.activity=activity;
    }

    public void SendRequest(final String jurl, final ArrayList<NetParam> params, final JsonCallbacks ulistner, final String args, final String waitmsg)
    {
        class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
            private JsonCallbacks listner;

            protected PerformNetworkRequest(JsonCallbacks listner){
                this.listner = listner;
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                try{
                    if(waitmsg.length()!=0){

                    }
                }catch (Exception ex){
                    ex.toString();
                }
            }
            //this method will give the response from the request
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    if(s.contains("Error")){
                        JSONObject js=new JSONObject(s);
                        String msg= js.getString("data");
                        listner.onPostError(msg);
                    }else{
                        listner.onPostSuceess(s,args);
                    }
                } catch (JSONException e) {
                    listner.onPostError(e.getMessage());
                }
            }
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