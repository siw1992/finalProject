import threading, time
from urllib.request import urlopen
from bluepy import btle
import struct

class MyDelegate(btle.DefaultDelegate):
    g = ""  # 자이로 값 받아오기
    p = ""  # 심박수 값 받아오기
    s = ""  # 연기 값 받아오기
    t = ""  # 온도 값 받아오기
    cnt=0
    stopValue=0

    def __init__(self, params):
        print("init...")
        self.sendValuesUsingThread()
        btle.DefaultDelegate.__init__(self)

    def handleNotification(self, cHandle, data):
        print("handling notification...")
        print(data)
        convData = data.decode("utf-8")
        #print(type(convData))
        if convData[0] == 'p':
            print("p : " + convData[2:])
            self.p = convData[2:]
        elif convData[0] == 's':
            print("s : " + convData[2:])
            self.s = convData[2:]
        elif convData[0] == 'g':
            print("g : " + convData[2:])
            self.g = convData[2:]
        elif convData[0] == 't':
            print("t : " + convData[2:])
            self.t = convData[2:]
        else:
            print("data error")
        print("값들 : " + str(self.cnt)+"/"+str(self.g) + "/" + str(self.p) + "/" + str(self.s) + "/" + str(self.t))

        #공백값이 없을때만 스프링에 전송
        if (str(self.g) != "" and str(self.p) != "" and str(self.s) != "" and str(self.t) != ""):
            if self.cnt > 3:
                self.sv()
                self.cnt=0
        self.cnt+=1

    def sendValuesUsingThread(self):  # 센서정보를 실시간으로 전송
        self.t2 = threading.Thread(target=self.main)
        self.t2.start()

    def sv(self):  # 10초에 한번씩 실시간으로 스프링에 센서값 전송
        print("sv start...")
        time.sleep(10)
        sensors = ["g", "p", "s", "t"]
        values = ["n", "n", "n", "n"]
        try:
            values[0] = self.g
            values[1] = self.p
            values[2] = self.s
            values[3] = self.t
            if (self.stopValue != str(1)) :
                for i in range(len(sensors)):
                    if values[i] != "":
                        url = "http://192.168.0.133/spring0615_mvc/sensor?sensorName="+sensors[i]+"&status="+values[i]
                        print("send val : " + sensors[i] + " / " + values[i])
                        urlopen(url)
                    else:
                        print("empty val...")

                if(self.g != values[0]): print("val changed...")
                else: print("val is not changed...")
                values[0] = self.g
                values[1] = self.p
                values[2] = self.s
                values[3] = self.t
        except Exception as e:
            print("exception....")
            print(e)

    def stopThread(self):
        print("stop....")
        self.stopValue=str(1)

    def main(self):
        print("send-thread start...")
        print("start send value test....")
        # test = MyDelegate(btle.DefaultDelegate)
        p = btle.Peripheral('3C:A3:08:9F:D4:25')
        p.setDelegate(MyDelegate(0))
        while (self.stopValue != str(1)):
            if p.waitForNotifications(1.0):
                continue
        print("while문 빠져나감...스레드 종료...")
        #self.t2.join()#스레드 종료

if __name__ == "__main__":
    print("start send value test....")
    #test = MyDelegate(btle.DefaultDelegate)
    p = btle.Peripheral('3C:A3:08:9F:D4:25')
    p.setDelegate(MyDelegate(0))
    while True:
        if p.waitForNotifications(1.0):
            print("waitForNotifications")
            continue
    print("while문 빠져나감...")