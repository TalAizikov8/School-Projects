#Tal Aizikov     101150420
#This program will take 4 numbers and give you the biggest difference between 2 numbers

#getting user input and making it int so calculations can be done
print ("Give me four number")
numOne = int(input("The first number is:  "))
numTwo = int(input("The second number is: "))
numThree = int(input("The third number is:  "))
numFour = int(input("The fourth number is: "))

#below checks for the largest and smallest numbers
biggestNum = max(numOne, numThree, numThree, numFour)
smallestNum = min(numOne, numThree, numThree, numFour)

#finds the difference between the largest and smallest numbers
difference = biggestNum - smallestNum

#prints the output
print(f"The largest difference is: {difference}")