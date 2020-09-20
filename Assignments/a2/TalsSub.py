#Tal Aizikov     101150420
#This program will find what kinda food and topings the user want and will give them a detailed recipt 

liner = ("="*28)

price = 0.00
totalPrice = 0.00
tax = 0.00

toppings = ""

#intro to hook user
print(liner)
print("| Welcome to Tal's Sub Sub |")
print(liner)

while True:
    #desplaying options and getting user sellection
    print("Please select your sub:")
    print("1. \"Meat\"-ball sub ($7.99)")
    print("2. Cold-cut Club sub ($8.25)")
    print("3. Philly's Cheese Mis-Steak sub ($9.55)")
    print("4. Veggie Pile sub ($6.75)")
    selection = int(input("I want: "))
    #Depending on the selection adding different subs, prices
    if (selection == 1):
        sub = "\"Meat\"-ball sub" #selecting user sub
        price = 7.99 #to all the prices together
    elif (selection == 2):
        sub = "Cold-cut Club sub"
        price = 8.25
    elif (selection == 3):
        sub = "Philly's Cheese Mis-Steak sub" 
        price = 9.55
    elif (selection == 4):
        sub = "Veggie Pile sub"   
        price = 6.75
    #Getting the program to exit if they made a valid choice
    if price > 1: #If the price is bigger than one then input must be valid
        break
    else: #If not print the massage bellow
        print("You did not select one of the allowed subs. Try again!")

#printing a devider
print(liner)

#into to toping selection
print("Select your toppings.")
print("Type any of the following and hit enter:")
print("lettuce, tomatoes, onions, peppers, jalepenos, pickles, cucumbers, olives, or guacamole.")
print("Please note: guacamole costs an extra $1.50")
print("Type \"done\" to stop.")
#Getting user to select toppings
while True:
    #getting user input
    selection = input("I want: ")

    if selection == "guacamole":
        toppings = toppings + selection + ", " #adds to all the topings
        price = price + 1.5 #adds to price if they selected gaucamole
    elif selection == "lettuce" or selection == "tomatoes" or selection == "onions" \
    or selection == "peppers" or selection == "jalepenos" or selection == "pickles" \
    or selection == "cucumbers" or selection == "olives": #checks for all the allowed topings
        toppings = toppings + selection + " " #adds to all the topings
    elif selection == "done": #if user is done exits
        break
    else: #if selection is invalid makes them try again
        print(selection + " is not a toping, try again")
print(liner)
#printing order nicly
print("Your Order")
print("sub: " + sub)
print("toppings: " + toppings)
selection = input("is this correct? (y/n) ")
if selection == "y": #only done calculations if they order was right
    tax = price * 0.13 #calculated tax
    totalPrice = price + tax #calculated the total price
    #prints order bellow
    print("\n"+liner)
    print(f"Subtotal: ${price:.2f}")
    print(f"Tax:      ${tax:.2f}")
    print(liner)
    print(f"Total     ${totalPrice:.2f}")
else: #If the order was wrong
    print("I am sorry please try again!")


    

