package men.ngopi.zakwan.jasanesia;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import men.ngopi.zakwan.jasanesia.Model.MessageModel;

public class ChatActivity extends AppCompatActivity {

    private EditText chatEditText;
    private ImageView sendButton;

    private String TAG = "===== Chat Activity";

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView postList;
    private FirestoreRecyclerAdapter<MessageModel, MessageHolder> adapter;
    private FirestoreRecyclerOptions<MessageModel> response;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        firebaseFirestore = FirebaseFirestore.getInstance();

        postList = findViewById(R.id.activity_chat_recycle_layout);
        postList.setNestedScrollingEnabled(false);
        postList.setLayoutManager(new LinearLayoutManager(this));
        getPosts();


        chatEditText = findViewById(R.id.activity_chat_edit_text);
        sendButton =  findViewById(R.id.activity_chat_send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG, "onClick: "+ "button clicked" );
                String str = chatEditText.getText().toString();
                chatEditText.setText("");
                sendMessage(str , "0" , "1");

            }
        });
    }

    private void sendMessage(String str , String tipe_transak , String tipe_user){
        MessageModel tmp = new MessageModel(
                "1",
                "Damaz",
                str,
                "" + Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE),
                "0",
                "1",
                "0",
                "0"
        );
        firebaseFirestore.collection("chat").document().set(tmp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.w(TAG, "onComplete: complete" );
                } else {
                    Log.i("Error", "Error Upload Image Profile");
                }
            }
        });
    }

    private void getPosts(){
        firebaseFirestore.collection("chat")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        Query query = firebaseFirestore.collection("chat")
                .orderBy("waktu", Query.Direction.ASCENDING);

        response = new FirestoreRecyclerOptions.Builder<MessageModel>()
                .setQuery(query, MessageModel.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<MessageModel, MessageHolder>(response) {
            @Override
            public int getItemCount() {
                Log.d("Get Id User", "onEvent: "+ super.getItemCount());
                return super.getItemCount();
            }

            @Override
            protected void onBindViewHolder(@NonNull final MessageHolder holder,final int position, @NonNull MessageModel model) {
                Log.d("Get Id User", "onEvent: "+ model.getId());

                if(model.getTipe_user().equals("1") && model.getTipe_transaksi().equals("0")){
                    holder.freelancerLinearLayout.setVisibility(View.GONE);
                    holder.textUserSendiri.setText(model.getText());
                } else if(model.getTipe_user().equals("0") && model.getTipe_transaksi().equals("0")){
                    holder.sendirilancerLinearLayout.setVisibility(View.GONE);
                    holder.nama.setText(model.getNama_pengirim());
                    holder.textUserLain.setText(model.getText());

                }else if(model.getTipe_user().equals("0") && model.getTipe_transaksi().equals("1") && model.getStatus_pembayaran().equals("0")){
                    holder.transferLinearLayout.setVisibility(View.VISIBLE);
                    holder.freelancerLinearLayout.setVisibility(View.GONE);
                    holder.sendirilancerLinearLayout.setVisibility(View.GONE);
                    holder.angka.setText(model.getText());
                    holder.namaForTransac.setText(model.getNama_pengirim());
                    holder.transBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String id = adapter.getSnapshots().getSnapshot(position).getId();
//                          Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class );
//                          Intent mainActivity = new Intent(getApplicationContext(),ChatActivity.class );
                            Intent mainActivity = new Intent(getApplicationContext(),SummaryActivity.class );
                            mainActivity.putExtra("ID_MESSAGE", id);
//                          Intent mainActivity = new Intent(getApplicationContext(),ConfirmActivity.class );
                            startActivity(mainActivity);
                        }
                    });
                }else if(model.getTipe_user().equals("0") && model.getTipe_transaksi().equals("1") && model.getStatus_pembayaran().equals("1")){
                    holder.suksesLinearLayout.setVisibility(View.VISIBLE);
                    holder.freelancerLinearLayout.setVisibility(View.GONE);
                    holder.sendirilancerLinearLayout.setVisibility(View.GONE);
                    holder.namaSuksesText.setText(model.getNama_pengirim());
                    holder.nominalSukses.setText(model.getText());
                    holder.angka.setText(model.getText());

                }
            }

            @NonNull
            @Override
            public MessageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.activity_chat_recycle_layout, viewGroup, false);

                return new MessageHolder(view);

            }
        };

        adapter.notifyDataSetChanged();
        postList.setAdapter(adapter);

    }


    public class MessageHolder extends RecyclerView.ViewHolder {
        private TextView nama, textUserLain , textUserSendiri , angka , namaForTransac , namaSuksesText , nominalSukses;
        private LinearLayout sendirilancerLinearLayout , freelancerLinearLayout , transferLinearLayout , suksesLinearLayout;
        private Button transBtn;
        public MessageHolder(View itemView){
            super(itemView);
            nama = itemView.findViewById(R.id._activity_chat_nama_freelancer);
            textUserLain = itemView.findViewById(R.id._activity_chat_message_freelancer);
            textUserSendiri = itemView.findViewById(R.id._activity_chat_message_sendiri);
            angka = itemView.findViewById(R.id._activity_chat_nominal);
            transBtn = itemView.findViewById(R.id.activity_chat_transfer_button);
            sendirilancerLinearLayout = itemView.findViewById(R.id._activity_chat_sendiri_linear);
            freelancerLinearLayout = itemView.findViewById(R.id._activity_chat_freelancer_linear);
            transferLinearLayout = itemView.findViewById(R.id.activity_chat_transac_layout);
            namaForTransac = itemView.findViewById(R.id._activity_chat_nama_freelancer_for_transac);
            suksesLinearLayout = itemView.findViewById(R.id.activity_chat_transac_layout_setelah_sukses);
            namaSuksesText = itemView.findViewById(R.id._activity_chat_nama_freelancer_for_transac_sukses);
            nominalSukses = itemView.findViewById(R.id._activity_chat_nominal_setelah_trasnfer_sukses);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
