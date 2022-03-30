package com.example.testmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private int level;
    private int maxlevel;
    private int square;
    private int score;
    private int screenIndicator;
    private int scoreIndicator;
    private final ArrayList<Button> arrayOfButtons;
    private final Set<Button> selectList;
    public MainActivity(){
        this.maxlevel = 16;
        this.level = 0;
        this.square = 0;
        this.score = 0;
        this.screenIndicator = 4; //default screen is 4x4
        this.arrayOfButtons = new ArrayList<>();
        this.selectList = new HashSet<>();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayOfButtons.clear();
    }
    public void goHome (View view) { //goes back to home screen
        setContentView(R.layout.activity_main);
        score = 0;
        level = 0; //sets level back to zero when switched to home screen
        selectList.clear();
        arrayOfButtons.clear();
    }
    public void hs4(View view) {
        setContentView(R.layout.fourhighscore);
    }
    public void hs5(View view) {
        setContentView(R.layout.fivehighscore);
    }
    public void hs6(View view) {
        setContentView(R.layout.sixhighscore);
    }
    public void hs7(View view) {
        setContentView(R.layout.sevenhighscore);
    }
    public void highScore() {
        SharedPreferences preferences = getSharedPreferences("PREFS", 0); //https://www.youtube.com/watch?v=_cV7cgQFDo0

    }

    public int randomSquare() {
        Random rando = new Random();
        square = rando.nextInt(arrayOfButtons.size());
        return square; //random index in the button array
    }

    public Button selectSquare(ArrayList<Button> buttonArray) {
        return buttonArray.get(randomSquare());
    }

    public void Enforce() {
        for (Button B : arrayOfButtons) { //this for loop allows me to cycle through each button and set a on click listener
            B.setOnClickListener(new View.OnClickListener() { //Says to replace with lambda idk what that is
                @Override
                //I'm Making every button clickable
                public void onClick(View view) {
                    if (selectList.contains(B)) {
                        score++;
                        TextView scorecount = findViewById(R.id.scorecount);
                        scorecount.setText(String.format("%d/%d", score, scoreIndicator));
                        B.setBackgroundTintList(getColorStateList(android.R.color.white)); // changes color of button to white on click
                        selectList.remove(B);
                        B.setEnabled(false);
                        if (selectList.size() == 0) {
                            if (screenIndicator == 4) {
                                setContentView(R.layout.nice4);
                            }
                            else if (screenIndicator == 5) {
                                setContentView(R.layout.nice5);
                            }
                            else if (screenIndicator == 6) {
                                setContentView(R.layout.nice6);
                            }
                            else if (screenIndicator == 7) {
                                setContentView(R.layout.nice7);
                            }
                            else {
                                setContentView(R.layout.activity_main);
                            }
                        }
                    }
                    else {
                        selectList.clear();
                        if (screenIndicator == 4) {
                            setContentView(R.layout.fourhighscore);
                            TextView currentlevel = findViewById(R.id.currentlevel);
                            currentlevel.setText(String.format("%d\nSquares: %d", level, score));
                            currentlevel.setGravity(Gravity.CENTER);
                        }
                        else if (screenIndicator == 5) {
                            setContentView(R.layout.fivehighscore);
                            TextView currentlevel = findViewById(R.id.currentlevel);
                            currentlevel.setText(String.format("%d\nSquares: %d", level, score));
                            currentlevel.setGravity(Gravity.CENTER);
                        }
                        else if (screenIndicator == 6) {
                            setContentView(R.layout.sixhighscore);
                            TextView currentlevel = findViewById(R.id.currentlevel);
                            currentlevel.setText(String.format("%d\nSquares: %d", level, score));
                            currentlevel.setGravity(Gravity.CENTER);
                        }
                        else if (screenIndicator == 7) {
                            setContentView(R.layout.sevenhighscore);
                            TextView currentlevel = findViewById(R.id.currentlevel);
                            currentlevel.setText(String.format("%d\nSquares: %d", level, score));
                            currentlevel.setGravity(Gravity.CENTER);
                        }
                        else {
                            setContentView(R.layout.activity_main); //so no bugs can happen
                        }

                    } } }); }
    }

    public void IncreaseLevel(){
        level = level + 1; //level increases by 1
        TextView levelChange = findViewById(R.id.LEVELNUM);
        levelChange.setText(String.format("%d/%d", level, maxlevel));
    }
    public void lighter(ArrayList<Button> buttonArray) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Button B : selectList)
                    B.setBackgroundTintList(getColorStateList(android.R.color.white));
                for (Button B : buttonArray) {
                    B.setEnabled(false); }
                //prevents users from clicking on something in the button array. This can be redundant idk
            }
        }, 50); //This is 0.05 seconds after the screen/grid appears
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (Button B : buttonArray) {
                    B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
                    B.setEnabled(true); } //re enables the button's functionality
                }
        }, 800); //0.75 seconds later the color changes back to it's original color
    }

    public void fourSquared(View view) {
        setContentView(R.layout.fourbyfour);
        arrayOfButtons.clear();
        add4x4();
        screenIndicator = 4;
        scoreIndicator = 136;
        maxlevel = 16;
        TextView scorecount = findViewById(R.id.scorecount);
        scorecount.setText(String.format("%d/%d", score, scoreIndicator));
        IncreaseLevel();
        if (level > 16) {
            setContentView(R.layout.fourhighscore); //change this later to leaderboard
        }
        while (selectList.size() != level) {
            selectList.add(selectSquare(arrayOfButtons));
        }
        lighter(arrayOfButtons);
        Enforce();
        if (selectList.size() == 0) {
            for (Button B : arrayOfButtons) {
                B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
            }
        }
    }
    public void fiveSquared(View view) { //changes start button on main screen to five by five grid
        setContentView(R.layout.fivebyfive);
        arrayOfButtons.clear();
        add5x5();
        screenIndicator = 5;
        scoreIndicator = 225;
        maxlevel = 25;
        TextView scorecount = findViewById(R.id.scorecount);
        scorecount.setText(String.format("%d/%d", score, scoreIndicator));
        IncreaseLevel();
        if (level > 25) {
            setContentView(R.layout.fivehighscore); //change this
        }
        while (selectList.size() != level) {
            selectList.add(selectSquare(arrayOfButtons));
        }
        lighter(arrayOfButtons);
        Enforce();
        if (selectList.size() == 0) {
            for (Button B : arrayOfButtons) {
                B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
            }
        }

    }
    public void sixSquared(View view) {
        setContentView(R.layout.sixbysix);
        arrayOfButtons.clear();
        add6x6();
        screenIndicator = 6;
        scoreIndicator  = 666;
        maxlevel = 36;
        TextView scorecount = findViewById(R.id.scorecount);
        scorecount.setText(String.format("%d/%d", score, scoreIndicator));
        IncreaseLevel();
        if (level > 36) {
            setContentView(R.layout.sixhighscore); //change this
        }
        while (selectList.size() != level) {
            selectList.add(selectSquare(arrayOfButtons));
        }
        lighter(arrayOfButtons);
        Enforce();
        if (selectList.size() == 0) {
            for (Button B : arrayOfButtons) {
                B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
            }
        }

    }
    public void sevenSquared(View view) {
        setContentView(R.layout.sevenbyseven);
        arrayOfButtons.clear();
        add7x7();
        screenIndicator = 7;
        scoreIndicator = 1225;
        maxlevel = 49;
        TextView scorecount = findViewById(R.id.scorecount);
        scorecount.setText(String.format("%d/%d", score, scoreIndicator));
        IncreaseLevel();
        if (level > 49) {
            setContentView(R.layout.sevenhighscore); //change this
        }
        while (selectList.size() != level) {
            selectList.add(selectSquare(arrayOfButtons));
        }
        lighter(arrayOfButtons);
        Enforce();
        if (selectList.size() == 0) {
            for (Button B : arrayOfButtons) {
                B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
            }
        }
    }

    public void add4x4() {
        arrayOfButtons.add(findViewById(R.id.ButtonR1C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C4));
    }
    public void add5x5() {
        arrayOfButtons.add(findViewById(R.id.ButtonR1C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C5));
    }
    public void add6x6() {
        arrayOfButtons.add(findViewById(R.id.ButtonR1C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C6));
    }
    public void add7x7() {
        arrayOfButtons.add(findViewById(R.id.ButtonR1C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR1C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR2C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR3C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR4C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR5C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR6C7));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C1));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C2));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C3));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C4));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C5));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C6));
        arrayOfButtons.add(findViewById(R.id.ButtonR7C7));
    }

}