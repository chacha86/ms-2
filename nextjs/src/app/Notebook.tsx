import {useEffect, useState} from "react";
import {get} from "@/global/fetchApi";
import {test} from "@/global/UI";

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
            test();
            if (target === 0) {
                target = data[0].id;
            }
        }

        getNotebookList();
    }, []);

    return (
        <ul className="menu menu-dropdown p-0">
            {notebookList && notebookList.map((notebook: NotebookDto) => (
                notebook.children.length === 0 ?
                    <BookItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem}/> :
                    <GroupItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem}/>
            ))}
        </ul>
    );
}

function BookItem({notebook, target, onClickItem}: {
    notebook: NotebookDto,
    target: number,
    onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void
}) {

    const itemClass = notebook.id == target ? ' bg-blue-600 text-white' : '';
    return (
        <li>
            <a className={itemClass} data-id={notebook.id}
               onClick={onClickItem}>{notebook.title}</a>
        </li>
    );
}

function GroupItem({notebook, target, onClickItem}: {
    notebook: NotebookDto,
    target: number,
    onClickItem: (e: React.MouseEvent<HTMLAnchorElement>) => void
}) {
    let itemClass = "menu-dropdown-toggle relative";
    itemClass += notebook.id == target ? ' bg-blue-600 text-white' : '';
    let filterClass = "filter border-2 absolute w-[15px] h-[50%] p-[12px] right-[0.3rem] cursor-copy";
    filterClass += notebook.id == target ? ' bg-indigo-300' : '';

    return (
        <li>
            {/*<details>*/}
            {/*    <summary>*/}
            <span className={itemClass} data-id={notebook.id}
                  onClick={onClickItem}>{notebook.title}<span
                className={filterClass}></span></span>
            <NoteBookList target={target} children={notebook.children} onClickItem={onClickItem}/>
            {/*<ul className="menu-dropdown">*/}
            {/*    <li><a>Submenu 1</a></li>*/}
            {/*    <li><a>Submenu 2</a></li>*/}
            {/*</ul>*/}
            {/*<a className={notebook.id == target ? 'bg-blue-600' : ''} data-id={notebook.id} onClick={onClickItem}>{notebook.title}</a>*/}
            {/*</summary>*/}
            {/*</details>*/}
        </li>
    );
}
