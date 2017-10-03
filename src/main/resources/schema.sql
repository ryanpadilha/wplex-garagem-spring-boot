CREATE TABLE IF NOT EXISTS wp_company (
	id bigserial not null primary key,
	created timestamp not null default now(),
	initials text not null,
	name text not null
);

CREATE TABLE IF NOT EXISTS wp_garage (
	id bigserial not null primary key,
	created timestamp not null default now(),
	initials text not null,
	name text not null,
	company_id bigint not null
);

alter table wp_garage add constraint fk_company foreign key (company_id) references wp_company(id);