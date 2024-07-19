import { Editor, } from "@toast-ui/react-editor"
import React from "react";

// interface EditorWrapperProps extends EditorProps {
//     forwardedRef: React.ForwardedRef<Editor>
// }
// function Editorwrapper({ forwardedref, ...props }: any) {
//     return <editor ref={forwardedref} {...props} />
// }
const EditorWrapper = ({aaa, ...props}:{aaa:any}) => {
    return <Editor ref={aaa} {...props} />
}

export default EditorWrapper;