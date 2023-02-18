import numpy as np


MAX_COMPOSITE_MATERIAL_TEMP=25
MIN_COMPOSITE_MATERIAL_TEMP=20

TOLERENCE_VALUE=4
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
    count=0
    for i in range(1,len(temperatures)):
        diff=temperatures[i]-temperatures[i-1]
        if(diff>MAX_TEMP_FLUCTUATIONS_SENSOR):
            count+=1
    if(count>TOLERENCE_VALUE):
        return False
    return True


def Check_Freezer_Correctness(temperatures):
    count=0
    for i in range(1,len(temperatures)):
        diff=temperatures[i]-temperatures[i-1];
        if(diff>MAX_FREEZER_TEMP_FLUCTUATIONS_ALLOWED):
            count+=1
    if(count>10):
        return False
    return True



def Analyse_Composite_Material(temperatures):

    Threshold_Value_Cross=0
    TotalTempCrossValue=0
    for i in temperatures:
        if(temperatures>MAX_COMPOSITE_MATERIAL_TEMP or temperatures<MIN_COMPOSITE_MATERIAL_TEMP):
           Threshold_Value_Cross+=1
           TotalTempCrossValue+=min(abs(MAX_COMPOSITE_MATERIAL_TEMP-temperatures),abs(MIN_COMPOSITE_MATERIAL_TEMP-temperatures))
    
    return (Threshold_Value_Cross,TotalTempCrossValue)
    
    





# Thermostat_Material_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]
# IR_Material_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]
# Thermostat_Freezor_Temp=[20,21,20,22,23,24,25,26,25,24,24,24,25,23,22,24,23,23,22,23]

# STATS=Analyze_Temperatures(Thermostat_Material_Temp)

# SENSOR_ACCURACY_MEASURE=Check_For_Sensor_Correctness(Thermostat_Material_Temp)

# Composite_Material_Analysis=Analyse_Composite_Material(Thermostat_Material_Temp)

# FREEZOR_CORRECTNESS=Check_Freezer_Correctness(Thermostat_Freezor_Temp)




