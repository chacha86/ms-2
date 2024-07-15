'use client';
// import dynamic from "next/dynamic";
import {Editor} from "@toast-ui/react-editor";
import '@toast-ui/editor/dist/toastui-editor.css';
import React, {useState, useEffect, useRef} from 'react';
import Loading from "@/global/Loading";

// const Editor = dynamic(
//     () => import('@toast-ui/react-editor').then(mod => mod.Editor),
//     {
//         ssr: false,  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
//         loading: () => <Loading />
//     }
// );
export default function MyEditor({content}: { content: string | undefined }) {
    const editorRef = useRef<Editor | null>(null);
    console.log("MyEditor");
    console.log("content: ", content);
    useEffect(() => {
        editorRef?.current?.getInstance().setMarkdown(content);
    }, [content]);
    return (
        <div>
            <Editor
                ref={editorRef}
                initialValue=""
                previewStyle="vertical"
                height="600px"
                initialEditType="markdown"
                useCommandShortcut={true}
            />
        </div>
    );
}