#Tal Aizikov     101150420
#This program will play fantasy football 

def readStats(f):
#This function will open and read all the stats from a seperated file
#The function will return a 2d list containing all the players

    data = [] #All the data from fi will go in data
    try:
        fi = open(f, "r") #Opens the file (only for reading)

        header = fi.readline() #Gets read of the header


        #A for loop to write everyline from the file as a 2d list
        for line in fi:
            line = line.strip("\n") #Strips line of \n 
            strings = line.split(",") # Split the line on runs of ,
            data.append(strings) # Add the "row" to the list.

        #A for loop to change all numbers to int
        for i in range (0,len(data)):
            #Second for used because we are dealing with a 2D least
            for j in range (0,len(data[0])):
                #using try so program doesnt crash
                try:#Checks if can be an int 
                    if type(int(data[i][j]))==int: 
                        data[i][j] = int(data[i][j])#if it can be an int changes it to an int
                except:#if it cants then it moves to the next value
                    pass
        fi.close()#always gotta close the file
    except FileNotFoundError:
        fileNotFound(f)
    except:
        print("something went wrong")
    return data #if file name invalid returns empty list


def fileNotFound (fileName):
    print("Sorry " + str(fileName) + " is not a valid for this program")


def statsForPlayer(lis, name):
#This function will take a 2d list of the players 
#and turn it into a 1d least of a player
    playerStats=[] #A spesific players stats will go here
    #Double for because 2D least
    for i in range (len(lis)): 
        if lis[i][0] == name: #If the least at position 0{names) then name matches
            for j in range (len(lis[0])): #another loop to write all the stats
                playerStats.append(lis[i][j]) #adds stats to the list
    return playerStats 
            

def filterByPos(lis, pos):
#This function will take a 2d least and filter it by position
#If its defense it will get all the defense players in one least
    positionList = []
    #Double for loop again
    for i in range (0,len(lis)):
        if lis[i][2] == pos:#checks if it the position wanted
            positionList.append([]) #adds a row
            for j in range (0,len(lis[0])): #loops to add all stats
                positionList[(-1)].append(lis[i][j]) #-1 is always the last index Therefore always adds to the end
    return positionList


def sortByPoints(lis):
#This function will take a 2d least and sort it by 
#The amount of points each player scored from high to low
    lis = lis[:] #lis = lis+[] #Makes a copy of list to not change the original
    #double for loop again
    for i in range (0,len(lis)):
        for j in range(1,len(lis)):
            if lis[j-1][6] < lis[j][6]: #if pair is out of order
                #swap
                temp = lis[j-1]
                lis[j-1] = lis[j]
                lis[j] = temp
    #return output  
    return lis

def buildBestTeam(stats,file):
#This function will take a 2d list and select the best players
#Then put the all to a seperated file
#1 Centre (C), 1 Leftwing (LW), 1 Rightwing (RW), and 2 Defence (D)
    #vars to check how many kinds of players there are VV
    try:
        moreThenOne = False
        #VVV used to find what positions are in the list
        positionList = []
        #VVV adds best team to this var which will later add to a file
        bestTeam = ""

        #V This loop will collect and check for what positions are on the list
        for i in range(0,len(stats)):
            positionList.append(stats[i][2]) #gets all the positions in the list
            if positionList[i] == positionList[i-1]: #if both positions are same onePosition is True
                moreThenOne = False #CHECK HERE COULD MAYBE USE ONE VAR!!!'''
            else:
                moreThenOne = True #If they are not the same there must be more then one position
            
        #V sets amout of players needed to 0 (will change later)    
        DPlayers = 2
        CPlayers = 1
        LWPlayers = 1
        RWPlayers = 1

        #If it is one position then has 5 players from that position
        if positionList[0] == "D":
            DPlayers = 0
        elif positionList[0] == "C":
            CPlayers = 0
        elif positionList[0] == "LW":
            LWPlayers = 0
        elif positionList[0] == "RW":
            RWPlayers = 0
        #if more then one then goes with defult team
        if moreThenOne == True: #both true means many positions
            DPlayers = 0
            CPlayers = 0
            LWPlayers = 0
            RWPlayers = 0

        #V The loops here finds the best in the in the position
        for i in range(CPlayers,1):
            position = filterByPos(stats,"C") #makes a list of positions
            bestAtposition = sortByPoints(position) #Organized by best to worst
            bestTeam += bestAtposition[i][0] + "\n" 
        for i in range(LWPlayers,1):
            position = filterByPos(stats,"LW")
            bestAtposition = sortByPoints(position)
            bestTeam += bestAtposition[i][0] + "\n"
        for i in range(RWPlayers,1):
            position = filterByPos(stats,"RW")
            bestAtposition = sortByPoints(position)
            bestTeam += bestAtposition[i][0] + "\n"
        for i in range(DPlayers,2):
            position = filterByPos(stats,"D")
            bestAtposition = sortByPoints(position)
            bestTeam += bestAtposition[i][0] + "\n"

        bestTeam = bestTeam[:-1] #removes last \n to make it exactly 5 lines!!
        f = open(file, 'w') #opens to write
        f.write(bestTeam) #writes to file
        f.close()
    except OSError:
        fileNotFound(file)
    except FileNotFoundError:
        fileNotFound(file)
    except:
        print("sorry there was an error running the program")

def displayTeamStats(stats, playerFile):
#This file will take a team and display all their stats
    f = open(playerFile, 'r')
    data = []
    for line in f: 
        player = [""]
        line = line.strip("\n") #Strips line of 
        number_strings = line.split(",") # Split the line on runs of ,
        player[0]=(number_strings[0]) # Add the "row" to your list.
        for i in range(0,len(stats)):
            if stats[i][0] == player[0]:#if finds player in the list
                for j in range (1,len(stats[0])): #Then adds all there stats to another list
                    player.append(stats[i][j]) 
        data.append(player) #Makes the list 2D (AKA adds a row)
    print("Name                       Team   Pos    Games  G      A      Pts    PIM    SOG    Hits   BS") #Header
    print("="*94) #dividing line
    for i in range (0,len(data)): 
        for j in range(0,len(data[0])):
            if j ==0:
                print(f"{data[i][j]:27}", end='') #The first lines with names makes it big!
            else:
                print(f"{data[i][j]:<7}", end='') #even splacing between all other things
        print("") #like \n goes down a line
    f.close()
        

def pointsPerTeam(stats, playerFile):
#This file will take a team and return the total points score together
    f = open(playerFile, 'r') 

    player=[]
    points = 0 #starts points at 0 because you dont know who players are
    for line in f: 
        line = line.strip("\n") #Strips line of ]n
        player += line.split(",") # Split the line on runs of 
    #V simple loop to add points
    for i in range(0,len(player)):
        for j in range(0,len(stats)):
            if player[i] == stats[j][0]:
                points += stats[j][6]
    return points


#Below is a main for testing
def main():
    allStats = readStats("nhl_2018.csv")
    playerStats = statsForPlayer(allStats,"Jason Zucker")
    temp = (filterByPos(allStats, "D"))
    pointList = (sortByPoints(allStats))
    buildBestTeam(temp,"bestPlayers.txt")
    displayTeamStats(allStats, "bestPlayers.txt")
    print(pointsPerTeam(allStats,"bestPlayers.txt"))

main()

#ALSO NOTE: all bonus questions should work!!