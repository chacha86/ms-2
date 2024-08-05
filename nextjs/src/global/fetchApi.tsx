const baseUrl = 'http://localhost:8999/api/v1';

function fetchApiJson(url: string, method: string, params: Record<string, string>) {
    return fetch(`${baseUrl}${url}`, {
        method: method,
        credentials: 'include',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(params),
    })
        .then((response) => response.json())
        .then((data) => data)
        .catch((error) => console.error(error));

}

async function fetchApiParam(url: string, params: Record<string, string>) {
    const urlParams = new URLSearchParams(params);
    const urlWithParams = `${baseUrl}${url}?${urlParams.toString()}`;
    console.log(urlWithParams);
    return fetch(urlWithParams, {
        method: 'GET',
        credentials: 'include',
    })
        .then((response) => response.json())
        .then((data) => data)
        .catch((error) => console.error(error));
}

async function get(url: string, params: Record<string, string>) {
    return await fetchApiParam(url, params);
}

async function post(url: string, params: Record<string, string>) {
    return await fetchApiJson(url, 'POST', params);
}

export { get, post }