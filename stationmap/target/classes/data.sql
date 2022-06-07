INSERT INTO company(id, parent_company_id,name) VALUES(1,'tcs123', 'tcs');
INSERT INTO company(id, parent_company_id,name) VALUES(2,'cogni123', 'cognizant');
INSERT INTO station(id, name,latitude,longitude,company_id,criteria) VALUES(1,'cognoCharge', 1000,30000,'cogni123','HIGHVOLUMEVOLTAGE');
INSERT INTO station(id, name,latitude,longitude,company_id,criteria) VALUES(4,'cognoCharging', 29.9697, -98.80322,'cogni123','FASTCHARGING');
INSERT INTO station(id, name,latitude,longitude,company_id,criteria) VALUES(5,'cognoCharging', 29.0000, -98.53506,'cogni123','HIGHVOLUMEVOLTAGE');
INSERT INTO station(id, name,latitude,longitude,company_id,criteria) VALUES(2,'titan', 32.9697, -96.80322,'tcs123','HIGHVOLUMEVOLTAGE');
INSERT INTO station(id, name,latitude,longitude,company_id,criteria) VALUES(3,'tata', 29.46786, -98.53506,'tcs123','FASTCHARGING');