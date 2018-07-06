#include <SoftwareSerial.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
//자이로 헤더
#include "Wire.h" 
#include "I2Cdev.h"
#include "MPU6050.h"

//자이로
MPU6050 accelgyro;
 
int16_t ax, ay, az;
int16_t gx, gy, gz;
 
int accel_reading;
int accel_corrected;
int accel_offset = 200;
float accel_angle;
float accel_scale = 1;
 
int gyro_offset = 151;
double dgy_x,deg;
double angle;
float last_read_time;
float last_x_angle,last_y_angle,last_z_angle;
//자이로

SoftwareSerial HM10(2,3); // RX, TX 블루투스
//심장박동
int sensorPin = A0; 
double alpha = 0.75;
//심장박동

//온도센서
int outputpin= A2;


char temp_t[20] = "t";
char zyro_z[20] = "z";
char heart_h[20] = "h";
char smoke_s[20] = "s";

void setup() {
  //기본 통신속도는 9600입니다.
  Wire.begin();
  Serial.begin(9600);
  HM10.begin(9600);
  accelgyro.initialize();
}

void loop() {
  char *temp;
  char *zyro;
  char *heart;
  char *smoke;
  temp = (malloc)(sizeof(char)*20);
  zyro = (malloc)(sizeof(char)*20);
  heart = (malloc)(sizeof(char)*20);
  smoke = (malloc)(sizeof(char)*20);
  //심장박동
  static double oldValue = 0;  //이전 값 저장 변수
  int rawValue = analogRead(sensorPin);  //센서에 아날로그값을 읽어와서 rawValue에 저장
  double value = alpha * oldValue + (1 + alpha) * rawValue;
  //Serial.print(rawValue);                //시리얼 모니터로 rawValue의 값을 출력
  //Serial.print(",");
  Serial.println(value);                 //시리얼 모니터로 연산한 값인 value의 값을 출력
  oldValue = value;
  delay(1000);  

  //심장박동 보내는 코드
  sprintf(heart,"h=%d",(int)value);
  Serial.print(heart);
  HM10.print(heart);


  //연기센서
  float sensor_volt; 
  float RS_air; //맑은 공기를 통해 RS의 값을 얻는다.
  float R0;    //LPG를 통해 R0 값 가져 오기
  float sensorValue; 

  /*--- Get a average data by testing 100 times ---*/ //->100번 테스트 하여 평균 테이터를 얻습니다. 
    for(int x = 0 ; x < 100 ; x++)
    //[int 변수 값은 x이며 x의 값은 0이다. 100은 x보다 크다]
  {
    sensorValue = sensorValue + analogRead(A1); 
    //[analogRead-> 0에서 5V 사이의 입력 전압을 0에서 1024 사이의 값으로 매핑하는 것을 뜻한다.]
  }
  sensorValue = sensorValue/100.0;
  //[sensorValue 센서벨류 100회 반복]
  /*-----------------------------------------------*/
  sensor_volt = sensorValue/1024*5.0;
  RS_air = (5.0-sensor_volt)/sensor_volt; // omit *RL
  R0 = RS_air/9.9; // The ratio of RS/R0 is 9.9 in LPG gas

  Serial.print("sensor_volt = ");
  Serial.print(sensor_volt);
  Serial.println("V");
  
  Serial.print("R0 = ");
  Serial.println(R0);
  delay(1000);
  //연기센서 끝
  sprintf(smoke,"s=%d",(int)R0);
  HM10.print(smoke);
  
  //자이로
  // 가속도 및 자이로 x,y,z 축에 대한 데이터 받아오기
  // 가속도 값은 -16800 ~ 16800 범위를 -90 ~ 90으로 바꿔줌
  accelgyro.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);
  accel_reading = ay;
  accel_corrected = accel_reading - accel_offset;
  accel_corrected = map(accel_corrected, -16800, 16800, -90, 90);
  accel_corrected = constrain(accel_corrected, -90, 90);
  accel_angle = (float)(accel_corrected * accel_scale);

  
  // 가속도만을 이용한 각도 출력
  Serial.print("Acc angle : ");
  Serial.print(accel_angle);
  Serial.print("\t");
 
  deg = atan2(ax, az) * 180 / PI;     // rad to deg
  
  // 자이로+가속도 조합한 각도
  dgy_x = gy / gyro_offset;
  angle = (0.95 * (angle + (dgy_x * 0.001))) + (0.05 * deg);
 
  // 자이로 가속도 둘 다 이용한 각도 출력
  Serial.print("Filter angle : ");
  Serial.println(angle) ;
  delay(1000);
  //자이로
  sprintf(zyro,"z=%d",(int)angle);
  HM10.print(zyro);
  //온도센서
  int reading = analogRead(outputpin);  // 센서로 부터 자료값을 받는다.
  float voltage = reading * 5.0 / 1024.0;
  float celsiustemp = (voltage - 0.5) * 100 ; 
  // 입력받은 자료값을 수정하여 필요한 자료값으로 바꾼다.

  
  // 수정하여 나온 자료값을 출력한다.(섭씨, 화씨 둘다 출력)
  delay(1000);
  //온도센서  
  //Serial.println(celsiustemp);
  sprintf(temp,"t=%d.%02d",(int)celsiustemp,(int)(celsiustemp*100)%100*(-1));
  //Serial.print(temp);
  //Serial.println(" Celsius");
  HM10.print(temp);
  
}


