
drop table if exists oauth_users;

create table oauth_users (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    username VARCHAR(100) UNIQUE NOT NULL, 
    password VARCHAR(255) NOT NULL,
    active BOOLEAN
) Engine=InnoDB;



create table oauth_permissions (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    id_user VARCHAR(100) NOT NULL REFERENCES s_users(id),
    user_role VARCHAR(255) NOT NULL
) Engine=InnoDB;


create table if not exists oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);



create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

create table if not exists oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP default now()
);


insert into oauth_users (username, password, active)
values ('patartimotius', 'evievi123', true);


insert into oauth_permissions (id_user, user_role)
values (1, 'ROLE_SUPERVISOR');

insert into oauth_permissions (id_user, user_role)
values (1, 'ROLE_OPERATOR');

insert into oauth_client_details
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types, authorities) values 
('clientauthcode', '123456', 'belajar', 'read,write', 'authorization_code, refresh_token', 'CLIENT');


insert into oauth_client_details 
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types) values 
('clientapp', '123456', 'belajar', 'read,write', 'password,refresh_token');
