

ALTER TABLE employee
ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE Employee
ALTER COLUMN PASSWORD TYPE VARCHAR(150);

UPDATE employee
SET password = '$2a$04$lC7MiedatQe3B48H3ycs3uKiPbTpHttqFzt6T8ok5UMa13bLO6qSu'
WHERE password = 'alio';


ALTER TABLE employee RENAME TO "user";