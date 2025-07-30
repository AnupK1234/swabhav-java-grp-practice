fetch("https://official-joke-api.appspot.com/jokes/random")
    .then(response=>{
        if(!response.ok){
            throw new Error("Failed to Fetch");
        }
        return response.json();
    })
    .then(data=>{
        const setup=data.setup;
        const punchline=data.punchline;
        document.getElementById("setup").textContent=setup;
        document.getElementById("punchline").textContent=punchline;
    })
    .catch(error=>{
        console.log("Error:",error);
    });