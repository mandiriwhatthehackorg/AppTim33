package men.ngopi.zakwan.jasanesia.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import men.ngopi.zakwan.jasanesia.MainActivity;
import men.ngopi.zakwan.jasanesia.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button next = findViewById(R.id.activity_loginbtn_login);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainActivity = new Intent(LoginActivity.this ,MainActivity.class );
                startActivity(mainActivity);

            }
        });
    }
}
