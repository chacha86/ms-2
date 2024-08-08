import { useEffect, useRef } from "react";

const NoteMenu = ({ x, y }: { x: number, y: number }) => {
    // const menuRef = useRef<HTMLDivElement>(null);
    console.log('note menu');
    console.log(x);
    console.log(y);
    useEffect(() => {

        // document.body.addEventListener('click', (e) => {
        //     if (menuRef.current && !menuRef.current.contains(e.target as Node)) {
        //         menuRef.current.classList.add('hidden');
        //     }
        // });
    }, [x, y]);
    return (
        <>
            <div className="modal-box w-[200px] z-[999] absolute" style={{left:x, top:y}}>
                <ul className="flex flex-col items-center gap-2">
                    <li className="w-[100%]"><button className="btn btn-xs w-[100%]">추가</button></li>
                    <li className="w-[100%]"><button className="btn btn-xs w-[100%]">수정</button></li>
                    <li className="w-[100%]"><button className="btn btn-xs w-[100%]">삭제</button></li>
                </ul>
            </div>
            <form method="dialog" className="modal-backdrop">
                <button>close</button>
            </form>
            {/* <div className={`absolute w-[200px] bg-gray-100 p-[5px]`} style={{ left: x, top: y }}>
            </div> */}
            {/* <button className="btn w-[100%]" onClick={() => {
                    menuRef.current?.classList.remove('hidden');
                }}>노트 메뉴</button> */}
        </>
    )
}

export default NoteMenu;