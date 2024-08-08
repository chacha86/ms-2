'use client';
import {useEffect} from "react";

export default function Page() {
    const originalConsoleError = console.error;

    console.error = function(message, ...optionalParams) {
        if (typeof message === 'string' && message.includes('GET http://localhost:3000/sdfsdf 404 (Not Found)')) {
            return;
        }
        originalConsoleError.apply(console, [message, ...optionalParams]);
    };
    useEffect(() => {
        console.log("sdfsdfsdf");
        console.error('xcvxcvxcv');

        fetch('sdfsdf').then((res) => {
            console.log(res);
        }).catch(() => {});
    }, []);
    return (
        <div>
            <h2>sdfsdfsdf</h2>
            <button>Try again</button>
        </div>
    )
}