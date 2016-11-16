package venture.cs414.android.monopoly2.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import venture.cs414.android.monopoly2.R;

public class UnmortgageProperty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unmortgage_property);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void clickUnmortgageButtonUnmortgageProperty(View view){
        notify("Testing if its working");
    }

    public void clickCancelButtonUnmortgageProperty(View view){
        //Todo implement code
        try {
            //implement call
            Intent intent = new Intent(this, MainActivity.class);
            //Todo need to pass the serializable object when it is created
            //intent.putExtra("difficulty", 1);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void notify(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}