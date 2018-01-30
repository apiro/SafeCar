package com.example.albertomariopirovano.safecar.inner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.albertomariopirovano.safecar.R;
import com.example.albertomariopirovano.safecar.data_comparators.KMComparator;
import com.example.albertomariopirovano.safecar.services.FetchService;

import java.util.Comparator;

/**
 * Created by albertomariopirovano on 04/04/17.
 */

public class TabOrderKM extends Fragment implements TabFragment, TAGInterface {

    private static final String TAG = "TabOrderKM";
    private String name = "KM";
    private ListView listView;
    private Comparator comparator = new KMComparator();
    private FetchService dataService = FetchService.getInstance();
    private SwipeRefreshLayout swipeRefreshLayout;

    public String getName() {
        return name;
    }

    public String getAssignedTag() {
        return TAG;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG, "Creating view");

        final View v = inflater.inflate(R.layout.tab_order_km, container, false);

        listView = (ListView) v.findViewById(R.id.listKM);

        if(listView == null) {
            Log.i(TAG,"Null list view");
        }

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefreshHomeKM);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Refreshed");
                dataService.insertTrips("KM", v, getComparator(), listView, 0, 10);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        Log.i(TAG, "Hidden trip insertion task");
        dataService.insertTrips("KM", v, getComparator(), listView, 0, 10);

        return v;
    }

    public Comparator getComparator() {
        return comparator;
    }
}
