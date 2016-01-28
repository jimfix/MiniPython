def everything(x,y,z):
    if x:
        print y
    else:
        print z
    a = (y + z) / y
    return a

print everything((24 == 24),(18 - 4),30)