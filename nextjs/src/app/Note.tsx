import React, { Component, useEffect, useState } from "react";
import { get } from "@/global/fetchApi";
import { paths, components } from "@/lib/api/v1/schema";

type NoteDto = components["schemas"]["NoteDto"];
export const NoteList = React.memo(({ bookId, target, onClickItem }: { bookId: number, target: NoteDto | null, onClickItem: (note:NoteDto) => void }) => {

    const [noteList, setNoteList] = useState< NoteDto[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    useEffect(() => {
        async function getNoteList() {

            // if(bookId === 0) {
            //     return;
            // }
            console.log("bookId: ", bookId);
            console.log(target);
            const result = await get(`/books/${bookId}/notes`, {});
            if(result.resultCode === "fail") {
                setIsLoading(true);
                throw new Error(result.message);
            }

            setIsLoading(false);
            console.log(result.body);
            setNoteList(result.body);
        }

        getNoteList();
    }, [bookId]);

    if (isLoading) {
        return <div>Loading...</div>;
    }
    let itemClass = 'hover:cursor-pointer hover:bg-gray-300';
    let selectedItemClass = itemClass + " bg-gray-500 text-white rounded-md";

    return (
        <ul className="h-[100%] overflow-scroll">
            {noteList && noteList.map((note: NoteDto, index:number) => (
                <li key={note.id} className={note.id === target?.id ? selectedItemClass : itemClass}>
                    <span className="block w-[100%] p-2" onClick={() => {onClickItem(noteList[index])}} data-idx={index}>{note.title}</span>
                </li>
            ))}
        </ul>
    );
});