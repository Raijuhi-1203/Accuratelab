package digitalbull.app.accuratelab;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Models.ProductModel;
import digitalbull.app.accuratelab.Services.CallJsonWithoutProgress;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;

public class Privacy extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        EdgeToEdge.enable(this);

        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Privacy Policy");

        TextView details = findViewById(R.id.details);

        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJsonWithoutProgress jc = new CallJsonWithoutProgress(Privacy.this);
        jc.SendRequest("get_privacy", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                JSONArray array = new JSONArray(json);
                for (int s = 0; s < array.length(); s++) {
                    JSONObject obj = array.getJSONObject(s);
                    details.setText(Html.fromHtml(obj.getString("privacy_information")));
                }
            }
            @Override
            public void onPostError(String msg) {
            }
        }, "", "Loading..");
    }
}
