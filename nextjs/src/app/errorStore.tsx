import {persist} from "zustand/middleware";
import {create} from "zustand";

interface Error {
    error: boolean;
    setError: (flag:boolean) => void;
}

const errorStore = create<Error>(
    (set) => ({
        error: false,
        setError: (flag) => set((state) => ({error: flag})),
    }),
);

export default errorStore;