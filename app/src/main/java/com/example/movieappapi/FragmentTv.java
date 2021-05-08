package com.example.movieappapi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappapi.models.tv.TvResult;
import com.example.movieappapi.models.tv.TvShowAdapter;
import com.example.movieappapi.networks.Consts;
import com.example.movieappapi.networks.GetRetrofit;
import com.example.movieappapi.networks.MovieService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTv extends Fragment {
    private RecyclerView rvShow;
    private TvShowAdapter tvShowAdapter;

    public FragmentTv(){

    }
    public static FragmentTv newInstance(){
        FragmentTv fragmen = new FragmentTv();
        Bundle args = new Bundle();
        fragmen.setArguments(args);
        return fragmen;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        rvShow = view.findViewById(R.id.rv_tv);
        rvShow.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rvShow.setHasFixedSize(true);
        load();
        return view;
    }

    private void load() {
        MovieService service = GetRetrofit.getInstance();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", Consts.APIKEY);
        params.put("language", Consts.LANGUAGE);
        params.put("page", Consts.PAGE);
        Call<TvResult> call = service.tvAiringToday(params);

        call.enqueue(new Callback<TvResult>() {
            @Override
            public void onResponse(Call<TvResult> call, Response<TvResult> response) {
                if (response.isSuccessful() && response.body().getTvShowList() != null){
                    tvShowAdapter = new TvShowAdapter();
                    tvShowAdapter.setTvShowList(response.body().getTvShowList());
                    rvShow.setAdapter(tvShowAdapter);
                }else {
                    Log.d(Consts.APIERROR, "error");
                }
            }

            @Override
            public void onFailure(Call<TvResult> call, Throwable t) {

                Log.d(Consts.APIERROR, "error");
            }
        });
    }
}
