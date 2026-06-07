# TaskManagerApp

A modular, clean-coded Java desktop application built with the Maven build system and Swing GUI framework. This project demonstrates software engineering best practices including **Event Handling (Forms & Confirmation Dialogs)**, **Custom Exception Handling**, **Input Validation**, **Code Refactoring**, and **Automated Unit Testing** via JUnit 5.

---

## 🛠️ Core Engineering Concepts Implemented

* **Event Handling:** Decoupled Swing `ActionEvent` triggers managed via lambda expressions for adding tasks, modal transitions, and interactive user validation checks (`JOptionPane.showConfirmDialog`).
* **Exception Handling:** Robust custom exception frameworks (`InvalidTaskException`, `DuplicateTaskException`) intercept empty inputs or naming collisions, reporting them back gracefully via visual dialog alerts without halting application runtime execution.
* **Code Refactoring:** Strict Separation of Concerns (SoC) separating graphical assets (`ui`) from structural domain records (`model`). Leveraged proper naming conventions, encapsulation, and deep data copying techniques.
* **Unit Testing:** Pristine automated boundary testing with a JUnit 5 test suite tracking validation rules, whitespace sanitization, and safe removal indices.

---

## 🏗️ Maven Project Architecture

The project conforms perfectly to the strict **Standard Maven Directory Layout**:

```text
my-task-manager/
├── pom.xml                      # Maven project configuration & dependency management
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── taskmanager/
    │               ├── exception/
    │               │   ├── DuplicateTaskException.java  # Thrown on identical titles
    │               │   └── InvalidTaskException.java    # Thrown on blank titles
    │               ├── model/
    │               │   ├── Priority.java                # Enum for task urgency [LOW, MEDIUM, HIGH]
    │               │   ├── Task.java                    # Clean task blueprint with timestamp
    │               │   └── TaskManager.java             # Core validation data manager
    │               └── ui/
    │                   ├── TaskApp.java                 # Main dashboard layout & removal events
    │                   └── TaskFormDialog.java          # Modal creation form dialog panel
    └── test/
        └── java/
            └── com/
                └── taskmanager/
                    └── TaskManagerTest.java           # Automated JUnit 5 test cases

```

## 🚀 Setup & Execution Instructions

Follow these step-by-step instructions to clone, build, and run the application on your local machine or a university lab computer.

### 1. Clone the Repository
Open your terminal (Command Prompt, PowerShell, or Git Bash) and run the following commands to clone the code and enter the project directory:
```bash
git clone https://github.com/ahmedtahir929/TaskManagerApp.git
cd Java-Task-Manager
```

### 2. Open the Project in IntelliJ IDEA
1. Launch **IntelliJ IDEA**.
2. Select **File -> Open** (or **Open** on the welcome screen).
3. Navigate to your cloned `TaskManagerApp` folder and select it.

---

### 3. Run Automated Unit Tests
You can run the entire test suite directly inside IntelliJ without using terminal commands:
1. In the Project sidebar on the left, navigate to `src/test/java/com/taskmanager/`.
2. Right-click on the `TaskManagerTest` file.
3. Select **Run 'TaskManagerTest'**.
4. A testing window will pop up at the bottom showing a green checkmark next to all **6 passing test cases**.

---

### 4. Launch the Graphical Application (GUI)
Running the visual interface dashboard is just as simple:
1. In the Project sidebar, expand `src/main/java/com/taskmanager/ui/`.
2. Double-click to open `TaskApp.java`.
3. Right-click anywhere inside the code editor area.
4. Select **Run 'TaskApp.main()'**.

The **Task Queue Dashboard** user interface window will instantly pop up on your screen, fully operational and ready to use!

## Outputs
<img width="600" height="688" alt="image" src="https://github.com/user-attachments/assets/40bb70cf-64a4-4c82-bb61-31cc65f5c0b9" />
<img width="603" height="691" alt="image" src="https://github.com/user-attachments/assets/0266d321-8916-43ac-8332-9c5f8d31ede7" />
<img width="606" height="686" alt="image" src="https://github.com/user-attachments/assets/e4f5d882-0655-44ab-9889-30a650a292b6" />
<img width="603" height="691" alt="image" src="https://github.com/user-attachments/assets/e9bfbcfa-f07c-4ab9-9af3-31ec2c53aa74" />
<img width="940" height="583" alt="image" src="https://github.com/user-attachments/assets/faadb616-88f0-4de8-aa95-bdb14c3d42ba" />





