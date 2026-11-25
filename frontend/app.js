const API_URL = "/salas";
const AUTH_URL = "/auth";

const authView = document.getElementById("auth-view");
const roomsView = document.getElementById("rooms-view");
const roomsGrid = document.getElementById("rooms-grid");
const roomDialog = document.getElementById("room-dialog");
const roomForm = document.getElementById("room-form");
const dialogTitle = document.getElementById("dialog-title");
const formMessage = document.getElementById("form-message");
const searchInput = document.getElementById("search-input");
const availabilityFilter = document.getElementById("availability-filter");
const loginForm = document.getElementById("login-form");
const signupForm = document.getElementById("signup-form");
const bookingForm = document.getElementById("booking-form");

let rooms = [];
let bookings = [];
let isSignupMode = false;

async function request(url, options = {}) {
    const response = await fetch(url, {
        ...options,
        credentials: "same-origin",
        headers: {
            ...(options.body ? { "Content-Type": "application/json" } : {}),
            ...options.headers
        }
    });

    if (!response.ok) {
        const body = await response.json().catch(() => ({}));
        const error = new Error(body.detail || body.error || "Não foi possível concluir a operação.");
        error.status = response.status;
        throw error;
    }

    if (response.status === 204) {
        return null;
    }

    return response.json();
}

function showAuth(message = "") {
    authView.classList.remove("hidden");
    roomsView.classList.add("hidden");
    loginForm.reset();
    signupForm.reset();
    document.getElementById("login-message").textContent = message;
    document.getElementById("signup-message").textContent = "";
    setAuthMode(false);
}

function showRooms(user) {
    authView.classList.add("hidden");
    roomsView.classList.remove("hidden");
    document.getElementById("current-user").textContent = user.nome;
    loadRooms();
    loadBookings();
}

function setAuthMode(signup) {
    isSignupMode = signup;
    loginForm.classList.toggle("hidden", signup);
    signupForm.classList.toggle("hidden", !signup);
    document.getElementById("auth-title").textContent = signup ? "Crie sua conta" : "Boas-vindas";
    document.getElementById("auth-description").textContent =
        signup ? "Leva só um instante para começar." : "Entre com seu e-mail para continuar.";
    document.getElementById("auth-switch-label").textContent =
        signup ? "Já tem uma conta?" : "Ainda não tem uma conta?";
    document.getElementById("auth-switch-button").textContent =
        signup ? "Fazer login" : "Criar conta";
}

async function loadCurrentUser() {
    try {
        const user = await request(`${AUTH_URL}/me`);
        showRooms(user);
    } catch (error) {
        showAuth(error.status === 401 ? "" : "Não foi possível conectar ao servidor. Tente novamente.");
    }
}

async function loadRooms() {
    try {
        rooms = await request(API_URL);
        renderRooms();
        renderRoomOptions();
    } catch (error) {
        if (error.status === 401) {
            showAuth("Sua sessão expirou. Entre novamente.");
            return;
        }
        roomsGrid.innerHTML = `<p class="empty">${escapeHtml(error.message)}</p>`;
    }
}

function renderRoomOptions(selectedRoomId = null) {
    const select = document.getElementById("booking-room");
    const selectableRooms = rooms.filter((room) =>
        room.disponibilidade || room.id === Number(selectedRoomId)
    );

    select.innerHTML = selectableRooms.length
        ? selectableRooms.map((room) =>
            `<option value="${room.id}">${escapeHtml(room.nome)}${room.disponibilidade ? "" : " (indisponível)"}</option>`
        ).join("")
        : '<option value="">Nenhuma sala disponível</option>';

    if (selectedRoomId !== null) {
        select.value = String(selectedRoomId);
    }
    select.disabled = selectableRooms.length === 0;
}

async function loadBookings() {
    try {
        bookings = await request("/reservas/minhas");
        renderBookings();
    } catch (error) {
        if (error.status === 401) {
            showAuth("Sua sessão expirou. Entre novamente.");
            return;
        }
        document.getElementById("bookings-list").innerHTML =
            `<p class="empty">${escapeHtml(error.message)}</p>`;
    }
}

