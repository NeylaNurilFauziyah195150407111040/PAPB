package com.example.praktikumrestapi;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class insert_form extends AppCompatActivity {

    Button submitButton;
    EditText editJudul, editDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_form);
        submitButton = (Button) findViewById(R.id.submitButton);
        editJudul = (EditText) findViewById(R.id.editJudul);
        editDeskripsi = (EditText) findViewById(R.id.editDeskripsi);
        PerpustakaanService perpustakaanService = RetrofitClient.getClient().create(PerpustakaanService.class);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call <Buku> postRequest = perpustakaanService.postBuku(
                        editJudul.getText().toString(),
                        editDeskripsi.getText().toString()
                );
                postRequest.enqueue(new Callback<Buku>() {
                    @Override
                    public void onResponse(Call<Buku> call, Response<Buku> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(insert_form.this, "success", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(insert_form.this, MainActivity.class));
                        } else {
                            Log.d("errt", "" + response.errorBody());
                            Toast.makeText(insert_form.this, "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Buku> call, Throwable t) {
                        Log.d("DataModel", "" + t.getMessage());
                        Toast.makeText(getApplicationContext(), "Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}