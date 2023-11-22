UPDATE employee
SET email = CASE
    WHEN email = 'agne@cognizant.com' THEN 'asdf.cognizant@gmail.com'
END
WHERE email IN ('agne@cognizant.com');