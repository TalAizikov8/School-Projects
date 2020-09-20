def printList(myList):
    index = 0
    while index < len(myList):
        print(myList[index])
        index+=1

def printList2(myList):
    for i in range (0, len(myList)):
        print(myList[i])

def printList3(myList):
    for ele in myList:
        print(ele)


def sumList(aList):
    sum=0
    for ele in aList:
        sum+=ele



def squareList(lis):
    #for num in lis:
    #    num = num*num

    #    return lis

    for i in range(len(lis)):
        lis[i] = lis[i]*lis[i]
    return lis

def main():
    names= ['alice', 'bob', 'jhon', 'dean']
    printList(names)
    printList2(names)



main()