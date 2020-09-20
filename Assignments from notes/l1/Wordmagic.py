#this program is the solution to the practice problem in ch2b

#get a word from user
userWord = input("Enter a word ")

#get a number from the user
number = int(input("Enter a number please "))

#print the answer to the riddle
print("I am thinking of "+str(number)+ ""+ userWord+"(s)")
print((userWord+'\t')*number)