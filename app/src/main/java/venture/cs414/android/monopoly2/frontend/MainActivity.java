package venture.cs414.android.monopoly2.frontend;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import venture.cs414.android.monopoly2.R;
import venture.cs414.android.monopoly2.backend.Board;
import venture.cs414.android.monopoly2.backend.GameFacade;


public class MainActivity extends AppCompatActivity {

    private GameFacade gameFacade;

    //TextView timerText;

    private TextView turnInfo;
    private TextView currentPlayerInfo;
    private TextView otherPlayerInfo;

    private PopupWindow notificationPopup;
    private PopupWindow blockerWindow;

    private RelativeLayout layout;
    private EditText costText;

    private int highestBid;
    private String highestBidder;
    private List<String> players;
    private int currentIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*int numPlayers = getIntent().getIntExtra("numPlayers", 2);
        int numMinutes = getIntent().getIntExtra("numMinutes", 5);*/

        gameFacade = GameFacade.getInstance();

        layout = (RelativeLayout)findViewById(R.id.contentMain);

        turnInfo = (TextView)findViewById(R.id.CurrentTurnInfoField);
        currentPlayerInfo = (TextView)findViewById(R.id.CurrentPlayerInfoField);
        otherPlayerInfo = (TextView)findViewById(R.id.AllPlayerInfoField);
        updateAllInfo();

