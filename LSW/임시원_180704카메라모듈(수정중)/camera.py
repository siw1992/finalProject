import time,io,threading,picamera,subprocess

class Camera(object):
    thread = None
    frame = None
    last_access = 0
    now = time.localtime()
    s = "day_%04d_%02d_%02d_time_%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)
    path = '/media/pi/h264/'
    cpPath = '/media/windows/'
    str = s + '.h264'
    cpStr = s + '.mp4'
    path += str
    cpPath += cpStr

    def initialize(self):
        if Camera.thread is None:
            Camera.thread = threading.Thread(target=self._thread)
            Camera.thread.start()
            while self.frame is None:
                time.sleep(0)

    def get_frame(self):
        Camera.last_access = time.time()
        self.initialize()
        return self.frame

    def _thread(self):
        self.camera = picamera.PiCamera()
        self.camera.resolution = (480,318)#720,480
        self.camera.hflip = True
        self.camera.vflip = True

        self.camera.start_preview()
        self.camera.start_recording(self.path, format='h264')
        print("camera recording start...")

        time.sleep(2)
        stream = io.BytesIO()
        for foo in self.camera.capture_continuous(stream, 'jpeg',use_video_port=True):
            stream.seek(0)
            self.frame = stream.read()
            stream.seek(0)
            stream.truncate()
            if time.time() - self.last_access > 10:
                break
        self.thread = None

    #rec종료
    def stopRec(self):
        self.camera.stop_recording()
        print("camera recording end...")
        try:
            time.sleep(1)
            test = 'MP4Box -add ' + self.path + ' ' + self.cpPath
            print(test)
            # 파이썬코드에..비밀번호를 쓰는건 좀...다른방법도 찾아보자ㅠㅠ
            subprocess.call(
                'sudo mount -t cifs -o user=kosta,password=kosta,file_mode=0777,dir_mode=0777 //192.168.0.133/mp4 /media/windows',
                shell=True)
            time.sleep(5)
            print("업로드 시작...")
            subprocess.call('MP4Box -add ' + self.path + ' ' + self.cpPath, shell=True)
            print("영상 변환 성공")
        except:
            print("영상 변환 실패")
        return