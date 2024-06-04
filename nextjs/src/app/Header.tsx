'use client';
import React, {useEffect, useState} from "react";
function Header({data}: { data: {result:string} }) {
    const [isLogin, setIsLogin] = useState<boolean>(data.result === "success");

    useEffect(() => {
        if (data.result === "success")
            setIsLogin(true);
    }, []);

    return (
        <div className="bg-blue-300 flex">
            <div className="w-[30%]">
                <a href="/">logo</a>
            </div>
            <div className="w-[70%] flex justify-end gap-5 mr-[10px]">
                {isLogin
                    ? <a href="/logout">로그아웃</a>
                    : <a href="/login">로그인</a>
                }
            </div>
        </div>
    );
}

export default Header;