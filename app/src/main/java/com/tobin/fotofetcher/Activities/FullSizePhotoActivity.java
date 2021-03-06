package com.tobin.fotofetcher.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.tobin.fotofetcher.R;

public class FullSizePhotoActivity extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_size_photo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String imageName = bundle.getString("image name");
        String tags = bundle.getString("tags");
        layout = (LinearLayout) findViewById(R.id.tagContainer);
        displayTags(tags);


    }

    public void displayTags(String tags){
        String[] tagArray = tags.split(",");
        Button btn;
        int counter = 0;

        for (int i = 0; i < tagArray.length / 3; i++) {
            TableRow row = new TableRow(this);

            for (int k = 1; k < 4; k++) {
                btn = new Button(this);
                TableRow.LayoutParams tr = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
                layout.setWeightSum(12.0f);
                btn.setLayoutParams(tr);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn.setBackground(this.getResources().getDrawable(R.drawable.button_shape));
                }
                btn.setTextColor(Color.LTGRAY);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                btn.setText(tagArray[counter]);
                counter++;
                row.addView(btn);
            }
            layout.addView(row);
        }
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
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

}
