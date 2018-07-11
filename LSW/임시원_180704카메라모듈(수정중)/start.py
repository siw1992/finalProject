#!/usr/bin/env python
from flask import Flask, request, render_template, Response
from camera import Camera, threading
from multiprocessing import Process
from sendToSpring2 import MyDelegate

app = Flask(__name__)
cam = Camera()

@app.route('/rec')                             #IP:5000/rec으로 접근 시, 녹화 시작
def index():
    return render_template('index.html')      #비디오 스트리밍 html(/templates/안의 index.html)

@app.route('/stop')                            #IP:5000/stop으로 접근 시, 녹화종료 후 플라스크 종료
def stop():
    th.stopThread() # 센서값 보내기 종료...
    cam.stopRec()   # 녹화 종료
    shutdown_server()   #플라스크 종료
    print("camera recording end...")
    return '* FLASK SERVER CLOSED *'    #html에 플라스크서버 종료 메시지 띄우기

@app.route('/video_feed')
def video_feed():
    #Request후에 받는 Response정보(정확히는 Response Headers에 들어가는 정보인듯. 크롬에서 F12로 확인가능?)
    return Response(gen(cam), mimetype='multipart/x-mixed-replace; boundary=frame')

def gen(camera):                                #비디오 스트리밍을 생성한다
    while True:
        frame = cam.get_frame()
        #바이너리로 처리한다는 설정값인듯
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
    print("preview start...")

def shutdown_server():                          #플라스크 서버를 종료한다
    func = request.environ.get('werkzeug.server.shutdown')  #종료 명령
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

if __name__ == '__main__':
    print("send-thread start...")
    th = MyDelegate(0)             # thread-1 : 블루투스로 센서값 받아옴->스프링으로 값보내기 시작
    print("cam-thread start...")  # thread-2 : 플라스크 시작
    server = Process(target=app.run(host='0.0.0.0', debug=False, threaded=True))  # 멀티프로세스 사용(플라스크 서버 종료를 구현하기 위해 사용)
    server.start()
    server.terminate()
    #server.join()
