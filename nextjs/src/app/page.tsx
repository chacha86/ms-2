"use client";
import React, {useCallback, useEffect, useState} from "react";
import {NoteList} from "@/app/Note";
import {NoteBookList} from "@/app/Notebook";
import Header from "@/app/Header";
import Detail from "@/app/Detail";
import Link from "next/link";
import {loginUserStore} from "@/app/login/loginUserStore";
import { redirect } from 'next/navigation'
import { components } from "@/lib/api/v1/schema";

type NotebookDto = components["schemas"]["NotebookDto"];
type NoteDto = components["schemas"]["NoteDto"];

export default function Home() {
    const [targetNotebook, setTargetNotebook] = useState<NotebookDto | null>(null);
    const [targetNote, setTargetNote] = useState<NoteDto | null>(null);
    const loginUser= loginUserStore((state) => state.loginUser);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const onClickBookItem = (notebook:NotebookDto) => {
        setTargetNotebook(notebook);
        setTargetNote(null);
    }

    const onClickNoteItem = (note:NoteDto) => {
        setTargetNote(note);
    }

    console.log("loginUser: ", loginUser);

    useEffect(() => {
        console.log("dfsdf : " + loginUser);
        setIsLoading(false);
    }, [targetNote, targetNotebook]);

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
                <div className="border-r-[1px] border-gray-300 w-[12%]">
                    <div className="h-[70%]">
                        <NoteBookList targetBook={targetNotebook} children={[]} onClickItem={onClickBookItem}/>
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
                <div className="border-r-[1px] border-gray-300 w-[15%] h-[800px] text-center ">
                    <NoteList targetBook={targetNotebook} onClickItem={onClickNoteItem} targetNote={targetNote}/>
                    <form>
                        <input type="submit" value="추가" className="postActionBtn"/>
                    </form>

                    <a className="btn sortDate">날짜순</a>
                    <a className="btn sortTitle">이름순</a>
                </div>
                <div className="w-[60%]">
                    <Detail targetNote={targetNote}/>
                </div>
            </div>
        </>
    );
}
