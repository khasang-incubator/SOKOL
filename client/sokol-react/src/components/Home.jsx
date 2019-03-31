import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import { Jumbotron, Grid, Row, Col, Image, Button, Table, Navbar, Nav, Form, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import './Home.css';

export default class Home extends Component {
    render() {
        return (
            <div className="grid">

                <Grid>
                    <Jumbotron className="jumbotron">
                        <Navbar className="navbar-tt">
                            <Link to="/about">
                                <Button bsStyle="danger" className="bt">Новый</Button>
                            </Link>
                            <Nav pullRight>
                                <Form inline className="search">
                                    <FormGroup controlId="formInlineName">
                                        <FormControl type="text" placeholder="Поиск" />
                                    </FormGroup>{' '}
                                    <Button type="submit">Найти</Button>
                                </Form>
                            </Nav>


                        </Navbar>

                        <Table striped className="t1">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Table heading</th>
                                    <th>Table heading</th>
                                    <th>Table heading</th>
                                    <th>Table heading</th>
                                    <th>Table heading</th>
                                    <th>Table heading</th>
                                    {/* <th>Table heading</th> */}
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Денис отдыхает в Мексике</td>
                                    <td>Миха живет в Краснодаре</td>
                                    <td>Андрей просто мерзнет</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    {/* <td>Table cell</td> */}
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>6</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                                <tr>
                                    <td>7</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                    <td>Table cell</td>
                                </tr>
                            </tbody>
                        </Table>
                    </Jumbotron>
                    {/* <Link to="/about">
                <Button bsStyle="primary">About</Button>
                </Link> */}
                </Grid>
            </div>
        )
    }
}