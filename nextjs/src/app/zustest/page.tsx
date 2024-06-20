'use client';
import {useState} from "react";
import Link from "next/link";
import {create} from "zustand";
import {persist} from 'zustand/middleware';

interface State {
    count: number;
    increase: () => void;
    decrease: () => void;
}

const useStore = create(
    persist<State>(
        (set) => ({
            count: 0,
            increase: () => set((state) => ({count: state.count + 1})),
            decrease: () => set((state) => ({count: state.count - 1})),
        }),
        {
            name: 'count-store',
        }
    )
);

export default function Test() {

    const count = useStore((state) => state.count);
    const increase = useStore((state) => state.increase);
    const decrease = useStore((state) => state.decrease);

    return (
        <>
            <div>
                <Link href="/">main</Link>
            </div>
            <div style={{textAlign: 'center', marginTop: '50px'}}>
                <h1>{count}</h1>
                <button onClick={increase} style={{marginRight: '10px'}}>Increase</button>
                <button onClick={decrease}>Decrease</button>
            </div>
        </>
    );
}
