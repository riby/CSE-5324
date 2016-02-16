package com.service.riby.mytestpro;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int num1;
    private int num2;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getNumber();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    public void getNumber()
    {
        num1=0;
        num2=0;

        Random random =new Random();
        while(num1==num2) {
            num1 = random.nextInt();
            num2 = random.nextInt();
        }
        Button button1= (Button)findViewById(R.id.button1);

        Button button2= (Button)findViewById(R.id.button2);

        button1.setText(String.valueOf(num1));
        button2.setText(String.valueOf(num2));

    }
    public void button1_click(View view) {

        if(num1>num2)
        {

            Snackbar.make(view, "Correct", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            score++;

        }
        else
        {

            Snackbar.make(view, "You Wrong!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            score--;
        }

        TextView text1= (TextView)findViewById(R.id.textView2);
        text1.setText(String.valueOf(score));

        getNumber();
    }


    public void button2_click(View view) {


        if(num1<num2)
        {

            Snackbar.make(view, "Correct", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            score++;

        }
        else
        {

            Snackbar.make(view, "You Wrong!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            score--;
        }
        TextView text1= (TextView)findViewById(R.id.textView2);
        text1.setText(String.valueOf(score));

        getNumber();
    }
}
