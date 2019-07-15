
    package com.example.solution;

            import android.content.Context;
            import android.content.Intent;
            import android.graphics.Bitmap;
            import android.os.Bundle;
            import android.provider.MediaStore;
            import android.support.design.widget.FloatingActionButton;
            import android.support.design.widget.Snackbar;
            import android.support.v7.app.AppCompatActivity;
            import android.support.v7.widget.Toolbar;
            import android.util.Log;
            import android.view.View;
            import android.widget.Button;
            import android.widget.EditText;
            import android.widget.ImageView;
            import android.widget.Toast;

            import com.example.solution.database.DatabaseHelper;
            import com.example.solution.database.Note;

            import javax.annotation.Nullable;

public class AddNoteActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etNote;
    Button btnaddphoto;
    Button btnaddvoicenote;
    Button btnsave;
    String noteText;
    String title;
    ImageView imgView;

    private static final int CAPTURE_IMAGE_REQUEST_CODE=500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        btnaddphoto = findViewById(R.id.btnaddphoto);
        btnaddvoicenote = findViewById(R.id.btnaddvoicenote);
        btnsave = findViewById(R.id.btnsave);
        imgView =findViewById(R.id.imgView);


      btnaddphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_IMAGE_REQUEST_CODE);
            }
        });



        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                noteText = etNote.getText().toString();
                Note note = new Note(title,noteText);
                DatabaseHelper databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
                long rows = databaseHelper.addNote(note);
                Log.d("AddNote","the number of notes is "+rows);
            }
        });





    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==CAPTURE_IMAGE_REQUEST_CODE&& requestCode==RESULT_OK){
            Bundle bundle=data.getExtras();
            Bitmap bitmap=(Bitmap)bundle.get("data");
            imgView.setImageBitmap(bitmap);
            Log.d("name",bitmap.toString());
        }
    }



}