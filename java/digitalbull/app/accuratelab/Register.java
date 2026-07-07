package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Register extends AppCompatActivity implements JsonCallbacks {
    Button btnlogin;
    EditText password,mobileno,name;
    ArrayList<NetParam> param;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        btnlogin=findViewById(R.id.btnlogin);
        password=findViewById(R.id.password);
        mobileno=findViewById(R.id.mobileno);
        name=findViewById(R.id.name);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validte()) {
                    Register_User();
                }
            }
        });

    }

    private void Register_User() {
        param = new ArrayList<NetParam>();
        CallJson jc = new CallJson(Register.this);
        param.add(new NetParam("mobile", mobileno.getText().toString()));
        param.add(new NetParam("name", name.getText().toString()));
        param.add(new NetParam("password", password.getText().toString()));
        jc.SendRequest("register_user", param, Register.this, "register_user", "Please wait while verifying..");
    }

    @Override
    public void onPostSuceess(String json, String method) throws JSONException {
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
                Intent act = new Intent(Register.this, Dashboard.class); //OTPage
                startActivity(act);
                UserUtil.ShowMsg("Register Successfully !", Register.this);
                finish();
            } else {
                Utility.ShowMEssage(Register.this, "Invalid details !");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(Register.this, "Invalid details !");
            e.printStackTrace();
        }
    }

    @Override
    public void onPostError(String msg) {

    }

    private boolean validte() {
        Boolean valid = true;

        String m = mobileno.getText().toString();
        m= String.valueOf(m.length());
        int a = Integer.parseInt(m);

        if (name.getText().length()==0){
            name.setError("Please enter name");
            valid=false;
        }else  if (a != 10){
            mobileno.setError("Please enter valid mobile no");
            valid=false;
        }else  if (password.getText().length()==0){
            password.setError("Please enter password");
            valid=false;
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

}
