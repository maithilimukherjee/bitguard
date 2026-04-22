# BitGuard

A full-stack digital transmission simulator that models how data is transmitted through a noisy communication channel using classical error detection and correction techniques.

The project demonstrates how data integrity is preserved using layered encoding, controlled noise injection, and receiver-side recovery mechanisms.

---

## Tech Stack

### Backend
- Java
- Spring Boot

### Frontend
- React (Next.js)

### Core Concepts
- Computer Networks
- Error Detection and Correction
- Data Encoding Techniques

---

## Core Concepts Implemented

- XOR-based encryption (basic obfuscation layer)
- Binary encoding of text data
- Hamming code for error correction
- CRC for error detection
- Noisy channel simulation
- Receiver-side decoding and correction pipeline

---

## Features

- End-to-end message transmission simulation
- Step-by-step visualization of data transformation:
  - Original → Encrypted → Binary → Hamming Encoded → Transmitted → Received → Corrected
- Configurable error probability for noise simulation
- CRC validation for detecting corrupted transmissions
- Real-time frontend visualization of transmission flow

---

## System Architecture

Frontend (Next.js)
    ↓
Backend API (Spring Boot /api/transmit)
    ↓
Transmission Pipeline:
    Encryption → Binary Conversion → Hamming Encoding → CRC Generation → Noise Injection → Decoding → Error Correction
    ↓
Response returned to frontend for visualization

---

## Project Status

- Backend: Completed (core transmission pipeline implemented)
- Frontend: Functional with step-by-step visualization
- UI/UX: Iterative improvements ongoing

---

## Learning Outcomes

This project demonstrates:

- Practical implementation of error detection and correction techniques
- Data transmission behavior in noisy channels
- Role of redundancy in reliable communication systems
- End-to-end system design combining backend processing and frontend visualization

---

## Future Enhancements

- Bit-level animation of transmission
- Packet loss visualization
- Network graph-based simulation view
- Interactive noise control and corruption simulation

---

## Note

This project is an educational simulation of classical computer network error control systems and is not intended for production-grade encryption or security use.