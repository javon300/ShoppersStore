package com.mm.shoppersstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;


public class Home extends AppCompatActivity
{

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView StatusBlueTv, PairedTv;
    ImageView BlueTv;
    Button OnBtn, OffBtn, DiscoverBtn, PairedBtn;

    BluetoothAdapter BlueAdapter;


    private static final String TAG = "HOME CLASS";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button startButton;

    List<Product> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        readFromDatabase();


        StatusBlueTv = findViewById(R.id.statusBluetoothTv);
        PairedTv = findViewById(R.id.pairedTv);
        BlueTv = findViewById(R.id.bluetoothTv);
        OnBtn = findViewById(R.id.onBtn);
        OffBtn = findViewById(R.id.offBtn);
        DiscoverBtn = findViewById(R.id.discoverBtn);
        PairedBtn = findViewById(R.id.pairedBtn);

        BlueAdapter = BluetoothAdapter.getDefaultAdapter();

        if (BlueAdapter == null) {
            StatusBlueTv.setText("Bluetooth is not available");
        } else{
            StatusBlueTv.setText("Bluetooth is available");
        }

        if(BlueAdapter.isEnabled()) {
            BlueTv.setImageResource(R.drawable.ic_action_on);
        } else {
            BlueTv.setImageResource(R.drawable.ic_action_off);
        }

        OnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                if (!BlueAdapter.isEnabled()) {
                    showToast("Turning On Bluetooth");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }

                else {
                    showToast("Bluetooth is already on");
                }
            }
        });

        OffBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                if (BlueAdapter.isEnabled()) {
                    BlueAdapter.disable();
                    showToast("Turning Bluetooth Off");
                    BlueTv.setImageResource(R.drawable.ic_action_off);
                }
                else {
                    showToast("Bluetooth is already off");
                }
            }
        });

        DiscoverBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                if (!BlueAdapter.isDiscovering()) {
                    showToast("Making Your Device Discoverable");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }
            }
        });

        PairedBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v){
                if (BlueAdapter.isEnabled()) {
                    PairedTv.setText("Paired Devices");
                    Set<BluetoothDevice> devices = BlueAdapter.getBondedDevices();
                    for (BluetoothDevice device: devices) {
                        PairedTv.append("\nDevice:" + device.getName() + "," + device);
                    }
                }
                else {
                    showToast("Turn on bluetooth to get paired devices");
                }
            }
        });


    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    BlueTv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth is on");
                }

                else {
                    showToast("Couldn't on Bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }



    public void rc(List<Product> data ){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ProductRecycleViewAdapter adapter = new ProductRecycleViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));}



    public void readFromDatabase()
    {
        db= FirebaseFirestore.getInstance();
        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {


//                                product.name = document.get("name").toString();
//                                product.bran = document.get("brand").toString();
//                                product.description = document.get("description").toString();
//                                product.imageAddress = document.get("imageAddress").toString();
                                Product product1 = new Product(document.get("name").toString(),document.get("brand").toString(), document.get("description").toString(), document.get("imageAddress").toString());


                                Log.d(TAG, document.getId() + " => " + product1.name);
                                data.add(product1);
                                rc(data);
                            }
                        } else
                        {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }


}