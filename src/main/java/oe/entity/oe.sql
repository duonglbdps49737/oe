USE master
GO
CREATE DATABASE OE
GO
USE OE
GO
CREATE TABLE Users(
	Email nvarchar(10) NOT NULL PRIMARY KEY,
	Password nvarchar(50) NOT NULL,
	Fullname nvarchar(50) NOT NULL,
	Admin bit NOT NULL,
	Enabled bit NOT NULL
)
GO
CREATE TABLE Videos(
	Id nvarchar(10) NOT NULL PRIMARY KEY,
	Title nvarchar(50) NOT NULL,
	Poster nvarchar(50) NOT NULL,
	Description nvarchar(50) NOT NULL,
	Views int NOT NULL,
	Active bit NOT NULL
)
GO
CREATE TABLE Favorites(
	Id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	VideoId nvarchar(10) NOT NULL REFERENCES Videos(Id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	Email nvarchar(10) NOT NULL REFERENCES Users(Email)
		ON DELETE CASCADE ON UPDATE CASCADE,
	LikeDate date NOT NULL
) 
GO
CREATE TABLE Shares(
	Id int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	VideoId nvarchar(10) NOT NULL REFERENCES Videos(Id)
		ON DELETE CASCADE ON UPDATE CASCADE,
	Email nvarchar(10) NOT NULL REFERENCES Users(Email)
		ON DELETE CASCADE ON UPDATE CASCADE,
	Recipients nchar(10) NOT NULL,
	ShareDate date NOT NULL
)
GO