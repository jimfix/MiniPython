x = pair("hi","mom")
y = pair(50,60)
print x
print x.left
print x.right
x.left = "wow"
print x.left
x.left = y
print x.left
print x.left.left