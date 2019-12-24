package ru.job4j.testsub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import ru.job4j.testsub.subscription.GooglePlayConnector;
import ru.job4j.testsub.subscription.PurchaseRequest;

public class MainActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecView();
        FloatingActionButton button = findViewById(R.id.floatingActionButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSubscription();

            }
        });
    }

    private void initRecView () {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void checkSubscription() {
        BillingClient billingClient = BillingClient.newBuilder(this)
                .setListener(this)
                .enablePendingPurchases()
                .build();
        GooglePlayConnector connector = new GooglePlayConnector(billingClient, this);
        billingClient.startConnection(connector);
        PurchaseRequest purchaseRequest = new PurchaseRequest(billingClient,this);
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list) {

    }
}
