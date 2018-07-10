--drop table fireStation;
--drop table fireFighter;
--drop table monitors;
--drop table event;
--drop table video;
--drop table heartRate;
--drop table temperature;
--drop table gyro;
--drop table smoke;
--drop table emergency;
--drop table healthinfo;
--create sequence video_seq
--start with 1
--INCREMENT by 1
--maxvalue 9999;
--
--create sequence heartRate_seq
--start with 1
--INCREMENT by 1
--maxvalue 9999;
--
--create sequence temperature_seq
--start with 1
--INCREMENT by 1
--maxvalue 9999;
--
--create sequence gyro_seq
--start with 1
--INCREMENT by 1
--maxvalue 9999;
--
--create sequence smoke_seq
--start with 1
--INCREMENT by 1
--maxvalue 9999;

create table fireStation(
  fireStationCode number not null primary key
);

create table fireFighter(
    fireFighterCode number not null primary key,
    fireStationCode number not null, 
    IP varchar2(20) not null,
    fireFighterName varchar2(20) not null,
    constraints FS_FK FOREIGN KEY(fireStationCode) REFERENCES fireStation(fireStationCode) on delete cascade
);

create table monitors(
    fireStationCode number not null,
    monitorCode number not null,
    monitorId varchar2(20) not null,
    monitorPwd varchar2(20) not null,
    constraints FS_FK2 FOREIGN KEY(fireStationCode) REFERENCES fireStation(fireStationCode) on delete cascade
);
--insert into fireStation(fireStationCode) values(1);
--insert into monitors values(1,1,'lkm','1234');
--insert into fireFighter values(1,1,'192.168.0.100','¿Ã∞ÊπŒ');

create table event(
    eventCode number not null primary key,
    eventYear varchar2(10) not null,
    eventMonth varchar2(10) not null,
    eventDay varchar2(10) not null,
    eventHour varchar2(10) not null,
    eventMinute varchar2(10) not null,
    eventSecond varchar2(10) not null,
    eventLocation varchar2(50) not null,
    GPS varchar2(50) not null
);

create table video(
    videoCode varchar2(20) not null primary key,
    fireFighterCode number not null,
    eventCode number not null,
    seq NUMBER not null,
    constraints FF_FK FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade,
    constraints EV_FK FOREIGN KEY(eventCode) REFERENCES event(eventCode) on delete cascade
);

create table temperature(
    temperatureCode varchar(20) not null primary key,
    fireFighterCode number not null,
    eventCode number not null,
    seq NUMBER not null,
    constraints FF_FK2 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade,
    constraints EV_FK2 FOREIGN KEY(eventCode) REFERENCES event(eventCode) on delete cascade
);

create table heartRate(
    heartRateCode varchar(20) not null primary key,
    fireFighterCode number not null,
    eventCode number not null,
    seq NUMBER not null,
    constraints FF_FK3 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade,
    constraints EV_FK3 FOREIGN KEY(eventCode) REFERENCES event(eventCode) on delete cascade
);

create table gyro(
    gyroCode varchar(20) not null primary key,
    fireFighterCode number not null,
    eventCode number not null,
    seq NUMBER not null,
    constraints FF_FK4 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade,
    constraints EV_FK4 FOREIGN KEY(eventCode) REFERENCES event(eventCode) on delete cascade
);

create table smoke(
    smokeCode varchar(20) not null primary key,
    fireFighterCode number not null,
    eventCode number not null,
    seq NUMBER not null,
    constraints FF_FK5 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade,
    constraints EV_FK5 FOREIGN KEY(eventCode) REFERENCES event(eventCode) on delete cascade
);

create table emergency(
    emergencyCode varchar2(40) not null primary key,
    fireFighterCode number not null,
    emcHeartRate varchar2(20) not null,
    emcTemperature varchar2(20) not null,
    emcSmoke varchar2(20) not null,
    emcGyro varchar2(20) not null,
    emcYear varchar2(10) not null,
    emcMonth varchar2(10) not null,
    emcDay varchar2(10) not null,
    emcHour varchar2(10) not null,
    emcMinute varchar2(10) not null,
    eventSecond varchar2(10) not null,
    emcLocation varchar2(50) not null,
    emcGPS varchar2(50) not null,
    constraints FF_FK6 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade
);

create table healthInfo(
    FFHealthInfo number not null primary key,
    fireFighterCode number not null,
    averageHeartRate varchar2(40) not null,
    emergencyRate varchar2(40) not null,
    healthCheck varchar2(40) not null,
    constraints FF_FK7 FOREIGN KEY(fireFighterCode) REFERENCES fireFighter(fireFighterCode) on delete cascade
);

commit;