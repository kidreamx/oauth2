import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignUp from './SignUpSuccess';
import axios from "axios"; // SignUp 컴포넌트

function Home() {

    return (
        <div>
            <a href="http://localhost:8080/oauth2/authorization/kakao">카카오 로그인</a>
        </div>
    );
}

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/oauth" element={<SignUp />} />
            </Routes>
        </Router>
    );
}

export default App;
