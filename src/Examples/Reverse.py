def nil():
    return (0-1)

def rev(xs):
    rxs = nil()
    while xs != nil():
        rxs = pair(xs.left,rxs)
        xs = xs.right
    return rxs

def countDown(n):
    if n==0:
        return nil()
    else:
        return pair(n,countDown(n-1))

def printThem(xs):
    ys = xs
    while ys != nil():
        print ys.left
        ys = ys.right

downs = countDown(10)
printThem(downs)
ups = rev(downs)
printThem(ups)
print "hello"


