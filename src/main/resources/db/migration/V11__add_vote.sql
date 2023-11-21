create table Vote (
ID UUID not null primary key,
ISSUE_ID UUID,
EMPLOYEE_ID UUID,
constraint fk_ISSUE_ID
    foreign key(ISSUE_ID)
        references Issue(ID) on delete cascade,
constraint fk_EMPLOYEE_ID
    foreign key (EMPLOYEE_ID)
        references Employee(ID)
);