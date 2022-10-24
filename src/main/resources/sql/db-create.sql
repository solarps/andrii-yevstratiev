drop table if exists subscription cascade;
drop table if exists users cascade;
drop table if exists activity cascade;

create table if not exists activity
(
    id       serial primary key,
    name     varchar(50),
    category varchar(50)
);

create table if not exists users
(
    id       serial primary key,
    name     varchar(50),
    login    varchar(50)
);

create table if not exists subscription
(
    user_id     integer,
    activity_id integer,
    spent_time  interval HOUR TO SECOND,
    foreign key (user_id) references users (id),
    foreign key (activity_id) references activity (id),
    primary key (user_id, activity_id)
);
