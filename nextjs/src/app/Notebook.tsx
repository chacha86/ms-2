import React, { useEffect, useState } from "react";
import { get } from "@/global/fetchApi";
import { paths, components } from "@/lib/api/v1/schema";

type NotebookDto = components["schemas"]["NotebookDto"];
type NoteDto = components["schemas"]["NoteDto"];

interface NoteBookListProps {
    children?: NotebookDto[];
    targetBook: NotebookDto | null;
    onClickItem: (notebook: NotebookDto) => void;
    targetNoteMap: Map<number, NoteDto | null>;
    initTargetNote: (notebookIdList: number[]) => void;
}
const NoteBookList: React.FC<NoteBookListProps> = React.memo(({ children, targetBook, onClickItem, targetNoteMap, initTargetNote }) => {

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
        <ul className="menu menu-dropdown p-0">
            {notebookList.map((notebook: NotebookDto) => (
                // notebook.children.length === 0 ?
                notebook.children && notebook.children.length !== 0 ?
                    <GroupItem key={notebook.id} notebook={notebook} targetBook={targetBook} onClickItem={onClickItem} targetNoteMap={targetNoteMap} initTargetNote={initTargetNote}/> :
                    <BookItem key={notebook.id} notebook={notebook} targetBook={targetBook} onClickItem={onClickItem} targetNoteMap={targetNoteMap}/>
            ))}
        </ul>
    );
});

function BookItem({ notebook, targetBook, onClickItem, targetNoteMap }: {
    notebook: NotebookDto,
    targetBook: NotebookDto | null,
    onClickItem: (notebook: NotebookDto) => void,
    targetNoteMap: Map<number, NoteDto | null>
}) {
    const baseClass = 'hover:bg-gray-300';
    const itemClass = notebook.id == targetBook?.id ? ' bg-gray-500 text-white' : ' text-black';
    const resultClass = baseClass + itemClass;
    return (
        <li>
            <a className={resultClass} data-id={notebook.id}
                onClick={() => {
                    if (notebook.id) {
                        onClickItem(notebook)
                    }
                }}>{notebook.title}</a>
        </li>
    );
}

function GroupItem({ notebook, targetBook, onClickItem, targetNoteMap, initTargetNote }: {
    notebook: NotebookDto,
    targetBook: NotebookDto | null,
    onClickItem: (notebook: NotebookDto) => void,
    targetNoteMap: Map<number, NoteDto | null>,
    initTargetNote: (notebookIdList: number[]) => void
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
    let filterClass = "filter border-2 absolute w-[15px] h-[50%] p-[12px] right-[0.3rem] cursor-copy";
    filterClass += notebook.id == targetBook?.id ? ' bg-indigo-300' : '';

    return (
        <li>
            <span className={itemClass} data-id={notebook.id} onClick={() => { onClickItem(notebook) }}>
                {notebook.title} <span className={filterClass} onClick={handleToggle}></span>
            </span>
            <NoteBookList targetBook={targetBook} children={notebook.children} onClickItem={onClickItem} targetNoteMap={targetNoteMap} initTargetNote={initTargetNote} />
        </li>
    );
}
export { NoteBookList };