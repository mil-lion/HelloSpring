/**
 * Author:  Igor Morenko <morenko at lionsoft.ru>
 * Created: 18 янв. 2020 г.
 */

drop table authorities;
drop table users;

create table users(
    username varchar(75)  not null primary key,
    password varchar(150) not null,
    enabled  numeric(1)   not null default 1
);
create table authorities (
    username  varchar(75) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

-- Bcrypt - PasswordEncoder
-- https://www.devglan.com/online-tools/bcrypt-hash-generator 
-- admin/password - ADMIN
insert into users(username, password, enabled)
values('admin', '$2a$04$INOTj84Voy89jr.WQgxp0e7A6q9496PPHp0e9PSu8V0D2H03JK9BK', 1);

insert into authorities(username, authority)
values('admin','ROLE_ADMIN');

-- user/password - USER
insert into users(username, password, enabled)
values('user', '$2a$04$INOTj84Voy89jr.WQgxp0e7A6q9496PPHp0e9PSu8V0D2H03JK9BK', 1);

insert into authorities(username, authority)
values('user','ROLE_USER');
