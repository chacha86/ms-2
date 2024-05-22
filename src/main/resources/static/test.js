getFetch = async (url, params={}) => {
    const queryString = new URLSearchParams(params).toString();
    url = url + '?' + queryString;
    console.log(url);
    return await fetch(url)
        .then((response) => response.json())
        .then((data) => {return data})
        .catch(error => console.error('Error:', error));
}

postFetch = async (url, data) => {
    return await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then((data) => {return data})
        .catch(error => console.error('Error:', error));
}