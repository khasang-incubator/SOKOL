import React, { Component } from 'react'
import { Navbar, Nav, NavItem, ButtonToolbar, DropdownButton, MenuItem, NavDropdown, Image } from 'react-bootstrap';
import { Link } from 'react-router-dom';
// import './Header.css'
import './Footer.css'

export default class CustomNavbar extends Component {
    render() {
        return (
            <Navbar className="navbar-sokolit">
                <Navbar.Header>
                   <Navbar.Brand>
                     <p className="navbar-brand-sokolit">&copy; 2019 Sokol IT</p> 
                   </Navbar.Brand>
                </Navbar.Header>                 
                    
                <Nav pullRight>
                 <Nav>
                    <NavItem eventKey={1} componentClass={Link} to="/home">
                        О нас 
                    </NavItem>
                    <NavItem eventKey={2} componentClass={Link} to="/about">
                        Правила использования
                    </NavItem>
                </Nav> 
                </Nav>
                
                </Navbar>
        )
    }
}

