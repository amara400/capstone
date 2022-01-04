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
	e.`name`,
    e.`role`,
    r.idRole
from mcuEdited e
inner join Role r on e.`role`=r.`role`
order by e.`name`;