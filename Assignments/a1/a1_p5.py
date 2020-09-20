#Tal Aizikov     101150420
#This program will take 3 sides of a triangle and give you the area

import math

#below getting user input and making it an int so calculations can be done
sideOne = int(input("Enter side length a: "))
sideTwo = int(input("Enter side length b: "))
sideThree = int(input("Enter side length c: "))

#calculates half the Perimeter to be used later in the area calculation
halfPerimeter = (sideOne + sideTwo + sideThree) / 2
area = math.sqrt(halfPerimeter*(halfPerimeter-sideOne)*(halfPerimeter-sideTwo)*(halfPerimeter-sideThree)) #calculates area

#below prints everthing nicely
print(f"A triangle with side lengths {sideOne}, {sideTwo}, and {sideThree} has an area of {area:.4f}")