function renderBookings() {
    const list = document.getElementById("bookings-list");
    if (bookings.length === 0) {
        list.innerHTML = '<p class="empty">Você ainda não fez reservas.</p>';
        return;
    }

    list.innerHTML = bookings.map((booking) => {
        const [year, month, day] = booking.data.split("-");
        const cancelled = booking.status === "CANCELADA";
        return `
            <article class="booking-card ${cancelled ? "booking-cancelled" : ""}">
                <div class="booking-details">
                    <strong>${escapeHtml(booking.salaNome)}</strong>
                    <span>${day}/${month}/${year} · ${booking.horaInicio}–${booking.horaFim}</span>
                    <span class="booking-status">${cancelled ? "Cancelada" : "Confirmada"}</span>
                </div>
                ${cancelled ? "" : `
                    <div class="booking-card-actions">
                        <button class="card-action" type="button" data-booking-action="edit"
                                data-id="${booking.id}">Editar</button>
                        <button class="card-action delete" type="button" data-booking-action="cancel"
                                data-id="${booking.id}">Cancelar reserva</button>
                    </div>
                `}
            </article>
        `;
    }).join("");
}

function resetBookingForm() {
    bookingForm.reset();
    document.getElementById("booking-id").value = "";
    document.getElementById("booking-message").textContent = "";
    document.getElementById("booking-title").textContent = "Reservar um espaço";
    document.getElementById("cancel-edit-button").classList.add("hidden");
    document.querySelector("#booking-form button[type='submit']").textContent = "Confirmar reserva";
    renderRoomOptions();
    setMinimumBookingDate();
}

function setMinimumBookingDate() {
    const now = new Date();
    const localDate = new Date(now.getTime() - now.getTimezoneOffset() * 60000)
        .toISOString()
        .slice(0, 10);
    const dateInput = document.getElementById("booking-date");
    dateInput.min = localDate;
    if (!dateInput.value) {
        dateInput.value = localDate;
    }
}

function editBooking(booking) {
    document.getElementById("booking-id").value = booking.id;
    document.getElementById("booking-room").value = String(booking.salaId);
    document.getElementById("booking-date").value = booking.data;
    document.getElementById("booking-start").value = booking.horaInicio;
    document.getElementById("booking-end").value = booking.horaFim;
    document.getElementById("booking-title").textContent = "Editar reserva";
    document.getElementById("booking-message").textContent = "";
    document.getElementById("cancel-edit-button").classList.remove("hidden");
    document.querySelector("#booking-form button[type='submit']").textContent = "Salvar alterações";
}

function renderRooms() {
    const search = searchInput.value.trim().toLowerCase();
    const filter = availabilityFilter.value;

    const visibleRooms = rooms.filter((room) => {
        const matchesSearch = room.nome.toLowerCase().includes(search);
        const matchesFilter =
            filter === "all" ||
            (filter === "available" && room.disponibilidade) ||
            (filter === "unavailable" && !room.disponibilidade);

        return matchesSearch && matchesFilter;
    });

    document.getElementById("total-count").textContent = rooms.length;
    document.getElementById("available-count").textContent =
        rooms.filter((room) => room.disponibilidade).length;

    if (visibleRooms.length === 0) {
        roomsGrid.innerHTML = '<p class="empty">Nenhuma sala encontrada.</p>';
        return;
    }

    roomsGrid.innerHTML = visibleRooms.map((room) => `
        <article class="room-card">
            <h2>${escapeHtml(room.nome)}</h2>
            <p class="room-meta">Capacidade: ${room.capacidade} pessoas</p>
            <span class="status ${room.disponibilidade ? "available" : "unavailable"}">
                ${room.disponibilidade ? "Disponível" : "Indisponível"}
            </span>
            <div class="card-actions">
                <button class="card-action" type="button" data-action="edit" data-id="${room.id}">Editar</button>
                <button class="card-action delete" type="button" data-action="delete" data-id="${room.id}">Excluir</button>
            </div>
        </article>
    `).join("");
}

function openRoomDialog(room = null) {
    roomForm.reset();
    formMessage.textContent = "";
    document.getElementById("room-id").value = room ? room.id : "";
    document.getElementById("room-name").value = room ? room.nome : "";
    document.getElementById("room-capacity").value = room ? room.capacidade : "";
    document.getElementById("room-availability").checked = room ? room.disponibilidade : true;
    dialogTitle.textContent = room ? "Editar sala" : "Nova sala";
    roomDialog.showModal();
    document.getElementById("room-name").focus();
}

function closeRoomDialog() {
    roomDialog.close();
    formMessage.textContent = "";
}

loginForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const message = document.getElementById("login-message");
    message.textContent = "";

    try {
        const user = await request(`${AUTH_URL}/login`, {
            method: "POST",
            body: JSON.stringify({
                email: document.getElementById("login-email").value,
                senha: document.getElementById("login-password").value
            })
        });
        showRooms(user);
    } catch (error) {
        message.textContent = error.message;
    }
});

signupForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const message = document.getElementById("signup-message");
    message.textContent = "";

    try {
        const user = await request(`${AUTH_URL}/cadastro`, {
            method: "POST",
            body: JSON.stringify({
                nome: document.getElementById("signup-name").value,
                email: document.getElementById("signup-email").value,
                senha: document.getElementById("signup-password").value
            })
        });
        showRooms(user);
    } catch (error) {
        message.textContent = error.message;
    }
});

roomForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    formMessage.textContent = "";

    const id = document.getElementById("room-id").value;
    const payload = {
        nome: document.getElementById("room-name").value.trim(),
        capacidade: Number(document.getElementById("room-capacity").value),
        disponibilidade: document.getElementById("room-availability").checked
    };

    try {
        await request(id ? `${API_URL}/${id}` : API_URL, {
            method: id ? "PUT" : "POST",
            body: JSON.stringify(payload)
        });
        closeRoomDialog();
        await loadRooms();
    } catch (error) {
        formMessage.textContent = error.message;
    }
});

roomsGrid.addEventListener("click", async (event) => {
    const button = event.target.closest("[data-action]");
    if (!button) {
        return;
    }

    const id = Number(button.dataset.id);
    const room = rooms.find((item) => item.id === id);

    if (button.dataset.action === "edit") {
        openRoomDialog(room);
    } else if (button.dataset.action === "delete"
            && window.confirm(`Excluir a sala "${room.nome}"?`)) {
        try {
            await request(`${API_URL}/${id}`, { method: "DELETE" });
            await loadRooms();
        } catch (error) {
            window.alert(error.message);
        }
    }
});

function escapeHtml(value) {
    const element = document.createElement("div");
    element.textContent = value;
    return element.innerHTML;
}

document.getElementById("auth-switch-button").addEventListener("click", () => {
    setAuthMode(!isSignupMode);
});
bookingForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const message = document.getElementById("booking-message");
    message.textContent = "";

    const id = document.getElementById("booking-id").value;
    const payload = {
        salaId: Number(document.getElementById("booking-room").value),
        data: document.getElementById("booking-date").value,
        horaInicio: document.getElementById("booking-start").value,
        horaFim: document.getElementById("booking-end").value
    };

    try {
        await request(id ? `/reservas/${id}` : "/reservas", {
            method: id ? "PUT" : "POST",
            body: JSON.stringify(payload)
        });
        resetBookingForm();
        await loadBookings();
    } catch (error) {
        message.textContent = error.message;
    }
});
document.getElementById("cancel-edit-button").addEventListener("click", resetBookingForm);
document.getElementById("bookings-list").addEventListener("click", async (event) => {
    const button = event.target.closest("[data-booking-action]");
    if (!button) {
        return;
    }

    const id = Number(button.dataset.id);
    const booking = bookings.find((item) => item.id === id);
    if (button.dataset.bookingAction === "edit") {
        renderRoomOptions(booking.salaId);
        editBooking(booking);
        return;
    }

    if (!window.confirm(`Cancelar a reserva da sala "${booking.salaNome}"?`)) {
        return;
    }

    try {
        await request(`/reservas/${id}`, { method: "DELETE" });
        if (document.getElementById("booking-id").value === String(id)) {
            resetBookingForm();
        }
        await loadBookings();
    } catch (error) {
        window.alert(error.message);
    }
});
document.getElementById("new-room-button").addEventListener("click", () => openRoomDialog());
document.getElementById("close-dialog").addEventListener("click", closeRoomDialog);
document.getElementById("cancel-dialog").addEventListener("click", closeRoomDialog);
document.getElementById("logout-button").addEventListener("click", async () => {
    try {
        await request(`${AUTH_URL}/logout`, { method: "POST" });
        showAuth("Você saiu da sua conta.");
    } catch (error) {
        window.alert(error.message);
    }
});
searchInput.addEventListener("input", renderRooms);
availabilityFilter.addEventListener("change", renderRooms);

setMinimumBookingDate();
loadCurrentUser();
