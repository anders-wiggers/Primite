# PRIMITE

Primite is an Android Game created to test different techniques learned during the Software Architecture course on AU. The App is a 'Clicker' game that features an advanced Talents, Loot system, Boss system, Firebase integration and much more.

## Video Demo
A video demo of the project can be view [Here](https://drive.google.com/file/d/1YfiDGx_5CANxsr4-JG7cIqGhiixOUHs2/view?usp=sharing)

## The Game

The game is a simple clicker game. The goal is to gather as much power as possible. The player can click to deal damage to an enemy. The player can upgrade his/her character by advancing the Talent tree or by buying new weapons. Weapons can also be obtained by slaying bosses. 

### Programming Techniques

To develop the game different design patterns were utilized.

#### Observer

To notify the view when the player has levelled up the observer pattern was chosen. The pattern is implemented by creating the `Observer.java` interface and extending it to the `PlayerImpl.java` class. The player class can add subscribers to an observer list which will get notified when an event has happens. 

#### Strategy Pattern 

To develop the Talent point System strategy patterns was choosen the allows for flexible talents that can be changed during runtime. The `TalentStrategy.java` interface was created. A standard Talent point(`Talent.java`) can then extend the interface which dictates how it acts in the system. 

```
    @Override
    public void effect(){
        for(int i = 0 ; i<point;i++){
            talentStrategy.effect();
        }
    }
```
The talentStrategy then affect the player with a given effect for each point spent in the point. 

#### Singleton

To move information regarding the current player and load data from the database the singleton pattern was used. This pattern allows easy access of player data publicly.

The singleton `Game.java` can be accessed via `Game.getInstance()` Information about the player can then easly be extraced via the `getPlayer()` method. 
```
Player player = Game.getInstance().getPlayer();
player.getLevel() 
```

This singleton strategy made programming the Shop and Weapons fragments easier, as all information was kept in the same place. 
