
ALTER TABLE employee
ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE employee
ADD COLUMN position varchar(255);

UPDATE employee
SET position = 'Team Lead'
WHERE email = 'agne@devbridge.com';

UPDATE employee
SET position = 'Full Stack Developer'
WHERE email = 'sarunas@devbridge.com' OR email = 'lukas@devbridge.com';


