# spring_security_Module_test

# users
1
username admin
password admin

2
username user
password user

3
username user1
password user1

# database

insert into public.user_status (id, name, has_grant_to_sing_in) values(0, 'UNACTIVE', false);

insert into public.user_status (id, name, has_grant_to_sing_in) values(1, 'ACTIVE', true);

insert into public.user_status (id, name, has_grant_to_sing_in) values(2, 'SUSPICIOUS', false);

insert into public.user_status (id, name, has_grant_to_sing_in) values(3, 'BLOCKED', false);

insert into public.user_status (id, name, has_grant_to_sing_in) values(4, 'BANNED', false);

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


