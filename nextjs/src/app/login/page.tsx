'use client'
import React, {useEffect, useState} from "react";
import {useRouter} from "next/navigation";
import MyErrorBoundary from "@/app/login/MyErrorBoundary";

export default function Login() {
    const [loginId, setLoginId] = useState('');
    const [loginPw, setLoginPw] = useState('');
    const router = useRouter();

    const onChangeLoginId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginId(e.target.value);
    }

    const onChangeLoginPw = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginPw(e.target.value);
    }
    const onClickLoginButton = () => {
        fetch('http://localhost:8999/api/v1/auth/login', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            credentials: 'include',
            body: JSON.stringify({
                "flag": "true",
                "loginId": loginId,
                "loginPw": loginPw
            })
        })
            .then((res) => {
                console.log(123123123);
                if (!res.ok) {
                    throw new Error("error");
                }
                return res.json();
            })
            .then((data) => {
                console.log(data);
                router.push("/");
            })
            .catch((err) => {
                console.log("sdfwsdfsdfsdf");
                console.error(err);
                throw new Error("error");
            })
    }

    // throw new Error("xcvxcv");

    return (
        <MyErrorBoundary fallback={"hihi"}>
            <div className="flex flex-col gap-10 w-[40%] mx-auto mt-[200px]">
                <div>
                    <h1 className="font-bold text-[1.5rem] text-center">Login</h1>
                </div>
                <div className="flex flex-col gap-3">
                    <input type="text" name="loginId" className="input input-bordered" placeholder="아이디" value={loginId}
                           onChange={onChangeLoginId}/>
                    <input type="password" name="loginPw" className="input input-bordered" placeholder="비밀번호"
                           value={loginPw} onChange={onChangeLoginPw}/>
                    <button className="btn btn-primary" onClick={onClickLoginButton}>로그인</button>
                </div>
            </div>
        </MyErrorBoundary>
    );
}