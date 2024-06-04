'use client';
import {useEffect, useRef, useState} from 'react';
import {Client} from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export function Test() {
    const client = new Client({
        webSocketFactory: () => new SockJS('http://localhost:8999/ws'),
        onConnect: () => {
            console.log('Connected');
            client.subscribe('/topic/test', message =>
                console.log(`Received: ${message.body}`)
            );
            client.publish({destination: '/app/sendMessage', body: '{"message" : "First Message}"'});
        },
    });
    client.activate();
}

export default function TestPage() {
    Test();
    useEffect(() => {
        // 데이터 가져오기를 시뮬레이션하기 위해 2초 지연
        const timer = setTimeout(() => {
            // setData("Some fetched data");
            console.log('Some fetched data');
        }, 2000);

        return () => clearTimeout(timer);
    }, []);

    // if (!data) {
    //     return <div>Loading...</div>;
    // }


    return (
        <div>
            <h1>Test Page</h1>
        </div>
    );
}