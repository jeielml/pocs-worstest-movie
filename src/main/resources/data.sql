-- drop table if exists movies;
--
-- create table movies(
--     id int auto_increment primary key,
--     year int not null,
--     title varchar(250) not null,
--     studios varchar(250) not null,
--     producer_id int not null,
--     winner   boolean not null
-- );

-- drop index if exists idx_movies_producers;
-- create index idx_movies_producers on movies (producer_id asc);

//insert into movies(year, title, studios, producers, winner)
//values(1980, 'Can''t Stop the Music', 'Associated Film Distribution', 'Allan Carr',true);

