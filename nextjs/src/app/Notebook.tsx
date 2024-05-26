import {useEffect, useState} from "react";
import {get} from "@/global/fetchApi";

interface NotebookDto {
    id: number;
    title: string;
    children: NotebookDto[];
}

interface NoteBookListProps {
    children?: NotebookDto[] | null;
    target: number;
    onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void;
}

export function NoteBookList({children, target, onClickItem}: NoteBookListProps) {
    const [notebookList, setNotebookList] = useState<NotebookDto[] | null>(null);

    useEffect(() => {

        if (children) {
            setNotebookList(children);
            return;
        }

        async function getNotebookList() {
            const data = await get("/books", {});
            setNotebookList(data);

            if(target === 0) {
                target = data[0].id;
            }
        }

        getNotebookList();
    }, []);

    return (
        <ul className="menu">
            {notebookList && notebookList.map((notebook: NotebookDto) => (
                notebook.children.length === 0 ?
                    <BookItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem} /> :
                    <GroupItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem} />
            ))}
        </ul>
    );
}

function BookItem({notebook, target, onClickItem}: { notebook: NotebookDto, target: number, onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void }) {
    return (
        <li>
            <a className={notebook.id == target ? 'bg-blue-600' : ''} data-id={notebook.id} onClick={onClickItem}>{notebook.title}</a>
        </li>
    );
}

function GroupItem({notebook, target, onClickItem}: { notebook: NotebookDto, target: number, onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void }) {
    return (
        <li>
            <details>
                <summary>
                    <a className={notebook.id == target ? 'bg-blue-600' : ''} data-id={notebook.id} onClick={onClickItem}>{notebook.title}</a>
                </summary>
                <NoteBookList target={target} children={notebook.children} onClickItem={onClickItem}/>
            </details>
        </li>
    );
}
