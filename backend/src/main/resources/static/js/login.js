async function login(){

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    try{

        const response = await fetch(
            "http://localhost:8080/api/users/login",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify({

                    email:email,
                    password:password

                })

            }
        );

        const data = await response.json();

        console.log(data);

        if(data.token){

            localStorage.setItem(
                "token",
                data.token
            );

            localStorage.setItem(
                "role",
                data.role
            );

            localStorage.setItem(
                "userId",
                data.userId
            );

            alert("Login Successful");



            // ROLE BASED REDIRECT

            if(data.role === "ROLE_ADMIN"){

                window.location.href =
                    "admin-dashboard.html";
            }

            else if(data.role === "ROLE_DOCTOR"){

                window.location.href =
                    "doctor-dashboard.html";
            }

            else{

                window.location.href =
                    "patient-dashboard.html";
            }

        }

        else{

            alert("Invalid Login");
        }

    }

    catch(error){

        console.log(error);

        alert("Server Error");
    }
}



function logout(){

    localStorage.clear();

    window.location.href =
        "login.html";
}