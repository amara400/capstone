use MCU_Dashboard;

select * from mcuEdited;

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

select * from Movie;

select distinct
	Genre
from mcuEdited
where Genre != '';

insert into Genre (`name`)
select distinct
	Genre
from mcuEdited
where Genre != '';

select * from Genre;

select distinct
	`role`
from mcuEdited;

insert into `Role` (`role`)
select distinct
	`role`
from mcuEdited;

select * from `Role`;

select
	mv.idMovie,
    mv.title,
    g.idGenre,
    g.`name`
from Movie mv
inner join mcuEdited e on mv.`title` =e.`title`
inner join Genre g on e.Genre=g.`name`;

insert into Movie_Genre (idMovie, idGenre)
select
	mv.idMovie,
    g.idGenre
from Movie mv
inner join mcuEdited e on mv.`title` =e.`title`
inner join Genre g on e.Genre=g.`name`;

select * from Movie_Genre;

select
	mv.title,
    g.`name`
from Movie mv
inner join Movie_Genre mg on mv.idMovie = mg.idMovie
inner join Genre g on mg.idGenre=g.idGenre;

select distinct
	`name`
from mcuEdited;

insert into Person (`name`)
select distinct
	`name`
from mcuEdited;

select * from Person;

select distinct
	p.`name`,
    p.idPerson,
    r.`role`,
    r.idRole
from Person p
inner join mcuEdited e on p.`name`=e.`name`
inner join Role r on e.`role` = r.`role`
order by p.`name`;

insert into Person_Role (idPerson, idRole)
select distinct
	p.idPerson,
    r.idRole
from Person p
inner join mcuEdited e on p.`name`=e.`name`
inner join Role r on e.`role` = r.`role`;

select * from Person_Role;

select
	p.`name`,
    p.idPerson,
    r.`role`,
    r.idRole
from Person p
inner join Person_Role pr on p.idPerson=pr.idPerson
inner join Role r on pr.idRole = r.idRole
order by p.`name`;

select
	mv.title,
    mv.idMovie,
    p.`name`,
    p.idPerson
from Movie mv
inner join mcuEdited e on mv.title = e.title
inner join Person p on e.`name` = p.`name`;

insert into Movie_Person (idMovie, idPerson)
select
	mv.idMovie,
    p.idPerson
from Movie mv
inner join mcuEdited e on mv.title = e.title
inner join Person p on e.`name` = p.`name`;

select * from Movie_Person;

select
	mv.title,
    mv.idMovie,
    p.`name`,
    p.idPerson
from Movie mv
inner join Movie_Person mp on mv.idMovie = mp.idMovie
inner join Person p on mp.idPerson = p.idPerson;

drop table mcuEdited;