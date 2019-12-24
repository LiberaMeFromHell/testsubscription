package ru.job4j.testsub.subscription;

import android.app.Activity;
import android.util.Log;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRequest {

    private final String SUBSCRIPTION_ID = "default_subscription";

    private BillingClient billingClient;
    private Activity activity;

    public PurchaseRequest(BillingClient billingClient, Activity activity) {
        this.billingClient = billingClient;
        this.activity = activity;
        requestProductDetails();
    }

    private void requestProductDetails() {
        Log.d("purchase re", "request Product Details");
        List<String> skuList = new ArrayList<>();
        skuList.add(SUBSCRIPTION_ID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(BillingClient.SkuType.SUBS);
        billingClient.querySkuDetailsAsync(params.build(),
                new SkuDetailsResponseListener() {
                    @Override
                    public void onSkuDetailsResponse(BillingResult billingResult,
                                                     List<SkuDetails> skuDetailsList) {
                        initRequest(skuDetailsList.get(0));
                    }
                });
    }

    private void initRequest(SkuDetails skuDetails) {

        Log.d("purchase req", "started ");

        BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skuDetails)
                .build();

        int responseCode = billingClient.launchBillingFlow(activity,flowParams)
                .getResponseCode();
    }
}
