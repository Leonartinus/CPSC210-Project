# Personal Project - Bridge Builder

## Description

The project is a little game called *Bridge Builder* that can use space bar to control the bridge length, but the
bridge is generated vertically. When the vertical bridge fall down, if the bridge is long enough to pass the ball to 
the next stage, get one point. If the bridge is too long or too short to pass the ball to the next stage, then loss the
game. People can play this little game to relax and kill time. Also, it can practice players' time sensing and observing 
skill, since perceiving the relationship between the time that the space bar has been pressed and the length of the
bridge is quite challenging and interesting.

**User story**:
- As a player, I want to be able to control the length of the bridge.
- As a player, I want to be able to quit the game.
- As a player, I want to be able to see how long the bridge is.
- As a player, I want to be able to see if the ball arrive the end stage.
- As a player, I want to be able to see my total score on the gaming interface.
- As a player, I want to be able to add my score to the history score.
- As a player, I want to be able to skip adding my score to the history score.
- As a player, I want to be able to save my history score into a file.
- As a player, I want to be able to load my history score from the saved file.

## Instructions for Grader
- Firstly, run the program, the new button is to start a new game, the resume button is load the game that saved
- If the new button is clicked, the new game will be started.
- Secondly, the game frame start, press the space to build the bridge
- Thirdly, press D to down the bridge to pass the ball to the next stage to get the score
- The save button is on the bottom of the window, the game can be saved during the game.
- When the save button is clicked the game will be saved, you can continue the game or quit.
- If quit directly, the game will not be saved at the time.
- The saved game can be restarted by press the resume button in the main manu.
- If the ball does not pass to the next stage, after the bridge is down, lose to game and move to the game over window
- Fourthly, add the score you just played into the history score by press the ADD button and jump to replay window
- Fifthly, The new history score will be showed in the table, you can see that the score is added or not
- Finally, press the R to restart the game, and press SAVE AND QUIT button to save the score and quit the game

## Phase 4: Task 2
- "New game start"
- "The file is loaded"
- "The bridge is built"
- "Pass the bridge, positions are reset"
- "The current score is updated to 1"
- "The game is over"
- "The 1th times score is added to the history scores"
- "The last time score changed to the next times'"
- "Turn the 1 score to the json"
- "History score is convert to the Json format"
- "Game is saved"
- "The history scores have been cleared"

## Phase 4: Task 3
The design of the project is complex, while the UML_Design_Diagram is drawing, there are so many associations and 
dependencies between the classes. If I have enough time, I would reduce the redundancy of the code, decrease the 
coupling and increase the cohesion of the code. For instance, I could delete some associations between the game and the
Score, since the game and UI have already had the association between the HistoryScore, and HistoryScore have the 
association with Score, so some arrows might be redundant which could be deleted. Furthermore, the HistoryScore might be
contained in the game, so the association between the UI and the game could be simplified as Game and UI, just delete
the arrows between the UI and the HistoryScore, only the association between the Game and UIs remains. Overall, the 
design still has space to improve.
