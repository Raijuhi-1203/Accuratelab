package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

public class LoginPage extends AppCompatActivity implements JsonCallbacks {
    TextView register;
    Button btnlogin;
    EditText password,mobileno;
    ArrayList<NetParam> param;
    CustomerModel customerModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        register=findViewById(R.id.register);
        btnlogin=findViewById(R.id.btnlogin);
        password=findViewById(R.id.password);
        mobileno=findViewById(R.id.mobileno);
        CheckLogins();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validate()) {
                    param = new ArrayList<NetParam>();
                    CallJson jc = new CallJson(LoginPage.this);
                    param.add(new NetParam("mobileno", mobileno.getText().toString()));
                    param.add(new NetParam("PASSWORD", password.getText().toString()));
                    jc.SendRequest("login", param, LoginPage.this, "login", "Please wait while verifying..");
                    // jc.SendRequest(str,"loginprocess", param, LoginPage.this, "loginprocess", "Please wait while verifying..");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,Register.class));
            }
        });
    }

    private void CheckLogins() {
        customerModel = SessionManage.getCurrentUser(this);
        if (customerModel != null) {
            if (customerModel.getCustomer_id() != null) {
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                finish();
            }
        }
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
                Intent act = new Intent(LoginPage.this, Dashboard.class);
                startActivity(act);
                UserUtil.ShowMsg("Login Successfully !", LoginPage.this);
                finish();
            } else {
                Utility.ShowMEssage(LoginPage.this, "Invalid Login user and Password!");
            }
        } catch (JSONException e) {
            Utility.ShowMEssage(LoginPage.this, "Invalid Login user and Password!");
            e.printStackTrace();
        }
    }

    @Override
    public void onPostError(String msg) {

    }

    private boolean Validate() {
        Boolean valid = true;

        String m = mobileno.getText().toString();
        m= String.valueOf(m.length());
        int a = Integer.parseInt(m);

        if (a != 10) {
            mobileno.setError("Please  Enter Valid Mobile No.");
            valid=false;
        } else if (password.getText().length() == 0) {
            password.setError("Please Enter Password");
            valid=false;
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    
}
