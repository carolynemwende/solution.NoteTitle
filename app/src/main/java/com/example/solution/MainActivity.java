package com.example.solution;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.solution.database.DatabaseHelper;
import com.example.solution.database.Note;
import com.example.solution.database.NotesAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;
    List<Note> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getBaseContext(),AddNoteActivity.class));
            }
        });


    list= findViewById(R.id.list);



    }

private void displayNotes() {
    DatabaseHelper  databaseHelper = new DatabaseHelper(getBaseContext(),"notes",null,1);
    notesList = new ArrayList<Note>();
    notesList = databaseHelper.getNotes();
    Log.d("MYNOTES","my database has"+notesList.size()+"notes");

    NotesAdapter notesAdapter= new NotesAdapter(getBaseContext(),0,notesList);

    NotesAdapter NotesAdapter= new NotesAdapter(this,android.R.layout.simple_list_item_1,notesList);
    list.setAdapter(notesAdapter);
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Note clickedNote =notesList.get(position);
            Intent intent = new Intent(getBaseContext(),ViewNote.class);
            intent.putExtra("NOTE_ID",clickedNote.getId());
            startActivity(intent);
        }
    });


}
public void displayNamesMethod (){
        List<String> nameLists= new ArrayList<String>();
        nameLists.add("Carol Mwende");
        nameLists.add("Jeruto Tiffany");
        nameLists.add("Zakia Mustafa");
        nameLists.add("Baraza Tracy");
        nameLists.add("Grace Nyokabi");
        nameLists.add("rima mercy");
        nameLists.add("mwasheti Mwende");
        nameLists.add("Lucy Muringo");
        nameLists.add("Nasambu Nat");
        nameLists.add("Asha Ally");
        nameLists.add("John Doe");




    ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nameLists);
    list.setAdapter(arrayAdapter);

}
    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();


//        displayNamesMethod();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
