def makeLists(n):
    lis = []
    for i in range(0,n):
        lis.append(i)
    print(lis)

def makeOddList(n):
    lis=[]
    for i in range(n):
        if i%2==1:
            lis.append(i)
    print(lis)

def makeCountDown(n):
    lis=[]
    for i in range(n, 0, -1):
        lis.append(i)
    print(lis)


makeLists(10)
makeOddList(20)
makeCountDown(10)

