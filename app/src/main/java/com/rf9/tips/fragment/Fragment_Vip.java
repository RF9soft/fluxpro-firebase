package com.rf9.tips.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.rf9.tips.R;
import com.rf9.tips.activity.ViewDetailsActivity;
import com.rf9.tips.databinding.FragmentVipBinding;
import com.rf9.tips.util.AppUtils2;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Fragment_Vip extends Fragment implements PurchasesUpdatedListener {
    FragmentVipBinding binding;

    private RecyclerView dashBoardRecycler, recent;
    ImageView profile;
    LinearLayoutManager HorizontalLayout;
    TextView old;

    public BillingClient billingClient;

    List<SkuDetails> skuDetailsList = new ArrayList<>();
    List<String> skuList = new ArrayList<>();
    boolean enableAll = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentVipBinding.inflate(inflater, container, false);


        onClickListeners();
        initBillingClient();

        return binding.getRoot();
    }

    private void initBillingClient() {

        // binding.llAllVIP.setVisibility(View.GONE);
        billingClient = BillingClient.newBuilder(getContext())
                .setListener(this)
                .enablePendingPurchases()
                .build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NotNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    loadPurchaseAsync();

                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        });

        skuList.add(AppUtils2.ALL_VIP);

    }

    private void loadPurchaseAsync() {
        billingClient.queryPurchasesAsync(BillingClient.SkuType.INAPP, (billingResult, list) -> {
            for (Purchase purchase : list) {
                for (String sku : purchase.getSkus()) {
                    if (sku.equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                        enableAll = true;

                    }

                }
                //consumePurchase(purchase);
            }
            onGetInAppProductList();
        });
    }

    private void onGetInAppProductList() {
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder().setSkusList(skuList);
        billingClient.querySkuDetailsAsync(params.setType(BillingClient.SkuType.INAPP).build(),
                (billingResult, skuDetailsList) -> {
                    if (skuDetailsList.isEmpty()) {
                        Toast.makeText(getContext(), "No Product Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    this.skuDetailsList.addAll(skuDetailsList);
                    for (SkuDetails skudetail : skuDetailsList) {
                        if (skudetail.getSku().equalsIgnoreCase(AppUtils2.ALL_VIP)) {
                            binding.tvAllVIP.setText(enableAll ? getActivity().getString(R.string.unlocked_all_vip) : getActivity().getString(R.string.access_all_vip));
                            // binding.llAllVIP.setBackgroundResource(enableAll ? R.drawable.grad1 : R.drawable.allsub);
                            binding.tvAllVIPDesc.setVisibility(enableAll ? View.GONE : View.VISIBLE);
                        }
                    }

                    getActivity().runOnUiThread(() -> {

                        binding.llAllVIP.setVisibility(View.VISIBLE);

                    });

                });
    }

    private void onClickListeners() {
        binding.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "ELITE VIP");


                } else {
                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "FIXED VIP");


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "HTFT VIP");


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "CORRECT SCORE");


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "BIG WIN VIP");


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });
        binding.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableAll) {
                    intentToActivity("VIP", "SPECIAL");


                } else {

                    openPopUp(AppUtils2.ALL_VIP);

                }
            }
        });


        binding.llAllVIP.setOnClickListener(view -> {
            if (!enableAll) {
                callInAppPurchasePlan(AppUtils2.ALL_VIP);
            }
        });

    }

    private void intentToActivity(String sub, String page) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        getActivity().startActivity(intent);
    }

    private void openPopUp(String plan) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.dialog_purchase_vip);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
//        String allPrice = getSkuDetails(AppUtils2.ALL_VIP).getPrice();
        String planName = "";


//        ((AppCompatButton) dialog.findViewById(R.id.btnPurchaseAllPlan))
//                .setText("ALL VIP Access\n (Life Time Access)\n" + allPrice);
        ((AppCompatButton) dialog.findViewById(R.id.btnPurchaseAllPlan))
                .setText("ALL VIP Access\n (Life Time Access)\n");


        dialog.findViewById(R.id.btnPurchaseAllPlan).setOnClickListener(v -> {
            callInAppPurchasePlan(AppUtils2.ALL_VIP);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public SkuDetails getSkuDetails(String id) {
        for (SkuDetails skuDetails : skuDetailsList) {
            if (skuDetails.getSku().equals(id)) {
                return skuDetails;
            }
        }
        return null;
    }

    private void callInAppPurchasePlan(String sku) {
        SkuDetails skuDetails = getSkuDetails(sku);
        if (skuDetails != null) {
            BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                    .setObfuscatedAccountId(UUID.randomUUID().toString())
                    .setSkuDetails(skuDetails)
                    .build();
            billingClient.launchBillingFlow(getActivity(), billingFlowParams);
        } else {
            Toast.makeText(getContext(), "This Product not avialable temporary", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {
        int responseCode = billingResult.getResponseCode();
        if (responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(getActivity(), "Your purchase has been canceled, we hope that you will return soon", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void handlePurchase(Purchase purchase) {
        boolean load = true;
        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged()) {
                load = false;

                AcknowledgePurchaseParams acknowledgePurchaseParams =
                        AcknowledgePurchaseParams.newBuilder()
                                .setPurchaseToken(purchase.getPurchaseToken())
                                .build();
                billingClient.acknowledgePurchase(acknowledgePurchaseParams, billingResult -> {
                    getActivity().runOnUiThread(() -> {

                        binding.llAllVIP.setVisibility(View.GONE);
                        loadPurchaseAsync();
                    });
                });
            }
        }
        if (load) {

            binding.llAllVIP.setVisibility(View.GONE);
            loadPurchaseAsync();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

}
