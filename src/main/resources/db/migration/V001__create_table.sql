
CREATE TABLE if not exists services (

	id SERIAL primary key,
	name varchar(200),
	strategy varchar(200)

);

CREATE TABLE if not exists service_registry (
	id SERIAL primary key,
	service_id  bigint references services(id),
	ip varchar(250),
	port varchar(20),
	version varchar(250),
	protocol varchar(250),
	score BIGINT
);

 insert into services (name, strategy) values ("dummyservice", "weightedrandomized");
 
 insert into service_registry  (service_id, ip, port, version, protocol, score) values
 (1, "localhost", "8110", "1", "http-json", 100);
 