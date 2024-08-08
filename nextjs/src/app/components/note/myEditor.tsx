'use client';
import '@toast-ui/editor/dist/toastui-editor.css';
import React, { useState, useEffect, useRef, forwardRef } from 'react';
import Loading from "@/global/Loading";
import dynamic, { DynamicOptions } from 'next/dynamic';
import { Editor, EditorProps } from '@toast-ui/react-editor';

const EditorWrapper = dynamic(
    () => import("./editorWrapper"),
    {
        ssr: false,  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
        loading: () => <Loading />
    }
);

const ForwardedEditor = forwardRef<Editor, EditorProps>((props, ref) => {
    return <EditorWrapper aaa={ref} {...props} />
});

export default function MyEditor({ content }: { content: string | undefined }) {
    console.log('MyEditor');
    const editorRef = useRef<Editor | null>(null);
    // const [re, setRe] = useState<boolean>(false);
    useEffect(() => {
        console.log('sdfsdf11');
        console.log(content);
        editorRef?.current?.getInstance().setMarkdown(content);
        // setRe(true);
    }, [content]);

    // useEffect(() => {
    //     console.log('sdfsdf22');
    //     console.log(editorRef);
    //     if(re) {
    //         setRe(false);
    //     }
    // }, [re]);

    return (
        <div>
            <ForwardedEditor
                ref={editorRef}
                initialValue={content}
                previewStyle="vertical"
                height="80vh"
                initialEditType="markdown"
                useCommandShortcut={true}
            />
        </div>
    );
}