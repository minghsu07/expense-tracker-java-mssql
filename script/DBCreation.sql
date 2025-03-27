--EXPENSETRACKER

DECLARE @DbName NVARCHAR(50)='ExpenseTracker';



--Create database
DECLARE @Sql NVarchar(MAX);
if DB_ID(@DbName) is null
BEGIN
	set @Sql='create database '+QUOTENAME(@DbName)+';';
	EXEC sp_executesql @Sql;
END
GO

--TABLE CREATION
USE [ExpenseTracker]
GO


DECLARE @LoginName NVarchar(50)='ExpenseTrackerApp';
DECLARE @Password NVarchar(50)='1qaz@WSX';
DECLARE @Sql NVarchar(MAX);

--Create a User for the application access


--create the login
set @Sql='CREATE LOGIN '+QUOTENAME(@LoginName)+' with password= '''+@Password+''';';
exec sp_executesql @Sql;

-- Create the user for the database
set @Sql='CREATE USER '+QUOTENAME(@LoginName)+' FOR LOGIN '+QUOTENAME(@LoginName)+';';
exec sp_executesql @Sql;

-- Grant db_owner role
set @Sql='ALTER ROLE db_owner ADD MEMBER '+QUOTENAME(@LoginName)+';';
exec sp_executesql @Sql;


drop table if exists EX_EXPENSE;
drop table if exists EX_CATEGORY;
drop table if exists EX_USER;

CREATE TABLE EX_USER
        (
                USER_ID      INT IDENTITY(1,1) PRIMARY KEY,
                USERNAME     VARCHAR(20) UNIQUE           ,
                PASSWORD     VARCHAR(40)                  ,
                CREATED_DATE DATE                         ,
                ISLOGIN      BIT DEFAULT 0 NOT NULL
        )
;

CREATE TABLE EX_CATEGORY
        (
                CATEGORY_ID   INT IDENTITY(1,1) PRIMARY KEY,
                CATEGORY_NAME VARCHAR(25) NOT NULL         ,
                USER_ID       INT NOT NULL                 ,
                CONSTRAINT FK_CATEGORY_USER FOREIGN KEY (USER_ID) REFERENCES EX_USER(USER_ID)
        )
;

CREATE TABLE EX_EXPENSE
        (
                EXPENSE_ID   INT IDENTITY(1,1) PRIMARY KEY                                  ,
                USER_ID      INT NOT NULL                                                   ,
                ITEM         NVARCHAR(50) NOT NULL                                          , --Using NVarchar, which supports Unicode
                CATEGORY_ID  INT NOT NULL                                                   ,
                AMOUNT       DECIMAL(10,2) NOT NULL                                         ,
                CREATED_DATE DATE NOT NULL                                                  ,
                DESCRIPTION  NVARCHAR(MAX)                                                  ,
                CONSTRAINT FK_EXPENSE_USER FOREIGN KEY (USER_ID) REFERENCES EX_USER(USER_ID),
                CONSTRAINT FK_EXPENSE_CATEGORY FOREIGN KEY (CATEGORY_ID) REFERENCES EX_CATEGORY(CATEGORY_ID)
        )
;