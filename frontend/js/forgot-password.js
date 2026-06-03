async function sendOtp(){

    const email =
        document.getElementById("email").value;

    try{

        const response = await fetch(
            "http://localhost:8080/api/users/send-otp",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify({

                    email:email

                })

            }
        );

        const data =
            await response.text();

        alert(data);

    }

    catch(error){

        console.log(error);

        alert("Error sending OTP");
    }
}



async function resetPassword(){

    const email =
        document.getElementById("email").value;

    const otp =
        document.getElementById("otp").value;

    const newPassword =
        document.getElementById("newPassword").value;

    try{

        const response = await fetch(
            "http://localhost:8080/api/users/reset-password",
            {

                method:"POST",

                headers:{
                    "Content-Type":"application/json"
                },

                body:JSON.stringify({

                    email:email,
                    otp:otp,
                    newPassword:newPassword

                })

            }
        );

        const data =
            await response.text();

        alert(data);



        if(data.includes("Successfully")){

            window.location.href =
                "login.html";
        }

    }

    catch(error){

        console.log(error);

        alert("Error resetting password");
    }
}