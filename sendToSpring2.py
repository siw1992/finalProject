import threading, time
from urllib.request import urlopen
from bluepy import btle
import struct

class MyDelegate(btle.DefaultDelegate):
    g = ""  # 자이로 값 받아오기
    p = ""  # 심박수 값 받아오기
    s = ""  # 연기 값 받아오기
    t = ""  # 온도 값 받아오기

    def __init__(self, params):
        print("init!!!!!!!!!")
        self.sendValuesUsingThread()
        btle.DefaultDelegate.__init__(self)

    def handleNotification(self, cHandle, data):
        print("handling notification...")
        print(data)
        convData = data.decode("utf-8")
        print(type(convData))
        if convData[0] == 'p':
            print("pppppppp")
            print(convData[2:])
            self.p = convData[2:]
        elif convData[0] == 's':
            print("ssssss")
            print(convData[2:])
            self.s = convData[2:]
        elif convData[0] == 'g':
            print("gggggg")
            print(convData[2:])
            self.g = convData[2:]
        elif convData[0] == 't':
            print("tttttt")
            print(convData[2:])
            self.t = convData[2:]
        else:
            print("data error")

    def sendValuesUsingThread(self):  # 온도정보를 실시간으로 전송
        t1 = threading.Thread(target=self.sv)
        t1.daemon = True
        t1.start()

    def sv(self):  # 5초에 한번 실시간으로 스프링에 온도값 전송
        sensors = ["g", "p", "s", "t"]
        values = ["n", "n", "n", "n"]
        try:
            values[0] = self.g
            values[1] = self.p
            values[2] = self.s
            values[3] = self.t
            print("before-----------------")
            print("before value0 : " + values[0])
            print("before value3 : " + values[3])
            while True:
                time.sleep(5)
                for i in range(len(sensors)):
                    if values[i] != "n":
                        url = "http://192.168.0.133/spring0615_mvc/sensor?sensorName="+sensors[i]+"&status="+values[i]
                        print(sensors[i] + " : " + values[i])
                        urlopen(url)
                values[0] = self.g
                values[1] = self.p
                values[2] = self.s
                values[3] = self.t
                print("after value0 : " + values[0])
                print("after value3 : " + values[3])
        except Exception as e:
            print("exception....")
            print(e)


if __name__ == "__main__":
    print("start send value test....")
    #test = MyDelegate(btle.DefaultDelegate)
    p = btle.Peripheral('3C:A3:08:9F:D4:25')
    p.setDelegate(MyDelegate(0))
    while True:
        if p.waitForNotifications(1.0):
            continue
        print("waiting...")