CREATE TABLE IF NOT EXISTS contact(
  sysid_contact INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS contact_name (
  sysid_contact_name INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  middle_name VARCHAR(250),
  sysid_contact INT NOT NULL,
  FOREIGN KEY (sysid_contact) REFERENCES contact(sysid_contact)
);

CREATE TABLE IF NOT EXISTS contact_address (
  sysid_address INT AUTO_INCREMENT PRIMARY KEY,
  street VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL,
  state_province VARCHAR(250) NOT NULL,
  postal_code VARCHAR(250) NOT NULL,
  sysid_contact INT NOT NULL,
  FOREIGN KEY (sysid_contact) REFERENCES contact(sysid_contact)
);


CREATE TABLE IF NOT EXISTS contact_phone (
  sysid_phone INT AUTO_INCREMENT PRIMARY KEY,
  number VARCHAR(250) NOT NULL,
  phone_type VARCHAR(250) NOT NULL,
  sysid_contact INT,
  FOREIGN KEY (sysid_contact) REFERENCES contact(sysid_contact)
);