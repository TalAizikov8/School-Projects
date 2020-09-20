#Tal Aizikov     101150420
#This will play the popular game of blackjack with you


import random #Will help choose random card

def sumHand(lis): #THIS CALCULATES THE VALUE OF THE HAND
    sum = 0
    Acount = 0 #making sure we can keep track of the As in the list

    for i in lis: 
        if i == 'A': 
            sum += 11 #Adds 11 by default but can switch to 1 if score over 21
            Acount += 1
        elif i == "J" or i == "Q" or i == "K": #adds 10 for J Q K
            sum += 10
        else:
            sum += int(i) #just switches the num to int and ands that

        if sum > 21 and Acount != 0: #if score is over 21 then A will be 1 and not 11
            sum -= 10
            Acount -= 1 #note if A is used it takes it of the A count

    return sum
    

def displayHand(lis): #THIS DISPLAYS THE HAND NICELY
    handDisplay = "Your hand: " #adding to a variable to keep it easy

    for i in range (0,len(lis)): 
        handDisplay += lis[i] + " " #adding what is in the hand to a var
    handDisplay += "("+str(sumHand(lis))+")" #then adding the score

    print(handDisplay) #prints the hand
    

def getRank(n): #THIS FINDS THE RANK YOU GOT ACCORDING TO GUIDELINES GIVEN
    #Note elif was not used because it is not needed
    if n <= 100:
        rank = "Ace!"
    if n <= 94: 
        rank = "King"
    if n <= 84:
        rank = "Queen"
    if n <= 74:
        rank = "Jack"
    if n <= 49:
        rank = "Commoner"
    if n <= 24:
        rank = "NOOB!"

    return rank


def dealCard(): #THIS WILL GIVE USER A RANDOM CARD!
    deck = ["A","2","3","4","5","6","7","8","9","10","J","Q","K"] #INDEX OF CARDS
    random1 = random.randint(0,12)
    card = deck.pop(random1) #Choses a random card
    return card



def main(): #MAIN PROGRAM
    userChoice = "" #weather the user wants to hit or stand
    score = 100 #score starts at 100 and goes down

    for i in range (1,6): #i from 1 to 6 (instead of 0 to 5) to match round number (saves space)
        cards = [] #making sure new hand each round
        print("Round " + str(i)) #print out the round number by printing the i

        for j in range (0,2):#starting off with 2 cards
            cards.append(dealCard())    #adds to cards this round by using dealCard()

        displayHand(cards) #displays the hand nicely
        print("Score: " + str(score)) #printing out score nicely

        while True: #(do while) to see if user wants to hit or stand 
            userChoice = input("Would you like to 'hit' or 'stand': ") #getting user input
            if userChoice == "hit": #IF THEY HIT
                cards.append(dealCard()) #Get another card
                displayHand(cards) #show user what they got
                if sumHand(cards) > 21: #check that it is under 21
                    print("Bust!")
                    score -= 21 #if over loose 21 points
                    break
            elif userChoice == "stand": #IF THEY STAND
                score -= (21 - sumHand(cards)) #Subtract the differance between 21 and their hand
                break
            else: #incase input invalid
                print("please enter a valid input")
            
        print("") #ENTER between rounds to make it more neat
    print("Final score: "+str(score)+", Your rank: "+getRank(score)) #displays the result!





main()