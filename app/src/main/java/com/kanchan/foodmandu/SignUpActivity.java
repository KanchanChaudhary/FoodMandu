package com.kanchan.foodmandu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kanchan.foodmandu.api.UserApi;
import com.kanchan.foodmandu.model.User;
import com.kanchan.foodmandu.serverresponse.ImageResponse;
import com.kanchan.foodmandu.serverresponse.SignUpResponse;
import com.kanchan.foodmandu.strictmode.StrictModeClass;
import com.kanchan.foodmandu.url.Url;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private ImageView imgprofile;
    private EditText etfirstname, etlastname, etsignupusername, etsignuppassword, etconfirmpassword;
    private Button btnsignup;
    String  imagepath= " " ;
    private  String imageName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        imgprofile=findViewById(R.id.imgProfile);
        etfirstname=findViewById(R.id.etFirstname);
        etlastname=findViewById(R.id.etLastname);
        etsignupusername=findViewById(R.id.etSignupUsername);
        etsignuppassword=findViewById(R.id.etSignupPassword);
        etconfirmpassword=findViewById(R.id.etConfirmPassword);
        btnsignup=findViewById(R.id.btnSignup);


        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etsignuppassword.getText().toString().equals(etconfirmpassword.getText().toString())) {
                    if (validate()) {
                        SaveImageOnly();
                        SignUp();
                    }

                }else {
                    Toast.makeText(SignUpActivity.this, "Password Does Not Match",Toast.LENGTH_SHORT).show();
                    etsignuppassword.requestFocus();
                    return;
                }
            }
        });
    }
    private boolean validate(){
        boolean status=true;
        if (etsignupusername.getText().toString().length()< 6){
            etsignupusername.setError("Minimum 6 Characters");
            status=false;
        }
        return status;
    }
    private void BrowseImage(){
        checkPermission();
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_OK){

            if (data==null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            Uri uri=data.getData();
            imgprofile.setImageURI(uri);
            imagepath=getRealPathFromUri(uri);
        }catch (Exception e){
            Toast.makeText(this, "Excep"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

//        Toast.makeText(this, imagepath, Toast.LENGTH_SHORT).show();

    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    private void SaveImageOnly() {
        File file=new File(imagepath);
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",
                file.getName(),requestBody);
        ;
       UserApi userApi= Url.getInstance().create(UserApi.class);
        Call<ImageResponse> responseBodyCall= userApi.uploadImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



    private void SignUp(){
        String fname = etfirstname.getText().toString();
        String lname = etlastname.getText().toString();
        String username = etsignupusername.getText().toString();
        String password = etsignuppassword.getText().toString();
        String conpassword = etconfirmpassword.getText().toString();

        User user=new User(fname,lname,username,password,conpassword, imageName);

        UserApi userApi= Url.getInstance().create(UserApi.class);
        Call<SignUpResponse> signUpCall= userApi.registerUser(user);

        signUpCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "Registered", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    private void checkPermission(){
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this ,new String[]
                    {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

    }

}

