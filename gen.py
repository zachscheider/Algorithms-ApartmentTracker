import sys
import string
import random as rng

# Alter this based on your needs
# Apt object passed in is much like
# that of the main Java project
def display(apt):
    s = "thePQ.insert(new Apartment({},{},{},{},{},{}));".format(
        apt.address,
        apt.apt_num,
        apt.city,
        apt.zip,
        apt.price,
		apt.sqr_feet
    )
    print(s)

def main(num):
    apt = Apt()
    for _ in range(int(num)):
        rand_apt(apt)
        display(apt)


def rand_string():
    s = "\""
    alphabet = string.ascii_letters + " " + string.digits
    for i in range(rng.randint(1,30)):
        s += alphabet[rng.randint(0,len(alphabet) - 1)] 
    s += "\""
    return s

def rand_int():
    return str(rng.randint(0,100))

def rand_apt(apt):
    apt.city    = rand_string()
    apt.address = rand_string()
    apt.apt_num = rand_int()
    apt.zip     = rand_int()
    apt.price   = rand_int()
    apt.sqr_feet = rand_int()

class Apt:
    def __init__(self):
        self.city     = ""
        self.address  = ""
        self.apt_num  = -1
        self.zip      = -1
        self.price    = -1
        self.sqr_feet = -1

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Include the number of trials to generate")
    else:
        main(sys.argv[1])