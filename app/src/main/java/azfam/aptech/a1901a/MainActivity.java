package azfam.aptech.a1901a;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnch,btnup;
    ImageView imageview;
    StorageReference ref;
    private int Imagedata=1;
    Uri filepath;
    String a = "Changes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnch=findViewById(R.id.select);
        btnup=findViewById(R.id.btnupload);
        imageview=findViewById(R.id.image);
        ref= FirebaseStorage.getInstance().getReference().child("Imageone");
        btnch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select();
            }
        });
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });
    }
    public void select()
    {
        Intent it=new Intent();
        it.setType("image/*");
        it.setAction(it.ACTION_GET_CONTENT);
        startActivityForResult(it.createChooser(it,"Choose Image"),Imagedata);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Imagedata && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filepath=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void upload()
    {

    }
}