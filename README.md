# spring_security_Module_test

# database


insert into public.user_status (id, name) values(0, 'UNACTIVE');
insert into public.user_status (id, name) values(1, 'ACTIVE');
insert into public.user_status (id, name) values(2, 'SUSPICIOUS');
insert into public.user_status (id, name) values(3, 'BLOCKED');
insert into public.user_status (id, name) values(4, 'BANNED');
insert into public.user_role (id, name) values(0, 'ADMIN');
insert into public.user_role (id, name) values(1, 'USER');
insert into public.user_role (id, name) values(2, 'VIEWER');
insert into public.user_permission (id, name) values (0, 'DEVELOPER_READ');
insert into public.user_permission (id, name) values (1, 'DEVELOPER_WRITE');
insert into public.user_role_permission (role, permission) values (0, 0);
insert into public.user_role_permission (role, permission) values (0, 1);
insert into public.user_role_permission (role, permission) values (1, 0);
insert into public.user_role_permission (role, permission) values (2, 0);
insert into public.user (id, name, password, role, status) values (0, 'admin', '$2a$10$XNhMxH2rBhvlVkg1ET/6nOvKZwpvBik97ZHjgs0QRXCprMCYpFmz6', 0, 1);
insert into public.user (id, name, password, role, status) values (1, 'user', '$2a$10$bQlx.Zkyzw/CMwzz/zltiuMMHlLPLacW.XrEPN075vmgcY8aHc9I2', 1, 1);
insert into public.user (id, name, password, role, status) values (2, 'user1', '$2a$10$NKMKIErnQptvuSAgdrPfheLRocn7t4/gett1yRPVWCg/3njVDvrAa', 2, 1);

/* tables

create table user_status (
id serial primaty key,
name character varying(20) not null unique
);

create table user_role (
id serial promary key,
name character varying(20) not null unique
);


create table user_permision (
id serial primary key,
name character varying(30) not null unique
);


create table user_role_permision (
role int references user_role(id) not null,
permision int references user_permision(id) not null,
constraint primary key(role, permision)
);


create table user (
id serial primary key,
name character varying(50) not null, unique,
password character varying(60) not null,
role int not null references user_role(id),
status int not null references user_status(id)
);

*/
