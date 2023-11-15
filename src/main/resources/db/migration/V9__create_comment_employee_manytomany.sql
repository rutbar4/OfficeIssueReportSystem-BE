CREATE TABLE comment_employee
(
comment_id UUID,
CONSTRAINT fk_comment_id
        FOREIGN KEY (comment_id)
        REFERENCES comment(id) ON DELETE CASCADE,
employee_id UUID,
CONSTRAINT fk_employee_id
        FOREIGN KEY (employee_id)
        REFERENCES employee(id) ON DELETE CASCADE,
issue_id UUID,
CONSTRAINT fk_issue_id
        FOREIGN KEY (issue_id)
        REFERENCES issue(id) ON DELETE CASCADE
);