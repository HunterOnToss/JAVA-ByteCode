

def fizBizz(a):

    str = ""
    if a % 3 == 0:
        str += "fizz"
    if a % 5 == 0:
        str += "bizz"

    if str : print str


for i in xrange(1, 1000):
    fizBizz(i)
