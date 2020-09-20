import random
# ask the user for input
#convert age to an integet
userAge = int(input("What is your age: "))

#check that its a valid answer
if userAge >0:
    if userAge >= 19:
        hasID = input ("do you have an ID?? (y/n) ") 
        if hasID == "y": #checks for their ID
            print("Welcome to the party")
        else: #If they dont have print this v
            print("Go home and get your ID")
    else:
        print("You are too young to go, come back in "+ str(19 - userAge))
        snick = input("or do you want try to snick in (y/n) ") #lets them try and snick in
        if snick == "y":
            success = random.randint(1,100)
            if success <= 35:
                print("you snuck into the party!!!")
            else:
                print("What are you doing get the hell out of here")
else:
    print("enter an age over 0")

