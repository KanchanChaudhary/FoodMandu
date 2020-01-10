package com.kanchan.foodmandu;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kanchan.foodmandu.api.ImageApi;
import com.kanchan.foodmandu.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSlider extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_slider);


        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addslider();
            }
        });
    }

    private void addslider()
    {
        ImageApi imageAPI = Url.getInstance().create(ImageApi.class);

        Call<Void> voidCall = imageAPI.addimage(Url.token,imageView.toString());

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddSlider.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddSlider.this, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddSlider.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
