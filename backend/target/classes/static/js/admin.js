async function loadPendingDoctors(){

    const token = localStorage.getItem("token");

    const response = await fetch(
        "http://localhost:8080/api/users",
        {
            headers:{
                "Authorization":"Bearer " + token
            }
        }
    );

    const data = await response.json();

    let html = "";

    data.forEach(user => {

        if(
            user.role === "ROLE_DOCTOR" &&
            user.approvalStatus === "PENDING"
        ){

            html += `

                <div class="card">

                    <h3>${user.name}</h3>

                    <p>${user.email}</p>

                    <p>${user.phone}</p>

                    <p>Status: ${user.approvalStatus}</p>

                    <button
                        onclick="approveDoctor(${user.id})">

                        Approve

                    </button>

                </div>

            `;
        }
    });

    document.getElementById("pendingDoctors")
        .innerHTML = html;
}



async function approveDoctor(id){

    const token = localStorage.getItem("token");

    await fetch(
        `http://localhost:8080/api/users/approve/${id}`,
        {
            method:"PUT",

            headers:{
                "Authorization":"Bearer " + token
            }
        }
    );

    alert("Doctor Approved");

    loadPendingDoctors();
}



async function loadUsers(){

    const token = localStorage.getItem("token");

    const response = await fetch(
        "http://localhost:8080/api/users",
        {
            headers:{
                "Authorization":"Bearer " + token
            }
        }
    );

    const data = await response.json();

    let html = "";

    data.forEach(user => {

        html += `

            <div class="card">

                <h3>${user.name}</h3>

                <p>Email: ${user.email}</p>

                <p>Phone: ${user.phone}</p>

                <p>Role: ${user.role}</p>

                <p>Status: ${user.approvalStatus}</p>

            </div>

        `;
    });

    document.getElementById("users")
        .innerHTML = html;
}



async function loadAppointments(){

    const token = localStorage.getItem("token");

    const response = await fetch(
        "http://localhost:8080/api/appointments",
        {
            headers:{
                "Authorization":"Bearer " + token
            }
        }
    );

    const data = await response.json();

    let html = "";

    data.forEach(a => {

        html += `

            <div class="card">

                <h3>Appointment ID: ${a.id}</h3>

                <p>Date: ${a.appointmentDate}</p>

                <p>Time: ${a.appointmentTime}</p>

                <p>Status: ${a.status}</p>

                <p>Token: ${a.tokenNumber}</p>

            </div>

        `;
    });

    document.getElementById("appointments")
        .innerHTML = html;
}



function logout(){

    localStorage.clear();

    window.location.href = "login.html";
}