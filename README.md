# PRIMITE

Primite is an Android Game i created to test diffrend techneies i learned in Software Achetechture course. The App is a 'Clicker' game which features an advance Talents, Loot system, Boss system, Firebase integration and much more.

### The Game

The game is a simple clicker game. The goal is to increase in power. The player can click to deal damage to an enemy. The player can they upgrade his/her charater by advancing the Talent three or by buying new weapons. Weapons can also be optained by slaying bosses. 

### Programming Techniques

To develop the game usesing alot of differend patterns.

##### Observer

To notify the view when the player has level up I choose to use the observer pattern, by creating the `Observer.java` interface and extending it to the `PlayerImpl.java`class. The player class is the able add subscribers to an observer list which will get notified when an event has happend 

##### Strategy Pattern 

To develop the Talent point System I choose the strategy patterns as this could save me alot of work. I created the `TalentStrategy.java` interface. A standart Talent point(`Talent.java`) need a TalentStrategy to determend what is does. 

```
    @Override
    public void effect(){
        for(int i = 0 ; i<point;i++){
            talentStrategy.effect();
        }
    }
```
The talentStrategy then affect the player with a given effect for each point spend in the point. 

### Todo List

in the current development pipe line is:
- [ ] Ability to sell items
- [x] DPS on UI with talent bonus
- [ ] Modify Weapon
- [x] Weapon Tick in Background
- [ ] Show advanced stats
- [ ] Display currency gained
- [ ] Bosses
- [ ] Enemyshow debuff
- [ ] Calculate Attack Speed 
- [ ] Attackspeed from 0.0 then [1.0:5.0]
- [x] Take legendary off buy table and Epic
- [ ] Add loot table to legendary
- [ ] Icon on talent page
- [ ] Firebase connection



