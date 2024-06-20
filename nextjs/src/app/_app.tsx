import App, { AppContext, AppInitialProps, AppProps } from 'next/app'

type AppOwnProps = { example: string }

export default function MyApp({
                                  Component,
                                  pageProps,
                                  example,
                              }: AppProps & AppOwnProps) {
    const originalConsoleError = console.error;
    const originalConsoleWarn = console.warn;
    const originalConsoleLog = console.log;

    // 콘솔 에러 메서드 덮어쓰기
    console.error = function(message, ...optionalParams) {
        return;
    };

    // 콘솔 경고 메서드 덮어쓰기
    console.warn = function(message, ...optionalParams) {
        // 경고 메시지를 무시
        return;
    };

    // 콘솔 로그 메서드 덮어쓰기
    console.log = function(message, ...optionalParams) {
        // 로그 메시지를 무시
        return;
    };
    return (
        <>
            <p>Data: {example}</p>
            <Component {...pageProps} />
        </>
    )
}

MyApp.getInitialProps = async (
    context: AppContext
): Promise<AppOwnProps & AppInitialProps> => {
    const ctx = await App.getInitialProps(context)

    return { ...ctx, example: 'data' }
}