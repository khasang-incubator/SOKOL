import React from 'react';
import { Table } from "react-bootstrap";
import './index.css';

export default function CoinTable(props) {
    return (
        <div className="grid">
        <Table striped className="t1">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Название</th>
                    <th>Описание</th>
                </tr>
            </thead>
        <tbody>
            {
                props.data.map(row => (
                    <tr>
                        <td>{row.id}</td>
                        <td>{row.name}</td>
                        <td>{row.description}</td>
                    </tr>
                ))
            }
        </tbody>
        </Table>
   
        
      
        </div>
    )
}