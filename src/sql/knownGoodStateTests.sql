use MCU_Dashboard_Test;

call set_known_good_state();

select * From Movie;

select * from Genre;

select
	mv.title,
    g.`name`
from Movie mv
inner join Movie_Genre mg on mv.idMovie = mg.idMovie
inner join Genre g on mg.idGenre = g.idGenre;

select
	mv.title,
    mv.idMovie,
    p.`name`,
    p.idPerson
from Movie mv
inner join Movie_Person mp on mv.idMovie = mp.idMovie
inner join Person p on mp.idPerson = p.idPerson;
