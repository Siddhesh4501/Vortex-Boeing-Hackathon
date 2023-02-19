# IOT - Time and Temperature Sensitive Material in Logistics

<br>
Our IoT model offers a real-time measurement of temperature data from the transportation system of composite materials. It meticulously analyzes the data and showcases a diverse range of parameters while providing a continuous, real-time monitoring of the composite materials.


## NodeMcu and Temperature Sensor

We have utilized NodeMcu and temperature sensors for the seamless monitoring of freezer temperature. By attaching the temperature sensors to the freezer, we have been able to achieve real-time monitoring of its temperature. Through integration with NodeMcu, we can obtain digital output for precise and continuous temperature monitoring.


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


 
 
Sparkfun-senosr | NodeMcu
--- | ---
![sparkfun-senosr](https://user-images.githubusercontent.com/88923837/219882730-7eba4b8f-5ce3-48f7-a8ea-ae0b5a040903.jpg) | ![NodeMcu](https://user-images.githubusercontent.com/88923837/219882738-d57f8997-d864-4007-9df1-328de76ed060.png)





## Raspberry Pi Thermal Camera in Real Time with MLX90640
In scenarios where attaching a thermal sensor to the surface of composite materials is not always possible, we need to devise innovative solutions for temperature measurement. To overcome this challenge, we have implemented the use of a thermal camera to sense the temperature of the materials. By integrating the thermal camera with Raspberry Pi, we can provide continuous, real-time temperature monitoring of the composite materials, regardless of their state or reaction with their surroundings.

RaspberryPi |  Thermal Imaging Camera MLX90640
--- | ---
![RaspberryPi_MLX90640](https://user-images.githubusercontent.com/88923837/219882744-7ae4b980-c948-4b37-997c-0b53f144f9fd.png) | ![MLX90640](https://user-images.githubusercontent.com/88923837/219882748-c76e3768-259b-443b-8874-da40c82becc1.jpg)


## Server implemented in Python Flask

At the core of our IoT model lies our server, which receives data from NodeMcu and Raspberry Pi, processes it, and delivers the processed data to the application. Our server is built using Flask and is designed to fetch real-time data from sensors and deliver accurate and relevant information to the app. We have also developed various algorithms to validate sensor readings and measure the status of composite materials, ensuring the accuracy and reliability of the information provided to the users.



## VortexBoing App

Our IoT model boasts a sophisticated mobile application that serves as the front-end of our system, providing users with a real-time experience for monitoring their composite materials. The application, built using Java, fetches real-time data from the server, enabling users to keep a close eye on their materials at all times. The application also features a tracking option for real-time position tracking of goods, making it easier for users to keep track of their materials' whereabouts. To access these features, users can log in to the application using their plain ID and password, which ensures secure and private monitoring of their materials. In addition, the application showcases various critical parameters, including material temperature, freezer temperature, current condition of the material, and more, delivering comprehensive insights and relevant information to the users.


Login page | Monitoring display
--- | ---
![monitor-display](https://user-images.githubusercontent.com/88923837/219923440-271fb8e7-8f93-477c-aeeb-7495a91f6330.jpeg) | ![monitor-display](https://user-images.githubusercontent.com/88923837/219923440-271fb8e7-8f93-477c-aeeb-7495a91f6330.jpeg)



