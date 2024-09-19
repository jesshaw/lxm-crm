import './home.css';

import React, { useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector, useAppDispatch } from 'app/config/store';
import { MenuItemsData, setBreadItems } from 'app/shared/reducers/ui';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);

  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(setBreadItems([MenuItemsData.homeMenuItem]));
  }, []);

  return (
    <div className="l-card">
      <div className="grid grid-cols-12 gap-1">
        <div className="col-span-12 md:col-span-3">
          <span className="hipster rounded" />
        </div>
        <div className="col-span-12 md:col-span-9">
          <h1 className="display-4">
            <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
          </h1>
          <p className="lead">
            <Translate contentKey="home.subtitle">This is your homepage</Translate>
          </p>
          {account?.login ? (
            <div>
              <Alert color="success">
                <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                  You are logged in as user {account.login}.
                </Translate>
              </Alert>
            </div>
          ) : (
            <div>
              <Alert color="warning">
                <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>

                <Link to="/login" className="alert-link">
                  <Translate contentKey="global.messages.info.authenticated.link"> sign in</Translate>
                </Link>
                <Translate contentKey="global.messages.info.authenticated.suffix">
                  , you can try the default accounts:
                  <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                  <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
                </Translate>
              </Alert>

              <Alert color="warning">
                <Translate contentKey="global.messages.info.register.noaccount">You do not have an account yet?</Translate>&nbsp;
                <Link to="/account/register" className="alert-link">
                  <Translate contentKey="global.messages.info.register.link">Register a new account</Translate>
                </Link>
              </Alert>
            </div>
          )}
          <p>
            <Translate contentKey="home.question">If you have any question on JHipster:</Translate>
          </p>

          <ul>
            <li>
              <a href="https://github.com/jesshaw/generator-jhipster-chimera" target="_blank" rel="noopener noreferrer">
                <Translate contentKey="home.link.homepage">Chimera homepage</Translate>
              </a>
            </li>
            <li>
              <a href="#" target="_blank" rel="noopener noreferrer">
                <Translate contentKey="home.link.stackoverflow">Chimera on Stack Overflow</Translate>
              </a>
            </li>
            <li>
              <a href="https://github.com/jesshaw/generator-jhipster-chimera/issues?state=open" target="_blank" rel="noopener noreferrer">
                <Translate contentKey="home.link.bugtracker">Chimera bug tracker</Translate>
              </a>
            </li>
            <li>
              <a href="" target="_blank" rel="noopener noreferrer">
                <Translate contentKey="home.link.chat">Chimera public chat room</Translate>
              </a>
            </li>
            <li>
              <a href="" target="_blank" rel="noopener noreferrer">
                <Translate contentKey="home.link.follow">follow @Chimera on Twitter</Translate>
              </a>
            </li>
          </ul>
          <p>
            <Translate contentKey="home.like">If you like Chimera, do not forget to give us a star on</Translate>
            <a href="https://github.com/jesshaw/generator-jhipster-chimera" target="_blank" rel="noopener noreferrer">
              GitHub
            </a>
            !
          </p>
        </div>
      </div>
    </div>
  );
};

export default Home;
