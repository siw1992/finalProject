#!/usr/bin/env python
from flask import Flask, request, render_template, Response
from camera import Camera
from multiprocessing import Process
import time

app = Flask(__name__)
cam = Camera()
@app.route('/rec')
def index():
    return render_template('index.html')

@app.route('/stop')
def stop():
    cam.stopRec()#녹화 종료
    shutdown_server()#플라스크 종료
    print("camera recording end...")
    return '* FLASK SERVER CLOSED *'

@app.route('/video_feed')
def video_feed():
    return Response(gen(cam), mimetype='multipart/x-mixed-replace; boundary=frame')

def gen(camera):
    while True:
        frame = cam.get_frame()
        yield (b'--frame\r\n'
               b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n')
    print("preview start...")

def shutdown_server():
    func = request.environ.get('werkzeug.server.shutdown')
    if func is None:
        raise RuntimeError('Not running with the Werkzeug Server')
    func()

if __name__ == '__main__':
    server = Process(target=app.run(host='0.0.0.0', debug=False, threaded=True))
    server.start()
    server.terminate()
    #join은 지워도 될듯
    server.join()

