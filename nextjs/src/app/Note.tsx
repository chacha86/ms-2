import {useEffect, useState} from "react";
import {get} from "@/global/fetchApi";

interface NoteDto {
    id: number;
    title: string;
    content: string;
}

export function NoteList({bookId}: { bookId: number }) {
    const [noteList, setNoteList] = useState<NoteDto[] | null>(null);

    useEffect(() => {
        async function getNoteList() {
            const data = await get("/notes", {bookId: String(bookId)});
            setNoteList(data);
        }

        getNoteList();
    }, []);

    return (
        <ul className="h-[100%] overflow-scroll">
            {noteList && noteList.map((note: NoteDto) => (
                <li>
                    <a className="getActionBtn">{note.title}</a>
                </li>
            ))}
        </ul>
    );
}