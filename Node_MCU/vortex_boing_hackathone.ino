#include <Wire.h>            // Used to establish serial communication on the I2C bus
#include <SparkFun_TMP117.h> // Used to send and recieve specific information from our sensor

#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <WiFiClient.h>
#include <NTPClient.h>
#include <ArduinoJson.h>


#define NTP_OFFSET   60 * 60      // In seconds
#define NTP_INTERVAL 60 * 1000    // In miliseconds
#define NTP_ADDRESS  "europe.pool.ntp.org"

TMP117 sensor; // Initalize sensor


WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, NTP_ADDRESS, NTP_OFFSET, NTP_INTERVAL);

WiFiClient client;

const char* ssid = "Evil";
const char* password = "DontAskMe";

String serverName = "http://192.168.204.200:5000/api/tempture";

void setup() {
  timeClient.begin();
  Wire.begin();
  Serial.begin(115200); 
  WiFi.begin(ssid, password);

  Wire.setClock(400000);   // Set clock speed to be the fastest for better communication (fast mode)

  // Function to check if the sensor will correctly self-identify with the proper Device ID/Address
  if(sensor.begin() == true) {
    Serial.println("Begin");
  }
  else {
    Serial.println("Device failed to setup- Freezing code.");
    while (1); // Runs forever
  }
  
  Serial.println("Connecting");
  while(WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());
 
  Serial.println("Timer set to 5 seconds (timerDelay variable), it will take 5 seconds before publishing the first reading.");
}
void loop() {
   timeClient.update();
   if (WiFi.status() == WL_CONNECTED && sensor.dataReady() == true) { //Check WiFi connection status 
    StaticJsonDocument<900> doc;
    
    unsigned long epcohTime =  timeClient.getEpochTime();
    float tempC = sensor.readTempC();

    doc["type"] = 1;
    doc["timestamp"] = epcohTime;
    doc["temperature"] = tempC;
    
    String jsonString;
    serializeJson(doc, jsonString);
    Serial.println(jsonString);
 
    HTTPClient http;    //Declare object of class HTTPClient
 
    http.begin(client, serverName);      //Specify request destination
    http.addHeader("Content-Type", "application/json");  //Specify content-type header
 
    int httpCode = http.POST(jsonString);   //Send the request
    String payload = http.getString();     //Get the response payload

    if (httpCode > 0) {
      String response = http.getString();
      Serial.println(response);
    } else {
      Serial.println("Error on HTTP request");
    }
    http.end();  
  } else {
    Serial.println("Error in WiFi connection");
  }
  delay(1000);  //Send a request every 1 seconds
}

/*
"type": 1,
"timestamp": 1676702712,
"temperature": 10, 
*/
