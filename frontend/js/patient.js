async function checkSymptoms(){

    const symptoms =
        document.getElementById("symptoms").value;

    const token = localStorage.getItem("token");

    const response = await fetch(
        `http://localhost:8080/api/ai/doctors?symptoms=${symptoms}`,
        {

            method:"GET",

            headers:{
                "Authorization":"Bearer " + token
            }

        }
    );

    const data = await response.json();

    let html = "";

    data.forEach(doctor => {

        html += `
            <div class="card">

                <h2>${doctor.name}</h2>

                <p>
                    Specialization:
                    ${doctor.specialization}
                </p>

                <p>
                    Experience:
                    ${doctor.experience} years
                </p>

                <p>
                    Available Time:
                    ${doctor.availableTime}
                </p>

                <p>
                    Phone:
                    ${doctor.phone}
                </p>

                <p>
                    Consultation Fee:
                    ₹${doctor.consultationFee}
                </p>

            </div>
        `;
    });

    document.getElementById("result").innerHTML =
        html;
}



async function bookAppointment(){

    const token = localStorage.getItem("token");

    const doctorId =
        document.getElementById("doctorId").value;

    const appointmentDate =
        document.getElementById("appointmentDate").value;

    const appointmentTime =
        document.getElementById("appointmentTime").value;

    const response = await fetch(
        "http://localhost:8080/api/appointments/book",
        {

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer " + token
            },

            body:JSON.stringify({

                appointmentDate:appointmentDate,

                appointmentTime:appointmentTime,

                patient:{
                    id:1
                },

                doctor:{
                    id:doctorId
                }

            })

        }
    );

    const data = await response.json();

    alert("Appointment Booked Successfully");
}



async function loadAppointments(){

    try{

        const token =
            localStorage.getItem("token");

        const response = await fetch(
            "http://localhost:8080/api/appointments/patient/1",
            {

                method:"GET",

                headers:{
                    "Authorization":"Bearer " + token
                }

            }
        );

        const data = await response.json();

        console.log(data);

        let html = "";

        data.forEach(a => {

            html += `

                <div class="card">

                    <h3>
                        Appointment ID:
                        ${a.id}
                    </h3>

                    <p>
                        Date:
                        ${a.appointmentDate}
                    </p>

                    <p>
                        Time:
                        ${a.appointmentTime}
                    </p>

                    <p>
                        Status:
                        ${a.status}
                    </p>

                    <p>
                        Token:
                        ${a.tokenNumber}
                    </p>

                    <button
                        onclick="cancelAppointment(${a.id})">

                        Cancel Appointment

                    </button>

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

async function loadDoctors() {

    const token =
        localStorage.getItem("token");

    const response = await fetch(
        "http://localhost:8080/api/doctors",
        {

            method:"GET",

            headers:{
                "Authorization":"Bearer " + token
            }

        }
    );

    const doctors =
        await response.json();

    const dropdown =
        document.getElementById("doctorId");

    dropdown.innerHTML =
        `<option value="">
            Select Doctor
        </option>`;

    doctors.forEach(d => {

        dropdown.innerHTML += `

            <option value="${d.id}">

                ${d.name}
                (${d.specialization})

            </option>

        `;
    });
}
async function loadAllDoctors(){

    const token =
        localStorage.getItem("token");

    try{

        const response = await fetch(
            "http://localhost:8080/api/doctors",
            {

                method:"GET",

                headers:{
                    "Authorization":
                    "Bearer " + token
                }

            }
        );

        const doctors =
            await response.json();

        let html = "";

        doctors.forEach(d => {

            html += `

                <div class="card">

                    <h2>
                         ${d.name}
                    </h2>

                    <p>
                        Specialization:
                        ${d.specialization}
                    </p>

                    <p>
                        Experience:
                        ${d.experience} years
                    </p>

                    <p>
                        Available Time:
                        ${d.availableTime}
                    </p>

                    <p>
                        Phone:
                        ${d.phone}
                    </p>

                    <p>
                        Consultation Fee:
                        ₹${d.consultationFee}
                    </p>

                </div>

            `;
        });

        document.getElementById(
            "doctorList"
        ).innerHTML = html;

    }

    catch(error){

        console.log(error);

        alert("Error loading doctors");
    }
}
async function cancelAppointment(id){

    const token =
        localStorage.getItem("token");

    await fetch(
        "http://localhost:8080/api/appointments/cancel/" + id,
        {

            method:"DELETE",

            headers:{
                "Authorization":"Bearer " + token
            }

        }
    );

    alert("Appointment Cancelled");

    loadAppointments();
    function logout(){

    localStorage.clear();

    window.location.href = "login.html";
}
}
loadDoctors();