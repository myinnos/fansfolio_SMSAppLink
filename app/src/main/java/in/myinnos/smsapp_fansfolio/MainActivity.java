package in.myinnos.smsapp_fansfolio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.myinnos.textmeapp_fansfolio.TextMefansfolioApp;

public class MainActivity extends AppCompatActivity {

    private EditText etMobileNumber;
    private Button btSend;
    private String mobileNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variables
        etMobileNumber = (EditText) findViewById(R.id.etMobileNumber);
        btSend = (Button) findViewById(R.id.btSend);

        // send button on click listener
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mobileNumber = etMobileNumber.getText().toString();
                //calling library funtion
                TextMefansfolioApp.make(MainActivity.this, mobileNumber, "one time SMS Sent! to "+ mobileNumber);
            }
        });
    }
}
