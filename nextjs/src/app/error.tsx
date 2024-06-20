'use client'

import {useRouter} from "next/navigation";

export default function Error({
                                  error,
                                  reset,
                              }: {
    error: Error & { digest?: string }
    reset: () => void
}) {

    return (
        <div>
            <h2>Something went wrong11111!</h2>
            <button>Try again</button>
        </div>
    )
}