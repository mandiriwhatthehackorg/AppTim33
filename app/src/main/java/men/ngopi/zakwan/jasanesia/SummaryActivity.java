package men.ngopi.zakwan.jasanesia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SummaryActivity extends AppCompatActivity {

    String id;
    private String TAG = "===== Summary";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summery);


        id = getIntent().getStringExtra("ID_MESSAGE");

        Log.w(TAG, "onCreate: "+id );
        Button next = findViewById(R.id.activity_summary_next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(SummaryActivity.this ,ConfirmActivity.class );
                mainActivity.putExtra("ID_MESSAGE", id);
                startActivity(mainActivity);
            }
        });
    }
}
