package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class SecondActivity extends AppCompatActivity {

    EditText et_result;
    Button btn_toFirst;
    ImageView iv_second;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        et_result = findViewById(R.id.et_result);
        iv_second = findViewById(R.id.iV_second);
        iv_second.setImageResource(R.drawable.facet1);

        //
        Drawable drawable = iv_second.getDrawable();

// Convert the drawable to a Bitmap
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            // If the drawable is not a BitmapDrawable, you can create a new Bitmap from it
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        final Bitmap finalBitmap = bitmap;

        //

        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        et_result.setText(intent.getStringExtra("textToEdit"));
        btn_toFirst = findViewById(R.id.btn_toFirst);
        btn_toFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to AdminPageActivity
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("editedText", et_result.getText().toString());
                setResult(MainActivity.REQUEST_CODE,intent);
                Intent intent2 = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("bitmap", finalBitmap);
                setResult(RESULT_OK,intent);




                finish();
            }
        });
    }
}