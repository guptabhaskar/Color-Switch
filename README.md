# Color-Switch
This was my Advanced Programming final project for my 3rd semester.
We replicated the popular game color-switch, by using Advanced JAVA concepts and JAVA Design patterns.
### Team members- Sudeep Reddy, Bhaskar Gupta

<div align='center'>
<img style="border:1px black solid;" height=400px width=250px src="https://github.com/guptabhaskar/Color-Switch/blob/master/img/GIF.gif">
</div>

## Problems Faced and how we solved them
### 1. How did we implement multiple “saved game” options?
1. By creating an array list of GameState objects and choosing them based on the number the user chooses which would indicate the index number in the array list.
### 2. How did we a resume the Game i.e continuing from where you left off from the pause menu?
1. Create a temporary Save; and Deserialize the Gamestate objects to retain the positions of ball and obstacles and the player’s score.
### 3. How to spawn obstacles and have smooth animations for moving them?
1. Spawn 3 obstacles; 1 on screen and 2 other out of the screen as the obstacles are passed remove them from the screen, add more obstacles in the background and bring them down as a whole.
2. Match the frame rate and use 2D animation transitions to move the obstacles so the there isn't any stutter/jitter in the animations.
### 4. How did we detect collision?
1. Use Shapes.intersect and getFill() to check intersection and color when the ball collides with the obstacle.

## Design patterns used:
#### 1. State Design Pattern
To keep track of the state of various game obstacles when storing their positions.
#### 2. Iterator Design Pattern
We have used this while iterating through obstacles on the screen to check whether the have been crossed and if crossed then are they out of the game screen to remove them from list and add more obstacles in the background.
#### 3. Factory Design Pattern
Used when spawning random obstacles on screen screen.

## Bonus parts of the project-
1. Audio
    - Similar to the original game; when the ball jumps,hits an obstacle or starting a new game.
    - Smooth and fluid Animations Of Obstacles moving down and the bouncing of the ball.
2. Bolt obstacle
    - Makes you immortal to pass through 5 incoming obstacles.
3. Boost
    - Makes you go really fast for 15 obstacles.





