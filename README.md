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
## Outputs
<img width="600" height="688" alt="image" src="https://github.com/user-attachments/assets/40bb70cf-64a4-4c82-bb61-31cc65f5c0b9" />
<img width="603" height="691" alt="image" src="https://github.com/user-attachments/assets/0266d321-8916-43ac-8332-9c5f8d31ede7" />
<img width="606" height="686" alt="image" src="https://github.com/user-attachments/assets/e4f5d882-0655-44ab-9889-30a650a292b6" />
<img width="603" height="691" alt="image" src="https://github.com/user-attachments/assets/e9bfbcfa-f07c-4ab9-9af3-31ec2c53aa74" />
![Uploading image.png…]()




