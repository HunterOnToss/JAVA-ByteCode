print "public class TooLong {\n"

y = 0

while y < 70000:
    print "public void m{}() ".format(y)
    print "{}"

    y += 1

print "\n}"