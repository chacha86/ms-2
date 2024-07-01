'use client'
import React, {useEffect, useState} from "react";
import {useRouter} from "next/navigation";
import {loginUserStore} from "@/app/login/loginUserStore";

enum resultState {
    IDLE = 0,
    SUCCESS = 1,
    FAIL = 2,
    ERROR = 3
}

export default function Login() {
    const [loginId, setLoginId] = useState('');
    const [loginPw, setLoginPw] = useState('');
    const router = useRouter();
    const [loginResult, setLoginResult] = useState<number>(resultState.IDLE);
    const loginUser = loginUserStore((state) => state.loginUser);
    const setLoginUser = loginUserStore((state) => state.setUser);

    const onChangeLoginId = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginId(e.target.value);
    }

    const onChangeLoginPw = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLoginPw(e.target.value);
    }
    const onClickLoginButton = () => {
        fetch('/api/v1/auth/login', {
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
                if (!res.ok) {
                    setLoginResult(resultState.FAIL);
                    Promise.reject("login fail");
                }
                return res.json();
            }).then((data) => {
                if(data.result === "success"){
                    setLoginResult(resultState.SUCCESS);
                    const testUser = {
                        username: "testUser"
                    }
                    setLoginUser(testUser);
                    router.push('/');
                    return;
                }
                setLoginResult(resultState.FAIL);
            })
            .catch((err) => {
                setLoginResult(resultState.ERROR);
                console.log("===>" + err);
            });
    }
    if (loginResult === resultState.ERROR) {
        throw new Error("login error");
    }

    return (
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
            <div>
                {loginResult === resultState.FAIL && <div className="text-red-500 text-center">로그인 실패</div>}
            </div>
        </div>
    );
}