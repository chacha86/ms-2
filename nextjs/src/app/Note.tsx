import {useEffect, useState} from "react";
import {get} from "@/global/fetchApi";

interface NoteDto {
    id: number;
    title: string;
    content: string;
}

export function NoteList({bookId, target, onClickItem}: { bookId: number, target:number, onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void}) {
    const [noteList, setNoteList] = useState<NoteDto[] | null>(null);

    useEffect(() => {
        async function getNoteList() {
            console.log("bookId: ", bookId);
            const data = await get("/notes", {bookId: String(bookId)});
            setNoteList(data);
        }

        getNoteList();
    }, [bookId]);

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
}