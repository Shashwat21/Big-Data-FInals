import redis
from flask import Flask
from flask import request

r = redis.StrictRedis(host='localhost', port=6379, db=0)

app = Flask(__name__)

@app.route('/user/<user_id>', methods = ['GET'])
def restful_api(user_id):
    if request.method == 'GET':
        recommendations = r.get(int(user_id))
        return recommendations if recommendations else "User %d does not exist" % int(user_id)
    else:
        return "No idea what you've done here, but don't do it again"

if __name__ == '__main__':
    app.run()