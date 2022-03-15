package com.example.testmemory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random; //uses random function
import java.util.ArrayList; /*I'm using arraylist instead of arrays because this is easier.
                                I have a python background and I think lists are more efficient for this project */


public class MainActivity extends AppCompatActivity {

    private int level;
    private int square;
    private int score;
    private ArrayList<Button> selectList;
    private boolean onoff;

    public MainActivity(){
        this.level = 0;
        this.square = 0;
        this.score = 0;
        this.selectList = new ArrayList<>();
        this.onoff = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayOfButtons();
    }
    public int randomSquare() {
        Random rando = new Random();
        square = rando.nextInt(arrayOfButtons().size());
        return square; // this is to choose a random number in the button array
    }
    public Button selectSquare(ArrayList<Button> buttonArray) {
        Button block = buttonArray.get(randomSquare());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                block.setBackgroundTintList(getColorStateList(android.R.color.white));
                for (Button B : buttonArray) {
                    B.setEnabled(false); }
                //prevents users from clicking on something in the button array. This can be redundant idk
            }
        }, 50); //This is 0.05 seconds after the screen/grid appears
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                block.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
                for (Button B : buttonArray) {
                    if (B != block) {
                        B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1"))); //to make sure all blocks are standard color
                    }
                    B.setEnabled(true); } //re enables the button's functionality
            }
        }, 800); //0.75 seconds later the color changes back to it's original color
        return block;
    }

    public void Enforce() {
        score = 0;
        for (Button B : arrayOfButtons()) { //this for loop allows me to cycle through each button and set a on click listener
            B.setOnClickListener(new View.OnClickListener() { //Says to replace with lambda idk what that is
                @Override
                //I'm Making every button clickable
                public void onClick(View view) {
                    if (selectList.contains(B)) {
                        B.setBackgroundTintList(getColorStateList(android.R.color.white)); // changes color of button to white on click
                        score++;
                    } else {
                        level = 0; //sets level to 0 to restart
                        score = 0;
                        selectList.clear();
                        setContentView(R.layout.activity_main); /*if it is the wrong button, the screen will go back to a fail screen
                                                                  Currently it resets to main screen */
                    }
                }
            });
        }
    }


    public ArrayList<Button> arrayOfButtons() {
        ArrayList<Button> arrayOfButtons = new ArrayList<>(); //I learned that arrays can have elements removed but the size of arrays can't be changed so i'm doing array list
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
        return arrayOfButtons;
    }

    public void IncreaseLevel(){
        level = level + 1; //level increases by 1
        TextView levelChange = findViewById(R.id.LEVELNUM);
        levelChange.setText(String.format("%d/25", level));
    }

    public void changeScreen(View view) { //changes start button on main screen to five by five grid
        setContentView(R.layout.fivebyfive);
        while (!onoff) {
            onoff = true;
            IncreaseLevel();
            for (int i = 1; i <= level; i++) {
                selectList.add(selectSquare(arrayOfButtons()));
            }
            Enforce();
            if (score == level){
                for (Button B : arrayOfButtons()) {
                    B.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#cad4d1")));
                }
                onoff = false;
            }
            if (level == 25) {
                break;
            }
        }
    }


    public void goHome(View view) { //goes back to home screen
        setContentView(R.layout.activity_main);
        level = 0; //sets level back to zero when switched to home screen
        selectList.clear();
    }
}
