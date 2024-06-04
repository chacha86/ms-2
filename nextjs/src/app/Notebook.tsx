import React, {useEffect, useState} from "react";
import {get} from "@/global/fetchApi";

interface NotebookDto {
    id: number;
    title: string;
    children: NotebookDto[];
}

interface NoteBookListProps {
    children?: NotebookDto[] | null;
    target: number;
    onClickItem: (e: React.MouseEvent<HTMLSpanElement>) => void;
}

const NoteBookList: React.FC<NoteBookListProps> = React.memo(({ children, target, onClickItem }) => {
// export function NoteBookList({children, target, onClickItem}: NoteBookListProps) {
    const [notebookList, setNotebookList] = useState<NotebookDto[] | null>(null);
    const [isLoding, setIsLoading] = useState<boolean>(true);

    useEffect(() => {

        if (children) {
            setNotebookList(children);
            return;
        }

        async function getNotebookList() {
            const data = await get("/books", {});
            setNotebookList(data);
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
                    <BookItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem} /> :
                    <GroupItem key={notebook.id} notebook={notebook} target={target} onClickItem={onClickItem} />
            ))}
        </ul>
    );
});

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
    onClickItem: (e: React.MouseEvent<HTMLSpanElement>) => void,
}) {

    const [isOpen, setIsOpen] = useState(false);

    const handleToggle = (e: React.MouseEvent<HTMLSpanElement>) => {
        e.stopPropagation();

        const selectedItem = e.currentTarget.parentNode as HTMLElement;
        selectedItem?.classList.toggle('menu-dropdown-show');
        selectedItem.nextElementSibling?.classList.toggle('menu-dropdown-show');

        setIsOpen(!isOpen);
    }

    useEffect(() => {
        setIsOpen(isOpen);
    }, [isOpen]);

    let itemClass = "menu-dropdown-toggle relative";
    itemClass += isOpen ? ' menu-dropdown-show' : '';
    itemClass += notebook.id == target ? ' bg-blue-600 text-white' : '';
    let filterClass = "filter border-2 absolute w-[15px] h-[50%] p-[12px] right-[0.3rem] cursor-copy";
    filterClass += notebook.id == target ? ' bg-indigo-300' : '';

    return (
        <li>
            <span className={itemClass} data-id={notebook.id} onClick={onClickItem}>
                {notebook.title} <span className={filterClass} onClick={handleToggle}></span>
            </span>
            <NoteBookList target={target} children={notebook.children} onClickItem={onClickItem}/>
        </li>
    );
}
export {NoteBookList};