'use client';
import React, {useCallback, useEffect, useRef, useState} from 'react';
import {getAssumedSourceType} from "next/dist/build/webpack/loaders/next-flight-loader";
import {useFlightStream} from "next/dist/server/app-render/use-flight-response";

declare global {
    interface Window {
        YT: typeof YT;
        onYouTubeIframeAPIReady: () => void;
    }
}

const YouTubePlayer: React.FC = () => {

    // const onPlayerReadyRef = useRef((event: { target: any }) => {
    //     event.target.playVideo();
    // });
    //
    // const onPlayerStateChangeRef = useRef((event: { data: number }) => {
    //     if (event.data === window.YT.PlayerState.PLAYING && !done) {
    //         setTimeout(() => player.stopVideo(), 6000);
    //         setDone(true);
    //     }
    // });

    const videoRef = useRef<HTMLIFrameElement | null>(null);
    const playerRef = useRef<YT.Player | null>(null);

    useEffect(() => {
        // YT API 스크립트 로드 여부 확인 및 로드
        if (!window.YT) {
            const tag = document.createElement('script');
            tag.src = "https://www.youtube.com/iframe_api";
            document.body.appendChild(tag);

            // YT API 준비됨을 확인하는 핸들러 설정
            window.onYouTubeIframeAPIReady = () => {
                console.log('YouTube Iframe API is ready.');
                if (videoRef.current) {
                    playerRef.current = new window.YT.Player(videoRef.current, {
                        videoId: 'crjrxVDPJ0A',
                        playerVars: {
                            autoplay: 1,  // 자동 재생 활성화
                            mute: 1       // 음소거 활성화
                        },
                        events: {
                            onReady: (event) => {
                                console.log('Player is ready.');
                                console.log(event.target);
                                event.target.playVideo();
                            }
                        }
                    });
                }
            };

            console.log(playerRef.current);
        }
    }, []);

    return (
        <>
            <div ref={videoRef}>
                <iframe
                    width="560"
                    height="315"
                    src="https://www.youtube.com/watch?v=4Wju6O-i9m8"
                    frameBorder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowFullScreen
                    title="YouTube video player"
                ></iframe>
            </div>
            <button onClick={() => playerRef.current?.playVideo()}>Play</button>
        </>
    );
};

export default YouTubePlayer;