# IOT - Time and Temperature Sensitive Material in Logistics

<br>
Out IOT model provides real-time measurement of temperature data from the composite materials transportation system, analyze it and shows various parameters and provide real time monitoring of the  composite material.


## NodeMcu and Temperature Sensor

We used NodeMcu and temperature sensor for continuous monitoring of freezor temperature. We attached temperature sensor to freezor for real time monitoring of freezor temperature and intergrate it with NodeMcu for digital output.


**Temperature sensor used:**
SparkFun High Precision Temperature Sensor - TMP117 (Qwiic)

Features:
1. Uses I2C interface (Qwiic-enabled)
2. Four selectable addresses
    - 0x48 (default), 0x49, 0x4A, 0x4B
3. 16-bit resolution, 0.0078°C
4. High accuracy, digital temperature sensor
    - ±0.1°C (max) from –20°C to 50°C
    - ±0.15°C (max) from –40°C to 70°C
    - ±0.2°C (max) from –40°C to 100°C
    - ±0.25°C (max) from –55°C to 125°C
    - ±0.3°C (max) from –55°C to 150°C

## Raspberry Pi Thermal Camera in Real Time with MLX90640
For monitoring of composite material, it will not always possible to attach thermal sensor to its surface. We need to think of some innovative idea to measure temperature of composite material.

We are here using Thermal camera to sense temperatue of material and integrated it with Raspberry Pi to provide real time temperature of composite material irrespictive of its state and reaction with surrounding.



## Server implemented in Python Flask

Our sever is the heart of our IOT model. We are getting data from NodeMcu and Raspberry Pi, processing it and delivering processed data to the app.
Our server is implemented using flask and fetching real time data from  sensors and delivering processed data to the app.
We design various algorithm for checking correctness of sensors and measuring status of composite material.


## VortexBoing App

App is the front face of our IOT model. It fetches data from the server and gives user real time experience for monitoring of its material. It is implemented in Java and also provides tracking option for real time position tracking of your goods. It showcase user various paramater such as material temperature, freezor temperature, current condition of material, etc.


