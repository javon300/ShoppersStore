package com.mm.shoppersstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.billingclient.api.*

class BuyActivity : AppCompatActivity() {

    private lateinit var buy_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        //Storing the button Id
        buy_button = findViewById(R.id.button_buy)

        val skuList = ArrayList<String>()
        skuList.add("android.test.purchased")

        val purchasesUpdatedListener = PurchasesUpdatedListener{
                billingResult, purchaseList ->
        }

        //Initializing the Billing client
        var billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases().build()
        //Defining the on click method for the buy now button.
        buy_button.setOnClickListener{
            billingClient.startConnection(object: BillingClientStateListener {
                override fun onBillingServiceDisconnected() {
                    TODO("Not yet implemented")
                }

                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK){
                        val params = SkuDetailsParams.newBuilder()
                        params.setSkusList(skuList)
                            .setType(BillingClient.SkuType.INAPP)

                        billingClient.querySkuDetailsAsync(params.build()){
                                billingResult, skuDetailsList ->

                            for (skuDetail in skuDetailsList!!){
                                val flowPurchase = BillingFlowParams.newBuilder()
                                    .setSkuDetails(skuDetail)
                                    .build()

                                val responseCode = billingClient.launchBillingFlow(this@BuyActivity, flowPurchase).responseCode
                            }

                        }


                    }
                }

            })
        }
    }
}