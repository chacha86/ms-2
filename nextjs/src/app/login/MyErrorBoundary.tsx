import React, { ReactNode } from 'react';

interface ErrorBoundaryProps {
    fallback: ReactNode;
    children: ReactNode;
}

interface ErrorBoundaryState {
    hasError: boolean;
}

class ErrorBoundary extends React.Component<ErrorBoundaryProps, ErrorBoundaryState> {
    constructor(props: ErrorBoundaryProps) {
        super(props);
        this.state = { hasError: false };
    }

    static getDerivedStateFromError(error: Error): ErrorBoundaryState {
        // Update state so the next render will show the fallback UI.
        return { hasError: true };
    }

    componentDidCatch(error: Error, info: React.ErrorInfo) {
        // Example "componentStack":
        //   in ComponentThatThrows (created by App)
        //   in ErrorBoundary (created by App)
        //   in div (created by App)
        //   in App
        logErrorToMyService(error, "");
    }

    render() {
        if (this.state.hasError) {
            // You can render any custom fallback UI
            return this.props.fallback;
        }

        return this.props.children;
    }
}

export default ErrorBoundary;

// Example usage of logErrorToMyService function
function logErrorToMyService(error: Error, componentStack: string) {
    // Log the error to an error reporting service
    console.error('Logging error to my service:', error, componentStack);
}
