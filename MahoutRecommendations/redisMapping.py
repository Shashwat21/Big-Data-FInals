import redis
import os

isbn_dictionary = {}

with open('/Users/varshacholennavar/Desktop/AmazonReviews/BX-CSV-Dump/Book-Mappings.csv', 'rb') as f:
    for line in f:
        try:
            isbn, counter = line.split('#@#')
            isbn_dictionary[int(counter)] = isbn
        except:
            print isbn, counter

r = redis.StrictRedis(host='localhost', port=6379, db=0)

p = os.popen('/usr/local/bin/hadoop-2.8.0/bin/hadoop fs -cat /user/varshacholennavar/recommendations/part*')

for recommendation in p:
    user, recommendations = recommendation.split('\t')
    recommendations = [entry.split(':')[0] for entry in recommendations.replace('[', '').replace(']', '').split(',')]
    recommendations = [isbn_dictionary[int(entry)] for entry in recommendations]
    r.set(int(user), recommendations)
