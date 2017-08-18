import os
from pymongo import MongoClient

# 'Creating Dictionaries
isbn_dictionary = {}
title_dictionary = {}

# 'Connecting MongoDB
client = MongoClient()
dbnames = client.database_names()
# 'Checking if database exists with the same name
if 'AmazonReviewsTitles' in dbnames:
    client.drop_database('AmazonReviewsTitles')
# 'Creating new Database
db = client.AmazonReviewsTitles

# 'Function to return ISBN if the Book Title is not available
def recomSend(link):
    if title_dictionary[int(link)] == "Not Found":
        return "ISBN:" + isbn_dictionary[link]
    else:
        return title_dictionary[link]

# 'Entering values in isbn and title dictionaries
with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/Test-Book-Mappings.csv', 'rb') as f:
    for line in f:
        try:
            isbn, bookTitle, counter = line.split('#@#')
            isbn_dictionary[int(counter)] = isbn
            title_dictionary[int(counter)] = bookTitle
        except:
            print isbn, counter

# 'Opening hadoop recommendation file
p = os.popen('/usr/local/bin/hadoop-2.8.0/bin/hadoop fs -cat /user/varshacholennavar/recommendations/part*')

# 'Looping through each line
for recommendation in p:
    # 'Separating Users And Recommendations from the mahout recommendation output
    user, recommendations = recommendation.split('\t')
    # 'Splitting by delimiter ':' and taking the first value which are the indexes for titles and isbn
    recommendations = [entry.split(':')[0] for entry in recommendations.replace('[', '').replace(']', '').split(',')]
    # 'Calling UDF recomSend() and sending each indexes to search from titles and isbn dictionaries
    recommendations = [recomSend(int(entry)) for entry in recommendations]
    try:
        result = db.recommendations.insert_one({"UserID": int(user), "Recommendations": recommendations})
    except:
        recommendations = str(recommendations).decode('iso-8859-1').encode('utf8')
        result = db.recommendations.insert_one({"UserID": int(user), "Recommendations": recommendations})