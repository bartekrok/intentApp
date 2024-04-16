package com.example.intentapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    public static final int REQUEST_CODE = 100;
    TextView tv_result;
    ImageView iv_img;
    Button btn_toSecond;
    Boolean check = false;

    ActivityResultLauncher<Intent> aL = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                Intent intent;
                Log.v("intent", "wywolane CalLBack");
                intent = o.getData();
                if(o.getResultCode()==REQUEST_CODE){
                    Log.v("intent_0", "odpowiedz z aktywnosci");
                    String str = intent.getStringExtra("result");
                    tv_result.setText(str);
                    return;
                }
                if(o.getResultCode()==RESULT_OK){
                    Bitmap picture = (Bitmap) intent.getExtras().get("bitmap");
                    Bitmap target = Bitmap.createScaledBitmap(picture,100,100,true);
                    Log.v("intent_1", "Zrobione");
                    iv_img.setImageBitmap(target);

                }else{
                    Log.v("intent_1", "Nie zrobione");

                }
                }
            });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
        btn_toSecond = findViewById(R.id.btn_toSecond);
        btn_toSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to AdminPageActivity
                check = true;
                /*
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("textToEdit", tv_result.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
                */
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                aL.launch(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            tv_result.setText(data.getStringExtra("editedText"));
            byte[] byteArray = data.getByteArrayExtra("bitmap");

// Convert byte array to Bitmap
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

// Use the bitmap as needed
        iv_img.setImageBitmap(bitmap);
    }
}
