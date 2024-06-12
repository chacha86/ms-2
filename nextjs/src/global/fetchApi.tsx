const baseUrl = 'http://localhost:8999/api/v1';
async function get(url: string, params: Record<string, string>){
    const queryString = new URLSearchParams(params).toString();
    const urlWithParams = `${baseUrl}${url}?${queryString}`;

    console.log(urlWithParams);
    return await fetch(urlWithParams,
        {credentials: 'include',}
        )
        .then((response) => response.json())
        .then((data) => data)
        .catch((error) => console.error(error));
}

async function post(url: string, params: Record<string, string>) {
    return await fetch(`${baseUrl}${url}`, {
        method: 'POST',
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

export { get, post }