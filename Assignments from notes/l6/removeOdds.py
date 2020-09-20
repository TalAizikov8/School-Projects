def removeOdds(l):
    for i in range(len(i)):
        if l[i]%2==1: #odd
            l.pop(i) #concurrent modification
    print(i)

def removeOdds2(l):
    for i in range(len(l)-1,-1,-1):
        if l[i]%2==1:
            l.pop(i) 
    print(l)

def removeOdds3(l):
    l2 = []
    for i in range(len(l)):
        if l[i]%2==0:
            l2.append(l[i]) 
    print(l2)

removeOdds2([1,2,3,4,5,6,7,8])
removeOdds3([1,2,3,4,5,6,7,8])