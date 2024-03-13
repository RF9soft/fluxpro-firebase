package com.rf9.tips.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.rf9.tips.R;
import com.rf9.tips.activity.ViewDetailsActivity;

public class FeedFragment extends Fragment {

    View view;
    LinearLayout one, two, three, four, five, six;
    AppCompatImageView tele;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        tele = view.findViewById(R.id.joinID);

        onClickListeners();

        return view;

    }

    private void onClickListeners() {
        one.setOnClickListener(view -> {
            intentToActivity("Free", "Daily Win Tips");
        });

        two.setOnClickListener(view -> {
            intentToActivity("Free", "Safe Picks");
        });

        three.setOnClickListener(view -> {
            intentToActivity("Free", "Daily 5+ Odds");
        });

        four.setOnClickListener(view -> {
            intentToActivity("Free", "Treble Tips");
        });

        five.setOnClickListener(view -> {
            intentToActivity("Free", "OverUnder");
        });

        six.setOnClickListener(view -> {
            intentToActivity("Free", "Mixed Tips");
        });
        tele.setOnClickListener(view -> {
            Intent intentt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/+J2aQCBPI5TRkZDJl"));
            startActivity(intentt);
        });
    }

    private void intentToActivity(String sub, String page) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        getActivity().startActivity(intent);
    }
}