        /*int numMiliSeconds = (numMinutes * 60 * 1000);
        new CountDownTimer(numMiliSeconds, 1000) {

            public void onTick(long millisUntilFinished) {
                int minutes = (((int)millisUntilFinished / 1000) / 60);
                int seconds = (((int)millisUntilFinished / 1000) % 60);
                String timerString = "Game Time:\n" + minutes + ":";
                if(seconds < 10){
                    timerString += "0";
                }
                timerString += seconds;
                setTitle(timerString);
            }

            public void onFinish() {
                //timerText.setText("done!");
                setTitle("Game Time:\n" + "0:00");
                //endGame();
            }
        }.start();*/
    }

    public void updateAllInfo(){
        turnInfo.setText(gameFacade.getCurrentMessage());
        currentPlayerInfo.setText(gameFacade.getCurrentPlayerInfo());
        otherPlayerInfo.setText(gameFacade.getOtherPlayerInfo());
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
        switch(id) {
            case R.id.menu_roll:
                try {
                    if(gameFacade.currentPlayerInJail()){
                        displayJailWindow();
                    }else {
                        gameFacade.moveCurrentPlayer();
                    }
                    updateAllInfo();

                    checkBuyProperty();

                    //Toast.makeText(getApplicationContext(), "Testing toast works", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error rolling", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_sell_property:
                try {
                    //implement call
                    Intent intent = new Intent(this, SellProperty.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_buy_house:
                try {
                    //implement call
                    Intent intent = new Intent(this, BuyHouse.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu_sell_house:
                try {
                    //implement call
                    Intent intent = new Intent(this, SellHouse.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_buy_hotel:
                try {
                    //implement call
                    Intent intent = new Intent(this, BuyHotel.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_sell_hotel:
                try {
                    //implement call
                    Intent intent = new Intent(this, SellHotel.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_mortgage:
                try {
                    //implement call
                    Intent intent = new Intent(this, MortgageProperty.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_unmortgage:
                try {
                    //implement call
                    Intent intent = new Intent(this, UnmortgageProperty.class);
                    //intent.putExtra("difficulty", 1);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_end_turn:
                try {
                    //implement call
                    gameFacade.advanceTurn();
                    updateAllInfo();
                    //Toast.makeText(getApplicationContext(), "Testing to make sure call works", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_quit:
                try {
                    //implement call
                    String message = gameFacade.removeCurrentPlayerFromGame();
                    updateAllInfo();
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public void sellProperty(View view){
        Intent intent = new Intent(this, SellProperty.class);
        //intent.putExtra("difficulty", 1);
        startActivity(intent);
        finish();
    }*/

    public void checkBuyProperty(){
        String propBuy = gameFacade.getCurrentPropertySale();
        //turnInfo.setText(propBuy);
        if(propBuy != null){
            //Create layouts and views for popup window
            LinearLayout popLayout = new LinearLayout(this);
            TextView salesText = new TextView(this);
            notificationPopup = new PopupWindow(this);

            //Set layout orientation
            popLayout.setOrientation(LinearLayout.VERTICAL);

            //Set the text for the popup
            salesText.setText(propBuy);

            //Create Button to dismiss
            Button but = new Button(this);
            but.setText("Yes");
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blockerWindow.dismiss();
                    notificationPopup.dismiss();
                    gameFacade.currentPlayerBuyCurrentProperty();
                    updateAllInfo();
                }
            });

            Button but2 = new Button(this);
            but2.setText("No");
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blockerWindow.dismiss();
                    notificationPopup.dismiss();
                    startAuction();
                    updateAllInfo();
                }
            });

            //Place layout in the popup
            popLayout.addView(salesText);
            popLayout.setBackgroundColor(Color.WHITE);
            popLayout.addView(but);
            popLayout.addView(but2);
            notificationPopup.setContentView(popLayout);

            placeBlocker();
            notificationPopup.showAtLocation(layout, Gravity.CENTER, 10, 10);
        }
    }

    public void startAuction(){
        //Create layouts and views for popup window
        LinearLayout popLayout = new LinearLayout(this);
        final TextView notificationText = new TextView(this);
        notificationPopup = new PopupWindow(this);
        costText = new EditText(this);
        costText.setInputType(InputType.TYPE_CLASS_NUMBER);

        //Set layout orientation
        popLayout.setOrientation(LinearLayout.VERTICAL);

        //Set the text for the popup
        players = gameFacade.getPlayerNames();
        highestBid = 0;
        currentIndex = 0;
        notificationText.setText(players.get(0) + ": Place a bid for: " + gameFacade.getCurrentPropertyName() + "\n"
        + "Current Highest Bid: $" + highestBid);

        //Place layout in the popup
        popLayout.addView(notificationText);
        costText.setText("0");
        popLayout.addView(costText);
        popLayout.setBackgroundColor(Color.WHITE);

        Button bidButton = new Button(this);
        bidButton.setText("Bid");
        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*blockerWindow.dismiss();
                notificationPopup.dismiss();
                highestBid = Integer.parseInt(costText.getText().toString());*/
                String bidString = costText.getText().toString();
                if(bidString.length() > 5){
                    notificationText.setText(players.get(currentIndex) + ": Place a bid for: " + gameFacade.getCurrentPropertyName() + "\n"
                            + "Current Highest Bid: $" + highestBid + "\n"
                            + "You cannot afford that bid");
                }else {
                    int bid = Integer.parseInt(bidString);
                    if (gameFacade.playerCanAfford(players.get(currentIndex), bid)) {
                        if (bid > highestBid) {
                            highestBid = bid;
                            highestBidder = players.get(currentIndex);
                            currentIndex++;
                            if (currentIndex >= players.size()) {
                                currentIndex = 0;
                            }
                            notificationText.setText(players.get(currentIndex) + ": Place a bid for: " + gameFacade.getCurrentPropertyName() + "\n"
                                    + "Current Highest Bid: $" + highestBid);
                        }
                    } else {
                        notificationText.setText(players.get(currentIndex) + ": Place a bid for: " + gameFacade.getCurrentPropertyName() + "\n"
                                + "Current Highest Bid: $" + highestBid + "\n"
                                + "You cannot afford that bid");
                    }
                }
            }
        });
        popLayout.addView(bidButton);

        Button dontBidButton = new Button(this);
        dontBidButton.setText("Don't Bid");
        dontBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                players.remove(currentIndex);
                if(currentIndex >= players.size()){
                    currentIndex = 0;
                }
                if(players.size() == 1){
                    try {
                        gameFacade.playerBuyProperty(highestBidder, gameFacade.getCurrentPropertyName(), highestBid);
                        blockerWindow.dismiss();
                        notificationPopup.dismiss();
                        updateAllInfo();
                        Toast.makeText(getApplicationContext(), highestBidder + " won the auction!", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                }else {
                    notificationText.setText(players.get(currentIndex) + ": Place a bid for: " + gameFacade.getCurrentPropertyName() + "\n"
                            + "Current Highest Bid: $" + highestBid);
                }
            }
        });
        popLayout.addView(dontBidButton);

        notificationPopup.setContentView(popLayout);
        notificationPopup.setFocusable(true);

        placeBlocker();
        notificationPopup.showAtLocation(layout, Gravity.CENTER, 10, 10);
    }

    public void displayJailWindow(){
        //Create layouts and views for popup window
        LinearLayout popLayout = new LinearLayout(this);
        TextView text = new TextView(this);
        notificationPopup = new PopupWindow(this);

        //Set layout orientation
        popLayout.setOrientation(LinearLayout.VERTICAL);

        //Set the text for the popup
        text.setText("You are in jail!  How would you like to get out?");

        //Create Button to dismiss
        Button but = new Button(this);
        but.setText("Roll");
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFacade.rollForJail();
                blockerWindow.dismiss();
                notificationPopup.dismiss();
                updateAllInfo();
            }
        });

        Button but2 = new Button(this);
        but2.setText("Pay $50");
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFacade.payJail();
                blockerWindow.dismiss();
                notificationPopup.dismiss();
                updateAllInfo();
            }
        });

        Button but3 = new Button(this);
        but3.setText("Use 'Get out of jail free' card");
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockerWindow.dismiss();
                notificationPopup.dismiss();
                gameFacade.useJailCard();
                updateAllInfo();
            }
        });

        //Place layout in the popup
        popLayout.addView(text);
        popLayout.setBackgroundColor(Color.WHITE);
        popLayout.addView(but);
        popLayout.addView(but2);
        popLayout.addView(but3);
        notificationPopup.setContentView(popLayout);

        placeBlocker();
        notificationPopup.showAtLocation(layout, Gravity.CENTER, 10, 10);
    }

    public void placeBlocker(){
        RelativeLayout blocker = new RelativeLayout(this);
        blocker.setVisibility(View.INVISIBLE);
        blockerWindow = new PopupWindow(blocker, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
        blockerWindow.showAtLocation(layout, Gravity.CENTER, 10, 10);
    }
}
