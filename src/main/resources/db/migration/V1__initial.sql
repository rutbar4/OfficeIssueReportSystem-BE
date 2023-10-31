create table Country(
                        ID UUID not null primary key,
                        COUNTRY_NAME varchar (50) not null
);

create table Office (
                        ID UUID not null primary key,
                        OFFICE_NAME varchar (50) not null,
                        COUNTRY_ID UUID,
                        constraint fk_COUNTRY_ID
                            foreign key(COUNTRY_ID)
                                references Country(ID)
);

create table Employee(
                         ID UUID not null primary key,
                         FULL_NAME varchar (150) not null,
                         EMAIL varchar (100) not null,
                         PASSWORD varchar (255) not null,
                         PHONE_NUMBER varchar (50) not null,
                         AVATAR varchar (250)
);

create table Address(
                        ID UUID not null primary key,
                        STREET varchar (150),
                        POST_CODE varchar (10) not null,
                        State_Province varchar (150),
                        CITY varchar (50) not null,
                        COUNTRY_ID UUID,
                        EMPLOYEE_ID UUID,
                        constraint fk_COUNTRY_ID
                            foreign key (COUNTRY_ID)
                                references Country(ID),
                        constraint fk_EMPLOYEE_ID
                            foreign key (EMPLOYEE_ID)
                                references Employee(ID)
);

create table Issue (
                       ID UUID not null primary key,
                       ISSUE_NAME varchar (150) not null unique,
                       ISSUE_STATUS varchar (15) not null,
                       START_TIME timestamp not null ,
                       FINISH_TIME timestamp ,
                       RATING int default 0,
                       DESCRIPTION varchar(254) not null,
                       EMPLOYEE_ID UUID,
                       OFFICE_ID UUID,
                       constraint fk_EMPLOYEE_ID
                           foreign key (EMPLOYEE_ID)
                               references Employee(ID),
                       constraint fk_OFFICE_ID
                           foreign key (OFFICE_ID)
                               references Office(ID)
);

create table Comment (
                         ID UUID not null primary key,
                         TEXT varchar(254) not null,
                         TIME timestamp not null,
                         LIKES int default 0,
                         ISSUE_ID UUID,
                         EMPLOYEE_ID UUID,
                         constraint fk_ISSUE_ID
                             foreign key(ISSUE_ID)
                                 references Issue(ID) on delete cascade,
                         constraint fk_EMPLOYEE_ID
                             foreign key (EMPLOYEE_ID)
                                 references Employee(ID)
);

create table Picture (
                         ID UUID not null primary key,
                         LINK varchar(254) not null,
                         ISSUE_ID UUID,
                         EMPLOYEE_ID UUID,
                         constraint fk_ISSUE_ID
                             foreign key(ISSUE_ID)
                                 references Issue(ID) on delete cascade,
                         constraint fk_EMPLOYEE_ID
                             foreign key (EMPLOYEE_ID)
                                 references Employee(ID)
);