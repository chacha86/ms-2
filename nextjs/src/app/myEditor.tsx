'use client';
import dynamic from "next/dynamic";
import '@toast-ui/editor/dist/toastui-editor.css';
import React, {useState, useEffect} from 'react';
import Loading from "@/global/Loading";

export default function MyEditor({checkEditorLoading}: { checkEditorLoading: () => void }) {
    const [editorRef, setEditorRef] = useState<any>(null);
    const Editor = dynamic(
        () => import('@toast-ui/react-editor').then(mod => mod.Editor),
        {ssr: false}  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
    );

    return (
        <div>
            <Editor
                onLoad={checkEditorLoading}
                initialValue="hello react editor world!"
                previewStyle="vertical"
                height="600px"
                initialEditType="markdown"
                useCommandShortcut={true}
            />
        </div>
    );
}