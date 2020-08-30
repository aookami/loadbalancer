
CREATE TABLE if not exists service_registry (
	id SERIAL primary key,
	name varchar(250),
	ip varchar(250),
	port varchar(20),
	version varchar(250),
	score BIGINT,
	last_heartbeat timestamp
);

