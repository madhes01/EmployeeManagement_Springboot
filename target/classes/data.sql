-- =========================
-- INSERT INTO SECTOR TABLE
-- =========================
INSERT INTO sector (name) VALUES
('IT'), ('HR'), ('Finance'), ('Marketing'), ('Operations'),
('Sales'), ('Cyber Security'), ('Cloud Computing'), ('AI & ML'),
('Data Engineering'), ('DevOps'), ('Quality Assurance'),
('Business Analysis'), ('Product Management'), ('Customer Support'),
('Legal'), ('Administration'), ('Research'), ('Procurement'), ('Training')
ON CONFLICT (name) DO NOTHING;

-- =========================
-- INSERT INTO EMPLOYEE TABLE
-- =========================
INSERT INTO employee (emp_id, name, email, domain, grade, sector_id) VALUES
('EMP001','Arun Kumar','arun1@example.com','Backend','A1',1),
('EMP002','Priya Sharma','priya2@example.com','Frontend','A2',1),
('EMP003','Rahul Verma','rahul3@example.com','Recruitment','B1',2),
('EMP004','Sneha Iyer','sneha4@example.com','Payroll','B2',3),
('EMP005','Vikram Singh','vikram5@example.com','Digital Marketing','A3',4),
('EMP006','Karthik Raj','karthik6@example.com','Logistics','C1',5),
('EMP007','Meera Nair','meera7@example.com','Sales Executive','B1',6),
('EMP008','Ajay Patel','ajay8@example.com','Network Security','A2',7),
('EMP009','Divya Menon','divya9@example.com','AWS','A1',8),
('EMP010','Rohit Das','rohit10@example.com','Machine Learning','A3',9),
('EMP011','Nisha Rao','nisha11@example.com','ETL','B2',10),
('EMP012','Sanjay Kumar','sanjay12@example.com','CI/CD','A2',11),
('EMP013','Pooja Shah','pooja13@example.com','Automation Testing','B1',12),
('EMP014','Amit Joshi','amit14@example.com','Requirement Analysis','A1',13),
('EMP015','Lavanya R','lavanya15@example.com','Roadmap Planning','A3',14),
('EMP016','Harish K','harish16@example.com','Client Support','C1',15),
('EMP017','Keerthi S','keerthi17@example.com','Compliance','B2',16),
('EMP018','Manoj T','manoj18@example.com','Office Admin','C2',17),
('EMP019','Anjali P','anjali19@example.com','Innovation','A2',18),
('EMP020','Dinesh V','dinesh20@example.com','Vendor Management','B1',19),
('EMP021','Suresh Babu','suresh21@example.com','Employee Training','A1',20),
('EMP022','Neha Kapoor','neha22@example.com','Spring Boot','A2',1),
('EMP023','Varun G','varun23@example.com','HR Operations','B1',2),
('EMP024','Ritika Jain','ritika24@example.com','Accounting','B2',3),
('EMP025','Abhishek M','abhishek25@example.com','SEO','A3',4),
('EMP026','Sathish R','sathish26@example.com','Operations Management','C1',5),
('EMP027','Monica D','monica27@example.com','Retail Sales','B2',6),
('EMP028','Yogesh P','yogesh28@example.com','Ethical Hacking','A1',7),
('EMP029','Deepika L','deepika29@example.com','Azure','A2',8),
('EMP030','Kiran N','kiran30@example.com','Deep Learning','A3',9),
('EMP031','Bhavana S','bhavana31@example.com','Big Data','B1',10),
('EMP032','Gokul R','gokul32@example.com','Docker','A2',11),
('EMP033','Swetha M','swetha33@example.com','Manual Testing','B2',12),
('EMP034','Prakash J','prakash34@example.com','Business Strategy','A1',13),
('EMP035','Ishwarya K','ishwarya35@example.com','Product Design','A3',14),
('EMP036','Naveen C','naveen36@example.com','Technical Support','C1',15),
('EMP037','Fathima A','fathima37@example.com','Legal Advisor','B1',16),
('EMP038','Rajeshwari P','rajeshwari38@example.com','Administration','C2',17),
('EMP039','Tarun E','tarun39@example.com','Research Analyst','A2',18),
('EMP040','Kavitha H','kavitha40@example.com','Supply Chain','B2',19),
('EMP041','Surya V','surya41@example.com','Corporate Training','A1',20),
('EMP042','Ashwin T','ashwin42@example.com','Java','A2',1),
('EMP043','Rekha N','rekha43@example.com','Talent Acquisition','B1',2),
('EMP044','Nitin S','nitin44@example.com','Auditing','B2',3),
('EMP045','Shalini R','shalini45@example.com','Content Marketing','A3',4),
('EMP046','Vimal K','vimal46@example.com','Operations Analyst','C1',5),
('EMP047','Anu Priya','anu47@example.com','Field Sales','B2',6),
('EMP048','Ramesh D','ramesh48@example.com','Security Audit','A1',7),
('EMP049','Gayathri M','gayathri49@example.com','Google Cloud','A2',8),
('EMP050','Lokesh P','lokesh50@example.com','Artificial Intelligence','A3',9)
ON CONFLICT (emp_id) DO NOTHING;