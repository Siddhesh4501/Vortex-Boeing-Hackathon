# Importing flask module in the project is mandatory
# An object of Flask class is our WSGI application.
from flask import Flask
from flask import request
from stats import *
import json
# Flask constructor takes the name of
# current module (__name__) as argument.
app = Flask(__name__)

# The route() function of the Flask class is a decorator,
# which tells the application which URL should call
# the associated function.

# TEMP_BUFFER_SIZE=10
IR_Material_Temp=[20]
Thermostat_Freezer_Temp=[10]

location=[[18.5204,73.8567]]





@app.route('/')
# ‘/’ URL is bound with hello_world() function.
def hello_world():
	return 'Hello World'



@app.route('/api/temperature',methods=["POST"])
def get_temperature_data():

    data=request.json
    # print(type(data))
    typeofdata=data["type"]
    timestamp=data["timestamp"]
    

    if(typeofdata==1):
        temperature=data["temperature"]
        Thermostat_Freezer_Temp.append(temperature)
        # if(len(Thermostat_Freezer_Temp)>TEMP_BUFFER_SIZE):
        #    Thermostat_Freezer_Temp.pop(0)
    elif(typeofdata==2):
        temperature=data["temperature"]
        IR_Material_Temp.append(temperature)
        # if(len(IR_Material_Temp)>TEMP_BUFFER_SIZE):
        #     IR_Material_Temp.pop(0)
    elif(typeofdata==3):
        curr_location=data["location"]
        location.append(curr_location)
        # print(type(curr_location[0]))

    print(len(IR_Material_Temp),len(Thermostat_Freezer_Temp))
    return "hello"
    

@app.route('/api/getData',methods=["GET"])
def get_Analasys_of_Components():
    data={}
    data["materialTemp"]=IR_Material_Temp[-1]
    data["freezerTemp"]=Thermostat_Freezer_Temp[-1]
    data["materialSensorStatus"]=str(Check_For_Sensor_Correctness(IR_Material_Temp))
    data["sensorStatus"]=str(Check_Freezer_Correctness(Thermostat_Freezer_Temp))
    data["location"]=location[-1]
    data["materialAnalysis"]=Analyse_Composite_Material(IR_Material_Temp)
    data=json.dumps(data)
    print(data)
    return data




# main driver function
if __name__ == '__main__':

	# run() method of Flask class runs the application
	# on the local development server.
	app.run(host='0.0.0.0', port=5000,debug=True)
	
