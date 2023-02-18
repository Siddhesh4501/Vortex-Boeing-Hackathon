# Importing flask module in the project is mandatory
# An object of Flask class is our WSGI application.
from flask import Flask
from flask import request
from stats import *
# Flask constructor takes the name of
# current module (__name__) as argument.
app = Flask(__name__)

# The route() function of the Flask class is a decorator,
# which tells the application which URL should call
# the associated function.


IR_Material_Temp=[]
Thermostat_Freezor_Temp=[]



@app.route('/')
# ‘/’ URL is bound with hello_world() function.
def hello_world():
	return 'Hello World'



@app.route('/api/temperature',methods=["POST"])
def get_temperature_data():

    data=request.json
    type=data["type"]
    timestamp=data["timestamp"]
    temperature=data["temperature"]

    if(type==1):
        Thermostat_Freezor_Temp.append(temperature)
        if(len(Thermostat_Freezor_Temp)>10):
           Thermostat_Freezor_Temp.pop(0)
    elif(type==2):
        IR_Material_Temp.append(temperature)
        if(len(IR_Material_Temp)>10):
            IR_Material_Temp.pop(0)

    return "hello"
    

# @app.route('/api/getData',methods=["GET"])
# def get_Analasys_of_Temperature():






# main driver function
if __name__ == '__main__':

	# run() method of Flask class runs the application
	# on the local development server.
	app.run(port=5000,debug=True)
