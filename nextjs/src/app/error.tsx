'use client'

import {useRouter} from "next/navigation";

export default function Error({
                                  error,
                                  reset,
                              }: {
    error: Error & { digest?: string }
    reset: () => void
}) {
    const router = useRouter()
    const test = () => {
        router.push("/login")
    }
    return (
        <div>
            <h2>Something went wrong!</h2>
            <button onClick={() => test()}>Try again</button>
        </div>
    )
}