async function register(){

    const name =
        document.getElementById("name").value;

    const email =
        document.getElementById("email").value;

    const phone =
        document.getElementById("phone").value;

    const password =
        document.getElementById("password").value;

    const role =
        document.getElementById("role").value;

    try{

        const response = await fetch(
            "http://localhost:8080/api/users/register",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify({

                    name:name,
                    email:email,
                    phone:phone,
                    password:password,
                    role:role

                })

            }
        );



        // RESPONSE HANDLE

        if(response.ok){

            const data = await response.json();

            console.log(data);

            alert("Registration Successful");

            window.location.href =
                "login.html";
        }

        else{

            const errorText =
                await response.text();

            console.log(errorText);

            alert(errorText);
        }

    }

    catch(error){

        console.log(error);

        alert("Server Error");
    }
}