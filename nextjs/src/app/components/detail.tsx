import MyEditor from "@/app/components/note/myEditor";
import React, { useEffect, useState } from "react";
import Loading from "@/global/Loading";
import { paths, components } from "@/lib/api/v1/schema";
import createClient from "openapi-fetch";

type NoteDto = components["schemas"]["NoteDto"];

export default function Detail({ targetNote }: { targetNote: NoteDto | null }) {
    console.log("Detail");
    console.log("targetNote: ", targetNote);
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [note, setNote] = useState<NoteDto | null>(null);

    useEffect(() => {
        console.log("useEffect");
        if (targetNote) {
        console.log("useEffect2");
            setNote(targetNote);
            setIsLoading(false);
            return;
        }
    }, [targetNote]);

    useEffect(() => {
        console.log(targetNote);
        setNote(targetNote);
        setIsLoading(false);
    }, []);
    // const checkEditorLoading = () => {
    //     console.log("checkEditorLoading");
    //     // setIsLoading(false);
    //     setIsEditorLoading(false);
    // }

    if(isLoading) {
        return <Loading />;
    }
    return (
        <div>
            <div>
                <input type="hidden" name="id" />
            </div>
            <div>
                <input className="input mb-[10px] input-bordered" type="text" name="title" value={note?.title} />
                {/* 제목 */}
            </div>
            <input type="hidden" name="content" id="editor-body" />
            <div className="">
                {/* <MyEditor checkEditorLoading={checkEditorLoading} /> */}
                <MyEditor content={note?.content} />
            </div>
            <div>
                {/* <div>
                    <input type="button" value="수정" className="postActionBtn" />
                </div>
                <div>
                    <input type="button" value="삭제" className="postActionBtn" />
                </div> */}

                {/* <ul className="flex gap-5">
                    <li>
                        <input type="submit" className="btn" /> */}

                        {/* <form>
                            <input type="submit" className="btn" />
                        </form> */}
                    {/* </li>
                </ul>

                <input type="submit" className="postActionBtn btn" value="추가" /> */}
                {/* <form>
                    <input
                        type="text"
                        name="name"
                        className="input input-bordered"
                        placeholder="태그 추가"
                    />
                    <input type="submit" className="postActionBtn btn" value="추가" />
                </form> */}

            </div>
        </div>
    )
}
