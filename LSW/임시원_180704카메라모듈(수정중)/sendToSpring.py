import threading,time
from urllib.request import urlopen

class SendValueToServer:
    g=0                                                     #자이로 값 받아오기
    p=0                                                     #심박수 값 받아오기
    s=0                                                     #연기 값 받아오기
    t=0                                                     #온도 값 받아오기
    def __init__(self):
        print("sendToString.py : ready...")

    def sendValuesUsingThread(self):                       #온도정보를 실시간으로 전송
        t1 = threading.Thread(target=self.sv)
        t1.daemon = True
        t1.start()

    def sv(self):                                       #5초에 한번 실시간으로 스프링에 온도값 전송
        try:
            while True:
                time.sleep(5)
                # 각 센서를 배열로 저장
                sensors = ["g", "p", "s", "t"]
                values = ["0","0","0","0"]
                values[0] = str(self.g)
                values[1] = str(self.p)
                values[2] = str(self.s)
                values[3] = str(self.t)

                for i in range(len(sensors)):
                    url = "http://192.168.0.133/spring0615_mvc/sensor?sensorName=" + sensors[i] + "&status="+values[i]
                    print(sensors[i]+" : "+values[i])
                    with urlopen(url) as f:
                        doc = f.read().decode()
                        print(doc)
                #값 바뀌는지 확인...
                #self.chageValues("g",11)
                #print("값 바뀜...")
        except:
            print("exception....")

    def chageValues(self,sensor,value):
        if sensor=="g":
            self.g=value
        elif sensor == "p":
            self.p=value
        elif sensor == "s":
            self.s = value
        elif sensor == "t":
            self.t = value

if __name__ == "__main__" :
    print("start send value test....")

    test = SendValueToServer()