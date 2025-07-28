fetch('https://randomuser.me/api/')
      .then(response => {
        if (!response.ok) {
          throw new Error("Failed to fetch");
        }
        return response.json();

      })
      .then(data => {
        const user = data.results[0];
      

        console.log("Full Name:", `${user.name.title} ${user.name.first} ${user.name.last}`);
        console.log("Gender:", user.gender);
        console.log("Email:", user.email);
        console.log("Phone:", user.phone);
        console.log("Cell:", user.cell);

      })
      .catch(error => {
        console.error("Error:", error);
      })
      .finally(() => {

      })
      // resolve reject