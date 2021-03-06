import flask
from flask import request
import string
import random
import time
import sys
app = flask.Flask(__name__)
app.config["DEBUG"] = True


def get_random_string(length):
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))



@app.route('/health-check/', methods=['GET'])
def healthCheck():
    randomNum = random.randint(0, 100)
    if randomNum > 1:
        print("waiting 30")
        time.sleep(30)
        print("done waiting")
        return ""
    elif  randomNum > 80 and randomNum < 90:
        return "not ok"
    else:
        return "ok"
    

@app.route('/data/', methods=['GET'])
def dataGet():
    return "HERES YOUR DATA: " + str(get_random_string(15))  + " from " + sys.argv[1] + "!"
    
    

@app.route('/data/', methods=['POST'])
def dataPost():
    print(request.data)
    return "HERES YOUR DATA: " + str(get_random_string(15))  + " from " + sys.argv[1] + "! - Content is: " + str(request.data)
    
    

app.run(host='0.0.0.0', port=sys.argv[1])


