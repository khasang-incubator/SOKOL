import React, { Component } from 'react'
import { Navbar, Nav, NavItem, ButtonToolbar, DropdownButton, MenuItem, NavDropdown, Image } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Header.css'

export default class CustomNavbar extends Component {
    render() {
        return (
            <div>
                <Navbar className="navbar-sokol">
                    <Navbar.Header>
                        <Navbar.Brand className="navbar-brand-sokol">
                           <a href="/"><img src="/img/logo.png" alt="none" /></a>
                        </Navbar.Brand>
                    </Navbar.Header>
                    <Nav>
                        <NavItem eventKey={1} componentClass={Link} to="/home">
                        Главная
                        </NavItem>
                        <NavItem eventKey={2} componentClass={Link} to="/about">
                        Запросы
                        </NavItem>
                        <NavDropdown eventKey={3} title="Настройки" id="nav-dropdown">
                            <MenuItem eventKey={3.1}>Департаменты</MenuItem>
                            <MenuItem eventKey={3.2}>Типы запросов</MenuItem>
                            <MenuItem divider />
                            <MenuItem eventKey={3.3}>Пользователи</MenuItem>
                        </NavDropdown>
                        <NavItem eventKey={4} componentClass={Link} to="/about">
                            EN
                        </NavItem>
                        <NavItem eventKey={5} componentClass={Link} to="/about">
                            RU
                        </NavItem>
                    </Nav>
                    <Nav pullRight>
                        <NavItem>
                            <Image src="/img/nophoto.png" responsive />
                        </NavItem>
                        <NavDropdown className="nophoto" eventKey={3} title="user" id="nav-user-dropdown">
                            <MenuItem eventKey={3.1}>Мой профиль</MenuItem>
                            <MenuItem eventKey={3.2}>Помощь</MenuItem>
                            <MenuItem divider />
                            <MenuItem eventKey={3.3}>Выход</MenuItem>
                        </NavDropdown>
                    </Nav>
                </Navbar>
                <Navbar className="navbar-department">
                        <Navbar.Header>
                        <Navbar.Brand className="navbar-brand-department">
                                Департаменты
                            </Navbar.Brand>
                        </Navbar.Header>
                    <Nav pullRight>
                            <NavItem>
                            <Image className="wing" src="/img/wing.jpg" />
                            </NavItem>
                        </Nav>
                </Navbar>
            </div>
        )
    }
}

