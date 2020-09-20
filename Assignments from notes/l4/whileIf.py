import random
#start the door as locked
open = False
#until theo door opens
chance = 0.1
#try to unlock door
pickLock = input("Would you like to pick the lock? (y/n) ")
while open == False and pickLock == 'y':
    if random.random() < 0.25:
        print("Success! The door opens, you loot the treasure")
        open = True
    elif random.random() < chance:
        print("Security arrives and you go to jail!")
        #exit
        pickLock = 'n'
    else:
        print("Try again")
        pickLock = input("Would you like to pick the lock? (y/n) ")
        chance += 0.01
        if pickLock == 'n':
            print("Good Choice")