import MyEditor from "@/app/myEditor";
import React, { useEffect, useState } from "react";
import Loading from "@/global/Loading";

export default function Detail() {
    const [isEditorLoading, setIsEditorLoading] = useState<boolean>(true);
    const [isLoading, setIsLoading] = useState<boolean>(true);

    const checkEditorLoading = () => {
        console.log("checkEditorLoading");
        // setIsLoading(false);
        setIsEditorLoading(false);
    }

    useEffect(() => {
        setIsLoading(false);
        console.log(isEditorLoading);
        // setIsEditorLoading(true);
    }, [isEditorLoading]);

    if (isLoading) {
        console.log("sdfsdf");
        return <Loading />
    }

    return (
        <form method="post" id="updateForm">
            <div>
                <input type="hidden" name="id" />
            </div>
            <div>
                <input type="text" name="title" value="test" />
                {/* 제목 */}
            </div>
            <input type="hidden" name="content" id="editor-body" />
            <div className="h-[600px]">
                <MyEditor checkEditorLoading={checkEditorLoading} />
            </div>
            <EditorAfter />
        </form >
    )
}

const EditorAfter = () => {

    return (
        <div>
            <div>
                <input type="button" value="수정" className="postActionBtn" />
            </div>
            <div>
                <input type="button" value="삭제" className="postActionBtn" />
            </div>

            <ul className="flex gap-5">
                <li>
                    <form>
                        <input type="submit" className="btn" />
                    </form>
                </li>
            </ul>
            <form>
                {/*<input*/}
                {/*    type="text"*/}
                {/*    name="name"*/}
                {/*    className="input input-bordered"*/}
                {/*    placeholder="태그 추가"*/}
                {/*/>*/}
                <input type="submit" className="postActionBtn btn" value="추가" />
            </form>
        </div>
    );
}