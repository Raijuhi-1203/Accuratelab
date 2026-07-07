package digitalbull.app.accuratelab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import digitalbull.app.accuratelab.Models.CustomerModel;
import digitalbull.app.accuratelab.Utils.SessionManage;

public class Profile extends AppCompatActivity {
    Button btn_update,btn_address,btn_menu;
    TextView txname,txmob;
    CustomerModel customerModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
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
        title.setText("Profile");

        txname=findViewById(R.id.txname);
        txmob=findViewById(R.id.txmob);
        btn_update=findViewById(R.id.btn_update);
        btn_address=findViewById(R.id.btn_address);
        btn_menu=findViewById(R.id.btn_menu);

        txname.setText(customerModel.getCustomer_name());
        txmob.setText(customerModel.getCustomer_mobileno());

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,ProfileUpdate.class));
            }
        });
        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,AddressUser.class));
            }
        });
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,MenuOption.class));
            }
        });

    }
}
