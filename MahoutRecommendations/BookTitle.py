import sys
import re
import numpy as np
import pandas as pd
import csv
from collections import defaultdict

i = 0
isbn_dictionary = {}
counter = 0
with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/BX-Books.csv', 'rb') as a:
    with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/TestName.csv', 'w')as h:
        for line in a:
            if i == 0:
                i = 1
                pass
            else:
                line = line.replace("\"", "")
                data = line.split(';')
                isbn = data[0]
                book = data[1]
                isbn_dictionary[book] = counter
                index = counter
                h.write("%s#@#%s\n" % (isbn, book))
                counter += 1