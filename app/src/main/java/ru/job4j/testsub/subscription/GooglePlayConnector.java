package ru.job4j.testsub.subscription;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;

public class GooglePlayConnector implements BillingClientStateListener {

    private BillingClient billingClient;
    private AppCompatActivity activity;

    public GooglePlayConnector(BillingClient billingClient, AppCompatActivity activity) {
        this.billingClient = billingClient;
        this.activity = activity;
    }

    @Override
    public void onBillingSetupFinished(BillingResult billingResult) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            Log.d("billing req", "OK");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.SERVICE_TIMEOUT) {
            Log.d("billing req", "SERVICE_TIMEOUT");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE) {
            Log.d("billing req", "SERVICE_UNAVAILABLE");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.BILLING_UNAVAILABLE) {
            Log.d("billing req", "BILLING_UNAVAILABLE");
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ERROR) {
            Log.d("billing req", "ERROR");
        }else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED) {
            Log.d("billing req", "FEATURE_NOT_SUPPORTED");
        }
    }

    @Override
    public void onBillingServiceDisconnected() {
        //TODO Try to restart the connection on the next request to Google Play by calling the startConnection() method.
        Log.d("billingResult", "disconnected");
    }
}
