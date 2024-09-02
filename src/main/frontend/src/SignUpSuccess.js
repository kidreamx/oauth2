// // src/pages/SignUpPage.js
// import React from 'react';
// import { useLocation } from 'react-router-dom';
//
// const SignUpSuccess = () => {
//     // const location = useLocation();
//     // const queryParams = new URLSearchParams(location.search);
//     // const successMessage = queryParams.get('message') || '로그인 성공!';
//
//     return (
//         <div>
//             {/*<h1>{successMessage}</h1>*/}
//             <p>회원 가입을 진행해 주세요.</p>
//             {/* 추가적인 회원가입 폼이나 컴포넌트를 여기에 배치할 수 있습니다. */}
//         </div>
//     );
// };
//
// export default SignUpSuccess;
import React from 'react';
import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import axiosInstance from './axiosInstance';

export const jwtTest = async () => {
    try {
        const response = await axiosInstance.get('/jwt-test');
        return response.data;
    } catch (error) {
        console.error('Error during JWT test:', error);
        throw error;
    }
};

const SingUpSuccess = (props) => {
    function useQuery() {
        return new URLSearchParams(useLocation().search);
    }

    const OauthPage = () => {
        const navigate = useNavigate();
        const query = useQuery();
        const accessToken = query.get('Bearer');
        let result;
        useEffect(() => {
            if (accessToken) {
                localStorage.setItem("Bearer", accessToken);
                jwtTest().then(r => console.log(r))
                navigate("/")
            }
        }, [accessToken, navigate]);

        return null;
    };

    return <OauthPage />;
};

export default SingUpSuccess;
