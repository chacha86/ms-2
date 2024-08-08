import React, { useEffect, useState } from "react";
import { get } from "@/global/fetchApi";
import { paths, components } from "@/lib/api/v1/schema";
import NoteMenu from "./note-menu";

type NotebookDto = components["schemas"]["NotebookDto"];
type NoteDto = components["schemas"]["NoteDto"];

interface NoteBookListProps {
    children?: NotebookDto[];
    targetBook: NotebookDto | null;
    onClickItem: (notebook: NotebookDto) => void;
    targetNoteMap: Map<number, NoteDto | null>;
    initTargetNote: (notebookIdList: number[]) => void;
    onClickMenu: (e: any) => void;
}
const NoteBookList: React.FC<NoteBookListProps> = React.memo(({ children, targetBook, onClickItem, targetNoteMap, initTargetNote, onClickMenu }) => {

    const [notebookList, setNotebookList] = useState<NotebookDto[]>([]);
    const [isLoding, setIsLoading] = useState<boolean>(true);


    useEffect(() => {

        if (children && children.length !== 0) {
            setNotebookList(children);
            setIsLoading(false);
            return;
        }

        async function getNotebookList() {
            const result = await get('/books', {});
            if (result.resultCode === "fail") {
                throw new Error("fail to get notebook list");
            }
            setNotebookList(result.body.tree);
            setIsLoading(false);
            initTargetNote(result.body.idList);
            if (targetBook === null) {
                onClickItem(result.body.tree[0]);
            }
        }

        getNotebookList();
    }, []);

    if (isLoding) {
        return <div>Loading...</div>;
    }

    return (
        <>
            <ul className="menu menu-dropdown p-0">
                {notebookList.map((notebook: NotebookDto) => (
                    // notebook.children.length === 0 ?
                    notebook.children && notebook.children.length !== 0 ?
                        <GroupItem key={notebook.id} notebook={notebook} targetBook={targetBook} onClickItem={onClickItem}
                            targetNoteMap={targetNoteMap} initTargetNote={initTargetNote} onClickMenu={onClickMenu} /> :
                        <BookItem key={notebook.id} notebook={notebook} targetBook={targetBook}
                            onClickMenu={onClickMenu} onClickItem={onClickItem} targetNoteMap={targetNoteMap} />
                ))}
            </ul>
        </>
    );
});

function BookItem({ notebook, targetBook, onClickItem, targetNoteMap, onClickMenu }: {
    notebook: NotebookDto,
    targetBook: NotebookDto | null,
    onClickItem: (notebook: NotebookDto) => void,
    targetNoteMap: Map<number, NoteDto | null>
    onClickMenu: (e: any) => void;
}) {
    const baseClass = 'hover:bg-gray-300';
    const itemClass = notebook.id == targetBook?.id ? ' bg-gray-500 text-white' : ' text-black';
    const resultClass = baseClass + itemClass;
    return (
        <li className={resultClass}>
            <span data-id={notebook.id}
                onClick={() => {
                    if (notebook.id) {
                        onClickItem(notebook)
                    }
                }}>{notebook.title}</span>
            <span className="absolute right-[0.5rem] top-[0.2rem] text-[1rem] font-bold hover:bg-gray-100 hover:text-black rounded-none hover:rounded-none p-[5px]" onClick={onClickMenu}>+</span>
        </li>
    );
}

function GroupItem({ notebook, targetBook, onClickItem, targetNoteMap, initTargetNote, onClickMenu }: {
    notebook: NotebookDto,
    targetBook: NotebookDto | null,
    onClickItem: (notebook: NotebookDto) => void,
    targetNoteMap: Map<number, NoteDto | null>,
    initTargetNote: (notebookIdList: number[]) => void
    onClickMenu: (e: any) => void;
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

    let itemClass = "menu-dropdown-toggle relative hover:bg-gray-300";
    itemClass += isOpen ? ' menu-dropdown-show' : '';
    itemClass += notebook.id == targetBook?.id ? ' bg-gray-500 text-white' : '';
    let filterClass = "filter border-2 absolute w-[10%] p-[12px] right-[0.3rem] cursor-copy";
    let menuClass = "absolute border-2 w-[10%] h-[50%] p-[12px] right-[2.5rem] cursor-copy";
    // let menuClass = "absolute w-[15px] h-[50%] p-[12px] right-[2.5rem] cursor-copy";
    filterClass += notebook.id == targetBook?.id ? ' bg-indigo-300' : '';

    return (
        <li>
            <span className={itemClass} data-id={notebook.id} onClick={() => { onClickItem(notebook) }}>
                {notebook.title} <span className="absolute right-[2.5rem] text-[1rem] font-bold hover:bg-gray-100 hover:text-black p-[5px]" onClick={onClickMenu}>+</span><span className={filterClass} onClick={handleToggle}></span>
            </span>
            <NoteBookList targetBook={targetBook} children={notebook.children} onClickItem={onClickItem}
                onClickMenu={onClickMenu} targetNoteMap={targetNoteMap} initTargetNote={initTargetNote} />
        </li>
    );
}
export { NoteBookList };