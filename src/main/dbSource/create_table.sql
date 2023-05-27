--USER_TABLE table creation
CREATE TABLE USER_TABLE(USER_ID number(10) primary key, user_name varchar2(50) not null, user_password varchar2(100), password_hash varchar2(200), salt varchar2(50), status varchar2(100),email varchar2(50), created_on timestamp, modified_on timestamp);

CREATE TABLE USER_ROLES(ROLE_ID number(10) primary key, ROLE_NAME varchar2(50) not null, USER_NAME varchar2(100));

CREATE TABLE ROLE_PERMISSION(PERMISSION_ID number(10) primary key, PERMISSION_NAME varchar2(50) not null, ROLE_NAME varchar2(50))

CREATE TABLE FILE_STORAGE(file_id number(10) primary key, file_name varchar2(100) NOT NULL, file_content BLOB, file_length number(10), file_type varchar2(50), created_on timestamp, modified_on timestamp);
