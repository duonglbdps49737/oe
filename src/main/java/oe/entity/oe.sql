USE master
GO
CREATE DATABASE OE
GO
USE OE
GO
CREATE TABLE Categories(
	Id NVARCHAR(5) NOT NULL PRIMARY KEY,
	Name nvarchar(50) NOT NULL
)
GO
CREATE TABLE Videos(
	Id nvarchar(20) NOT NULL PRIMARY KEY,
	Title nvarchar(50) NOT NULL,
	Poster nvarchar(50) NOT NULL,
	Description nvarchar(50) NOT NULL,
	Views int NOT NULL,
	Active bit NOT NULL,
	CategoryId NVARCHAR(5)
		REFERENCES Categories(Id) ON DELETE CASCADE ON UPDATE CASCADE
)
GO
CREATE TABLE Users(
	Email nvarchar(50) NOT NULL PRIMARY KEY,
	Password nvarchar(50) NOT NULL,
	Enabled bit NOT NULL,
	Fullname nvarchar(50) NOT NULL,
	Admin bit NOT NULL
)
GO
CREATE TABLE Favorites(
	Id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	VideoId nvarchar(20) NOT NULL 
		REFERENCES Videos(Id) ON DELETE CASCADE ON UPDATE CASCADE,
	Email nvarchar(50) NOT NULL 
		REFERENCES Users(Email) ON DELETE CASCADE ON UPDATE CASCADE,
	LikeDate date NOT NULL
) 
GO
CREATE TABLE Shares(
	Id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	VideoId nvarchar(20) NOT NULL 
		REFERENCES Videos(Id) ON DELETE CASCADE ON UPDATE CASCADE,
	Email nvarchar(50) NOT NULL 
	 REFERENCES Users(Email) ON DELETE CASCADE ON UPDATE CASCADE,
	Recipients nchar(255) NOT NULL,
	ShareDate date NOT NULL
)
GO
INSERT INTO Categories VALUES('TY', N'Tình yêu')
INSERT INTO Categories VALUES('XH', N'Xã hội')
INSERT INTO Categories VALUES('CS', N'Cuộc sống')
INSERT INTO Categories VALUES('AN', N'Âm nhạc')
GO
INSERT INTO Users VALUES(N'user1@gmail.com', 'solo', 1, 'User 1', 0)
INSERT INTO Users VALUES(N'user2@gmail.com', 'solo', 1, 'User 2', 0)
INSERT INTO Users VALUES(N'admin@gmail.com', 'solo', 1, 'Administrator', 1)