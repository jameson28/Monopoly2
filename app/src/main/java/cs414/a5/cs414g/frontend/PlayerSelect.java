package cs414.a5.cs414g.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;

import cs414.a5.cs414g.backend.GameFacade;
import venture.cs414.android.cs414g.R;

public class PlayerSelect extends AppCompatActivity {

    private Spinner numPlayersDrop;
    private Spinner numMinutesDrop;
    private GameFacade gameFacade;
    private boolean radio2;
    private boolean radio3;
    private boolean radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_select);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        numPlayersDrop = (Spinner)findViewById(R.id.spinnerNumPlayers);
        Integer dropDownOptions[] = new Integer[]{2, 3, 4};
        ArrayAdapter<Integer> numPlayersAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, dropDownOptions);
        numPlayersDrop.setAdapter(numPlayersAdapter);

        numMinutesDrop = (Spinner)findViewById(R.id.spinnerTime);
        Integer dropDownOptionsTime[] = new Integer[]{1, 5, 10, 20};
        ArrayAdapter<Integer> numMinutesAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, dropDownOptionsTime);
        numMinutesDrop.setAdapter(numMinutesAdapter);

        gameFacade = GameFacade.getInstance();
    }

    public void clickStartGame(View view){
        int numPlayers = (int)numPlayersDrop.getSelectedItem();
        int numMinutes = (int)numMinutesDrop.getSelectedItem();
        Intent intent = new Intent(this, MainActivity.class);

        /*intent.putExtra("numPlayers", numPlayers);
        intent.putExtra("numMinutes", numMinutes);*/
        gameFacade.setUp(numPlayers, numMinutes, PlayerSelect.this, radio2, radio3, radio4);
        startActivity(intent);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonPlayer2:
                if (checked){
                    radio2 = true;
                }
                    break;
            case R.id.radioButtonPlayer3:
                if (checked){
                    radio3 = true;
                }
                    break;
            case R.id.radioButtonPlayer4:
                if (checked){
                    radio4 = true;
                }
                    break;
        }
    }

}
