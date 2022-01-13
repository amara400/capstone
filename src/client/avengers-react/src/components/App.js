import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Switch, Route, Redirect, useHistory } from 'react-router-dom';
// import jwt decode from 'jwt-decode';
import AddMovie from './AddMovie';
import DeleteMovie from './DeleteMovie';
import EditMovie from './EditMovie';
import Movies from './Movies';
import MovieTable from './MovieTable';
import Home from './Home';
import About from './About';
import Contact from './Contact';
import NotFound from './NotFound';
import Header from './Header';
import FullMovieData from './FullMovieData';
import Login from './Login';
import Contributors from './Contributors'
import DeleteContributor from './DeleteContributor'
import AddContributor from './AddContributor'

function App(){

  return(
    <Router>
      <Header />
      <Switch>
        <Route exact path="/">
          <Home />
        </Route>
        <Route path="/login">
          <Login />
        </Route>
        <Route path="/about">
          <About />
        </Route>
        <Route path="/contact">
          <Contact />
        </Route>

        <Route exact path="/movies">
          <Movies />
        </Route>
        <Route path="/movies/add">
          <AddMovie />
        </Route>
        <Route path="/movies/edit/:idMovie">
          <EditMovie />
        </Route>
        <Route path="/movies/data/:idMovie">
          <FullMovieData />
        </Route>
        <Route path="/movie/delete/:idMovie">
          <DeleteMovie />
        </Route>

        <Route path="/movie/contributors/add">
          <AddContributor />
        </Route>

        <Route exact path="/movie/contributors/:idMovie">
          <Contributors />
        </Route>

        <Route path="/movie/contributors/delete/:idMovie/:role/:idPerson/:name">
          <DeleteContributor />
        </Route>



        <Route>
          <NotFound />
        </Route>
      </Switch>
    </Router>
  );
}

 
export default App;
