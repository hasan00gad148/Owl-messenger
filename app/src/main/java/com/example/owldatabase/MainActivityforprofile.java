package com.example.owldatabase;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityforprofile extends AppCompatActivity {
    ImageView mImageView ;
    Button mChooseBtn ,nButton ;
    EditText mText , nText ;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.image_View) ;
        mChooseBtn = findViewById(R.id.choose_image_btn) ;
        nButton = findViewById(R.id.button4) ;
        mText = findViewById(R.id.textView) ;
        nText = findViewById(R.id.textView3) ;

        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE} ;
                        requestPermissions(permissions , PERMISSION_CODE);
                    }
                    else{
                        PickImageFromGallery() ;
                    }
                }
                else{

                }
            }
        });
        nButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "your username is empty", Toast.LENGTH_SHORT).show();
                }
                else if (nText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "your bio is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(MainActivityforprofile.this , MainActivity.class) ;
                    startActivity(intent) ;
                }
            }
        });
    }

    private void PickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent , IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED ){
                    PickImageFromGallery();
                }
                else{
                    Toast.makeText(this , "permission denied" , Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            mImageView.setImageURI(data.getData());
        }
    }
}
