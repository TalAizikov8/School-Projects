x = 0

while x <= 20:
    if x%2 == 0:
        print(str(x))
    x += 1

#or

x = 0
while x < 21:
    print(x)
    x = x + 2

# or 
x = 0
b = True
while x <= 20:
    if b == True:
        print(x)
    b = not b    
    x += 1