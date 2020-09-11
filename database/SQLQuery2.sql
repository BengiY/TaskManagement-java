CREATE DATABASE JavaDB
USE JavaDB

CREATE TABLE TeamMember
(
MemberCode int primary key identity(0,1),
MemberName varchar(20),
MemberMail varchar(20)
);
CREATE TABLE Tasks
(
TaskCode int primary key identity(100,1),
TaskTitle varchar(20),
TaskDescription varchar(20),
MemberCode int FOREIGN KEY REFERENCES TeamMember(MemberCode),
TaskEstimatedTime int,
StartDate date,
EndDate date,
rating bigint
);