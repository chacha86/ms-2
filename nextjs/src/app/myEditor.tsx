'use client';
import dynamic from "next/dynamic";
import '@toast-ui/editor/dist/toastui-editor.css';
import React, { useState, useEffect, useRef } from 'react';
import Loading from "@/global/Loading";
import { EditorProps } from "@toast-ui/react-editor";

const Editor = dynamic(
    () => import('@toast-ui/react-editor').then(mod => mod.Editor),
    {
        ssr: false,  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
        loading: () => <Loading />
    }
);
const ForwardedEditor = React.forwardRef((props, ref) => (
    <Editor ref={ref} />
));

export default function MyEditor({ contentParam }: { contentParam: string }) {
    const editorRef = useRef(null);
    const [content, setContent] = useState<string>(contentParam);
    const [isEditorLoaded, setIsEditorLoaded] = useState(false);
    useEffect(() => {
        // 컴포넌트가 로드되었을 때 상태를 업데이트하여 리렌더링을 트리거
        const loadEditor = async () => {
            const mod = await import('@toast-ui/react-editor');
            if (mod && mod.Editor) {
                setIsEditorLoaded(true);
            }
        };

        loadEditor();
    }, []);

    useEffect(() => {
        if (isEditorLoaded && editorRef.current) {
            // Editor 컴포넌트가 렌더링되고 참조가 설정된 후 실행되는 코드
            console.log(editorRef.current); // Editor 인스턴스 접근
            // 필요한 초기화 또는 설정 작업 수행 가능
        }
    }, [isEditorLoaded]);
    return (
        <div>
            {isEditorLoaded ?
                <ForwardedEditor
                    ref={editorRef}
                    initialValue=""
                    previewStyle="vertical"
                    height="600px"
                    initialEditType="markdown"
                    useCommandShortcut={true}
                />
                : <Loading />}
        </div>
    );
}