import sys
import re

#Creating Empty Data dictionary for isbn and bookTitle
isbn_dictionary = {}
isbn_title = {}
counter = 0
i = 0

#Filling in the bookTitle dictionary
with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/TestName.csv', 'rb') as z:
    for newLines in z:
        isbn, bookTitle = newLines.split('#@#')
        isbn_title[isbn] = bookTitle

with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/BX-Book-Ratings.csv', 'rb') as f:
    with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/New-Book-Ratings.csv', 'w') as g:
        with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/Test-Book-Mappings.csv', 'w') as h:
            for line in f:
                if i == 0:
                    i = 1
                    pass
                else:
                    line = line.replace("\"", "")
                    user, book, rating = line.split(';')
                    try:
                        index = isbn_dictionary[book]
                    except:
                        isbn_dictionary[book] = counter
                        index = counter
                        try:
                            title = isbn_title[book]
                            title = str(title)
                            title = title.replace("\n", "")
                            h.write("%s#@#%s#@#%d\n" % (book, title, counter))
                            counter += 1
                        except:
                            title = "Not Found"
                            h.write("%s#@#%s#@#%d\n" % (book, title, counter))
                            counter += 1
                    g.write("%d,%d,%d\n" % (int(user), index, int(rating)))
                    #print counter