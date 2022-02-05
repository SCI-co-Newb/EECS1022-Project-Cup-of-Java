# EECS1022-Project-Cup-of-Java

##Name of Team:
Cup of Java

##Team Member's Information

|First Name|Last Name|York Email|Lecture Section|Lab Section|
|----------|---------|----------|---------------|-----------|
|Affan|Gazi|ag02@my.yorku.ca|N|02|
|Sahajdeep|Singh|ssingh03@my.yorku.ca|N|02|
|Oshiya|Brahmbhat|os0404@my.yorku.ca|N|02|
|Sourish|Dayalan|sourish6@my.yorku.ca|P|03|

## Project Title: Test{Memory}
## Project Description:
The core of the app is a guessing game.
Our main mode will start off with nxn squares. A select amount of squares will be highlighted for 1-5 seconds then turned back into the default colour, default as in the colour of the other squares, the user can’t tap the squares then. After the computer is done randomly selecting the squares, the user can tap and must select the selected squares, if the user selects the wrong square then the user loses. The squares are randomized each time and have a (optional) timed option.
There will be a leaderboard for each mode, one for now, that records the user’s top 3 personal scores (if we are able to).

## Functional Requirements:
### General App Requirements:
- The main screen will have the title at the top and modes just below it with a button where it’ll go to a leaderboard screen
- Have a navigation bar that has all of the modes plus a quit button somewhere at the end that adds another option to exit the app
- Instruction manual for each mode will be available on the main screen
- Background DMCA free music will play once the user enters into the app and loads and switches to the dedicated music for each of the modes and have a mute button somewhere on the screen
- There is a 0.5-2 sec transition from the main screen the to any of the modes
### Mode 1 Requirements:
- It will start off at a 2x2 grid which then added one row and one column after getting an attempt correct (after nxn grid it’ll be an n+1Xn+1 grid) With a max 8 x 8  grid
- Each time the attempt is correct, the level number will add + 1
- The amount of squares that can show up will be randomized between n to n + n/2 (rounded down if n is odd / n%2==1) where n is the number of squares on one row/column of the matrix ok
- Constrained to the middle of the screen no matter if the device is in landscape or portrait mode
- The randomized blocks will appear for 1-5 seconds depending on the level (5 seconds for easy and 1 second for hard)... (*easter egg*)(and maybe 0.1 for extreme)
- The user can tap the squares in any order and if the user gets one block wrong it gives a wrong screen and the user has to start over
- If we can do it we can add a timed option to prevent screenshotting the game and then doing it
- There can be a timer that goes down in the bottom of the screen
- The grid’s squares adjust, get smaller, to accommodate itself in the screen
### Leaderboard requirements:
- The top of the screen will say “Your High Scores”
- It’ll record the highest top 3 levels and date attempted like “Level 15             Feb 02 2022”
- This can be pulled from the the device’s date
*can adjust and drop off some harder functional requirements or simplify it later on*
