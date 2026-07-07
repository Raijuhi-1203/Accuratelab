package digitalbull.app.accuratelab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

import digitalbull.app.accuratelab.Services.UserUtil;

public class Contact extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        EdgeToEdge.enable(this);
        ImageView btback= findViewById(R.id.btback);
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView title= findViewById(R.id.title);
        title.setText("Contact Us");

        Button btncall=findViewById(R.id.btncall);
        Button btnwhtsapp=findViewById(R.id.btnwhtsapp);

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtil.CallDialog(Contact.this,"8936821111");
            }
        });
        btnwhtsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "9016508508"; // Country Code के साथ
                String message = "Hello, I would like to know more about your services.";

                try {
                    String url = "https://wa.me/" + phoneNumber + "?text=" +
                            URLEncoder.encode(message, "UTF-8");

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
