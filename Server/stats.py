import numpy as np


MAX_COMPOSITE_MATERIAL_TEMP=25
MIN_COMPOSITE_MATERIAL_TEMP=20

MAX_TEMP_FLUCTUATIONS_SENSOR=3


FREEZER_TEMP_VALUE=10
MAX_FREEZER_TEMP_FLUCTUATIONS_ALLOWED=2

def Analyze_Temperatures(temperatures):
    mean=np.average(temperatures)
    mode=np.mod(temperatures)
    median=np.median(temperatures)
    variance=np.var(temperatures);
    stDev=np.std(temperatures)
    return (mean,mode,median,variance,stDev)


def Check_For_Sensor_Correctness(temperatures):
    if(len(temperatures)<10):
         return "GOOD"
    count=0
    for i in range(len(temperatures)-9,len(temperatures)):
        diff=temperatures[i]-temperatures[i-1]
        if(diff>MAX_TEMP_FLUCTUATIONS_SENSOR):
            count+=1
    if(count>7):
           return "BAD"
    if(count>4):
        return "MODERATE"
    return "GOOD"


def Check_Freezer_Correctness(temperatures):
    if(len(temperatures)<10):
         return "GOOD"
    count=0
    for i in range(len(temperatures)-9,len(temperatures)):
        diff=temperatures[i]-temperatures[i-1];
        if(diff>MAX_FREEZER_TEMP_FLUCTUATIONS_ALLOWED):
            count+=1
    if(count>7):
       return "BAD"
    if(count>4):
        return "MODERATE"
    return "GOOD"



def Analyse_Composite_Material(temperatures):

    Threshold_Value_Cross=0
    TotalTempCrossValue=0
    for i in temperatures:
        if(i>MAX_COMPOSITE_MATERIAL_TEMP or i<MIN_COMPOSITE_MATERIAL_TEMP):
           Threshold_Value_Cross+=1
           TotalTempCrossValue+=min(abs(MAX_COMPOSITE_MATERIAL_TEMP-i),abs(MIN_COMPOSITE_MATERIAL_TEMP-i))
    if(Threshold_Value_Cross>10 or TotalTempCrossValue>40):
           return (2,"Material slightly damaged")
    if(Threshold_Value_Cross>5 or TotalTempCrossValue>20):
       return (1,"Material slightly damaged")
    return (0,"Material is in good condition")
    
    





# Thermostat_Material_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]
# IR_Material_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]
# Thermostat_Freezer_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]

# STATS=Analyze_Temperatures(Thermostat_Material_Temp)

# SENSOR_ACCURACY_MEASURE=Check_For_Sensor_Correctness(Thermostat_Material_Temp)

# Composite_Material_Analysis=Analyse_Composite_Material(Thermostat_Material_Temp)

# Freezer_CORRECTNESS=Check_Freezer_Correctness(Thermostat_Freezer_Temp)




