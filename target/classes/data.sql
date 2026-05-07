-- Seed data for PostgreSQL
-- Inserts sectors and employees for the EmployeeManagement application

INSERT INTO sector (id, name) VALUES
  (1, 'Finance'),
  (2, 'Engineering'),
  (3, 'Human Resources');

INSERT INTO employee (id, emp_id, name, email, domain, grade, sector_id) VALUES
  (1, 'EMP001', 'Alice Johnson', 'alice.johnson@example.com', 'Payroll', 'G5', 1),
  (2, 'EMP002', 'Brian Lee', 'brian.lee@example.com', 'Development', 'G6', 2),
  (3, 'EMP003', 'Cynthia Patel', 'cynthia.patel@example.com', 'Talent', 'G4', 3),
  (4, 'EMP004', 'David Smith', 'david.smith@example.com', 'Infrastructure', 'G6', 2);
