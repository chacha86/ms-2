'use client';
import dynamic from "next/dynamic";
import '@toast-ui/editor/dist/toastui-editor.css';
import React, { useState, useEffect, useRef } from 'react';
import Loading from "@/global/Loading";
import Editor from "quill/core/editor";
import { EditorProps } from "@toast-ui/react-editor";

export default function MyEditor({ checkEditorLoading }: { checkEditorLoading: () => void }) {
    const editorRef = useRef<EditorProps | null>(null);
    const [isLoading, setIsLoading] = useState<boolean>(true);
    const Editor = dynamic(
        () => import('@toast-ui/react-editor').then(mod => mod.Editor),
        { ssr: false }  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
    );

    useEffect(() => {

    }, [editorRef.current]);

    return (
        <div>
            <Editor
                ref={editorRef}
                onLoad={checkEditorLoading}
                // onLoad={()=>{console.log("sdfsdfs");editorRef.current}}
                initialValue="hello react editor world!"
                previewStyle="vertical"
                height="600px"
                initialEditType="markdown"
                useCommandShortcut={true}
            />
        </div>
    );
}