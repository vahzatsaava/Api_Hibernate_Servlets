create table if not exists files
(
    id int auto_increment not null primary key,
    fileName varchar(250) not null,
    location varchar(250) not null
);
create table if not exists users
(
    id int auto_increment not null primary key ,
    firstName varchar(250) not null,
    lastName varchar(250) not null
);
create table if not exists events
(
    id serial not null primary key,
    created date not null,
    file_id integer not null,
    user_id integer not null,
    FOREIGN key (file_id) REFERENCES files(id),
    FOREIGN key (user_id) REFERENCES users(id),
    UNIQUE (file_id,user_id)
  )
