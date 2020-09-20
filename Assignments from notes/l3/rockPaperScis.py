#rock paper scissors
import random

#computers choice
r = random.randint(1,3)
if r == 1:
    compAnswer = "rock"
elif r == 2:
    compAnswer = "paper"
else:
    compAnswer = "scissors"

#asks the user for what he wants to choose
userAnswer = input("Would you like rock/paper/scissors ")

print("You chose " + userAnswer + " and the computer chose " + compAnswer)

if compAnswer == userAnswer:
    print("It is a tie")
elif compAnswer == "rock" and userAnswer == "paper"\
    or compAnswer == "paper" and userAnswer == "scissors"\
    or compAnswer == "scissors" and userAnswer == "rock":
    print("YOU WIN!!")
else:
    print("You lose :(")
