# ⏰ Time Management Desktop Application

A Java Swing-based desktop application to help students manage time efficiently through goal setting, GPA tracking, study tools, weekly analytics, and smart deadline notifications.

Developed as part of **STAT 295 Project**.

---

## 🚀 Features

- 🎯 **Goal Tracker**  
  Create goals with type, deadline, and priority. Track completion status and progress.

- 📚 **GPA Calculator**  
  Add courses and grades to automatically compute your GPA.

- 🛠 **Study Tools Suite**  
  Includes:
  - 📌 Pomodoro Timer (focus sessions with breaks)
  - 📝 Custom Timer (10-90 minutes sessions options)
  - 🧘 Deep Work (60 minutes session)

- 📊 **Weekly Overview**  
  - View study duration for each day of the week via bar charts  
  - Color-coded:
    - Red: < 1 hour  
    - Orange: 1–3 hours  
    - Green: > 3 hours

- 👥 **Group Project Panel**  
  Collaborate on shared academic projects and view progress.

- 🔔 **Priority-Based Notifications**  
  Reminders are triggered based on priority and time remaining:
  | Priority | Reminder Schedule |
  |----------|--------------------|
  | High     | Daily              |
  | Medium   | 7, 3, and 1 day before |
  | Low      | 1 day before only  |

- 📈 **Analytics Panel**  
  Overview of goals, completions, and time distribution of the day.

- 🌙 **Dark/Light Mode Support**  
  *(Coming soon – planned with FlatLaf integration)*

---

## 💻 Technologies Used

- **Java 17+**
- **Java Swing**
- **JFreeChart** – Chart generation
- **JSON** – Local data storage
- **FlatLaf (Planned)** – For modern UI themes
- **IntelliJ IDEA** – Recommended IDE

---

## 🛠️ Setup Instructions

1. **Download or clone the project**
   ```bash
   git clone https://github.com/rabiademircan/SmartTime
   ```

2. **[Optional] Add FlatLaf Library for Dark/Light Mode**  
   *(Planned feature - not yet enabled)*

   - Download JAR: [https://www.formdev.com/flatlaf/](https://www.formdev.com/flatlaf/)
   - Place it inside a `lib/` folder in the project
   - Right-click → Add as Library in IntelliJ

3. **Run the application**
   - Open `AppGUI.java`
   - Click ▶ Run

---

## 🧪 How to Use

1. Login or register as a user.
2. Add academic goals (e.g. exams, projects) with deadlines and priority levels.
3. Track GPA progress via the GPA panel also CGPA can calculate by this panel.
4. Use the study tools tab for Pomodoro and task planning.
5. View weekly productivity charts in the Overview tab.
6. Check Notifications panel for upcoming due dates.
7. Work with teammates in the Group Project section.

---

## 📬 Contact

> For suggestions or collaboration:

- **Rabia Demircan**  
  📧 demircan.rabia@metu.edu.tr
  github link : https://github.com/rabiademircan

---

## 📄 License

This project is part of a course project and distributed for educational purposes only.
