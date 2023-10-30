CREATE TABLE roles (
    role_type VARCHAR(100),
    employee_id UUID,
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

INSERT INTO roles (role_type, employee_id)
VALUES('ADMIN', 'd06cb831-9427-41ee-adcc-271f7b02d626'),
        ('USER', 'd06cb831-9427-41ee-adcc-271f7b02d627'),
        ('USER', 'd06cb831-9427-41ee-adcc-271f7b02d628');
