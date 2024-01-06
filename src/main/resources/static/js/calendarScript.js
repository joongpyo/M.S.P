let currentDate = new Date();
let currentMonth = currentDate.getMonth();
let currentYear = currentDate.getFullYear();

function loadCalendar() {
    const calendarContainer = document.getElementById('days');
    calendarContainer.innerHTML = '';

    const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];

    // Days in month
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay();

    for (let i = 0; i < daysInMonth + firstDayOfMonth; i++) {
        const day = document.createElement('div');
        day.classList.add('calendar-day');

        const dayNumber = i - firstDayOfMonth + 1;

        if (i >= firstDayOfMonth && dayNumber <= daysInMonth) {
            day.textContent = dayNumber;

            // Highlight current day
            if (
                currentYear === currentDate.getFullYear() &&
                currentMonth === currentDate.getMonth() &&
                dayNumber === currentDate.getDate()
            ) {
                day.classList.add('current-month');
            }

            // Highlight Sundays and Saturdays
            if (i % 7 === 0) {
                day.classList.add('red'); // Sunday
            } else if (i % 7 === 6) {
                day.classList.add('blue'); // Saturday
            }

            day.addEventListener('click', () => {
                alert(`Clicked on ${currentYear}-${currentMonth + 1}-${dayNumber}`);
            });
        }

        calendarContainer.appendChild(day);
    }
}

// Previous month button
const prevMonthBtn = document.getElementById('prevMonthBtn');
prevMonthBtn.addEventListener('click', () => {
    currentMonth--;
    if (currentMonth < 0) {
        currentMonth = 11;
        currentYear--;
    }
    loadCalendar();
});

// Next month button
const nextMonthBtn = document.getElementById('nextMonthBtn');
nextMonthBtn.addEventListener('click', () => {
    currentMonth++;
    if (currentMonth > 11) {
        currentMonth = 0;
        currentYear++;
    }
    loadCalendar();
});

// 최초 로드 시 실행
loadCalendar();
