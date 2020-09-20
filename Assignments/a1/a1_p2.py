#Tal Aizikov     101150420
#This program will take a distance in cm and turn it to inches

metDistance = input("Enter any distance in cm ") #getting user input

foot = (int(metDistance) / 30.48)  #finding the foot
remainder = int(metDistance) % 30.48 #calculating remainder to be used to find inchs
inch = (remainder / 2.54) #finding inches
#if you enter number like 182cm it would give you 12 inches
if round(inch) == 12: #this checks for the problem and corrects it
    inch = 0
print (metDistance + f"cm in approximately {foot:.0f}' {inch:.0f}\"") #prints everything nicely