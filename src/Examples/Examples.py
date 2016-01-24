print "Part 1: "

def square(x):
    return x*x
print square(4)
print square

print "Part 2: "

x = 5
if x < 6:
    print 1
else:
    print 0

print "Part 3: "

while i < max-1:
    print i
    i = i + 1

print "Part 4: "

def return6():
    return 6
x = 5
print (x <= 5)
print (x <= (return6()))
print (x <= (return6()-2))
print (10 <= (10-20))

print "Part 5: "

def looptest(x):
    i = 0
    sum = 0
    while (i < x):
        j = 0
        while j < i:
            print j
            j = j+1
        i=i+1
looptest(4)
        