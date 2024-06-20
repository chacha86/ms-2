'use client';
import React, {useEffect, useState} from "react";
import {cookies} from "next/headers";
import {get, post} from "@/global/fetchApi";
import {redirect, useRouter} from "next/navigation";
import Link from "next/link";
import errorStore from "@/app/errorStore";

function Header() {
    const [isLogin, setIsLogin] = useState<boolean>(false);
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const router = useRouter();
    // const setError = errorStore((error) => error.setError);
    const [error, setError] = useState<boolean>(false);

    useEffect(() => {
        const getUser = async () => {

            try {

                fetch("http://localhost:8999/api/v1/auth/user", {
                    method: "GET",
                    credentials: "include",
                })
                    .then((res) => {
                        if (!res.ok) {
                            setError(true);
                            console.error("error123123123");
                            throw new Error();
                        }
                        return res;
                    })
                    .then((res) => res.json())
                    .then((data) => {
                        const result = data;
                        if (result.data === "user") {
                            setIsLogin(true);
                            setIsLoading(false);
                        }
                    })
                    .catch((err) => {
                        console.error("error123123123");
                    });
            } catch (e) {
                console.error("error123123123");
            }

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