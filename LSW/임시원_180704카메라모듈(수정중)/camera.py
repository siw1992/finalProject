import time,io,threading,picamera,subprocess

class Camera(object):
    thread = None
    frame = None
    last_access = 0
    now = time.localtime()
    s = "day_%04d_%02d_%02d_time_%02d%02d%02d" % (now.tm_year, now.tm_mon, now.tm_mday, now.tm_hour, now.tm_min, now.tm_sec)

    #소방관1,2의 파일경로를 결정
    print("set IP...")
    t = subprocess.check_output('hostname -I', shell=True)
    IP = t.decode().split(" ")[0]
    path = '/media/windows/'
    cpPath = '/media/windows/'

    if IP == "192.168.0.132":
        #윈도우-라즈베리 마운트 안하고 이 경로에 접근하면 터짐
        path += '1/h264/'
        cpPath += '1/'

    else:
        # 윈도우-라즈베리 마운트 안하고 이 경로에 접근하면 터짐
        path += '2/h264/'
        cpPath += '2/'

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
        self.camera.resolution = (480,320)#720,480 / 480,320
        self.camera.hflip = True
        self.camera.vflip = True

        self.camera.start_preview()                              #미리보기 시작
        self.camera.start_recording(self.path, format='h264')   #블랙박스 녹화 시작
        print("camera recording start...")

        time.sleep(2)
        stream = io.BytesIO()                                   #바이너리스트림 오픈
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
            #라즈베리와 연동된 윈도우 계정 로그인(윈도우 공유폴더에 접근하기 위함)
            #라즈베리 부팅시 윈도우-라즈베리 자동 마운트됨
            #subprocess.call('sudo mount -t cifs -o user=kosta,password=kosta,file_mode=0777,dir_mode=0777 //192.168.0.133/mp4 /media/windows',shell=True)
            time.sleep(5)
            print("업로드 시작...")
            #h264파일을 mp4로 변환 후, 윈도우 공유폴더 경로에 저장
            subprocess.call('MP4Box -add ' + self.path + ' ' + self.cpPath, shell=True)
            print("영상 변환 성공")
        except:
            print("영상 변환 실패")
        return