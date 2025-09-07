# SecureSoftware_Assignment3

Objective
To protect the Application Layer (Layer 7) of the OSI model by:
- Input validation to prevent injection attacks.
- Encryption of sensitive data.
- Secure session handling.

---

Part 1: Java Secure Login System

SecureAppLayerFull.java

Main Security Measures
Input Filtering – Cleans user inputs to reduce the risk of attacks.
AES Encryption – Keeps passwords safe by encrypting them before storage or verification.
PreparedStatement – Safeguards queries against SQL injection.
Session Handling – Mimics a secure session message after login success.
Note: Database username and password should come from environment variables (DB_USER, DB_PASS) instead of being hardcoded.

---

Part 2: One-Time Pad in C++

OneTimePad.cpp

Core Highlights
Key Generation – Creates a random key the same length as the message.
XOR Operation – Encrypts and decrypts text with perfect secrecy if the key is not reused.
Practical Example – Displays the original message, encrypted output (raw bytes), and the decrypted result.

---

