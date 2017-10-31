package com.example.ftrani.apiclimademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tvTemperatura, tvHumedad, tvPresion, tvUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemperatura = (TextView) findViewById(R.id.tvTemperatura);
        tvPresion = (TextView) findViewById(R.id.tvPresion);
        tvHumedad = (TextView) findViewById(R.id.tvHumedad);
        tvUbicacion = (TextView) findViewById(R.id.tvUbicacion);

        final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        final String KEY_API = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

        //Call<City> cityCall = service.getCity(3835994, KEY_API, "metric", "es");  //Santa Rosa
        //Call<City> cityCall = service.getCity(3860259, KEY_API, "metric", "es"); //Cordoba - 3860259
        Call<City> cityCall = service.getCity(3836564, KEY_API, "metric", "es"); //San Salvador Jujuy 3836564

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();

                tvUbicacion.setText(String.valueOf(city.getName()));
                tvTemperatura.setText(String.valueOf(city.getClima().getTemp())+" Celsius");
                tvHumedad.setText(String.valueOf(city.getClima().getHumidity())+" %");
                tvPresion.setText(String.valueOf(city.getClima().getPressure())+" HPA");

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
