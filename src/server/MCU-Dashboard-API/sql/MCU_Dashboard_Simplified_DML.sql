-- ------------------------------------------- --
-- !!!Important - first import the MCU_Edited.CSV into mcuEdited table!!!

-- check table for 126 rows of data
select * from mcuEdited;
-- ------------------------------------------- --


-- -------------------------- --
-- Upload data to Movie table
insert into `Movie` (title, franchise, UsReleaseDate, runtime, ImdbRating, metascore, budget, domesticGross, totalGross, openingGross, oscarNominations, oscarsWon)
select distinct
	title,
	Franchise,
    `US release Date`,
    `Duration(min)`,
    `IMDB rating`,
    metascore,
    Budget,
    `Domestic Gross`,
    `Total Gross`,
    `Opening Gross`,
    `Oscar Nomination`,
    `Oscar won`
from mcuEdited;

-- check Movie table
select * from Movie;
-- -------------------------- --


-- ----------------------------- --
-- Upload data into Person table --
insert into Person (`name`)
select distinct
	`name`
from mcuEdited;

-- check Person table
select * from Person;
-- ----------------------------- --


-- ----------------------------------- --
-- Upload data into Movie_Person table --
insert into Movie_Person (idMovie, `role`, idPerson)
select idMovie, `role`, idPerson
from mcuEdited
inner join Movie using (title)
left outer join Person using (`name`);

-- check Movie_Person
select * from Movie_Person;

-- check joined table MOvie_Person + Person
select mp.idMovie, mp.`role`, p.idPerson, p.`name`
from Movie_Person mp
left outer join Person p using (idPerson);
-- ----------------------------------- --


-- ------------------------- --
-- insert data into app_user --
-- passwords are set to "P@ssw0rd!"
insert into app_user (username, password_hash, disabled)
    values
    ('stan@lee.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0),
    ('peter@parker.com', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 0);

-- check app_user 
select * from app_user;
-- ------------------------- --


-- ------------------------- --
-- insert data into app_role
insert into app_role (`name`) values
    ('USER'),
    ('ADMIN');

-- check app_role
select * from app_role;
-- ------------------------- --


-- ------------------------------ --
-- insert data into app_user_role
insert into app_user_role
    values
    (1, 2),
    (2, 1);
    
    -- check app_user_role
    select * from app_user_role;
    -- ------------------------------ --
