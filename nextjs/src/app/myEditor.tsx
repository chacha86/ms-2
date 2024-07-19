'use client';
// import dynamic from "next/dynamic";
import '@toast-ui/editor/dist/toastui-editor.css';
import React, { useState, useEffect, useRef, forwardRef } from 'react';
import Loading from "@/global/Loading";
import dynamic, { DynamicOptions } from 'next/dynamic';
import { Editor, EditorProps } from '@toast-ui/react-editor';

const EditorWrapper = dynamic(
    () => import("./EditorWrapper"),
    {
        ssr: false,  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
        loading: () => <Loading />
    }
);

const ForwardedEditor = forwardRef<Editor, EditorProps>((props, ref) => {
    return <EditorWrapper aaa={ref} {...props} />
});

export default function MyEditor({ content }: { content: string | undefined }) {
    const editorRef = useRef<Editor | null>(null);
    useEffect(() => {
        editorRef?.current?.getInstance().setMarkdown(content);
    }, [content]);

    return (
        <div>
            <ForwardedEditor
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