//Database table Script for run servlet application

CREATE TABLE venderinfo ( 

VendorName tinytext NOT NULL, 

VendorPass varchar(50) NOT NULL, 

VendorRepass varchar(50) NOT NULL 

);

insert into venderinfo values ('admin', 'admin123', 'admin123')