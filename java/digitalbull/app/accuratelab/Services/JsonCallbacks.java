package digitalbull.app.accuratelab.Services;

import org.json.JSONException;

public interface JsonCallbacks
{
   void onPostSuceess(String json, String method) throws JSONException;
   void onPostError(String msg);
}
