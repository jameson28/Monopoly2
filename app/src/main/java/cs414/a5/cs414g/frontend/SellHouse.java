package cs414.a5.cs414g.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import cs414.a5.cs414g.backend.GameFacade;
import venture.cs414.android.cs414g.R;

public class SellHouse extends AppCompatActivity {

    private GameFacade gameFacade;

    private Spinner propertySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_house);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameFacade = GameFacade.getInstance();

        propertySpinner = (Spinner)findViewById(R.id.SelectPropertyDropdownSellHouse);
        ArrayAdapter<String> propertyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, gameFacade.getStreetsCanSellHouse());
        propertySpinner.setAdapter(propertyAdapter);
    }

    public void clickSellButtonSellHouse(View view){
        if(propertySpinner.getSelectedItem() != null){
            String propertyName = propertySpinner.getSelectedItem().toString();
            gameFacade.sellAHouse(propertyName);
            notify("You sold a house from " + propertyName);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void clickCancelButtonSellHouse(View view){
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
