"use client";

import { useState } from "react";
import { transmitMessage } from "./lib/api";

export default function Home() {
  const [message, setMessage] = useState("");
  const [errorProbability, setErrorProbability] = useState(0.2);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [visibleSteps, setVisibleSteps] = useState([]);
  const [typing, setTyping] = useState(false);
  

  const handleTransmit = async () => {
    setLoading(true);
    setResult(null);
    setVisibleSteps([]);


    try {
      const data = await transmitMessage(message, errorProbability);
      setResult(data);

      const steps = [
  { label: "original", value: data.original },
  { label: "encrypted", value: data.encrypted },
  { label: "binary", value: data.binary },
  { label: "hamming", value: data.hamming },
  { label: "crc", value: data.crc },
  { label: "received", value: data.received },
  { label: "crc valid", value: String(data.crcValid) },
  { label: "corrected", value: data.correctedBinary },
];

for (let i = 0; i < steps.length; i++) {
  setTimeout(() => {
    setVisibleSteps((prev) => {
      const updated = [...prev];
      updated.push(steps[i]);
      return updated;
    });
  }, i * 700);
}

      setTimeout(() => setLoading(false), steps.length * 600);

    } 
    catch (err) {
      setResult({ error: "transmission failed" });
      setLoading(false);
    }
  };

  return (
    <div style={styles.page}>
      <h1 style={styles.title}>bitguard transmission simulator</h1>

      <textarea
        placeholder="enter message..."
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        style={styles.textarea}
      />

      <div style={styles.sliderBox}>
        <label>
          error probability: <b>{errorProbability}</b>
        </label>

        <input
          type="range"
          min="0"
          max="1"
          step="0.1"
          value={errorProbability}
          onChange={(e) =>
            setErrorProbability(parseFloat(e.target.value))
          }
          style={styles.slider}
        />
      </div>

      <button onClick={handleTransmit} style={styles.button}>
        transmit
      </button>

      {loading && (
        <p style={styles.loading}>
          transmitting through noisy channel...
        </p>
      )}

      {visibleSteps.map((step, index) => (
  <div key={index} style={styles.stepBox}>
    <div style={styles.label}>{step.label}</div>
    <div style={styles.value}>{step.value}</div>
  </div>
))}

      
      {result && !loading && visibleSteps.length === 0 && (
  <pre>{JSON.stringify(result, null, 2)}</pre>
)}
    </div>
  );
}

const styles = {
  page: {
    background: "#111",
    display: "flex",
    flexDirection: "column",
    padding: "30px",
    fontFamily: "century gothic, sans-serif",
    maxWidth: "800px",
    margin: "auto",
  },

  title: {
    marginBottom: "20px",
    textAlign: "center",
    fontWeight: "bold",
    fontSize: "24px",
  },

  stepBox: {
  marginTop: "15px",
  padding: "12px",
  borderRadius: "8px",
  background: "#111",
  border: "1px solid #333",
  display: "flex",
  flexDirection: "column",
  gap: "6px"
},

label: {
  fontWeight: "bold",
  color: "#00ff88",
  textTransform: "uppercase",
  fontSize: "12px",
  letterSpacing: "1px"
},

value: {
  whiteSpace: "pre-wrap",
  wordBreak: "break-word",
  color: "#0f0",
  fontSize: "13px"
},

  textarea: {
    width: "100%",
    height: "120px",
    padding: "10px",
    fontSize: "14px",
    borderRadius: "8px",
    border: "1px solid #333",
    background: "#222",
    color: "#0f0",
  },

  sliderBox: {
    marginTop: "20px",
    marginBottom: "20px",
  },

  slider: {
    width: "100%",
    height: "5px",
    background: "#222",
    outline: "none",
    borderRadius: "5px",
    cursor: "pointer",
    accentColor: "#00ff88",
  },

  button: {
    padding: "10px 20px",
    cursor: "pointer",
    background: "black",
    color: "white",
    border: "none",
    borderRadius: "10px",
  },

  loading: {
    marginTop: "15px",
    color: "gray",
  },

  output: {
    marginTop: "20px",
    background: "#111",
    color: "#0f0",
    padding: "15px",
    borderRadius: "8px",
  },
};