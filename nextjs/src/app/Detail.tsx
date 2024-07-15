import MyEditor from "@/app/myEditor";
import React, { useEffect, useState } from "react";
import Loading from "@/global/Loading";
import { paths, components } from "@/lib/api/v1/schema";
import createClient from "openapi-fetch";

type NoteDto = components["schemas"]["NoteDto"];

export default function Detail({targetNoteId}:{targetNoteId: number}) {
    console.log("Detail");
    console.log("targetNoteId: ", targetNoteId);
    // const [isEditorLoading, setIsEditorLoading] = useState<boolean>(true);
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const [note, setNote] = useState<NoteDto | null>(null);

    // const checkEditorLoading = () => {
    //     console.log("checkEditorLoading");
    //     // setIsLoading(false);
    //     setIsEditorLoading(false);
    // }

    useEffect(() => {
        const test = async () => {
            const client = createClient<paths>({ baseUrl: "http://localhost:8999" });
            const { data, error } = await client.GET("/api/v1/notes/{id}", {
                params: {
                    path: {
                        id: targetNoteId
                    },
                },
                credentials: "include"
            });
            if (error) {
                console.log("error: ", error);
                return;
            }
            setNote(data);
            console.log("note: ", data);
            console.log("note.content: ", data.content);
            setIsLoading(false);
        }
        test();
        // setIsEditorLoading(true);
    }, [targetNoteId]);

    return (
        <form method="post" id="updateForm">
            <div>
                <input type="hidden" name="id" />
            </div>
            <div>
                <input type="text" name="title" value={note?.title} />
                {/* 제목 */}
            </div>
            <input type="hidden" name="content" id="editor-body" />
            <div className="h-[600px]">
                {/* <MyEditor checkEditorLoading={checkEditorLoading} /> */}
                <MyEditor content={note?.content} />
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