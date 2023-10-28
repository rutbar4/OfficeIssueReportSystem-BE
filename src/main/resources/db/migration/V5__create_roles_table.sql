CREATE TABLE roles (
    role VARCHAR(100),
    user_id UUID,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

INSERT INTO roles (role, user_id)
VALUES('ADMIN', 'd06cb831-9427-41ee-adcc-271f7b02d626'),
        ('USER', 'd06cb831-9427-41ee-adcc-271f7b02d627'),
        ('USER', 'd06cb831-9427-41ee-adcc-271f7b02d628');