#Tal Aizikov     101150420
#This program will get marks from different assignments and tests and give the % of each
#Then it will calculate the final % for the whole class


divider = 25 #better to have a variable rather then writing the same number twice

#Tal Aizikov     101150420
#below it calculates the precentage of each assignment and test
#I chose to use one line and not do set a new variable because it looks more neat
#and it is still comes to the same number 
#eg. takes the input divides it by how much its out off and multiplies by 100 to get the precentage
firstAssign = int(input("Assignment #1 (/18): ")) / 18 *  100
secondAssign = int(input("Assignment #2 (/22): ")) / 22 * 100
thirdAssign = int(input("Assignment #3 (/15): ")) / 15 * 100
fourthAssign = int(input("Assignment #4 (/30): ")) / 30 * 100
midterm = int(input("Midterm (/35): ")) / 35 * 100
final = int(input("Final Exam (/50): ")) / 50 * 100
#below it calculates the final grade 
#I tried to do all the calculations in the same area
finalGrade = ((firstAssign*0.1) + (secondAssign*0.1) + (thirdAssign*0.1) + (fourthAssign*0.1) +(midterm*0.25) + (final*0.35))

#below print everthing nicely
print("You grades are:")
print("="*divider) #devider line

print(f"Assignment #1:  {firstAssign:.2f}%")#rounds to 2 decimal places
print(f"Assignment #2:  {secondAssign:.2f}%")
print(f"Assignment #3:  {thirdAssign:.2f}%")
print(f"Assignment #4:  {fourthAssign:.2f}%")
print(f"Midterm:        {midterm:.2f}%")
print(f"final:          {final:.2f}%")

print("="*divider)

print(f"Your final grade is {finalGrade:.2f}%" )



