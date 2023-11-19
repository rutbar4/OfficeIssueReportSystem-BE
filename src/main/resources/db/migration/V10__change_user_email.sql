UPDATE employee
SET email = CASE
    WHEN email = 'sarunas@devbridge.com' THEN 'sarunas@cognizant.com'
    WHEN email = 'agne@devbridge.com' THEN 'agne@cognizant.com'
    WHEN email = 'lukas@devbridge.com' THEN 'lukas@cognizant.com'
END
WHERE email IN ('sarunas@devbridge.com', 'agne@devbridge.com', 'lukas@devbridge.com');