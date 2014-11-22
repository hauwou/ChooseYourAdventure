package com.agctonline.chooseyouradventure.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.agctonline.chooseyouradventure.R;


public class MainActivity extends Activity {

    private EditText mNameField; //create class member variables
    private Button mStartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for what happens when users click on start button here

                String name = mNameField.getText().toString(); //get the user input from field
                //Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                //"this" refers to the anon class called by "new" keyword
                //must add the parent class so that maketext knows which context it belongs to
                startStory(name);//call the startStory method, there is no need to create
                //a new function here, but for demo purpose...
            }
        });
    }

    private void startStory(String nameParam){
        Intent goToNewWindow = new Intent(this,StoryActivity.class);
        //create new intent object, 2 parameters, this=current context/activity window,
        //2nd param is where you want to navigate to (which is the storyActivity window.
        //Intent in this case is (you are here, where you want to go - .class is needed)

        //.putExtra is a method of the Intent class that can be used to pass params of extra info,
        //info are passed as key/value pair, need to create multiple putExtra statements for
        //multiple info params
        //goToNewWindow.putExtra("name",nameParam);
        //goToNewWindow.putExtra("age",ageParam);

        goToNewWindow.putExtra(getString(R.string.key_name),nameParam);

        startActivity(goToNewWindow);
        //startActivity is the built in android system method to "execute" an activity intent
    }


    @Override
    protected void onResume() {
        super.onResume();
        //onResume is part of the android life cycle, when the finished() method was invoked in story activity and returned to main activity, main activity is "resumed", instead of "created"
        //mNameField.setText("");//reset name field
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
