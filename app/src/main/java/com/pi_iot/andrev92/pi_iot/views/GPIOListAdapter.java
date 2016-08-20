package com.pi_iot.andrev92.pi_iot.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.pi_iot.andrev92.pi_iot.IOTApp;
import com.pi_iot.andrev92.pi_iot.R;
import com.pi_iot.andrev92.pi_iot.model.GPIO;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avlad92 on 8/20/2016.
 */
public class GPIOListAdapter extends RecyclerView.Adapter<GPIOListAdapter.GPIOViewHolder> {

    @Override
    public GPIOViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gpio_button,parent,false);
        return new GPIOViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GPIOViewHolder holder, final int position) {
        holder.getGpio_name().setText("GPIO "+(position+2));
        holder.getPositive_btn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IOTApp.getApiService().changeGpio(new GPIO(position+2,1)).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });

            }
        });

        holder.getNegative_btn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IOTApp.getApiService().changeGpio(new GPIO(position+2,0)).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return 26;
    }


    static class GPIOViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.gpio_id)
        TextView gpio_name;

        @BindView(R.id.positive_btn)
        FrameLayout positive_btn;

        @BindView(R.id.negative_btn)
        FrameLayout negative_btn;

        public GPIOViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public TextView getGpio_name() {
            return gpio_name;
        }

        public FrameLayout getPositive_btn() {
            return positive_btn;
        }

        public FrameLayout getNegative_btn() {
            return negative_btn;
        }
    }
}
