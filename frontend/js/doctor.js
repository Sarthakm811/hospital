async function updateAvailability() {

    try {

        const token = localStorage.getItem("token");

        const availability =
            document.getElementById("availability").value;

        const doctorId =
            localStorage.getItem("userId");

        const response = await fetch(
            `http://localhost:8080/api/doctors/update/${doctorId}?availableTime=${availability}`,
            {
                method: "PUT",

                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        if (!response.ok) {
            throw new Error("Failed to update availability");
        }

        alert("Availability Updated Successfully");

    }
    catch(error){

        console.log(error);

        alert("Error updating availability");
    }
}



async function loadDoctorAppointments() {

    try {

        const token =
            localStorage.getItem("token");

        const userId =
            localStorage.getItem("userId");

        // GET DOCTOR DETAILS

        const doctorResponse = await fetch(
            `http://localhost:8080/api/doctors/user/${userId}`,
            {
                method: "GET",

                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        const doctor =
            await doctorResponse.json();

        // GET APPOINTMENTS

        const response = await fetch(
            `http://localhost:8080/api/appointments/doctor/${doctor.id}`,
            {
                method: "GET",

                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        const data =
            await response.json();

        console.log(data);

        // =========================
        // DASHBOARD COUNTS
        // =========================

        const today =
            new Date().toISOString().split("T")[0];

        let todayCount = 0;
        let pendingCount = 0;
        let completedCount = 0;

        data.forEach(a => {

            if(a.appointmentDate === today){
                todayCount++;
            }

            if(
                a.status &&
                a.status.toUpperCase() === "PENDING"
            ){
                pendingCount++;
            }

            if(
                a.status &&
                a.status.toUpperCase() === "COMPLETED"
            ){
                completedCount++;
            }

        });

        const todayElement =
            document.getElementById("todayCount");

        const pendingElement =
            document.getElementById("pendingCount");

        const completedElement =
            document.getElementById("completedCount");

        if(todayElement){
            todayElement.innerText = todayCount;
        }

        if(pendingElement){
            pendingElement.innerText = pendingCount;
        }

        if(completedElement){
            completedElement.innerText = completedCount;
        }

        // =========================
        // APPOINTMENT CARDS
        // =========================

        let html = "";

        data.forEach(a => {

            html += `

                <div class="card">

                    <h3>
                        Appointment ID: ${a.id}
                    </h3>

                    <p>
                        Date: ${a.appointmentDate}
                    </p>

                    <p>
                        Time: ${a.appointmentTime}
                    </p>

                    <p>
                        Status: ${a.status}
                    </p>

                    ${
                        a.status !== "COMPLETED"
                        ?
                        `<button class="action-btn"
                            onclick="completeAppointment(${a.id})">
                            Mark Completed
                        </button>`
                        :
                        ""
                    }

                </div>

            `;
        });

        document.getElementById("appointments")
            .innerHTML = html;

    }
    catch(error){

        console.log(error);

        alert("Error loading appointments");
    }
}



async function completeAppointment(id) {

    try {

        const token =
            localStorage.getItem("token");

        await fetch(
            `http://localhost:8080/api/doctors/complete/${id}`,
            {
                method: "PUT",

                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        );

        alert("Appointment Completed");

        loadDoctorAppointments();

    }
    catch(error){

        console.log(error);

        alert("Error completing appointment");
    }
}



// AUTO LOAD WHEN PAGE OPENS

window.onload = function(){

    loadDoctorAppointments();

};