#Tal Aizikov   101150420

"""This program will play a game with you,
you will need to enter symbols and a location and it will change all the surrounding sybols
that are the same into your symbold"""

#the function below will read the level chose from a file
def readLevels(lvl):
    try: #try incase the file name is invalid
        fil = "ascii_levels/ascii_level" + str(lvl) + ".txt" #the file opened changes depending on level chosen
        f = open(fil, "r") #opens file

        symbols = [] #will print 2D list into this list
        #for loop writes file into a 2D least
        for line in f:
            line = line.strip("\n") #Strips line of \n 
            line = list(line)
            symbols.append(line) # Add the "row" to the list.
            
        f.close() #always close file
        return symbols #returns the 2D list of symbols
    except: #if couldnt find the file will print v
        print("failed to read the game")

#the function below will display the corrent board
def displayBoard(lis):
    liner = 0 #the liner will deside how long the devider in (---------)

    print("   ",end="") #here to keep everything nice and lined, could also use f string
    for i in range (0,len(lis[0])): #prints the colums
        print(str(i%10), end = "")
        liner+=1

    print("\n   "+"-"*liner)#goes down a line. line an enter

    for i in range (0,len(lis)): #loops to print row and all numbers in list
        if i < 10:
            print("0"+str(i)+"|", end="") #prints the rows if the less then 10 by adding a 0 first
        else:
            print(str(i)+"|", end="") #prints 2 digit rows
        for j in range (0,len(lis[0])): #prints all numbers in a single row
            print(lis[i][j], end="")
        print("")


#This function will use a recorution loop to change all symbols into the wanted one
def fill(board, replaced, wanted, x, y):
    try: #try incase input invalid 
        #below makes sure within input range
        #base case
        if x < 0:
            pass
        elif y < 0:
            pass
        elif x > len(board) -1:
            pass
        elif y > len(board[0])-1:
            pass
        
        else: #if it is does this v
            if board[x][y]== wanted: #if they chose a letter thats already there
                return 

            if board[x][y] != wanted and board[x][y] == replaced: 
                board[x][y] = wanted #changes to the wanted sign 

                #below checks all around and sends it into the fill function again
                fill(board, replaced, wanted, x+1, y)
                fill(board, replaced, wanted, x-1, y)
                fill(board, replaced, wanted, x, y-1)
                fill(board, replaced, wanted, x, y+1)
        
    


        
    except:
        pass


     


    

#belows fanction is extra to keep code organized
def checkWinner(board):
    for i in range(0, len(board)-1):
        for j in range (0, len(board[0])-1):
            if board[i][j] != board[i][j+1]: #checks every row is the same
                return False
            elif board[i][j] != board[i+1][j]:
                return False
            elif board[i][j] != board[i+1][j+1]:
                return False
    return True

#this function gets the user action
def getUserAction(row, col):
    action = []
    
    #while true if input is valit it breaks
    while True:
        userSymbol = input("Please choose the wanted symbol (@,#,%,&): ")
        if userSymbol == '#' or userSymbol == '%' or userSymbol == '&' or userSymbol == '@':
            action.append(userSymbol)
            break
        else:
            print("You did not choose one of the allowed symbols")
        
    while True:
        userRow = input("Please choose a wanted row [0, "+str(row)+"] ")
        if int(userRow) > row or int(userRow) < 0:
            print("bad input Please choose a wanted row [0, "+str(row)+"]")
        else:
            action.append(int(userRow))
            break

    while True:
        userCol = input("Please choose a wanted row [0, "+str(col)+"] ")
        if int(userCol) > col or int(userCol) < 0:
            print("bad input Please choose a wanted row [0, "+str(col)+"]")
        else:
            action.append(int(userCol))
            return action    

        


                    
            



def main():
    #while true if they dont want to continue breaks
    while True:
        moves = [0,0,0,0,0,0] #at [0] is level 1 [1] level 2.. at [5] is total moves
        for i in range (1,6):
            level = readLevels(i)
            winner = False
            print("ROUND" + str(i))
            while winner == False:
                displayBoard(level)

                action = (getUserAction((len(level)-1), len(level[0])-1)) #-1 because they start at 0 and not 1

                
                x = int(action[1])
                y = int(action[2])
                symbolReplaced = level[x][y]

                wantedReplace = action[0]

                fill (level, symbolReplaced, wantedReplace,x , y)

                moves[i-1] +=1
                moves[5] +=1
                winner = checkWinner(level)
                if winner == True:
                    print(f"round {i} took {moves[i-1]} moves")
        print("YOU WIN!!")
        print(f"Your total moves are {moves[5]}")

        while True:
            playAgain = print("Would you like to play again? (y,n)")
            if playAgain == 'y' or playAgain == 'n':
                break
            else:
                print("enter a valid input")
        if playAgain == "n":
            break
        

    
main()