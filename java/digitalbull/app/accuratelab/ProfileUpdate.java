package digitalbull.app.accuratelab;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Services.CallJson;
import digitalbull.app.accuratelab.Services.JsonCallbacks;
import digitalbull.app.accuratelab.Services.NetParam;
import digitalbull.app.accuratelab.Services.UserUtil;
import digitalbull.app.accuratelab.Services.Utility;
import digitalbull.app.accuratelab.Utils.SessionManage;

public class ProfileUpdate extends AppCompatActivity {
    EditText txnm,txmail,txmob;
    Spinner spnrgender;
    CustomerModel customerModel;
    Button btn_save;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);
        EdgeToEdge.enable(this);
        customerModel=(CustomerModel) SessionManage.getCurrentUser(getApplicationContext());
        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Update Profile");

        txnm=findViewById(R.id.txnm);txmail=findViewById(R.id.txmail);
        txmob=findViewById(R.id.txmob);spnrgender=findViewById(R.id.spnrgender);
        btn_save=findViewById(R.id.btn_save);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrgender.setAdapter(adapter);

        txnm.setText(customerModel.getCustomer_name());
        txmail.setText(customerModel.getCustomer_email());
        txmob.setText(customerModel.getCustomer_mobileno());
       // spnrgender.setSelection(Integer.parseInt(customerModel.getCustomer_gender()));

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txnm.getText().length()==0){
                    txnm.setError("Please enter name");
                }else  if (txmail.getText().length()==0){
                    txmail.setError("Please enter email id");
                }else if (txmob.getText().length()==0){
                    txmob.setError("Please enter mobile no");
                }else {
                    UpdateData();
                }
            }
        });

    }

    private void UpdateData() {
        ArrayList<NetParam> param;
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(ProfileUpdate.this);
        param.add(new NetParam("name",txnm.getText().toString()));
        param.add(new NetParam("mobile",txmob.getText().toString()));
        param.add(new NetParam("gender",spnrgender.getSelectedItem().toString()));
        param.add(new NetParam("mail",txmail.getText().toString()));
        param.add(new NetParam("dob"," "));
        jc.SendRequest("update_user", param, new JsonCallbacks() {
            @Override
            public void onPostSuceess(String json, String method) throws JSONException {
                UserUtil.ShowMsg("Details Updated !!",ProfileUpdate.this);
                CustomerModel sd = new CustomerModel();
                try {
                    JSONObject obj = UserUtil.ConvertStringToJsonObject(json);
                    if (obj.length() != 1) {
                        sd.setCustomer_date(obj.getString("customer_date"));
                        sd.setId(obj.getString("id"));
                        sd.setCustomer_email(obj.getString("customer_email"));
                        sd.setCustomer_gender(obj.getString("customer_gender"));
                        sd.setCustomer_id(obj.getString("customer_id"));
                        sd.setCustomer_mobileno(obj.getString("customer_mobileno"));
                        sd.setCustomer_name(obj.getString("customer_name"));
                        sd.setCustomer_password(obj.getString("customer_password"));
                        sd.setCustomer_profilephoto(obj.getString("customer_profilephoto"));
                        sd.setCustomer_status(obj.getString("customer_status"));
                        sd.setCustomer_temp_id(obj.getString("customer_temp_id"));
                        sd.setOtp(obj.getString("otp"));
                        sd.setEmail_verification_code(obj.getString("email_verification_code"));
                        sd.setEmail_verified(obj.getString("email_verified"));
                        sd.setMobileno_verified(obj.getString("mobileno_verified"));
                        sd.setCustomer_time(obj.getString("customer_time"));
                        SessionManage.SetCustomerSessions(getApplicationContext(), sd);
                        finish();
                    }
                } catch (JSONException e) {
                    Utility.ShowMEssage(ProfileUpdate.this, "Invalid details !");
                    e.printStackTrace();
                }
            }
            @Override
            public void onPostError(String msg) {

            }
        }, " ", "Loading..");
    }

}
