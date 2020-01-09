package com.kanchan.foodmandu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kanchan.foodmandu.api.UserApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Url;

public class SignUpActivity extends AppCompatActivity {
    private ImageView imgprofile;
    private EditText etfirstname, etlastname, etsignupusername, etsignuppassword, etconfirmpassword;
    private Button btnsignup;
    String  imagepath;
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
        Uri uri=data.getData();
        imgprofile.setImageURI(uri);
        imagepath=getRealPathFromUri(uri);

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

        UserApi userApi=


    }
    private void SignUp(){

    }

}

