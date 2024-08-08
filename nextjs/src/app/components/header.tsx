'use client';
import React, {useEffect, useRef, useState} from "react";
import {cookies} from "next/headers";
import {get, post} from "@/global/fetchApi";
import {redirect, useRouter} from "next/navigation";
import Link from "next/link";
import {loginUserStore} from "@/app/login/loginUserStore";

function Header({sendHeightToMain}: {sendHeightToMain: (height: number) => void}) {
    const [loginUser, setLoginUser] = loginUserStore((state) => [state.loginUser, state.setUser]);
    const headerEl = useRef<HTMLDivElement | null>(null);

    const onClickLogout = async () => {
        const result = await post("/auth/logout", {});
        if (result.result === "success") {
            console.log("logout success");
            setLoginUser(null);
            //router.push("/login");
            return;
        }
    }

    useEffect(() => {
        if(headerEl.current?.clientHeight) {
            sendHeightToMain(headerEl.current.clientHeight);
        }
    }, []);

    return (
        <div ref={headerEl} className="bg-gray-700 p-[10px] flex">
            <div className="w-[30%]">
                <Link href="/" className="text-white text-[2rem]">cnote</Link>
            </div>
            <div className="w-[70%] flex justify-end gap-5 mr-[10px]">
                {loginUser !== null
                    ? <div className="flex gap-6">
                        <span>{loginUser.username}</span>
                        <button onClick={onClickLogout}>로그아웃</button>
                    </div>
                    : <Link href="/login">로그인</Link>
                }
            </div>
        </div>
    );
}

export default Header;