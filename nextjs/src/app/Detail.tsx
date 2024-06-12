import MyEditor from "@/app/myEditor";
import React, {useEffect, useState} from "react";
import Loading from "@/global/Loading";

export default function Detail() {
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const checkEditorLoading = () => {
        setIsLoading(false);
    }

    useEffect(() => {

    }, []);

    return (
        <>
            <form method="post" id="updateForm">
                <div>
                    <input type="hidden" name="id"/>
                </div>
                <div>
                    <input type="text" name="title"/>
                </div>
                <input type="hidden" name="content" id="editor-body"/>
                <MyEditor checkEditorLoading={checkEditorLoading}/>
                <div>
                    <input type="button" value="수정" className="postActionBtn"/>
                </div>
            </form>
            <form id="deleteForm">
                <input type="button" value="삭제" className="postActionBtn"/>
            </form>
            <ul className="flex gap-5">
                <li>
                    <form>
                        <input type="submit" className="btn"/>
                    </form>
                </li>
            </ul>
            <form>
                <input
                    type="text"
                    name="name"
                    className="input input-bordered"
                    placeholder="태그 추가"
                />
                <input type="submit" className="postActionBtn btn" value="추가"/>
            </form>
        </>
    )
}