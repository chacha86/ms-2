import React, { Component, useEffect, useState } from "react";
import { get } from "@/global/fetchApi";
import { paths, components } from "@/lib/api/v1/schema";

type NoteDto = components["schemas"]["NoteDto"];
type NotebookDto = components["schemas"]["NotebookDto"];
export const NoteList = React.memo(({ targetBook, targetNote, onClickItem }: { targetBook: NotebookDto | null, targetNote: NoteDto | null, onClickItem: (notebook: NotebookDto, note: NoteDto) => void, targetNoteMap: Map<number, NoteDto | null> }) => {

    const [noteList, setNoteList] = useState<NoteDto[]>([]);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    useEffect(() => {
        if (targetBook === null) {
            setIsLoading(false);
            return;
        }

        async function getNoteList() {

            const targetBookId = targetBook?.id;
            const result = await get(`/books/${targetBookId}/notes`, {});

            if (result.resultCode === "fail") {
                setIsLoading(true);
                throw new Error(result.message);
            }
            if (targetNote === null && targetBook) {
                onClickItem(targetBook, result.body[0]);
            }
            setIsLoading(false);
            console.log(result.body);
            setNoteList(result.body);
        }

        getNoteList();
    }, [targetBook]);

    if (isLoading) {
        return <div>Loading...</div>;
    }
    let itemClass = 'hover:cursor-pointer hover:bg-gray-300';
    let selectedItemClass = itemClass + " bg-gray-500 text-white rounded-md";

    return (
        <ul className="h-[100%] overflow-scroll">
            {noteList && noteList.map((note: NoteDto, index: number) => (
                <li key={note.id} className={note.id === targetNote?.id ? selectedItemClass : itemClass}>
                    <span className="block w-[100%] p-2" onClick={
                        () => {
                            if (targetBook?.id) {
                                onClickItem(targetBook, noteList[index]);
                            }
                        }} data-idx={index}>{note.title}</span>
                </li>
            ))}
        </ul>
    );
});