"use client";
import React, { useCallback, useEffect, useRef, useState } from "react";
import { NoteList } from "@/app/components/note/note";
import { NoteBookList } from "@/app/components/note/notebook";
import Header from "@/app/components/header";
import Detail from "@/app/components/detail";
import Link from "next/link";
import { loginUserStore } from "@/app/login/loginUserStore";
import { redirect } from 'next/navigation'
import { components } from "@/lib/api/v1/schema";
import NoteMenu from "./components/note/note-menu";

type NotebookDto = components["schemas"]["NotebookDto"];
type NoteDto = components["schemas"]["NoteDto"];

export default function Home() {
    const [targetNotebook, setTargetNotebook] = useState<NotebookDto | null>(null);
    const [targetNote, setTargetNote] = useState<NoteDto | null>(null);
    const [targetNoteMap, setTargetNoteMap] = useState<Map<number, NoteDto | null>>(new Map());
    const [headerHeight, setHeaderHeight] = useState<number>(0);
    const loginUser = loginUserStore((state) => state.loginUser);
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [menuPostion, setMenuPostion] = useState<any>(null);
    const menuModalRef = useRef<HTMLDialogElement | null>(null);

    const initTargetNote = (notebookIdList: number[]) => {
        const tmp = new Map(targetNoteMap);
        notebookIdList.forEach((id: number) => {
            tmp.set(id, null);
        });

        setTargetNoteMap(tmp);
    }

    const onClickBookItem = (notebook: NotebookDto) => {
        setTargetNotebook(notebook);
        if (notebook.id) {
            const note = targetNoteMap.get(notebook.id);
            if (note !== undefined) {
                setTargetNote(note);
            }
        }
    }

    const onClickNoteItem = (notebook: NotebookDto, note: NoteDto) => {
        console.log("note click");
        console.log(note);
        setTargetNote(note);
        const tmp = new Map(targetNoteMap);
        if (targetNotebook?.id) {
            tmp.set(targetNotebook.id, note);
            setTargetNoteMap(tmp);
        }
    }

    const onClickMenu = (e: any) => {
        e.stopPropagation();
        if (menuModalRef.current) {
            menuModalRef.current.showModal();
        }
        setMenuPostion({ x: e.clientX, y: e.clientY });
    }

    const sendHeightToMain = (height: number) => {
        setHeaderHeight(height);
    }

    useEffect(() => {
        console.log(headerHeight);
        console.log(targetNote);
        setIsLoading(false);
    }, [headerHeight]);

    useEffect(() => {
        console.log('sdfsdfsdf');
        console.log(targetNote);
        console.log(isLoading);
        setIsLoading(false);
    }, [targetNote]);

    if (isLoading) {
        return <div>loading...</div>
    }

    if (loginUser === null) {
        redirect('/login');
    }


    return (
        <>
            <dialog ref={menuModalRef} id="menu-modal" className="modal">
                {menuPostion && <NoteMenu x={menuPostion.x} y={menuPostion.y} />}
            </dialog>
            <Header sendHeightToMain={sendHeightToMain} />
            <div className={`flex h-[calc(100vh-${headerHeight}px)] px-[10px] pt-[10px]`}>
                <div className=" flex flex-col justify-between border-r-[1px] border-gray-300 w-[12%]">
                    <div>
                        <NoteBookList targetBook={targetNotebook} children={[]} onClickItem={onClickBookItem} targetNoteMap={targetNoteMap} initTargetNote={initTargetNote}
                            onClickMenu={onClickMenu} />
                    </div>
                    {/* <form>
                        <input type="submit" value="추가" className="postActionBtn btn" />
                    </form>

                    <form method="post">
                        <input
                            type="submit"
                            value="하위 노트북 추가"
                            className="postActionBtn btn"
                        />
                    </form>
                    <form method="post">
                        <input type="submit" value="삭제" className="postActionBtn btn" />
                    </form>
                    <button className="btn">노트 이동</button>
                    <button className="btn">검색</button>
                    <button className="btn">노트 이름 변경</button>
                    <button className="btn">태그 목록</button> */}
                </div>
                <div className="border-r-[1px] border-gray-300 w-[15%] text-center ">
                    <NoteList targetBook={targetNotebook} onClickItem={onClickNoteItem} targetNote={targetNote} targetNoteMap={targetNoteMap} />
                    {/* <form>
                        <input type="submit" value="추가" className="postActionBtn" />
                    </form> */}

                    {/* <a className="btn sortDate">날짜순</a>
                    <a className="btn sortTitle">이름순</a> */}
                </div>
                <div className="w-[73%] p-[10px]">
                    <Detail targetNote={targetNote} />
                </div>
            </div>
        </>
    );
}
