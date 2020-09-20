def cap(word):
    out = ''
    for i in range (len(word)):
        if  i == len(word)-1 or word[i+1] == ' ':
            out+= word[i].upper()
        else:
            out+= word[i]
    print(out)

cap("hello my name is tal")

def userWord():
    word = ""
    words = [[],[],[],[],[],[],[],[],[]]
    while word != "stop":
        word=input("Word: ")
        words[len(word)].append(word)
    print (words)

userWord()

def reverse(lis):
    if len(lis) == 0:
        return []
    else:
        return reverse(lis[1:]) + [lis[0]]


print(reverse([5,4,3,2,1]))

def copyEvenLines(inFile, outFile):
    try:
        f = open(infile,"r")
        lines = f.readlines()

        f = open(outFile,"w")

        for e in range (0,len(lines), 2):
            f.write(lines[i])
        f.close()
        return True

    except:
        False

def mutateList(L):
    for i in range(0,len(L)):
        for j in range(0,len(L[i])):
            if L[i][j] % 2 == 0:
                L[i][j] //= 2
            elif L[i][j] < 10:
                L[i][j] *= 10
            if len(L[i]) < 5:
                for j in range(len(L[i]),5):
                    L[i].append(0)

lis = [1,2,3,4,1,7,12,8,4,5,6,3]

def find(lis, key):
    if lis[0]==key:
        return 0
    else:
        return 1 + find(lis[1:],key)


print(find(lis,8))