"use client";

import { useState } from "react";
import { transmitMessage } from "./lib/api";

export default function Home() {
  const [message, setMessage] = useState("");
  const [errorProbability, setErrorProbability] = useState(0.2);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [visibleSteps, setVisibleSteps] = useState([]);
  

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
        { label: "hamming", value: data["hamming (sent)"] },
        { label: "received", value: data["received (corrupted)"] },
        { label: "corrected", value: data["corrected binary"] },
      ];

      steps.forEach((step, i) => {
        setTimeout(() => {
          setVisibleSteps((prev) => [...prev, step]);

        
          setTimeout(() => setTyping(false), 300);
        }, i * 600);
      });

      setTimeout(() => setLoading(false), steps.length * 600);

    } catch (err) {
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
        <div key={index} style={styles.output}>
          <b>{step.label}</b>
          <pre>{step.value}</pre>
        </div>
      ))}

      
      {result && !loading && (
        <pre style={styles.output}>
          {JSON.stringify(result, null, 2)}
        </pre>
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