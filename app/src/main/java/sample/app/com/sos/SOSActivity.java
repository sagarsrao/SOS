package sample.app.com.sos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SOSActivity extends AppCompatActivity {
    ImageButton sosButton;
    Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        sosButton = findViewById(R.id.sos);
        // contactButton=(Button)findViewById(R.id.button);

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      Toast.makeText(getApplicationContext(), "hell0", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SOSActivity.this, ContactActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Sample", "Activity");
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}

/*
        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //send current location to selected contacts with default msg.

            }
        });
    }
*/



