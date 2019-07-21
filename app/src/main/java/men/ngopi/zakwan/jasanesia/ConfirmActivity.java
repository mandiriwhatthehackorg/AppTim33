package men.ngopi.zakwan.jasanesia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONException;
import org.json.JSONObject;

import men.ngopi.zakwan.jasanesia.Model.Freelancer;
import men.ngopi.zakwan.jasanesia.Model.MessageModel;

public class ConfirmActivity extends AppCompatActivity {

    private String TAG = "===== Login Activity";

    private FirebaseFirestore firebaseFirestore;

    private MessageModel messageModel;


    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        id = getIntent().getStringExtra("ID_MESSAGE");

        firebaseFirestore = FirebaseFirestore.getInstance();
        Button next = findViewById(R.id.activity_confirm_next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.collection("chat").document(id)
                        .update(
                                "status_pembayaran" , "1"
                        ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        executeTransfer();
                        Intent intent;
                        intent = new Intent(ConfirmActivity.this, ChatActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                        Log.w(TAG, "Error updating document"+  e.getMessage());
                    }
                });



            }
        });
    }

    private void executeTransfer(){
        AndroidNetworking.post("http://api33-api33.apps.openshift.mandiriwhatthehack.com/")
                .addHeaders("Content-Type","application/json")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            int x = (int)Math.random() * 100000;
                            String jwtToken = response.getString("jwt");
                            AndroidNetworking.post("https://apigateway.mandiriwhatthehack.com/gateway/TrxAndPaymentAPI/1.0/transfer")
                                    .addHeaders("Content-Type","application/json")
                                    .addHeaders("Authorization" , "Bearer "+ jwtToken)
                                    .addJSONObjectBody(new JSONObject()
                                            .put("Request", new JSONObject()
                                                    .put(     "transactionID" , "000" + x)
                                                    .put(    "transactionDate" , "2018-09-10")
                                                    .put(  "referenceID" , "Order/2018/001")
                                            .put(   "sourceAccountNumber" ,     "1111006395939")
                                            .put(  "beneficiaryAccountNumber" , "1111006405800")
                                            .put(  "amount" , "1000000")
                                            .put(     "currency" , "IDR")
                                            .put(  "sourceAccountCustType" , "1")
                                            .put(  "beneficiaryCustType" , "1")
                                            .put(  "remark1" , "Test RTGS 1")
                                    .put(  "remark2" , "BIAYA KLIRING1")
                                    ))
                                    .setPriority(Priority.MEDIUM)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {


                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            Log.d(TAG, "onResponse an error: " + anError.getErrorDetail());
                                            Toast.makeText(getApplicationContext(), "" + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }catch (JSONException e){
                            Toast.makeText(getApplicationContext(), "Error jwt", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onResponse an error: " + anError.getErrorDetail());
                        Toast.makeText(getApplicationContext(), "" + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
