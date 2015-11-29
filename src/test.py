def fact(x): {
    temp = 0
    if (x < 1): {
      temp = 1
    }
    else: {
      temp = x * (x-1)
      return temp
    }
}

{
  print fact(n)
}