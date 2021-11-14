Assumption: Milestone 2 + Milestone3

About the enemy:
1.	slug
a.	The slug has the smallest combat radius, which is 1
b.	The slug’s combat effectiveness is the smallest, 10
c.	The slug has the lowest blood volume at 20
d.	There are at most two slugs in the map, randomly generated on the map, slower than vampires and faster than zombies. When there are more than 2 monsters in the picture, they will not be generated.
e.	When the character's defense power is greater than the slug attack power, the slug cannot cause damage to the character
f.	The slug will not refresh with the number of rounds, only the number of monsters in the picture
2.	Zombies
a.	Zombie’s battle radius is larger than slug, which is 2
b.	Zombie’s combat power is 20
c.	Zombie’s HP is 50
d.	There is no limit to the number of zombies in the map, it is completely determined by the number of zombie pits. The zombies are randomly generated on the path near the zombie pit, and each zombie pit only spawns one zombie in each round.
e.	The trigger probability of the zombie assimilation soldier is 10%. When the soldier becomes a zombie, the new zombie will overlap with the previous zombie position and join the battle
f.	The combat radius of zombies is slightly larger than slug, and there is no support radius like slug
3.	Vampire
a.	Vampires have the largest combat radius, which is 3
b.	The vampire has the highest combat power, at 40
c.	The blood volume of vampires is the same as that of zombies, both are 50
d.	The support radius of the vampire is 4. When there is a battle in the area around 4, the vampire will join the battle
e.	The vampire’s fatal damage attack trigger probability is 10%, and the typhoid fever generated in the entire battle is doubled (can be understood as a violent mode)
f.	The vampire counts the number of rounds from the time the vampire card is placed. It appears once in five rounds. The number is determined by the number of vampire castles, and there is no upper limit.
g.	Stake weapons cause very high damage to vampires, killing them in one hit.
4.	Doggie
a.	Doggie’s combat power is 80, HP is 100, and the combat radius is 3
b.	Doggie appears once every twenty laps, and will appear in a fixed position, opposite to the walking direction of the character
c.	The probability of defeating Doggie to get DoggieCoin is 1/5
d.	The value of DoggieCoin is different every time you pass the castle, it is completely random
e.	DoggieCoin has no use other than selling money
5.	Muske
a.	Muske has a combat power of 100, a blood volume of 200, and a combat radius of 3
b.	Muske will appear once every forty laps, will appear in a fixed position, and move slowly
c.	The DogginCoin price for the round to defeat Muske is 10,000


About the building:
1.	By default, the player knows the placement conditions of the cards in each building (there is no misplacement)
2.	The probability of obtaining each card is the same as 1/8, and there is a 35% probability of obtaining a building card in each battle.
3.	Each time you pass by the village, you can add up to 50 drops of blood to the character. If the character's blood volume is higher than 950, it will be added to the upper limit of 1000. There is no situation where the blood volume exceeds the upper limit.
4.	Each time you pass the barracks, you can get up to two soldiers. If the number of existing soldiers is greater than 2, the number of soldiers will be added to the upper limit of 4, and there is no situation that exceeds the upper limit.
5.	The attack range of the tower is that the tower will not receive damage and can kill enemies within the attack range
6.	The scope of the campfire is also 2, which will directly double the current damage value of the character. If the character is equipped with weapons, the attack power will also be doubled.
7.	The trap is a one-off. When the monster steps into the trap, it will die directly, and the trap will become invalid and disappear from the picture.

About equipment:
1.	A character can only be equipped with one attack equipment during the turn. When a new weapon is equipped, the equipped weapon will be automatically discarded. Since the equipped weapon is considered used, it cannot be taken off and put back into the backpack or disassembled because of the weapon. There is loss.
a.	Does the sword have the highest attack power, 10
b.	The attack power of the stack is 5, but it can cause fatal damage to vampires and protect the character from receiving damage
c.	The staff’s attack power is very low, which is 1, which can be regarded as a magic item. The trance effect is triggered at a rate of 10%, and the trance effect is 0.9s (the character walks three squares)
2.	A character can be equipped with three types of armor during the turn, but each type of armor can only be equipped with one. The equipped armor cannot be taken off (it takes too long). The equipped armor will permanently act on the character to help The character's defense power is increased. When the character's defense power is greater than the enemy's attack power, it will not be able to cause damage to the character. The defense power can be accumulated, and the maximum defense power is 15%.
a.	The defense of the armor is 5
b.	The shield's defense power is 5, and when the shield is equipped, the chance of a vampire's fatal blow is 4%
c.	The helmet’s defense power is 5, but the character’s base attack power will be reduced to 15
d.	The health potion will directly return the character's HP to full, and the recovered HP will not exceed the upper limit of the character's HP. The health potion is used in battle by pressing the "enter" key. The health potion does not occupy the position of the backpack bar. There is no upper limit to the number of potions
3.	The probability of obtaining all equipment type cards is the same as one-eighth, and each battle has a 35 chance of dividing and conquering to obtain an item card.
4.	Rare items will only drop when fighting a vampire. The chance of appearing is one in ten. The character can be resurrected directly when the character dies. Rare items, like health potions, do not occupy the character’s backpack slot and exist separately in the character’s inventory. In reserve.

About the battle:
1.	The details of the battle will not be displayed, but the change in the character's blood volume will be displayed. The result of the battle is the death of the enemy or the death of the character. The game is terminated
2.	There is a sequence of battles, with the character attacking first.
3.	About the role:
4.	As long as the character completes 100,000 experience or 100,000 gold coins or 50 battle rounds, they can win the game.
5.	If there is no resurrection ring when the character dies, the game will end directly
6.	If the character has a resurrection ring, the character will start again from the castle, the equipment bar will be cleared, and the card will still exist (implemented in milestone3)

About the front end:
1.	In addition to the requirements of the title, add the text of the number of health potions and The one ring to record their number and usage
2.	Both the health potion and The one ring are used by keyboard keys
3.	Once the HP is less than 0, if there is a resurrection ring, a window will pop up to ask whether to use it. If the hero HP is used to return to full, the game is in a paused state. You can wear equipment and place cards, if you need to continue For games, click Space.
4.	Once the HP is less than 0 and there is no resurrection ring, the automatic pop-up game ends, and the closed pop-up game is paused. At this time, you can close or enter the main interface.
5.	For the rare-item in the json file read before the start of the world, the rare item can be used or equipped in the game only when the rare item [] in the json file exists.
6.	The mall will only open when passing by the castle, and there are no restrictions on buying and selling all items.
7.	The music will automatically play when the game starts, and it will be closed after the game is over
