export default function Loading() {
    // You can add any UI inside Loading, including a Skeleton.
    return (
        <div className="flex flex-col items-center justify-center min-h-screen">
            <div className="border-t-4 border-blue-500 border-solid rounded-full w-16 h-16 animate-spin mb-4"></div>
        </div>
    )
}