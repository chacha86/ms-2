'use client';
import '@toast-ui/editor/dist/toastui-editor.css';
import {Editor} from '@toast-ui/react-editor';
import React, {useState, useEffect} from 'react';

export default function MyEditor() {

    // const Editor = dynamic(
    //     () => import('@toast-ui/react-editor').then(mod => mod.Editor),
    //     { ssr: false }  // 이 옵션은 서버 사이드 렌더링을 비활성화합니다.
    // );

    return (
        <Editor
            initialValue="hello react editor world!"
            previewStyle="vertical"
            height="600px"
            initialEditType="markdown"
            useCommandShortcut={true}
        />
    );
}