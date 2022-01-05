use MCU_Dashboard;

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

insert into Genre (`name`)
select distinct
	Genre
from mcuEdited
where Genre != '';

insert into `Role` (`role`)
select distinct
	`role`
from mcuEdited;

insert into Movie_Genre (idMovie, idGenre)
select
	mv.idMovie,
    g.idGenre
from Movie mv
inner join mcuEdited e on mv.`title` =e.`title`
inner join Genre g on e.Genre=g.`name`;

insert into Person (`name`)
select distinct
	`name`
from mcuEdited;

insert into Person_Role (idPerson, idRole)
select distinct
	p.idPerson,
    r.idRole
from Person p
inner join mcuEdited e on p.`name`=e.`name`
inner join Role r on e.`role` = r.`role`;

insert into Movie_Person (idMovie, idPerson)
select
	mv.idMovie,
    p.idPerson
from Movie mv
inner join mcuEdited e on mv.title = e.title
inner join Person p on e.`name` = p.`name`;

drop table mcuEdited;