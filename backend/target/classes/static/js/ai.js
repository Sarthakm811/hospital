async function checkSymptoms(){

    const symptoms =
        document.getElementById("symptoms").value;

    const token = localStorage.getItem("token");

    const response = await fetch(
    `http://localhost:8080/api/ai/doctors?symptoms=${symptoms}`,
    {

        method: "GET",

        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + localStorage.getItem("token")
        }

    }
);

    const result = await response.text();

    document.getElementById("result").innerHTML =
        "Suggested Specialist: " + result;
    function logout(){

    localStorage.clear();

    window.location.href = "login.html";
}
}