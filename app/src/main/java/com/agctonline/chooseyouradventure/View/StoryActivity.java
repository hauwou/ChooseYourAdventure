package com.agctonline.chooseyouradventure.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agctonline.chooseyouradventure.Model.Page;
import com.agctonline.chooseyouradventure.Model.Story;
import com.agctonline.chooseyouradventure.R;

//When this class is called, it will load up the
// activity_story.xml file and will display the default
// values of the xml files if there is no method called
// manipulates the xml.


public class StoryActivity extends Activity {

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;


    public static final String TAG = StoryActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intentFromMainAct = getIntent();
        //do a ctrl+q to get the purpose of getIntent method.
        //it say "Return the intent that started this activity."
        //the line above is basically grab the object and params
        //that was passed from the mainActivity

        mName = intentFromMainAct.getStringExtra(getString(R.string.key_name));
        //use the getstringextra method to extra the value using the key



        //check to make sure that the variable has value, a way to sanitize
        //data coming from outside sources
        //if use getIntExtra, can use ("key", -1),
        // the second param is default value if key is null.
        if (mName == null) {
            mName = "Yo";
        }

        Log.d(TAG, mName);

        mImageView= (ImageView) findViewById(R.id.storyImageView);
        mTextView= (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2= (Button) findViewById(R.id.choiceButton2);

        loadPage(0);


    }

    private void loadPage(int choice) {
        mCurrentPage =mStory.getPage(choice);

        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        //the page.getImageId() method returns the int
        //value of the image set in the Page class
        //The getResources().getDrawable() method is a          //built in system method that gets the image
        //from the Image Id.

        mImageView.setImageDrawable(drawable);
        //Place the image from the drawable variable
        //to the mImageView placeholder

        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText,mName);
        //dynamically inserting the user name into
        // the story's string.

        mTextView.setText(pageText);

        if (mCurrentPage.isFinal()){
            //in this conditional block, hide the choice1 button if reach the end of the story, set text for choice2, set onclicklistener for choice2. Inside the onclick, use finish() to stop the current activity and return to previous (main) activity. Alternatively, use intent to get to the main activity.

            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play Again");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
        else {
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());
            //Note, the set/get Text methods from the
            // Page class is different than the
            // get/set Text from the built in TextView class

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }




    }


    //The 2 methods below are not needed for now and are created by default
//
//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_story, menu);
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
