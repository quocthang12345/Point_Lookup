import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'semantic-ui-css/semantic.min.css'
import HeaderComponent from './header/HeaderComponent';
import LandingPageComponent from './index/LandingPageComponent';
import LoginComponent from './auth/LoginComponent';
import RegisterComponent from './auth/RegisterComponent';
import ProfileComponent from './profile/ProfileComponent';
import SchelduleComponent from './scheldule/SchelduleComponent';
import ScoreComponent from './score/ScoreComponent';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useLocation
} from "react-router-dom";
import { useState } from 'react';

function App() {
  let location = useLocation();

  return (
    <div className="App">
       
        <Router>
            <HeaderComponent />
            <Switch> 
                <Route path="/login" exact component={LoginComponent} />

                <Route path="/register" exact component={RegisterComponent} />

                <Route path="/" exact component={LandingPageComponent} />

                <Route path="/profile" component={ProfileComponent} />

                <Route path="/scheldule" component={SchelduleComponent} />

                <Route path="/score" component={ScoreComponent} />
            </Switch>
          </Router>
    </div>
  );
}

export default App;
