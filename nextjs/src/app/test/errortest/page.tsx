'use client';
import {useEffect} from "react";

export default function ErrorTest() {

    useEffect(() => {
        fetch('http://localhost:8999/aaa').then((res) => {
            console.log(res)
            if(!res.ok) {
                console.log("error");
                throw new Error();
            }
        }).catch((err) => {throw new Error();})
    }, []);

    return (
        <div>
            <h2>hihi!</h2>
        </div>
    )
}