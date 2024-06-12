'use client';
import React, {useEffect, useState} from "react";
import {cookies} from "next/headers";
import {get, post} from "@/global/fetchApi";
import {redirect, useRouter} from "next/navigation";
import Link from "next/link";

function Header() {
    const [isLogin, setIsLogin] = useState<boolean>(false);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const router = useRouter();

    useEffect(() => {
        const getUser = async () => {
            const res = await fetch("http://localhost:8999/api/v1/auth/user", {
                method: "GET",
                credentials: "include",
            });
            const result = await res.json();
            console.log("result: ", result);

            if (result.data === "user") {
                setIsLogin(true);
            }

            setIsLoading(false);
        }

        getUser();
    }, []);

    const onClickLogout = async () => {
        const result = await post("/auth/logout", {});
        if (result.result === "success") {
            console.log("logout success");
            setIsLogin(false);
            router.push("/");
        }
    }
    return (
        <div className="bg-blue-300 flex">
            <div className="w-[30%]">
                <a href="/">logo</a>
            </div>
            <div className="w-[70%] flex justify-end gap-5 mr-[10px]">
                {isLogin
                    ? <button onClick={onClickLogout}>로그아웃</button>
                    : <Link href="/login">로그인</Link>
                }
            </div>
        </div>
    );
}

export default Header;