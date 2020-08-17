package com.example.fruitsplash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity2 extends AppCompatActivity {
    int activePlayer = 1;//i am taking int instead of boolean to extend the game for more than 2 players
    //zero:X one:O two:empty - gameStatus
    int[] gameStatus = {2,2,2,2,2,2,2,2,2};
    int[][] winpositions = {{0, 1, 2}, {0, 3, 6}, {0, 4, 8}, {1, 4, 7}, {3, 4, 5},{2, 4, 6}, {2, 5, 8}, {6, 7, 8}};
    Boolean gameActive = true;
    TextView winnerTextView ;
    String winner;
    /*all the above variables are declared globally as they should remain constant
    even after going through all methods(snippets of code)
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.apple:
                 activePlayer = 1;
                 return true;
             case  R.id.pineapple:
                 activePlayer = 0;
                 return true;
             default:
                 return false;


         }
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedbox = Integer.parseInt(counter.getTag().toString());/*very important topic of tags starts here
                                                                           we use tags to generically apply same code to all nine boxes*/
        if (gameStatus[tappedbox] == 2 && gameActive) {
            gameStatus[tappedbox] = activePlayer;
            counter.setTranslationY(-2000);
            if (activePlayer == 1) {// this if else combo is to switch between X and O
                activePlayer = 0;
                counter.setImageResource(R.drawable.applef);

            } else {
                activePlayer = 1;
                counter.setImageResource(R.drawable.pineapplef);

            }
            counter.animate().translationYBy(2000).rotation(1800).setDuration(300);
            for (int[] winningposition : winpositions) {//checking the winner
                if (gameStatus[winningposition[0]] == gameStatus[winningposition[1]] && gameStatus[winningposition[1]] == gameStatus[winningposition[2]] && (gameStatus[winningposition[1]] != 2)) {
                    winner = "";
                    if (activePlayer == 0) {
                        winner = "APPLE HAS WON ";
                        gameActive = false;
                    } else if(activePlayer == 1) {
                        winner = "PINEAPPLE HAS WON";
                        gameActive = false;
                    }else {
                        winner = "DRAW";
                        gameActive = false;
                    }




                    Button playAgain = (Button)findViewById(R.id.button);
                    winnerTextView = (TextView)findViewById((R.id.textView));/* this is to display winner in textView
                                                                                            rather displaying it in toast*/



                       winnerTextView.setText(winner);


                    playAgain.setVisibility(View.VISIBLE);// make the play again button visible
                    Log.i("hiiiiiiiiii",winner);
                    winnerTextView.setVisibility(View.VISIBLE);









                }
              
            }
        }
    }

    public void playAgain(View view) {
        Button playAgain = (Button) findViewById(R.id.button);
        TextView winnerTextView = (TextView) findViewById(R.id.textView);
        playAgain.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {//to iterate through the grid *IMPORTANT*
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);//to remove all images

        }
        //i am taking int instead of boolean to extend the game for more than 2 players
        //zero:X one:O two:empty
        for(int i=0;i<gameStatus.length;i++) {//resetting the game board positions to make it ready for replay
            gameStatus[i] = 2 ;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}