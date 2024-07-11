"use client";
import React, {useCallback, useEffect, useState} from "react";
import {NoteList} from "@/app/Note";
import {NoteBookList} from "@/app/Notebook";
import Header from "@/app/Header";
import Detail from "@/app/Detail";
import Link from "next/link";
import {loginUserStore} from "@/app/login/loginUserStore";
import { redirect } from 'next/navigation'

export default function Home() {
    const [targetNotebookId, setTargetNotebookId] = useState<number>(0);
    const [targetNoteId, setTargetNoteId] = useState<number>(0);
    const loginUser= loginUserStore((state) => state.loginUser);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const onClickBookItem = (e: React.MouseEvent<HTMLSpanElement>) => {
        setTargetNotebookId(Number(e.currentTarget.dataset.id));
    }


    const onClickNoteItem = (e: React.MouseEvent<HTMLSpanElement>) => {
        console.log("note id: ", e.currentTarget.dataset.id);
        setTargetNoteId(Number(e.currentTarget.dataset.id));
    }

    console.log("loginUser: ", loginUser);

    useEffect(() => {
        console.log("dfsdf : " + loginUser);
        setIsLoading(false);
    }, [targetNoteId, targetNotebookId]);

    if(isLoading) {
        return <div>loading...</div>
    }

    if(loginUser === null) {
        redirect('/login');
    }

    return (
        <>
            <Header/>
            <div className="flex">
                <div className="bg-indigo-300 w-[20%]">
                    <div className="h-[70%]">
                        <NoteBookList target={targetNotebookId} children={null} onClickItem={onClickBookItem}/>
                    </div>
                    <Link href="/zustest">zustand</Link>
                    <form>
                        <input type="submit" value="추가" className="postActionBtn btn"/>
                    </form>

                    <form method="post">
                        <input
                            type="submit"
                            value="하위 노트북 추가"
                            className="postActionBtn btn"
                        />
                    </form>
                    <form method="post">
                        <input type="submit" value="삭제" className="postActionBtn btn"/>
                    </form>
                    <a href="/signup" className="btn">
                        회원 가입
                    </a>
                    <a href="/logout" className="btn">
                        로그아웃
                    </a>

                    <button className="btn">노트 이동</button>
                    <button className="btn">검색</button>
                    <button className="btn">노트 이름 변경</button>
                    <button className="btn">태그 목록</button>
                </div>
                <div className="bg-red-300 w-[20%] h-[800px] text-center ">
                    <NoteList bookId={targetNotebookId} onClickItem={onClickNoteItem} target={targetNoteId}/>
                    <form>
                        <input type="submit" value="추가" className="postActionBtn"/>
                    </form>

                    <a className="btn sortDate">날짜순</a>
                    <a className="btn sortTitle">이름순</a>
                </div>
                <div className="w-[60%]">
                    <Detail targetNoteId={targetNoteId}/>
                </div>
            </div>
        </>
    );
}
