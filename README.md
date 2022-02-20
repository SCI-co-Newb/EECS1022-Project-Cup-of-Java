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
- The main screen will have the title at the top and a start button at the bottom just below it with a button where it’ll go to a leaderboard screen
- The main screen will also have our group name at the top and the instructions of the game
- A quit button at the end of an attempt that goes back to the main screen 
- Background DMCA free music will play once the user enters into the app and loads and switches to the dedicated music for each of the modes and have a mute button somewhere on the screen
### Main Mode Requirements:
- It will be fixed as a 5x5 grid, but later we can add/change it to a mode where:
 It will start off at a 2x2 grid which then added one row and one column after getting an attempt correct (after nxn grid it’ll be an n+1Xn+1 grid) With a max 8 x 8  grid The amount of squares that can show up will be randomized between n to 2n where n is the number of squares on one row/column of the matrix if we do the other mode
- Otherwise the 5x5 square will start off with (randomized) 1 square to a max 20 squares depending on the level.
- Grid is constrained to the middle of the screen no matter if the device is in landscape or portrait mode
- The randomized blocks will appear for 1-5 seconds (5 seconds for first 5 levels and then every 5 levels -1 second up to a minimum of 1 second)
- The user can tap the squares in any order and if the user gets one block wrong the user either loses one heart or, if there are no hearts left, then user gets taken to the result screen and the user can either start over or exit to the main page, progress will be checked and recorded if beaten the previous high score
- If we can do it we can add a timed option to prevent screenshotting the game and then doing it (or add a feature that blocks screenshots when the game is ongoing)
- There can be a timer that goes down in the bottom of the screen (which can lap for every level and records it for the user to see)
- Each time the attempt is correct, the level number will add + 1 and + 1 block is there to memorize (for 5x5 grid)
- (For the increasing grid) The grid’s squares adjust, get smaller, to accommodate itself in the screen (otherwise the 5x5 grid will stay fixed)
### Leaderboard requirements:
- Shows after the user ran out of lives or reaches maximum level (for 5x5 grid)
- The top of the screen will say “Your High Scores”
- It’ll record the highest top 3 levels and date attempted like “Level 15             Feb 02 2022”
- This can be pulled from the the device’s date from using java’s utilities class
- The bottom of the screen will have the current attempt’s record
- There will also be a replay button that goes to the game screen and a quit button that goes to the main screen
- *can adjust and drop off some harder functional requirements or simplify it later on*

