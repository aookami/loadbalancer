CREATE TABLE data_table (
	id bigint auto_increment primary key,
	name varchar(255),
	data blob,
	file_type varchar(255)
);


insert into data_table (name, data, file_type) values ('1rst data', null, 'string');
insert into data_table (name, data, file_type) values ('2nd data', null , 'string');



