-- Add column password
ALTER TABLE user ADD COLUMN password VARCHAR(255);

-- Insert role
INSERT INTO role VALUES(1, 'ADMIN');

-- Insert into user_role
INSERT INTO user_role VALUES(1,1);