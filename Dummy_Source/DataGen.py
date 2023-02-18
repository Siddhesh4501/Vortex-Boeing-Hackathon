import requests
import datetime
import time
import json
IR_Material_Temp=[20,21,20,22,23,24,25,28,25,24,24,24,25,23,22,24,23,23,22,23]
Thermostat_Freezer_Temp=[10,11,10,12,11,9,10,10,10,9,11,10,10,10,12,11,10,12,11,9,10]

SERVER_IP="http://127.0.0.1:5000/api/temperature"

count=0
len=len(IR_Material_Temp)
while(True):
    count=count%len
    data={}
    data["type"]=1
    data["timestamp"]=datetime.datetime.now().strftime("%s")
    data["temperature"]=Thermostat_Freezer_Temp[count]
    resp=requests.post(SERVER_IP,json=data)
    print(resp)
    data={}
    data["type"]=2
    data["timestamp"]=datetime.datetime.now().strftime("%s")
    data["temperature"]=IR_Material_Temp[count]
    resp=requests.post(SERVER_IP,json=data)
    print(resp)

    time.sleep(1)
    
    count+=1


