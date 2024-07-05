import React, { Component, useEffect, useState } from "react";
import { get } from "@/global/fetchApi";
import createClient from "openapi-fetch";
import { paths, components } from "@/lib/api/v1/schema";

type NoteDto = components["schemas"]["NoteDto"];
// interface NoteDto {
//     id: number;
//     title: string;
//     content: string;
// }

export const NoteList = React.memo(({ bookId, target, onClickItem }: { bookId: number, target: number, onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void }) => {

    // export function NoteList({bookId, target, onClickItem}: { bookId: number, target:number, onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void}) {
    const [noteList, setNoteList] = useState< NoteDto[] | null>(null);
    // const [noteList, setNoteList] = useState<any[] | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    useEffect(() => {
        console.log('note start');
        async function getNoteList() {
            const client = createClient<paths>({ baseUrl: "http://localhost:8999" });
            const { data, error, response } = await client.GET("/api/v1/notes", { params: { query: { id: bookId } },credentials:"include"});
            console.log("bookId: ", bookId);
            console.log(response);
            // const data = await get("/notes", {bookId: String(bookId)});
            if(error) {
                throw new Error(error);
            }
            // if (data.data === "fail") {
            //     setIsLoading(true);
            //     return;
            // }
            setIsLoading(false);
            setNoteList(data);
        }

        getNoteList();
    }, [bookId]);

    if (isLoading) {
        return <div>Loading...</div>;
    }
    let itemClass = 'hover:cursor-pointer p-2';
    let selectedItemClass = itemClass + " bg-blue-600 text-white";

    return (
        <ul className="h-[100%] overflow-scroll">
            {noteList && noteList.map((note: NoteDto) => (
                <li key={note.id} className={note.id === target ? selectedItemClass : itemClass}>
                    <span className="block w-[100%]" onClick={onClickItem} data-id={note.id}>{note.title}</span>
                </li>
            ))}
        </ul>
    );
});