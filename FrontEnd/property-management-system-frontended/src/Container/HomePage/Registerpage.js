// Register.js

import React, { useState } from 'react';
import './Register.css';

const RegisterPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [isOwner, setIsOwner] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Add your registration logic here
    console.log('Email:', email);
    console.log('Password:', password);
    console.log('First Name:', firstName);
    console.log('Last Name:', lastName);
    console.log('Is Owner:', isOwner);
  };

  return (
    <div className="register-container">
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>First Name:</label>
          <input
            type="text"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>Last Name:</label>
          <input
            type="text"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="isOwner">Are you an owner?</label>
          <div id="isOwner" className="option-container">
            <input
              type="radio"
              id="yes"
              name="isOwner"
              value="yes"
              checked={isOwner === 'yes'}
              onChange={(e) => setIsOwner(e.target.value)}
            />
            <label htmlFor="yes">Yes</label>
            <input
              type="radio"
              id="no"
              name="isOwner"
              value="no"
              checked={isOwner === 'no'}
              onChange={(e) => setIsOwner(e.target.value)}
            />
            <label htmlFor="no">No</label>
          </div>
        </div>
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterPage;


