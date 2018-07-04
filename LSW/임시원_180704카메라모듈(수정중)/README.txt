20180704_LSW

*사용방법?
~http로 접근하여, cctv제어하도록 구현(시작명령어 : python3 start.py)~
1.
@app.route('/rec') -> 동영상 녹화 시작(cctv시작)
사용 예)192.168.0.132:5000/rec 
2.
@app.route('/stop') -> 동영상 녹화 종료후, 플라스크 종료(h264->mp4로 변환후, 윈도우 공유폴더로 업로드)
사용 예)192.168.0.132:5000/stop

*필요 패키지(까먹은거있으면 추가하겠음..)(samba,smbclient,cifs-utils,MP4Box)
sudo apt-get install samba samba-common(라즈베리 - 윈도우 네트워크 연동을 위한 패키지)
sudo apt-get install smbclient(윈도우의 공유폴더 사용을 위한 패키지)
sudo apt-get install cifs-utils(윈도우의 공유 폴더를 마운트하기 위해)
sudo apt-get install gpac(h264->mp4변환.명령어ex)MP4Box -add a.h264 a.mp4)

*작동원리(정리해서 추가하겠음)

------------------------------------------------------------------------------------------------------