'use client';
import {create} from "zustand";
import {persist} from "zustand/middleware";

interface LoginUser {
    username: string;
}
interface State {
    loginUser : LoginUser | null;
    setUser: (loginUser:LoginUser | null) => void;
}

export const loginUserStore = create(
    persist<State>(
        (set) => ({
            loginUser: null,
            setUser: (user:LoginUser | null) => set({loginUser: user}),
        }),
        {
            name: 'login-user-store',
        }
    )
);
